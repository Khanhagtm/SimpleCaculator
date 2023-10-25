package com.example.calculator

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    private lateinit var inputTextView: TextView
    private lateinit var outputTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputTextView = findViewById(R.id.input)
        outputTextView = findViewById(R.id.output)

        // Set click listeners for numeric and operator buttons
        val buttons = arrayOf(
            R.id.button_0, R.id.button_1, R.id.button_2, R.id.button_3, R.id.button_4,
            R.id.button_5, R.id.button_6, R.id.button_7, R.id.button_8, R.id.button_9,
            R.id.button_plus, R.id.button_minus, R.id.multiply, R.id.button_division, R.id.button_comma
        )


        for (buttonId in buttons) {
            val button = findViewById<Button>(buttonId)
            button.setOnClickListener { onButtonClick(button) }
        }

        // Set click listeners for special buttons
        findViewById<Button>(R.id.button_clear).setOnClickListener { clear() }
        findViewById<Button>(R.id.button_ce).setOnClickListener { clearEntry() }
        findViewById<Button>(R.id.button_backspace).setOnClickListener { backspace() }
        findViewById<Button>(R.id.button_equal).setOnClickListener { evaluate() }
    }

    @SuppressLint("SetTextI18n")
    private fun onButtonClick(button: Button) {
        val currentInput = inputTextView.text.toString()
        val buttonText = button.text.toString()

        // Append the button's text to the input text
        inputTextView.text = currentInput + buttonText
    }

    private fun clear() {
        // Clear the entire input text
        inputTextView.text = ""
        outputTextView.text = ""
    }

    private fun clearEntry() {
        // Clear the current input (CE)
        inputTextView.text = ""
    }

    private fun backspace() {
        // Remove the last character from the input
        val currentInput = inputTextView.text.toString()
        if (currentInput.isNotEmpty()) {
            inputTextView.text = currentInput.substring(0, currentInput.length - 1)
        }
    }

    @SuppressLint("SetTextI18n")
    private fun evaluate() {
        val expression = inputTextView.text.toString()

        try {
            val result = ExpressionBuilder(expression).build().evaluate()
            outputTextView.text = result.toString()
        } catch (e: Exception) {
            outputTextView.text = "Error"
        }
    }
}
