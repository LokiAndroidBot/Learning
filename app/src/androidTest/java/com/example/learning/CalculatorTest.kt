package com.example.learning

import android.content.Context
import android.content.Intent
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.uiautomator.By
import androidx.test.uiautomator.UiDevice
import androidx.test.uiautomator.Until
import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class CalculatorTest {

    private val device: UiDevice =
        UiDevice.getInstance(InstrumentationRegistry.getInstrumentation())

    @Test
    fun testAddition() {
        // Start the app (replace with the correct package name for your app)
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val packageName = context.packageName
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
        intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)  // Clear any existing tasks
        context.startActivity(intent)

        // Wait for the app to launch
        device.wait(Until.hasObject(By.pkg(packageName).depth(0)), 5000)

        device.findObject(By.text("Calc")).click()

        device.wait(Until.hasObject(By.text("Result:")), 1000)
        // Perform actions on the UI
        device.findObject(By.text("2")).click()  // Click "2"
        device.findObject(By.text("+")).click()  // Click "+"
        device.findObject(By.text("3")).click()  // Click "3"
        device.findObject(By.text("=")).click()  // Click "="

        // Check the result
        val resultText = device.findObject(By.textContains("Result")).text  // Replace with the actual ID of the result TextView
        assertEquals("Result: 5", resultText)
    }

    @Test
    fun testSubtraction() {
        // Start the app (same as above)
        val context: Context = InstrumentationRegistry.getInstrumentation().targetContext
        val packageName = context.packageName
        val intent = context.packageManager.getLaunchIntentForPackage(packageName)
        intent?.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK)
        context.startActivity(intent)

        // Wait for the app to launch
        device.wait(Until.hasObject(By.pkg(packageName).depth(0)), 5000)

        device.findObject(By.text("Calc")).click()

        device.wait(Until.hasObject(By.text("Result:")), 1000)
        // Perform actions for subtraction (e.g., 5 - 3)
        device.findObject(By.text("5")).click()
        device.findObject(By.text("-")).click()
        device.findObject(By.text("3")).click()
        device.findObject(By.text("=")).click()

        // Check the result
        val resultText = device.findObject(By.textContains("Result")).text  // Replace with the actual ID of the result TextView
        assertEquals("Result: 2", resultText)
    }
}
