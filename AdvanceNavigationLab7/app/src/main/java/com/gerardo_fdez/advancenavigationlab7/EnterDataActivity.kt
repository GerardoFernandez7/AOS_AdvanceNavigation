package com.gerardo_fdez.advancenavigationlab7

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EnterData(onSendData: (UserData) -> Unit) {
    var name by remember { mutableStateOf("") }
    var age by remember { mutableStateOf("") }
    var idNumber by remember { mutableStateOf("") }
    var driverLicenseNumber by remember { mutableStateOf("") }
    var socialSecurityNumber by remember { mutableStateOf("") }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Ingresar Datos") })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()) // Permite el desplazamiento si el contenido excede la pantalla
                .fillMaxSize(),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = name,
                onValueChange = { name = it },
                label = { Text("Nombre") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = age,
                onValueChange = { age = it },
                label = { Text("Edad") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = idNumber,
                onValueChange = { idNumber = it },
                label = { Text("Número de ID") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = driverLicenseNumber,
                onValueChange = { driverLicenseNumber = it },
                label = { Text("Número de Licencia de Conducir") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(16.dp))
            OutlinedTextField(
                value = socialSecurityNumber,
                onValueChange = { socialSecurityNumber = it },
                label = { Text("Número de Seguro Social") },
                modifier = Modifier.fillMaxWidth()
            )
            Spacer(modifier = Modifier.height(24.dp))
            Button(
                onClick = {
                    val ageInt = age.toIntOrNull() ?: 0
                    val userData = UserData(
                        name = name,
                        age = ageInt,
                        idNumber = idNumber,
                        driverLicenseNumber = driverLicenseNumber,
                        socialSecurityNumber = socialSecurityNumber
                    )
                    onSendData(userData)
                },
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Generar documentos")
            }
        }
    }
}
