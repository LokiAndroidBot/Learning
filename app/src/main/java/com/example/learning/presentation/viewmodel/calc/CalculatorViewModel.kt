package com.example.learning.presentation.viewmodel.calc

import androidx.lifecycle.ViewModel
import com.example.learning.presentation.intent.calc.CalculatorIntent
import com.example.learning.presentation.state.calc.CalculatorState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class CalculatorViewModel : ViewModel() {
    private val _state = MutableStateFlow(CalculatorState())
    val state: StateFlow<CalculatorState> = _state

    private var currentInput = ""
    private var lastOperator: String? = null
    private var operand: String? = null

    fun onIntent(intent: CalculatorIntent) {
        when (intent) {
            is CalculatorIntent.EnterNumber -> {
                currentInput += intent.number
                _state.update { it.copy(input = currentInput) }
            }

            is CalculatorIntent.EnterOperator -> {
                operand = currentInput
                lastOperator = intent.operator
                currentInput = ""
                _state.update { it.copy(input = "$operand ${intent.operator}") }
            }

            CalculatorIntent.Clear -> {
                currentInput = ""
                operand = null
                lastOperator = null
                _state.update { CalculatorState() }
            }

            CalculatorIntent.Calculate -> {
                if (operand != null && lastOperator != null && currentInput.isNotEmpty()) {
                    val result =
                        calculate(operand!!.toDouble(), lastOperator!!, currentInput.toDouble())
                    _state.update { it.copy(result = result.toString(), input = result.toString()) }
                    currentInput = result.toString()
                    operand = null
                    lastOperator = null
                }
            }
        }
    }

    private fun calculate(operand1: Double, operator: String, operand2: Double): Double {
        return when (operator) {
            "+" -> operand1 + operand2
            "-" -> operand1 - operand2
            "*" -> operand1 * operand2
            "/" -> if (operand2 == 0.0) 0.0 else operand1 / operand2 // Handle division by zero
            else -> 0.0
        }
    }
}
