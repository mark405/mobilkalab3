package com.example.lab3

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.lab3.models.InputDataModel
import com.example.lab3.models.ResultModel
import kotlin.math.sqrt
import org.apache.commons.math3.special.Erf
import kotlin.math.abs
import kotlin.math.roundToInt


class SolarViewModel : ViewModel() {
    private val _inputData = MutableLiveData(
        InputDataModel(
            5.0, 1.0, 0.25, 7.0
        )
    )
    val inputData: MutableLiveData<InputDataModel> = _inputData

    private val _resultData = MutableLiveData(ResultModel())
    val resultData: MutableLiveData<ResultModel> = _resultData

    fun updatePc(value: String) {
        _inputData.value = _inputData.value!!.copy(Pc = value.toDoubleOrNull() ?: 0.0);
    }

    fun updateσ1(value: String) {
        _inputData.value = _inputData.value!!.copy(σ1 = value.toDoubleOrNull() ?: 0.0);
    }

    fun updateσ2(value: String) {
        _inputData.value = _inputData.value!!.copy(σ2 = value.toDoubleOrNull() ?: 0.0);
    }

    fun updateB(value: String) {
        _inputData.value = _inputData.value!!.copy(B = value.toDoubleOrNull() ?: 0.0);
    }

    fun countResult() {
        val σw1: Double =  (countImbalancePart(_inputData.value!!.σ1, _inputData.value!!.Pc) * 10).roundToInt() / 10.0
        val W1: Double = countW(σw1, _inputData.value!!.Pc)
        val П1: Double = countП(W1, _inputData.value!!.B)
        val W2: Double = countW((1 - σw1), _inputData.value!!.Pc)
        System.out.println(W2)
        val Ш1: Double = countП(W2, _inputData.value!!.B)
        val σw2: Double = (countImbalancePart(_inputData.value!!.σ2, _inputData.value!!.Pc) * 10).roundToInt() / 10.0
        val W3: Double = countW(σw2, _inputData.value!!.Pc)
        val П2: Double = countП(W3, _inputData.value!!.B)
        val W4: Double = countW((1 - σw2), _inputData.value!!.Pc)
        val Ш2: Double = countП(W4, _inputData.value!!.B)
        _resultData.value = ResultModel(
            σw1,
            W1,
            П1,
            W2,
            Ш1,
            σw2,
            W3,
            П2,
            W4,
            Ш2
        )
    }

    private fun countW(σw: Double, Pc: Double): Double {
        return Pc * 24 * σw
    }

    private fun countП(w: Double, B: Double): Double {
        return w * B
    }

    private fun countPd(σ: Double, Pc: Double, p: Double): Double {
        return (abs(σ) * Erf.erf((p - Pc) / (sqrt(2.0) * σ))) / (2 * σ)
    }

    fun countImbalancePart(σ: Double, Pc: Double): Double {
        val startPoint: Double = 4.75
        val endPoint: Double = 5.25
        val result: Double = countPd(σ, Pc, endPoint) - countPd(σ, Pc, startPoint)
        System.out.println(result)
        return result;
    }


}