package com.example.calculatorappfinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var output: TextView
    private lateinit var sign: TextView
    private lateinit var boxOne: EditText
    private lateinit var boxTwo: EditText

    // use logcat package:com.example.calculatorappfinal
    // to find out why the app is crashing
    // ToDo: Build different layout for landscape mode
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initializeViews()

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
//
        // onClickListeners for btn and decimal/Dot
        val digitListener = OnClickListener { v ->
            val b = v as Button
           boxTwo.append(b.text)
           output.append(b.text)
        }

        btn0.setOnClickListener(digitListener)
        btn1.setOnClickListener(digitListener)
        btn2.setOnClickListener(digitListener)
        btn3.setOnClickListener(digitListener)
        btn4.setOnClickListener(digitListener)
        btn5.setOnClickListener(digitListener)
        btn6.setOnClickListener(digitListener)
        btn7.setOnClickListener(digitListener)
        btn8.setOnClickListener(digitListener)
        btn9.setOnClickListener(digitListener)
        btnDot.setOnClickListener(digitListener)

        // onClickListener for operation
        val opListener = OnClickListener { v ->
            var operation = (v as Button).text.toString()
            var boxTwoNum = boxTwo.text.toString()
            var boxOneNum = boxOne.text.toString()
            // sanitize the input
            if(boxOneNum == "."){
                boxOneNum = "0"
            }
            if(boxTwoNum == "."){
                boxTwoNum = "0"
            }
            if (boxTwoNum.length == 0 && boxOneNum.length == 0) {
                output.append("Invalid argument  ")
            } else if (boxOneNum.length == 0) {
                boxOne.setText(boxTwoNum)
                sign.setText(operation)
                boxTwo.text.clear()
            } else if (boxTwoNum.length == 0) {
                sign.setText(operation)
            } else {
                // calculate answer and return String
                if(sign.text.toString() == "="){
                    boxOne.setText(boxTwoNum)
                } else {
                    val ans =
                        calcAnsAndReturnString(
                            boxOneNum.toDouble(),
                            boxTwoNum.toDouble(),
                            sign.text.toString()
                        )
                    boxOne.setText(ans)
                }
                sign.setText(operation)
                boxTwo.text.clear()

            }
        }
        btnMultiply.setOnClickListener(opListener)
        btnDivide.setOnClickListener(opListener)
        btnMinus.setOnClickListener(opListener)
        btnAdd.setOnClickListener(opListener)
        btnEqualTo.setOnClickListener(opListener)
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

    fun calcAnsAndReturnString(a: Double, b: Double, s: String): String {
        var res: Double = 0.0
        res = if (s == "+") {
            a + b
        } else if (s == "-") {
            a - b
        } else if (s == "/") {
            if (b != 0.0)
                a / b
            else {
                // divide by zero
                0.0
            }
        } else {
            a * b
        }
        return res.toString()
    }
}
