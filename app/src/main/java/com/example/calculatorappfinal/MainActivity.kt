package com.example.calculatorappfinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler // delay 2 sec and clear the textview at bottom
import android.view.View
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var output: TextView
    private lateinit var sign: TextView
    private lateinit var boxOne: EditText
    private lateinit var boxTwo: EditText
    private var handler = Handler()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeViews();

        // initialization of all the button variables
        val btn0 = findViewById<Button>(R.id.button0)
        val btn1 = findViewById<Button>(R.id.button1)
        val btn2 = findViewById<Button>(R.id.button2)
        val btn3 = findViewById<Button>(R.id.button3)
        val btn4 = findViewById<Button>(R.id.button4)
        val btn5 = findViewById<Button>(R.id.button5)
        val btn6 = findViewById<Button>(R.id.button6)
        val btn7 = findViewById<Button>(R.id.button7)
        val btn8 = findViewById<Button>(R.id.button8)
        val btn9 = findViewById<Button>(R.id.button9)
        val btnDot = findViewById<Button>(R.id.buttonDecimal)

        // operations
        val btnMultiply = findViewById<Button>(R.id.buttonMultiply)
        val btnMinus = findViewById<Button>(R.id.buttonMinus)
        val btnAdd = findViewById<Button>(R.id.buttonAdd)
        val btnDivide = findViewById<Button>(R.id.buttonDivide)
        val btnEqualTo = findViewById<Button>(R.id.buttonEquals)

        // onClickListeners for btn and decimal/Dot
        val listener = View.OnClickListener { v ->
            val b = v as Button
            output.append(b.text)
            boxTwo.append(b.text)
        }
        btn1.setOnClickListener(listener)
        btn2.setOnClickListener(listener)
        btn3.setOnClickListener(listener)
        btn4.setOnClickListener(listener)
        btn5.setOnClickListener(listener)
        btn6.setOnClickListener(listener)
        btn7.setOnClickListener(listener)
        btn8.setOnClickListener(listener)
        btn9.setOnClickListener(listener)
        btn0.setOnClickListener(listener)
        btnDot.setOnClickListener(listener)

        // onClickListener for operation
        val opListener = View.OnClickListener { v ->
            val operation = (v as Button).text.toString()
            val boxTwoNum = boxTwo.text.toString()
            val boxOneNum = boxOne.text.toString()
            if (boxTwoNum.length == 0 && boxOneNum.length == 0) {
                output.append("Invalid argument  ")
                handler.postDelayed(
                    fun() {
                        output.setText("")
                    }, 2000
                )
            } else if (boxOneNum.length == 0) {
                boxOne.setText(boxTwoNum)
                sign.setText(operation)
                boxTwo.text.clear()
            } else {
                sign.setText(operation)
            }
        }
        btnMultiply.setOnClickListener(opListener)
        btnDivide.setOnClickListener(opListener)
        btnMinus.setOnClickListener(opListener)
        btnAdd.setOnClickListener(opListener)

        // onClickListener for =
        btnEqualTo.setOnClickListener(object : OnClickListener {
            override fun onClick(p0: View?) {
                val numberOne = boxOne.text.toString()
                val numberTwo = boxTwo.text.toString()
                if (numberTwo.length == 0 && numberOne.length == 0) {
                    output.append("both arguments are required")
                    handler.postDelayed(
                        fun() {
                            output.setText("")
                        }, 2000
                    )
                } else if (numberTwo.length == 0) {
                    boxTwo.setText(numberOne)
                    boxOne.text.clear()
                    sign.setText("") // clear sign
                } else {
                    val a = numberOne.toDouble()
                    val b = numberTwo.toDouble()
                    var res: Double
                    if (sign.text == "+") {
                        res = a + b
                    } else if (sign.text == "-") {
                        res = a - b
                    } else if (sign.text == "/") {
                        res = a / b
                    } else {
                        res = a * b
                    }
                    val ans = String.format("%.4f", res) // format ans to 4 decimal places
                    boxOne.setText(ans)
                    boxTwo.text.clear()
                    sign.setText("")
                }
            }
        })


    }

    fun initializeViews() {
        output = findViewById<TextView>(R.id.result)
        boxOne = findViewById<EditText>(R.id.numOne)
        boxTwo = findViewById<EditText>(R.id.numTwo)
        sign = findViewById<TextView>(R.id.operation)
        // and clear the text
        output.setText("")
        boxOne.setText("")
        boxTwo.setText("")
    }
}
