package com.example.calculator

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CalculatorViewModel: ViewModel() {

    var previousInput = MutableLiveData("")
        private set
    var currentInput = MutableLiveData("0")
        private set

    fun performAction(action: Int){
        when(action){
            0->{ //delete
                currentInput.value = currentInput.value?.dropLast(1)
            }
            1->{ //Clear
                currentInput.value = "0"
                previousInput.value = ""
            }
            2->{ //Calculate
                calculate()
            }
        }
    }

    fun addCharacter(ch: Char){
        if(currentInput.value=="0" && currentInput.value!!.last()<'0' && ch<'0'){
            return
        }
        if(currentInput.value=="0"){
            currentInput.value = ch.toString()
            return
        }
        currentInput.value += ch
    }

    private fun calculate(){
        previousInput.value = currentInput.value

        val nums = ArrayList<Int>()
        val operators = ArrayList<Char>()
        var temp = 0
        var negative = false

        if(currentInput.value!![0]=='-'){
            negative = true
        }

        currentInput.value!!.forEachIndexed {index, currentChar ->
            if(currentChar in '0'..'9'){
                temp*=10
                temp+=(currentChar-'0')
            }
            else{
                if(temp!=0){
                    if(negative){
                        temp*=-1
                        negative = false
                    }
                    nums.add(temp)
                    temp = 0
                }
                if(index!=0) {
                    operators.add(currentChar)
                }
            }
        }
        nums.add(temp)

        if(nums.lastIndex-operators.lastIndex<=1){
            var ans = nums[0].toFloat()
            operators.forEachIndexed { index, currentOperator ->
                when(currentOperator){
                    '+'->{
                        ans+=nums[index+1]
                    }
                    '-'->{
                        ans-=nums[index+1]
                    }
                    'X'->{
                        ans*=nums[index+1]
                    }
                    '/'->{
                        ans/=nums[index+1]
                    }
                    else->{}
                }
            }
            if(negative){
                ans*=-1
            }
            currentInput.value = ans.toString()
        }

    }

}