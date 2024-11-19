package com.example.learning.presentation.state.calc

data class CalculatorState(
    val input: String = "",  // Represents the current input displayed on the calculator
    val result: String = ""  // Represents the result of the calculation
)

