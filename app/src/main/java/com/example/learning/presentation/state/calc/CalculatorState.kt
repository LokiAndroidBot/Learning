package com.example.learning.presentation.state.calc

data class CalculatorState(
    // Represents the current input displayed on the calculator
    val input: String = "",
    // Represents the result of the calculation
    val result: String = ""
)
