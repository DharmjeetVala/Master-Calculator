package com.example.master_calc

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class Gst_Calculator : AppCompatActivity() {

    private lateinit var amountEditText: EditText
    private lateinit var gstPercentageEditText: EditText
    private lateinit var taxAmountTextView: TextView
    private lateinit var totalAmountTextView: TextView

    lateinit var back_btn: ImageButton
    private lateinit var button0: Button
    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button
    private lateinit var button5: Button
    private lateinit var button6: Button
    private lateinit var button7: Button
    private lateinit var button8: Button
    private lateinit var button9: Button
    private lateinit var buttonPoint: Button
    private lateinit var buttonClear: Button
    private lateinit var buttonCalculate: Button
    private var focusedEditText: EditText? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gst_calculator)

        amountEditText = findViewById(R.id.amountEditText)
        gstPercentageEditText = findViewById(R.id.gstPercentageEditText)
        taxAmountTextView = findViewById(R.id.taxAmountTextView)
        totalAmountTextView = findViewById(R.id.totalAmountTextView)

        button0 = findViewById(R.id.button0)
        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        button3 = findViewById(R.id.button3)
        button4 = findViewById(R.id.button4)
        button5 = findViewById(R.id.button5)
        button6 = findViewById(R.id.button6)
        button7 = findViewById(R.id.button7)
        button8 = findViewById(R.id.button8)
        button9 = findViewById(R.id.button9)
        buttonPoint = findViewById(R.id.buttonPoint)
        buttonClear = findViewById(R.id.buttonClear)
        buttonCalculate = findViewById(R.id.buttonCalculate)

        val buttons = listOf(button0, button1, button2, button3, button4, button5, button6, button7, button8, button9)
        buttons.forEachIndexed { index, button ->
            button.setOnClickListener {
                focusedEditText?.append(index.toString())
            }
        }

        buttonPoint.setOnClickListener {
            focusedEditText?.let {
                if (!it.text.contains(".")) {
                    it.append(".")
                }
            }
        }

        buttonClear.setOnClickListener {
            amountEditText.text.clear()
            gstPercentageEditText.text.clear()
            focusedEditText?.text?.clear()
            taxAmountTextView.text = "Tax Amount: "
            totalAmountTextView.text = "Total Amount: "
        }

        buttonCalculate.setOnClickListener {
            calculateGST()
        }

        amountEditText.setOnClickListener {
            focusedEditText = amountEditText
        }

        gstPercentageEditText.setOnClickListener {
            focusedEditText = gstPercentageEditText
        }

        back_btn = findViewById(R.id.back_button)
        back_btn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun calculateGST() {
        val amountText = amountEditText.text.toString()
        val gstPercentageText = gstPercentageEditText.text.toString()

        if (amountText.isNotEmpty() && gstPercentageText.isNotEmpty()) {
            val amount = amountText.toDouble()
            val gstPercentage = gstPercentageText.toDouble()

            val taxAmount = (amount * gstPercentage) / 100
            val totalAmount = amount + taxAmount

            taxAmountTextView.text = "Tax Amount: %.2f".format(taxAmount)
            totalAmountTextView.text = "Total Amount: %.2f".format(totalAmount)
        } else {
            taxAmountTextView.text = "Tax Amount: "
            totalAmountTextView.text = "Total Amount: "
        }
    }

}