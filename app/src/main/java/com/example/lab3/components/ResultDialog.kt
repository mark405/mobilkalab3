package com.example.lab3.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.example.lab3.models.ResultModel

@Composable
fun ResultDialog(onDismiss: () -> Unit, result: ResultModel) {
    AlertDialog(
        onDismissRequest = {
            onDismiss()
        },
        title = {
            Text(text = "Результат")
        },
        text = {
            Column {
                Text("Частка енергії, що генерується без небалансів: σw1 = ${result.σw1 * 100}%")
                Text("За ${result.σw1 * 100}% енергії: W1 = ${result.W1.toInt()} МВт*год")
                Text("сонячна електростанція отримає прибуток: П1 = ${result.П1.toInt()} тис. грн")
                Text("а за ${(1 - result.σw1) * 100}% енергії: W2 = ${result.W2.toInt()} МВт*год")
                Text("виплачує штраф: Ш1 = ${result.Ш1} тис. грн")
                Text("Збиток: ${result.Ш1 - result.П1} тис. грн")
                Text("Після вдосконалення системи частка енергії, що генерується без небалансів: σw2 = ${result.σw2 * 100}%")
                Text("За ${result.σw2 * 100}% енергії: W3 = ${result.W3} МВт*год")
                Text("сонячна електростанція отримає прибуток: П2 = ${result.П2.toInt()} тис. грн")
                Text("а за ${1 - (result.σw2/100)}% енергії: W4 = ${result.W4} МВт*год")
                Text("виплачує штраф: Ш2 = ${result.Ш2.toInt()} тис. грн")
                Text("Прибуток: ${result.П2 - result.Ш2.toInt()} тис. грн")
            }

        },
        confirmButton = {
            Button(
                colors = ButtonColors(
                    containerColor = Color.Cyan.copy(alpha = 0.5f),
                    contentColor = Color.Black,
                    disabledContentColor = Color.Black,
                    disabledContainerColor = Color.Cyan.copy(alpha = 0.5f),
                ),
                onClick = onDismiss
            ) {
                Text("OK")
            }
        },
    )

}