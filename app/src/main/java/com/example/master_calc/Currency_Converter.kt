package com.example.master_calc

import android.content.Intent
import android.os.Bundle
import android.view.WindowInsetsAnimation
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

class Currency_Converter : AppCompatActivity() {

    private lateinit var amountEditText: EditText
    private lateinit var fromCurrencySpinner: Spinner
    private lateinit var toCurrencySpinner: Spinner
    private lateinit var convertButton: Button
    private lateinit var resultTextView: TextView
    lateinit var back_btn: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_currency_converter)

        amountEditText = findViewById(R.id.amountEditText)
        fromCurrencySpinner = findViewById(R.id.fromCurrencySpinner)
        toCurrencySpinner = findViewById(R.id.toCurrencySpinner)
        convertButton = findViewById(R.id.convertButton)
        resultTextView = findViewById(R.id.resultTextView)

        val currencies = arrayOf("USD", "EUR", "GBP", "INR", "CAD", "JPY", "AUD", "CHF", "CNY", "NZD", "KRW", "HKD", "SEK", "SGD", "NOK")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        fromCurrencySpinner.adapter = adapter
        toCurrencySpinner.adapter = adapter

        convertButton.setOnClickListener {
            convertCurrency()
        }

        back_btn = findViewById(R.id.back_button)
        back_btn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun convertCurrency() {
        val amount = amountEditText.text.toString().toDoubleOrNull()
        val fromCurrency = fromCurrencySpinner.selectedItem.toString()
        val toCurrency = toCurrencySpinner.selectedItem.toString()

        if (amount == null) {
            resultTextView.text = "Please enter a valid amount"
            return
        }

        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.exchangerate-api.com/v4/latest/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = retrofit.create(CurrencyApi::class.java)
        val call = api.getRates(fromCurrency)

        call.enqueue(object : Callback<ExchangeRatesResponse> {
            override fun onResponse(call: Call<ExchangeRatesResponse>, response: Response<ExchangeRatesResponse>) {
                if (response.isSuccessful) {
                    val rates = response.body()?.rates ?: return
                    val fromRate = rates[fromCurrency] ?: 1.0
                    val toRate = rates[toCurrency] ?: 1.0
                    val convertedAmount = amount * toRate / fromRate
                    resultTextView.text = "Converted Amount: $convertedAmount $toCurrency"
                }
            }

            override fun onFailure(call: Call<ExchangeRatesResponse>, t: Throwable) {
                resultTextView.text = "Failed to fetch rates"
            }
        })
    }
}

interface CurrencyApi {
    @GET("latest")
    fun getRates(@Query("base") base: String): Call<ExchangeRatesResponse>
}

data class ExchangeRatesResponse(val rates: Map<String, Double>)