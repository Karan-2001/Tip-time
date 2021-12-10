package com.example.tiptime

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.Toast
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val calbutton: Button =findViewById(R.id.calculate_button)
        calbutton.setOnClickListener {
            calulateTip()
        }
      //  binding.costOfServiceEditText.setOnKeyListener { view, keyCode, _ -> handleKeyEvent(view, keyCode)}
    }

    fun calulateTip() {
        val str=binding.costOfServiceEditText.text.toString()
        val cost=str.toDoubleOrNull()
        if(cost==null)
        {binding.TipAmt.text=""
            val toast = Toast.makeText(this, "Enter the Cost!!", Toast.LENGTH_SHORT)
            toast.show()
        return
        }
        val rs=binding.radiogroup.checkedRadioButtonId
        val per = when(rs) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent -> 0.18
            else -> 0.15
        }
        var tips=per*cost
        val round=binding.roundUp.isChecked
        if(round)
        {
            tips= ceil(tips)
        }
        val formattedTip = NumberFormat.getCurrencyInstance().format(tips)
        binding.TipAmt.text=getString(R.string.tip_amount,formattedTip)
    }

    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }
}