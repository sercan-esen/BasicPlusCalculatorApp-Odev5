package com.sercanesen.calculatorapp

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.widget.doAfterTextChanged
import com.sercanesen.calculatorapp.databinding.ActivityMainBinding
import java.lang.Exception
import java.math.BigInteger
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        customLogger("${BigInteger.TEN}")
        setScreenFullSize()
        changeStatusBarColor(Color.BLACK)
        clickListeners()
        resultTextWatcher()
    }
    private fun resultTextWatcher() {
        binding.tvResult.doAfterTextChanged {
            if(binding.tvResult.text.isEmpty()) {
                binding.tvResult.visibility = View.GONE
            } else {
                binding.tvResult.visibility = View.VISIBLE
            }
        }
    }

    private fun clickListeners() {
        binding.apply {
            tvNumberZero.setOnClickListener {
                removeZeroOnFirstKey()
                clearDisplayIfErrorExists()
                tvDisplay.text = "${tvDisplay.text}0"
            }
            tvNumberOne.setOnClickListener {
                clearDisplayIfErrorExists()
                removeZeroOnFirstKey()
                tvDisplay.text = "${tvDisplay.text}1"
            }
            tvNumberTwo.setOnClickListener {
                clearDisplayIfErrorExists()
                removeZeroOnFirstKey()
                tvDisplay.text = "${tvDisplay.text}2"
            }
            tvNumberThree.setOnClickListener {
                clearDisplayIfErrorExists()
                removeZeroOnFirstKey()
                tvDisplay.text = "${tvDisplay.text}3"
            }
            tvNumberFour.setOnClickListener {
                removeZeroOnFirstKey()
                clearDisplayIfErrorExists()
                tvDisplay.text = "${tvDisplay.text}4"
            }
            tvNumberFive.setOnClickListener {
                removeZeroOnFirstKey()
                clearDisplayIfErrorExists()
                tvDisplay.text = "${tvDisplay.text}5"
            }
            tvNumberSix.setOnClickListener {
                removeZeroOnFirstKey()
                clearDisplayIfErrorExists()
                tvDisplay.text = "${tvDisplay.text}6"
            }
            tvNumberSeven.setOnClickListener {
                removeZeroOnFirstKey()
                clearDisplayIfErrorExists()
                tvDisplay.text = "${tvDisplay.text}7"
            }
            tvNumberEight.setOnClickListener {
                removeZeroOnFirstKey()
                clearDisplayIfErrorExists()
                tvDisplay.text = "${tvDisplay.text}8"
            }
            tvNumberNine.setOnClickListener {
                removeZeroOnFirstKey()
                clearDisplayIfErrorExists()
                tvDisplay.text = "${tvDisplay.text}9"
            }
            ivDelete.setOnClickListener {
                clearDisplayIfErrorExists()
                val currentText = tvDisplay.text
                val newText = currentText.dropLast(1)
                tvDisplay.text = newText

            }
            tvAc.setOnClickListener {
                tvDisplay.text = ""
                changeTextOfTvResult("")
            }
            tvNumberSum.setOnClickListener {
                clearDisplayIfErrorExists()
               if(ifDisplayIsEmpty()) {
                   return@setOnClickListener
               }
                if(tvDisplay.text.get(tvDisplay.length()-1) == '+') {
                    //do nothing
                } else {
                    tvDisplay.text = "${tvDisplay.text}+"
                }
            }
            tvNumberEqual.setOnClickListener {
                clearDisplayIfErrorExists()
                if(ifDisplayIsEmpty()) {
                    return@setOnClickListener
                }
                try {
                    getSum()
                }catch (e: NumberFormatException) {
                    changeTextOfTvResult(MAX_VALUE_ERROR)
                    binding.tvDisplay.text = ""
                } catch (e: Exception) {
                    changeTextOfTvResult("")
                    binding.tvDisplay.text = ""
                }
            }
        }
    }

    private fun getSum() {
        var displayText = binding.tvDisplay.text
        if(displayText.length == 0) {
            //return@setOnClickListener
        }
        if(displayText.get(displayText.length-1) == '+') {
            displayText = displayText.dropLast(1)
        }
        val numberList = displayText.split("+")

        var totalOfNumbers = BigInteger("0")
        for(i in 0..numberList.size-1) {
            totalOfNumbers += numberList.get(i).toBigInteger()
        }
        changeTextOfTvResult(totalOfNumbers.toString())
    }

    private fun removeZeroOnFirstKey() {
        binding.apply {
            if(tvDisplay.text.length == 1 && tvDisplay.text == "0") {
                tvDisplay.text = ""
            }
        }

    }

    private fun changeTextOfTvResult(text: String) {
        binding.tvResult.text = text.getDisplayFormattedValue()
    }

    private fun clearDisplayIfErrorExists() {
        if(binding.tvDisplay.text == MAX_VALUE_ERROR) {
            binding.tvDisplay.text = ""
        }
    }

    private fun ifDisplayIsEmpty(): Boolean {
        return binding.tvDisplay.text.isEmpty()
    }

}