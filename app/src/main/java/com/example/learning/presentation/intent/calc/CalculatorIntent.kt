package com.example.learning.presentation.intent.calc

sealed class CalculatorIntent {
    data class EnterNumber(val number: String) : CalculatorIntent()
    object Clear : CalculatorIntent()
    object Calculate : CalculatorIntent()
    data class EnterOperator(val operator: String) : CalculatorIntent()
}
