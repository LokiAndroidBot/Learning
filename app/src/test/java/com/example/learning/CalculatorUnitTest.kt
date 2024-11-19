package com.example.learning

import com.example.learning.presentation.intent.calc.CalculatorIntent
import com.example.learning.presentation.viewmodel.calc.CalculatorViewModel
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class CalculatorUnitTest {

    private lateinit var viewModel: CalculatorViewModel

    @Before
    fun setUp() {
        viewModel = CalculatorViewModel()  // Initialize the ViewModel
    }

    @Test
    fun `test addition`() = runTest {
        // Given: Input 2 + 3
        viewModel.onIntent(CalculatorIntent.EnterNumber("2"))
        viewModel.onIntent(CalculatorIntent.EnterOperator("+"))
        viewModel.onIntent(CalculatorIntent.EnterNumber("3"))

        // When: "=" is pressed
        viewModel.onIntent(CalculatorIntent.Calculate)

        // Then: Result should be 5.0
        val state = viewModel.state.first()
        assertEquals("Expected 2 + 3 to be 5.0", "5.0", state.result)
    }

    @Test
    fun `test subtraction`() = runTest {
        // Given: Input 5 - 3
        viewModel.onIntent(CalculatorIntent.EnterNumber("5"))
        viewModel.onIntent(CalculatorIntent.EnterOperator("-"))
        viewModel.onIntent(CalculatorIntent.EnterNumber("3"))

        // When: "=" is pressed
        viewModel.onIntent(CalculatorIntent.Calculate)

        // Then: Result should be 2.0
        val state = viewModel.state.first()
        assertEquals("Expected 5 - 3 to be 2.0", "2.0", state.result)
    }

    @Test
    fun `test multiplication`() = runTest {
        // Given: Input 4 * 2
        viewModel.onIntent(CalculatorIntent.EnterNumber("4"))
        viewModel.onIntent(CalculatorIntent.EnterOperator("*"))
        viewModel.onIntent(CalculatorIntent.EnterNumber("2"))

        // When: "=" is pressed
        viewModel.onIntent(CalculatorIntent.Calculate)

        // Then: Result should be 8.0
        val state = viewModel.state.first()
        assertEquals("Expected 4 * 2 to be 8.0", "8.0", state.result)
    }

    @Test
    fun `test division`() = runTest {
        // Given: Input 6 / 3
        viewModel.onIntent(CalculatorIntent.EnterNumber("6"))
        viewModel.onIntent(CalculatorIntent.EnterOperator("/"))
        viewModel.onIntent(CalculatorIntent.EnterNumber("3"))

        // When: "=" is pressed
        viewModel.onIntent(CalculatorIntent.Calculate)

        // Then: Result should be 2.0
        val state = viewModel.state.first()
        assertEquals("Expected 6 / 3 to be 2.0", "2.0", state.result)
    }

    @Test
    fun `test division by zero`() = runTest {
        // Given: Input 8 / 0
        viewModel.onIntent(CalculatorIntent.EnterNumber("8"))
        viewModel.onIntent(CalculatorIntent.EnterOperator("/"))
        viewModel.onIntent(CalculatorIntent.EnterNumber("0"))

        // When: "=" is pressed
        viewModel.onIntent(CalculatorIntent.Calculate)

        // Then: Result should be 0.0 (division by zero handled)
        val state = viewModel.state.first()
        assertEquals("Expected division by zero to return 0.0", "0.0", state.result)
    }

    @Test
    fun `test invalid operator`() = runTest {
        // Given: Input 5 % 3 (invalid operator)
        viewModel.onIntent(CalculatorIntent.EnterNumber("5"))
        viewModel.onIntent(CalculatorIntent.EnterOperator("%"))
        viewModel.onIntent(CalculatorIntent.EnterNumber("3"))

        // When: "=" is pressed
        viewModel.onIntent(CalculatorIntent.Calculate)

        // Then: Result should be 0.0 (invalid operator fallback)
        val state = viewModel.state.first()
        assertEquals("Expected invalid operator % to return 0.0", "0.0", state.result)
    }

    /*@Test
    fun `test multiple operations`() = runTest {
        // Given: Input 2 + 3 * 4
        viewModel.onIntent(CalculatorIntent.EnterNumber("2"))
        viewModel.onIntent(CalculatorIntent.EnterOperator("+"))
        viewModel.onIntent(CalculatorIntent.EnterNumber("3"))
        viewModel.onIntent(CalculatorIntent.EnterOperator("*"))
        viewModel.onIntent(CalculatorIntent.EnterNumber("4"))

        // When: "=" is pressed
        viewModel.onIntent(CalculatorIntent.Calculate)

        // Then: Result should be 14.0 (following order of operations: 3 * 4 = 12, 2 + 12 = 14)
        val state = viewModel.state.first()
        assertEquals("Expected 2 + 3 * 4 to be 14.0", "14.0", state.result)
    }*/
}
