package pt.ipg.calculadoradespesasfinal

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import pt.ipg.calculadoradespesasfinal.ui.theme.CalculadoraDespesasFinalTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        setContent {
            CalculadoraDespesasFinalTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FinanceCalculator()
                }
            }
        }
    }
}

@Composable
fun FinanceCalculator() {
    var income by remember { mutableStateOf(TextFieldValue("")) }
    var expenses by remember { mutableStateOf(TextFieldValue("")) }
    var result by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(16.dp))

        // Campo de texto para ganhos
        OutlinedTextField(
            value = income,
            onValueChange = { income = it },
            label = { Text("Insira seus ganhos") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Campo de texto para despesas
        OutlinedTextField(
            value = expenses,
            onValueChange = { expenses = it },
            label = { Text("Insira suas despesas") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Botão para calcular
        Button(
            onClick = {
                val incomeValue = income.text.toDoubleOrNull()
                val expensesValue = expenses.text.toDoubleOrNull()
                if (incomeValue == null || expensesValue == null) {
                    result = "Por favor, insira valores válidos."
                } else {
                    val balance = incomeValue - expensesValue
                    result = when {
                        balance > 0 -> "Bom trabalho!Estás a economizar bom dinheiro! Saldo: $balance"
                        balance < 0 -> "Que pena! Estás em dividas. Tenta reduzir as tuas despesas. Saldo: $balance"
                        else -> "Não sobrou dinheiro nenhum este mês. Tenta economizar uma parte do que ganhas. Saldo: $balance"
                    }
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Calcular")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Texto do resultado
        Text(result)
    }
}

@Preview(showBackground = true)
@Composable
fun FinanceCalculatorPreview() {
    CalculadoraDespesasFinalTheme {
        FinanceCalculator()
    }
}
