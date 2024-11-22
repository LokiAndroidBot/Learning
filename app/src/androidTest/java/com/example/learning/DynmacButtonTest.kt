package com.example.learning


import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.assertIsNotEnabled
import androidx.compose.ui.test.assertTextContains
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import com.example.learning.presentation.ui.dynamic_button.ButtonType
import com.example.learning.presentation.ui.dynamic_button.DynamicButton
import org.junit.Rule
import org.junit.Test

class DynamicButtonTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @Test
    fun testDynamicButtonAllScenarios() {
        // Track if button click worked
        var clicked = false

        // Composable content with all possibilities
        composeTestRule.setContent {
            Column {
                // FILLED Button
                DynamicButton(buttonType = ButtonType.FILLED,
                    text = "Filled Button",
                    onClick = { clicked = true })
                // OUTLINED Button
                DynamicButton(buttonType = ButtonType.OUTLINED,
                    text = "Outlined Button",
                    onClick = { clicked = true })
                // ELEVATED Button
                DynamicButton(buttonType = ButtonType.ELEVATED,
                    text = "Elevated Button",
                    onClick = { clicked = true })
                // ROUNDED Button
                DynamicButton(buttonType = ButtonType.ROUNDED,
                    text = "Rounded Button",
                    onClick = { clicked = true })
                // CIRCLE Button with Icon
                DynamicButton(buttonType = ButtonType.CIRCLE,
                    icon = Icons.AutoMirrored.Filled.ArrowForward,
                    text = "Circle Button",
                    onClick = { clicked = true })
                // GRADIENT Button
                DynamicButton(buttonType = ButtonType.GRADIENT,
                    text = "Gradient Button",
                    onClick = { clicked = true })
                // Disabled Button
                DynamicButton(
                    enabled = false,
                    text = "Disabled Button",
                    onClick = { clicked = true })
                // Loading Button
                DynamicButton(
                    isLoading = true,
                    text = "Loading Button",
                    onClick = { clicked = true })
                // Button without Text (Empty Text)
                DynamicButton(text = "", onClick = { clicked = true })
            }
        }

        // Validate Filled Button
        composeTestRule.onNodeWithText("Filled Button").assertIsDisplayed()
        composeTestRule.onNodeWithText("Filled Button").assertTextContains("Filled Button")


        // Validate Outlined Button
        composeTestRule.onNodeWithText("Outlined Button").assertIsDisplayed()
        composeTestRule.onNodeWithText("Outlined Button").assertTextContains("Outlined Button")

        // Validate Elevated Button
        composeTestRule.onNodeWithText("Elevated Button").assertIsDisplayed()

        // Validate Rounded Button
        composeTestRule.onNodeWithText("Rounded Button").assertIsDisplayed()
        // You can check the rounded shape visually or verify it with a custom matcher.

        // Validate Circle Button
        composeTestRule.onNodeWithText("Circle Button").assertIsDisplayed()
        composeTestRule.onNodeWithText("Circle Button").assertTextContains("Circle Button")

        // Validate Gradient Button
        composeTestRule.onNodeWithText("Gradient Button").assertIsDisplayed()

        // Validate Disabled Button (Ensure click is not possible)
        composeTestRule.onNodeWithText("Disabled Button").assertIsDisplayed()
        composeTestRule.onNodeWithText("Disabled Button").assertIsNotEnabled()

        // Validate Loading Button (Ensure CircularProgress is displayed)
        //composeTestRule.onNodeWithText("Loading Button").assertIsDisplayed()
        //composeTestRule.onNodeWithTag("loadingIndicator").assertIsDisplayed()

        // Validate Empty Text Button (Ensure no text is shown)
        composeTestRule.onNodeWithText("").assertIsDisplayed()

        // Validate Button Click (Ensure onClick works)
        composeTestRule.onNodeWithText("Filled Button").performClick()
        assert(clicked)

        // Validate Disabled Button does not click
        clicked = false
        composeTestRule.onNodeWithText("Disabled Button").performClick()
        assert(!clicked)
    }
}
