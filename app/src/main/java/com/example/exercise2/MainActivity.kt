package com.example.exercise2

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.databinding.DataBindingUtil
import com.example.exercise2.databinding.ActivityMainBinding
import kotlin.math.pow

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.buttonCalculate.setOnClickListener {
            calculateAll(it)
        }
        binding.buttonReset.setOnClickListener {
            resetAll(it)
        }
    }

    private fun resetAll(view: View?) {
        binding.apply {
            val height = binding.editTextHeight
            val weight = binding.editTextWeight
            val image = binding.imageViewProfile
            val bmi = binding.textViewBMI

            height.setText("")
            weight.setText("")
            bmi.text = "BMI: "
            image.setImageResource(R.drawable.empty)
        }
    }

    private fun calculateAll(view: View?) {
        binding.apply {
            val image = binding.imageViewProfile
            val bmi = binding.textViewBMI

            bmi.text = "BMI: " + (calculateBMI()).toString()
            image.setImageResource(CountImage())
        }
    }

    private fun CountImage(): Int {
        val bmi = calculateBMI()
        return when {
            bmi < 18.5 -> R.drawable.under
            bmi > 18.5 && bmi < 24.9 -> R.drawable.normal
            else -> R.drawable.over
        }
    }

    private fun calculateBMI(): Double {
        binding.apply {
            val height = binding.editTextHeight.text.toString().toDouble() / 100
            val weight = binding.editTextWeight.text.toString().toDouble()

            return weight / height.pow(2)
        }
    }
}
