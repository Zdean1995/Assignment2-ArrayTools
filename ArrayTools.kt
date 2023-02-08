fun main() {
    var continueBoolean = true

    println("Array Tools")
    while(continueBoolean) {
        println("Select which tool you want to use:")
        println("1. Caesar Cipher")
        println("2. Array Average")
        println("3. Array Contains")
        println("4. Array Reverse")

        when (getUserInput("int") as Int) {
            1 -> {
                println("Please enter a string:")
                val uncodedString = getUserInput("string") as String
                println("please enter a shift integer for the cipher:")
                val shiftValue = getUserInput("int") as Int
                val codedString = ceaserCipher(uncodedString, shiftValue)

                println("$uncodedString with a shift value of $shiftValue has been encoded as $codedString")
            }
            2 -> {
                println("Please any number of integers that you want to be averaged:")
                val intArray = getIntArray()
                val average = arrayAvg(intArray)
                println("the average of your integers is $average")
            }
            3 -> {
                println("Please enter any number of integers that you want to search:")
                val intArray = getIntArray()
                println("Please enter the integer that you want to search for:")
                val searchInt = getUserInput("int") as Int
                val doesContain = arrayContains(intArray, searchInt)
                if(doesContain) {
                    println("The array does contain $searchInt")
                }
                else {
                    println("The array does not contain $searchInt")
                }
            }
        }
    }
}

fun getIntArray(): IntArray{
    var intArray = intArrayOf()
    while (true){
        intArray += getUserInput("int") as Int
        println("continue? (y/n)")
        val continueBoolean = getUserInput("continue") as Boolean
        if(!continueBoolean) {
            return intArray
        }
    }
}

fun ceaserCipher(uncodedString: String, shiftValue: Int): String {
    var codedString = ""
    for(char in uncodedString) {
        val charInt = char.code
        if(charInt in 48..57) {
            codedString += (((charInt - 48 + shiftValue) % 10) + 48).toChar()
        }
        else if (charInt in 65..90){
            codedString += (((charInt - 65 + shiftValue) % 26) + 65).toChar()
        }
        else if (charInt in 97..122) {
            codedString += (((charInt - 97 + shiftValue) % 26) + 97).toChar()
        }
        else {
            codedString += charInt.toChar()
        }
    }
    return codedString
}

fun arrayAvg(numericArray: IntArray): Int {
    var arrayAverage = 0
    for (i in numericArray){
        arrayAverage += i
    }
    arrayAverage /= numericArray.size
    return arrayAverage
}

fun arrayContains(intArray: IntArray, searchInt: Int): Boolean{
    for(i in intArray) {
        if(i == searchInt){
            return true
        }
    }
    return false
}

fun reverse(intArray: IntArray): IntArray{
    var reversedArray: IntArray = intArrayOf()
    for(i in intArray.indices) {
        reversedArray += intArray[(intArray.size - 1) - i]
    }

    return reversedArray
}

fun getUserInput(outputType: String): Any{
    var userInput = readln()
    while (true) {
        when (outputType) {
            "int" -> {
                try {
                    return userInput.toInt()
                } catch (exception: NumberFormatException) {
                    println("please enter an integer")
                }
            }
            "string" -> {
                return userInput
            }
            "continue" -> {
                if(userInput == "y" || userInput == "Y" ) {
                    return true
                } else if (userInput == "n" || userInput == "N") {
                    return false
                } else {
                    println("please enter y or n")
                }
            }
        }
    }
}