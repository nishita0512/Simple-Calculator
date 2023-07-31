package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val calculatorViewModel = CalculatorViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply{

            calculatorViewModel.previousInput.observe(this@MainActivity){
                textViewOne.text = it
            }

            calculatorViewModel.currentInput.observe(this@MainActivity){
                textViewTwo.text = it
            }

            setInputButtonClick(btnNumberZero,'0')
            setInputButtonClick(btnNumberOne,'1')
            setInputButtonClick(btnNumberTwo,'2')
            setInputButtonClick(btnNumberThree,'3')
            setInputButtonClick(btnNumberFour,'4')
            setInputButtonClick(btnNumberFive,'5')
            setInputButtonClick(btnNumberSix,'6')
            setInputButtonClick(btnNumberSeven,'7')
            setInputButtonClick(btnNumberEight,'8')
            setInputButtonClick(btnNumberNine,'9')
            setInputButtonClick(btnAdd,'+')
            setInputButtonClick(btnSubtract,'-')
            setInputButtonClick(btnMultiply,'X')
            setInputButtonClick(btnDivide,'/')

            btnDelete.setOnClickListener {
                calculatorViewModel.performAction(0)
            }
            btnClear.setOnClickListener {
                calculatorViewModel.performAction(1)
            }
            btnEqual.setOnClickListener {
                calculatorViewModel.performAction(2)
            }

        }

    }

    private fun setInputButtonClick(btn: Button, ch: Char){
        btn.setOnClickListener {
            calculatorViewModel.addCharacter(ch)
        }
    }

}