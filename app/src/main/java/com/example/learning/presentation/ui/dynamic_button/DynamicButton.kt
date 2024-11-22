package com.example.learning.presentation.ui.dynamic_button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
@Composable
fun DynamicButton(
    modifier: Modifier = Modifier,
    buttonType: ButtonType = ButtonType.FILLED,
    text: String = "Button",
    icon: ImageVector? = null,
    isLoading: Boolean = false,
    enabled: Boolean = true,
    onClick: () -> Unit
) {
    val buttonModifier = when (buttonType) {
        ButtonType.GRADIENT -> modifier
            .fillMaxWidth()
            .background(
                brush = Brush.horizontalGradient(listOf(Color.Magenta, Color.Blue)),
                shape = RoundedCornerShape(8.dp)
            )

        ButtonType.CIRCLE -> modifier.size(56.dp)
        else -> modifier.fillMaxWidth()
    }

    Button(
        onClick = onClick,
        modifier = buttonModifier,
        shape = when (buttonType) {
            ButtonType.ROUNDED -> RoundedCornerShape(16.dp)
            ButtonType.CIRCLE -> CircleShape
            else -> RectangleShape
        },
        enabled = enabled && !isLoading,
        colors = when (buttonType) {
            ButtonType.FILLED -> ButtonDefaults.buttonColors(containerColor = Color.Blue)
            ButtonType.OUTLINED -> ButtonDefaults.outlinedButtonColors()
            else -> ButtonDefaults.buttonColors()
        },
        border = when (buttonType) {
            ButtonType.OUTLINED -> BorderStroke(2.dp, Color.Blue)
            else -> null
        },
        elevation = when (buttonType) {
            ButtonType.ELEVATED -> ButtonDefaults.buttonElevation(defaultElevation = 8.dp)
            else -> null
        }
    ) {
        when {
            isLoading -> {
                CircularProgressIndicator(
                    color = Color.White,
                    modifier = Modifier.size(16.dp)
                )
            }

            icon != null -> {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = Color.White
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = text, color = Color.White)
            }

            else -> {
                Text(
                    text = text,
                    color = when (buttonType) {
                        ButtonType.FILLED -> Color.White
                        ButtonType.OUTLINED -> Color.Blue
                        else -> Color.Black
                    }
                )
            }
        }
    }
}

enum class ButtonType {
    FILLED, OUTLINED, ELEVATED, ROUNDED, CIRCLE, GRADIENT
}

@Composable
fun ListDynamicButton(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        DynamicButton(buttonType = ButtonType.FILLED, text = "Filled Button", onClick = {})
        DynamicButton(buttonType = ButtonType.OUTLINED, text = "Outlined Button", onClick = {})
        DynamicButton(buttonType = ButtonType.ELEVATED, text = "Elevated Button", onClick = {})
        DynamicButton(buttonType = ButtonType.ROUNDED, text = "Rounded Button", onClick = {})
        DynamicButton(buttonType = ButtonType.CIRCLE,
            text = "Circle Button",
            icon = Icons.Default.Add,
            onClick = {})
        DynamicButton(buttonType = ButtonType.GRADIENT, text = "Gradient Button", onClick = {})
    }
}
@Preview(showBackground = true)
@Composable
fun PreviewAllDynamicButtons() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        DynamicButton(buttonType = ButtonType.FILLED, text = "Filled Button", onClick = {})
        DynamicButton(buttonType = ButtonType.OUTLINED, text = "Outlined Button", onClick = {})
        DynamicButton(buttonType = ButtonType.ELEVATED, text = "Elevated Button", onClick = {})
        DynamicButton(buttonType = ButtonType.ROUNDED, text = "Rounded Button", onClick = {})
        DynamicButton(buttonType = ButtonType.CIRCLE,
            text = "Circle Button",
            icon = Icons.Default.Add,
            onClick = {})
        DynamicButton(buttonType = ButtonType.GRADIENT, text = "Gradient Button", onClick = {})
    }
}

