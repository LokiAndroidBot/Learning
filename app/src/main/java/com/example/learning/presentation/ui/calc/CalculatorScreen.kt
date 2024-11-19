package com.example.learning.presentation.ui.calc

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.example.learning.presentation.intent.calc.CalculatorIntent
import com.example.learning.presentation.viewmodel.calc.CalculatorViewModel

@Composable
fun CalculatorScreen(
    navController: NavHostController,
    viewModel: CalculatorViewModel = viewModel(),
) {
    val state by viewModel.state.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        // Display Input and Result
        Text(
            text = state.input,
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End,
            fontSize = 24.sp,
        )
        Text(
            text = "Result: ${state.result}",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.End,
            fontSize = 20.sp,
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Number Pad
        Column {
            val buttons = listOf(
                listOf("7", "8", "9", "+"),
                listOf("4", "5", "6", "-"),
                listOf("1", "2", "3", "*"),
                listOf("C", "0", "=", "/"),
            )

            buttons.forEach { row ->
                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    modifier = Modifier.fillMaxWidth(),
                ) {
                    row.forEach { button ->
                        Button(
                            onClick = {
                                when (button) {
                                    "C" -> viewModel.onIntent(CalculatorIntent.Clear)
                                    "=" -> viewModel.onIntent(CalculatorIntent.Calculate)
                                    "+", "-", "*", "/" -> viewModel.onIntent(
                                        CalculatorIntent.EnterOperator(
                                            button,
                                        ),
                                    )

                                    else -> viewModel.onIntent(CalculatorIntent.EnterNumber(button))
                                }
                            },
                            modifier = Modifier.size(64.dp),
                        ) {
                            Text(text = button)
                        }
                    }
                }
            }
        }
    }
}
