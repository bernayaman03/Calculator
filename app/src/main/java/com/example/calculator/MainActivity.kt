
package com.example.calculator

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var editText: EditText
    private lateinit var btnPlus: Button
    private lateinit var btnMinus: Button
    private lateinit var btnMulti: Button
    private lateinit var btnDiv: Button
    private lateinit var btnEqual: Button
    private lateinit var btnClear: Button

    private var firstOperand = 0.0
    private var pendingOperator = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editText)
        btnPlus = findViewById(R.id.btnPlus)
        btnMinus = findViewById(R.id.btnMinus)
        btnMulti = findViewById(R.id.btnMulti)
        btnDiv = findViewById(R.id.btnDiv)
        btnEqual = findViewById(R.id.btnEqual)
        btnClear = findViewById(R.id.btnClear)

        val opButtons = listOf(btnPlus, btnMinus, btnMulti, btnDiv)
        opButtons.forEach { button ->
            button.setOnClickListener {
                if (editText.text.isNotEmpty()) {
                    firstOperand = editText.text.toString().toDoubleOrNull() ?: 0.0
                    pendingOperator = button.text.toString()
                    editText.setText("")
                }
            }
        }

        btnEqual.setOnClickListener {
            val secondOperand = editText.text.toString().toDoubleOrNull() ?: 0.0
            val result = when (pendingOperator) {
                "+" -> firstOperand + secondOperand
                "-" -> firstOperand - secondOperand
                "*" -> firstOperand * secondOperand
                "/" -> if (secondOperand != 0.0) firstOperand / secondOperand else "Hata"
                else -> secondOperand
            }
            editText.setText(result.toString())
        }

        btnClear.setOnClickListener {
            editText.setText("")
            firstOperand = 0.0
            pendingOperator = ""
        }
    }
}
