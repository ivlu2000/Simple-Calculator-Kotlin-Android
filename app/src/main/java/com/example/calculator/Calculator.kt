package com.example.calculator

class Calculator {
    fun calculate(term: String): Double {
        val splitList = splitTerm(term)
        val listWithMultiplyAndDivide = splitList.filter { it.contains(Regex("[*/]")) }
        val matrixWithMultiplyAndDivide = listWithMultiplyAndDivide.map {
            it.split(Regex("(?<=[*/])|(?=[*/])"))
        }
        val resultListFromMultiplyAndDivideTerms =
            matrixWithMultiplyAndDivide.map { sequentialCalculation(it).toString() }
        var counter = 0
        val finalListTerm = splitList.map {
            if (it.contains(Regex("[*/]"))) {
                resultListFromMultiplyAndDivideTerms[counter++]
            } else
                it
        }

        return sequentialCalculation(finalListTerm)
    }

    private fun splitTerm(term: String): List<String> {
        val regex = Regex("(?<=[-+])|(?=[-+])")
        val splitTerm = term.split(regex).toMutableList()
        splitTerm.remove("")
        if (splitTerm[0] == "-") {
            splitTerm[0] = splitTerm[0].plus(splitTerm[1])
            splitTerm.removeAt(1)
        }
        println(splitTerm)
        return splitTerm
    }



    private fun sequentialCalculation(termList: List<String>): Double {
        var result = termList[0].toDouble()
        for (i in IntRange(0, termList.size - 2)) {
            when (termList[i]) {
                "+" -> result += termList[i + 1].toDouble()
                "-" -> result -= termList[i + 1].toDouble()
                "*" -> result *= termList[i + 1].toDouble()
                "/" -> result /= termList[i + 1].toDouble()
            }
        }
        return result
    }
}