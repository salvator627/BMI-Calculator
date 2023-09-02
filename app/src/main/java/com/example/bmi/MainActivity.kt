package com.example.bmi

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weightText = findViewById<EditText>(R.id.etWeight)
        val heightText = findViewById<EditText>(R.id.etHeight)
        val calculate = findViewById<Button>(R.id.btnCalculate)

        calculate.setOnClickListener {
            val weight = weightText.text.toString()
            val height = heightText.text.toString()
            if (validateInput(weight, height)) {
                val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
                val bmi2Digits = String.format("%.2f", bmi).toFloat()
                displayResult(bmi2Digits)
            }
        }
    }

    private fun validateInput(weight:String?,height:String?):Boolean{
        return when{
            weight.isNullOrEmpty()-> {
                Toast.makeText(this, "Weight is empty", Toast.LENGTH_SHORT).show()
                return false
            }
            height.isNullOrEmpty()-> {
                Toast.makeText(this, "Height is empty", Toast.LENGTH_SHORT).show()
                return false
            }
            else ->{
                return true
            }
        }
    }

    private fun displayResult(bmi:Float){
        val resultIndex = findViewById<TextView>(R.id.tvResult)
        val resultDesc = findViewById<TextView>(R.id.tvDemoResult)
        val info = findViewById<TextView>(R.id.tvNormal)

        resultIndex.text = bmi.toString()
        info.text = "Normal Range is 18.5 - 24.9"

        var resultText = ""
        var color = 0

        when{
            bmi<18.5 ->{
                resultText = "Underweight"
                color = R.color.underweight
            }
            bmi in 18.5..24.99 ->{
                resultText = "Healthy"
                color = R.color.normal
            }
            bmi in  25.00..29.99->{
                resultText = "Overweight"
                color = R.color.overweight
            }
            bmi>29.99 ->{
                resultText = "Obese"
                color = R.color.obesite
            }
        }
        resultDesc.setTextColor(ContextCompat.getColor(this,color))
        resultDesc.text =resultText

    }
}