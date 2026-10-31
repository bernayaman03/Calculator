package com.example.calculator // Buradaki isim senin proje isminle aynı olmalı

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    // 1. ADIM: Sınavda sorulacak meşhur "lateinit" tanımlamaları
    private lateinit var editText: EditText
    private lateinit var btnPlus: Button
    private lateinit var btnMinus: Button
    private lateinit var btnMulti: Button
    private lateinit var btnDiv: Button
    private lateinit var btnEqual: Button
    private lateinit var btnClear: Button

    // Hesaplama için gerekli hafıza değişkenleri
    private var firstOperand = 0.0
    private var pendingOperator = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // Tasarım dosyamızı bağladık

        // 2. ADIM: XML'deki ID'ler ile kod arasındaki köprüyü kur (findViewById)
        editText = findViewById(R.id.editText)
        btnPlus = findViewById(R.id.btnPlus)
        btnMinus = findViewById(R.id.btnMinus)
        btnMulti = findViewById(R.id.btnMulti)
        btnDiv = findViewById(R.id.btnDiv)
        btnEqual = findViewById(R.id.btnEqual)
        btnClear = findViewById(R.id.btnClear)

        // 3. ADIM: Operatör butonlarına toplu halde tıklama özelliği ekle
        val opButtons = listOf(btnPlus, btnMinus, btnMulti, btnDiv)
        opButtons.forEach { button ->
            button.setOnClickListener {
                if (editText.text.isNotEmpty()) {
                    // Ekrandaki sayıyı al ve hafızaya (firstOperand) at
                    firstOperand = editText.text.toString().toDoubleOrNull() ?: 0.0
                    // Hangi operatöre basıldığını kaydet (+, -, *, /)
                    pendingOperator = button.text.toString()
                    // Yeni sayı girilebilmesi için ekranı temizle
                    editText.setText("")
                }
            }
        }

        // 4. ADIM: Eşittir (=) butonuna basınca sonucu hesapla
        btnEqual.setOnClickListener {
            val secondOperand = editText.text.toString().toDoubleOrNull() ?: 0.0
            val result = when (pendingOperator) {
                "+" -> firstOperand + secondOperand
                "-" -> firstOperand - secondOperand
                "*" -> firstOperand * secondOperand
                "/" -> if (secondOperand != 0.0) firstOperand / secondOperand else "Hata"
                else -> secondOperand
            }
            // Sonucu ekrana yazdır
            editText.setText(result.toString())
        }

        // 5. ADIM: Temizle (C) butonu her şeyi sıfırlasın
        btnClear.setOnClickListener {
            editText.setText("")
            firstOperand = 0.0
            pendingOperator = ""
        }
    }
}