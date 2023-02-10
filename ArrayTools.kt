/*
Zachary Dean
A00275392
JAV-1001 - 11354

This is a console application that is used to show off a handful of functions.  These functions include a ceaser cipher
encoder, a function used for finding the average of a integer array, a function used to search an integer array for a
certain integer, and a function used for reversing an array of integers.
 */


fun main() {
    //The name of the app is printed so the user knows what they're using
    println("Array Tools")
    //the program loops so the user can use more than one function
    do {
        println("Select which tool you want to use:")
        println("1. Caesar Cipher")
        println("2. Array Average")
        println("3. Array Contains")
        println("4. Array Reverse")

        //this when loop selects which of the above options the user chooses
        when (getUserInput("int") as Int) {
            1 -> {
                //the user inputs a string they want to encode and a shift value then both are passed to the ceaser cipher function
                println("Please enter a string:")
                val uncodedString = getUserInput("string") as String
                println("please enter a shift integer for the cipher:")
                val shiftValue = getUserInput("int") as Int
                val codedString = ceaserCipher(uncodedString, shiftValue)

                println("$uncodedString with a shift value of $shiftValue has been encoded as $codedString")
            }
            2 -> {
                //the user inputs an int array and the function outputs the average
                println("Please any number of integers that you want to be averaged:")
                val intArray = getIntArray()
                val average = arrayAvg(intArray)
                println("the average of ${intArray.contentToString()} is $average")
            }
            3 -> {
                //the user inputs an int array and an int and the function outputs a boolean based on if the array contains that int
                println("Please enter any number of integers that you want to search:")
                val intArray = getIntArray()
                println("Please enter the integer that you want to search for:")
                val searchInt = getUserInput("int") as Int
                val doesContain = arrayContains(intArray, searchInt)
                if(doesContain) {
                    println("${intArray.contentToString()} does contain $searchInt")
                }
                else {
                    println("${intArray.contentToString()} does not contain $searchInt")
                }
            }
            4 -> {
                //the user inputs an int array and the function reverses itr
                println("Please enter any number of integers that you want to reverse:")
                val intArray = getIntArray()
                val reversedIntArray = reverse(intArray)
                println(intArray.contentToString() + " reversed is " + reversedIntArray.contentToString())
            }
            else -> {
                //the user inputs an invalid int
                println("Invalid option")
            }
        }
        //the program asks the user if they go again. If they say no then the program ends.
        println("continue? (y/n)")
    } while(getUserInput("continue") as Boolean)
}

/*
What I decided to do for the ceaser cipher is to have letters and numbers be shifted but everything else (punctuation etc.)
be kept the same.  Casing on letters is kept the same.
 */
fun ceaserCipher(uncodedString: String, shiftValue: Int): String {
    //first the coded string is initialized as an empty string
    var codedString = ""
    for(char in uncodedString) {
        //then the character is converted to its ascii number
        val charInt = char.code
        /*
        the logic for this is that since the int has to be kept in  the range of the numbers or letters, the first number
        is subtracted from the value and the shift value it added.  After that the remainder of the range of the character
        types is found and the first number is added back. That way the number is kept within the range and shifted accurately.
        Figuring this out was the hardest part of the assignment. Once the character has been shifted it is added to the
        coded String
         */
        codedString += when (charInt) {
            //for numbers
            in 48..57 -> {
                (((charInt - 48 + shiftValue) % 10) + 48).toChar()
            }
            //for uppercase letters
            in 65..90 -> {
                (((charInt - 65 + shiftValue) % 26) + 65).toChar()
            }
            //for lowercase letters
            in 97..122 -> {
                (((charInt - 97 + shiftValue) % 26) + 97).toChar()
            }
            //for everything else
            else -> {
                charInt.toChar()
            }
        }
    }
    return codedString
}

//This function finds the average of a integer array.  All the array functions use int.
fun arrayAvg(numericArray: IntArray): Int {
    var arrayAverage = 0
    //all the arrays are added to the output int
    for (i in numericArray) {
        arrayAverage += i
    }

    //then the output int is divided by the size of the array and returned
    return arrayAverage/numericArray.size
}

//this function checks if an int array contains an int provided by the user
fun arrayContains(intArray: IntArray, searchInt: Int): Boolean{
    //a for loop goes through the array while comparing the number to the number at each index.
    //if the number is found then the function stops looping and returns true
    for(i in intArray) {
        if(i == searchInt) {
            return true
        }
    }
    //if the number is not found the function returns false
    return false
}

//this function reverses an int array
fun reverse(intArray: IntArray): IntArray{
    var reversedArray: IntArray = intArrayOf()
    //a for loop goes through the input array. Most interesting techniques in the code I learned from corrections
    //from intellij such as the array.indicies method
    for(i in intArray.indices) {
        //this equation gets each index from the input array from the back and puts it into the output array
        reversedArray += intArray[(intArray.size - 1) - i]
    }
    return reversedArray
}

//This function is used for getting an integer array from the user
fun getIntArray(): IntArray{
    var intArray = intArrayOf()
    //this while loop loops until the user doesn't want to add anymore integers to the array.
    while (true){
        intArray += getUserInput("int") as Int
        println("continue? (y/n)")
        if(!(getUserInput("continue") as Boolean)) {
            return intArray
        }
    }
}

//This function is used for getting user input based on a string passed in.  This function is either very smart or very stupid.
fun getUserInput(outputType: String): Any{
    var userInput = readln()
    while (true) {
        when (outputType) {
            //if the user input type is int then a try catch block is used to ensure that the user enters a int
            "int" -> {
                try {
                    return userInput.toInt()
                } catch (exception: NumberFormatException) {
                    println("please enter an integer")
                    userInput = readln()
                }
            }
            //if the user input type is a string then it just returns whatever the user inputs since it's a string anyway
            "string" -> {
                return userInput
            }
            //if the user input type is continue the program checks for a y or n and returns true or false based on the
            //input.  This represents yes or no.
            "continue" -> {
                when (userInput) {
                    "y", "Y" -> {
                        return true
                    }
                    "n", "N" -> {
                        return false
                    }
                    else -> {
                        println("please enter y or n")
                        userInput = readln()
                    }
                }
            }
        }
    }
}