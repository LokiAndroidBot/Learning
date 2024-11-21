package com.example.learning

import com.example.learning.presentation.intent.calc.CalculatorIntent
import com.example.learning.presentation.viewmodel.calc.CalculatorViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CalculatorViewModelTest {

    private lateinit var viewModel: CalculatorViewModel
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = CalculatorViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `test entering a number updates input`() = runTest {
        // When user enters "5"
        viewModel.onIntent(CalculatorIntent.EnterNumber("5"))

        // Verify the state is updated
        val state = viewModel.state.first()
        assertEquals("5", state.input)
        assertEquals("", state.result)
    }

    @Test
    fun `test entering an operator updates input with operator`() = runTest {
        // Given a number is entered
        viewModel.onIntent(CalculatorIntent.EnterNumber("8"))

        // When an operator "+" is entered
        viewModel.onIntent(CalculatorIntent.EnterOperator("+"))

        // Verify the state is updated
        val state = viewModel.state.first()
        assertEquals("8 +", state.input)
        assertEquals("", state.result)
    }

    @Test
    fun `test calculation updates result`() = runTest {
        // Given a calculation "8 + 5"
        viewModel.onIntent(CalculatorIntent.EnterNumber("8"))
        viewModel.onIntent(CalculatorIntent.EnterOperator("+"))
        viewModel.onIntent(CalculatorIntent.EnterNumber("5"))

        // When "=" is pressed
        viewModel.onIntent(CalculatorIntent.Calculate)

        // Verify the state is updated
        val state = viewModel.state.first()
        assertEquals("13.0", state.result)
        assertEquals("13.0", state.input)
    }

    @Test
    fun `test clear resets state`() = runTest {
        // Given some input
        viewModel.onIntent(CalculatorIntent.EnterNumber("9"))
        viewModel.onIntent(CalculatorIntent.EnterOperator("*"))
        viewModel.onIntent(CalculatorIntent.EnterNumber("3"))

        // When "C" (Clear) is pressed
        viewModel.onIntent(CalculatorIntent.Clear)

        // Verify the state is reset
        val state = viewModel.state.first()
        assertEquals("", state.input)
        assertEquals("", state.result)
    }

    @Test
    fun `test division by zero returns zero`() = runTest {
        // Given a division by zero "8 / 0"
        viewModel.onIntent(CalculatorIntent.EnterNumber("8"))
        viewModel.onIntent(CalculatorIntent.EnterOperator("/"))
        viewModel.onIntent(CalculatorIntent.EnterNumber("0"))

        // When "=" is pressed
        viewModel.onIntent(CalculatorIntent.Calculate)

        // Verify the result is "0.0"
        val state = viewModel.state.first()
        assertEquals("0.0", state.result)
        assertEquals("0.0", state.input)
    }
}
