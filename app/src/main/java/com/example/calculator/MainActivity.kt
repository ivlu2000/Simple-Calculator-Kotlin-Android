package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private lateinit var calculatorTextView: TextView
    private lateinit var calculator: Calculator
    private var lastSignIsDigit: Boolean = false
    private var lastSignIsDot: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        calculatorTextView = findViewById(R.id.calculatorText)
        calculator = Calculator()
    }


    fun onDigit(view: View) {
        calculatorTextView.append((view as Button).text)
        lastSignIsDigit = true
    }

    fun onArithmeticOperator(view: View) {
        if (lastSignIsDigit || (view as Button).text == "-" && calculatorTextView.text.isEmpty()) {
            calculatorTextView.append((view as Button).text)
            lastSignIsDigit = false
            lastSignIsDot = false
        }
    }

    fun onDot(view: View) {
        if (lastSignIsDigit && !lastSignIsDot) {
            calculatorTextView.append((view as Button).text)
            lastSignIsDot = true
        }
    }

    fun onClear(view: View) {
        calculatorTextView.text = ""
    }


    fun onEquals(view: View) {
        val result = calculator.calculate(calculatorTextView.text.toString())
        calculatorTextView.text = result.toString()

    }
}