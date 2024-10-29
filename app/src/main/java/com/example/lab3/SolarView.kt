package com.example.lab3

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.lab3.components.InputRow
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.lab3.components.ResultDialog


@Composable
fun SolarView(viewModel: SolarViewModel) {
    val inputData by viewModel.inputData.observeAsState()
    val openDialog = remember { mutableStateOf(false) }

    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            Column {
                Text("Прибуток вдосконалення системи прогнозу")
                InputRow(
                    textValue = inputData!!.Pc.toString(),
                    baseText = "P",
                    upperIndexText = "",
                    lowerIndexText = "c",
                    onValueChange = { value -> viewModel.updatePc(value) }
                )
                InputRow(
                    textValue = inputData!!.σ1.toString(),
                    baseText = "σ",
                    lowerIndexText = "1",
                    onValueChange = { value -> viewModel.updateσ1(value) }
                )
                InputRow(
                    textValue = inputData!!.σ2.toString(),
                    baseText = "σ",
                    lowerIndexText = "2",
                    onValueChange = { value -> viewModel.updateσ2(value) }
                )
                InputRow(
                    textValue = inputData!!.B.toString(),
                    baseText = "B",
                    onValueChange = { value -> viewModel.updateB(value) }
                )

                Button(onClick = { viewModel.countResult(); openDialog.value = true }) {
                    Text(text = "Обчислити")
                }
            }

            if (openDialog.value) {
                viewModel.resultData.value?.let {
                    ResultDialog(
                        result = it,
                        onDismiss = { openDialog.value = false })
                }
            }
        }

    }
}