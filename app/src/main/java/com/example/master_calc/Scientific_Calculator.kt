package com.example.master_calc

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class Scientific_Calculator : AppCompatActivity() {

    //textview
    lateinit var primaryTV : TextView; lateinit var secondaryTV : TextView

    //Button
    lateinit var back_btn: ImageButton
    lateinit var acBtn : Button; lateinit var cBtn : Button; lateinit var brace1Btn : Button;
    lateinit var brace2Btn : Button; lateinit var sinBtn : Button; lateinit var cosBtn : Button;
    lateinit var tanBtn : Button; lateinit var logBtn : Button; lateinit var factBtn : Button;
    lateinit var squareBtn : Button; lateinit var sqRootBtn : Button; lateinit var btnDiv : Button;
    lateinit var btnMul : Button; lateinit var btnSub : Button; lateinit var btnAdd : Button;
    lateinit var btnEqual : Button; lateinit var btnPie : Button; lateinit var btnPoint : Button;
    lateinit var btn0 : Button; lateinit var btn1 : Button; lateinit var btn2 : Button;
    lateinit var btn3 : Button; lateinit var btn4 : Button; lateinit var btn5 : Button;
    lateinit var btn6 : Button; lateinit var btn7 : Button; lateinit var btn8 : Button;
    lateinit var btn9 : Button;


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scientific_calculator)

        // Initialize TextViews
        primaryTV = findViewById(R.id.idTVPrimary)
        secondaryTV = findViewById(R.id.idTVSecondary)

        // Initialize Buttons
        acBtn = findViewById(R.id.idBtnAc)
        cBtn = findViewById(R.id.idBtnC)
        brace1Btn = findViewById(R.id.idBtnBrac1)
        brace2Btn = findViewById(R.id.idBtnBrac2)
        sinBtn = findViewById(R.id.idBtnSin)
        cosBtn = findViewById(R.id.idBtnCos)
        tanBtn = findViewById(R.id.idBtnTan)
        logBtn = findViewById(R.id.idBtnLog)
        factBtn = findViewById(R.id.idBtnFact)
        squareBtn = findViewById(R.id.idBtnSquare)
        sqRootBtn = findViewById(R.id.idBtnSqRoot)
        btnDiv = findViewById(R.id.idBtnDiv)
        btnMul = findViewById(R.id.idBtnMul)
        btnSub = findViewById(R.id.idBtnSub)
        btnAdd = findViewById(R.id.idBtnAdd)
        btnEqual = findViewById(R.id.idBtnEqual)
        btnPie = findViewById(R.id.idBtnPie)
        btnPoint = findViewById(R.id.idBtnDot)
        btn0 = findViewById(R.id.idBtn0)
        btn1 = findViewById(R.id.idBtn1)
        btn2 = findViewById(R.id.idBtn2)
        btn3 = findViewById(R.id.idBtn3)
        btn4 = findViewById(R.id.idBtn4)
        btn5 = findViewById(R.id.idBtn5)
        btn6 = findViewById(R.id.idBtn6)
        btn7 = findViewById(R.id.idBtn7)
        btn8 = findViewById(R.id.idBtn8)
        btn9 = findViewById(R.id.idBtn9)

        back_btn = findViewById(R.id.back_button)
        back_btn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }


        btn0.setOnClickListener {
            primaryTV.text = (primaryTV.text.toString() + "0")
        }
        btn1.setOnClickListener {
            primaryTV.text = (primaryTV.text.toString() + "1")
        }
        btn2.setOnClickListener {
            primaryTV.text = (primaryTV.text.toString() + "2")
        }
        btn3.setOnClickListener {
            primaryTV.text = (primaryTV.text.toString() + "3")
        }
        btn4.setOnClickListener {
            primaryTV.text = (primaryTV.text.toString() + "4")
        }
        btn5.setOnClickListener {
            primaryTV.text = (primaryTV.text.toString() + "5")
        }
        btn6.setOnClickListener {
            primaryTV.text = (primaryTV.text.toString() + "6")
        }
        btn7.setOnClickListener {
            primaryTV.text = (primaryTV.text.toString() + "7")
        }
        btn8.setOnClickListener {
            primaryTV.text = (primaryTV.text.toString() + "8")
        }
        btn9.setOnClickListener {
            primaryTV.text = (primaryTV.text.toString() + "9")
        }
        btnPoint.setOnClickListener {
            primaryTV.text = (primaryTV.text.toString() + ".")
        }
        btnPie.setOnClickListener {
            primaryTV.text = (primaryTV.text.toString() + "3.142")
            secondaryTV.text = (btnPie.text.toString())
        }
        btnEqual.setOnClickListener {
            val str: String = primaryTV.text.toString()
            val result: Double = evaluate(str)
            val r = result.toString()
            primaryTV.text = r
            secondaryTV.text = str
        }

        btnAdd.setOnClickListener {
            primaryTV.text = (primaryTV.text.toString() + "+")
        }
        btnSub.setOnClickListener {
            val str: String = primaryTV.text.toString()
            if (!str.get(index = str.length - 1).equals("-")) {
                primaryTV.text = (primaryTV.text.toString() + "-")
            }
        }
        btnMul.setOnClickListener {
            val str: String = primaryTV.text.toString()
            if (!str.get(index = str.length - 1).equals("*")) {
                primaryTV.text = (primaryTV.text.toString() + "*")
            }
        }
        btnDiv.setOnClickListener {
            primaryTV.text = (primaryTV.text.toString() + "/")
        }
        sqRootBtn.setOnClickListener {
            if (primaryTV.text.toString().isEmpty()) {
                Toast.makeText(this, "Enter a valid number", Toast.LENGTH_SHORT).show()
            } else {
                val str: String = primaryTV.text.toString()
                val r = Math.sqrt(str.toDouble())
                val result = r.toString()
                primaryTV.text = result
            }
        }
        squareBtn.setOnClickListener {
            if (primaryTV.text.toString().isEmpty()) {
                Toast.makeText(this, "Enter a valid number", Toast.LENGTH_SHORT).show()
            } else {
                val d: Double = primaryTV.text.toString().toDouble()
                val square = d * d
                primaryTV.text = square.toString()
                secondaryTV.text = square.toString()
            }
        }
        sinBtn.setOnClickListener {
            primaryTV.text = (primaryTV.text.toString() + "sin")
        }
        cosBtn.setOnClickListener {
            primaryTV.text = (primaryTV.text.toString() + "cos")
        }
        tanBtn.setOnClickListener {
            primaryTV.text = (primaryTV.text.toString() + "tan")
        }
        logBtn.setOnClickListener {
            primaryTV.text = (primaryTV.text.toString() + "log")
        }
        factBtn.setOnClickListener {
            if (primaryTV.text.toString().isEmpty()) {
                Toast.makeText(this, "Enter a valid number", Toast.LENGTH_SHORT).show()
            } else {
                val value: Int = primaryTV.text.toString().toInt()
                val fact: Int = factorial(value)
                primaryTV.text = fact.toString()
                secondaryTV.text = fact.toString()
            }
        }
        acBtn.setOnClickListener {
                primaryTV.text = ""
                secondaryTV.text = ""
        }
        cBtn.setOnClickListener {
            var str: String = primaryTV.text.toString()
            if (!str.equals("")) {
                str = str.substring(0, str.length - 1)
                primaryTV.text = str
            }
        }
        brace1Btn.setOnClickListener {
            primaryTV.text = (primaryTV.text.toString() + "(")
        }
        brace2Btn.setOnClickListener {
            primaryTV.text = (primaryTV.text.toString() + ")")
        }
    }

    private fun evaluate(str: String): Double {
        return object : Any(){
            var pos = -1
            var ch = 0

            fun nextChar(){
                ch = if (++pos < str.length) str[pos].toInt() else -1
            }

            fun eat(charToEdt: Int): Boolean{
                while (ch == ' '.toInt())nextChar()
                if(ch == charToEdt){
                    nextChar()
                    return true
                }
                return false
            }

            fun parse(): Double{
                nextChar()
                val x = parseExpression()
                if(pos<str.length) throw RuntimeException("Unexpected : "+ch.toChar())
                return x
            }

            fun parseExpression(): Double{
                var x = parseTerm()
                while (true){
                    if(eat('+'.toInt()))x += parseTerm()
                    else if(eat('-'.toInt()))x -= parseTerm()
                    else return x
                }
            }

            fun parseTerm(): Double{
                var x = parseFactor()
                while (true){
                    if(eat('*'.toInt()))x *= parseFactor()
                    else if(eat('/'.toInt()))x/= parseFactor()
                    else return x
                }
            }

            fun parseFactor() : Double{
                if(eat('+'.toInt())) return parseFactor()
                if(eat('-'.toInt())) return -parseFactor()

                var x:Double
                val startPos = pos

                if(eat('('.toInt())){
                    x = parseExpression()
                    eat(')'.toInt())
                }else if(ch>='0'.toInt() && ch<='9'.toInt() || ch == '.'.toInt()){
                    while (ch>='0'.toInt() && ch<='9'.toInt() || ch == '.'.toInt())nextChar()

                    x = str.substring(startPos,pos).toDouble()
                }else if (ch>='a'.toInt() && ch<='z'.toInt()){

                    while(ch>='a'.toInt() && ch<='z'.toInt())nextChar()
                    val func = str.substring(startPos,pos)
                    x = parseFactor()
                    if(func == "sqrt"){
                        x = Math.sqrt(x)
                    }else if(func == "sin"){
                        x = Math.sin(Math.toRadians(x))
                    }else if(func == "cos"){
                        x = Math.cos(Math.toRadians(x))
                    }else if(func == "tan"){
                        x = Math.tan(Math.toRadians(x))
                    }else if(func == "log"){
                        x = Math.log10(x)
                    }
                }else{
                    throw RuntimeException("Unexpected : "+ch.toChar())
                }
                if(eat('^'.toInt()))x = Math.pow(x,parseFactor())
                return x
            }
        }.parse()
    }

    private fun factorial(n: Int): Int {
        return if (n == 1 || n == 0) 1 else n * factorial(n - 1)
    }
}
