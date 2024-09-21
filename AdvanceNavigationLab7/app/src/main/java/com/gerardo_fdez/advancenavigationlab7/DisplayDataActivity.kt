package com.gerardo_fdez.advancenavigationlab7

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DirectionsCar
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Security

// Definición de las pantallas de documento
sealed class DocumentScreen(val title: String, val icon: androidx.compose.ui.graphics.vector.ImageVector) {
    object ID : DocumentScreen("ID", Icons.Filled.Person)
    object DriverLicense : DocumentScreen("Licencia de Conducir", Icons.Filled.DirectionsCar)
    object SocialSecurity : DocumentScreen("Seguro Social", Icons.Filled.Security)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DisplayData(userData: UserData) {
    var selectedScreen by remember { mutableStateOf<DocumentScreen>(DocumentScreen.ID) }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Mostrar Datos") })
        },
        bottomBar = {
            NavigationBar {
                NavigationBarItem(
                    selected = selectedScreen is DocumentScreen.ID,
                    onClick = { selectedScreen = DocumentScreen.ID },
                    label = { Text("ID") },
                    icon = { Icon(DocumentScreen.ID.icon, contentDescription = "ID") }
                )
                NavigationBarItem(
                    selected = selectedScreen is DocumentScreen.DriverLicense,
                    onClick = { selectedScreen = DocumentScreen.DriverLicense },
                    label = { Text("Licencia") },
                    icon = { Icon(DocumentScreen.DriverLicense.icon, contentDescription = "Licencia de Conducir") }
                )
                NavigationBarItem(
                    selected = selectedScreen is DocumentScreen.SocialSecurity,
                    onClick = { selectedScreen = DocumentScreen.SocialSecurity },
                    label = { Text("Seguro Social") },
                    icon = { Icon(DocumentScreen.SocialSecurity.icon, contentDescription = "Seguro Social") }
                )
            }
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            when (selectedScreen) {
                is DocumentScreen.ID -> IDDocument(userData)
                is DocumentScreen.DriverLicense -> DriverLicenseDocument(userData)
                is DocumentScreen.SocialSecurity -> SocialSecurityDocument(userData)
            }
        }
    }
}

@Composable
fun IDDocument(userData: UserData) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(24.dp)
        ) {
            Text(text = "Documento de Identidad", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Nombre: ${userData.name}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Edad: ${userData.age}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "ID: ${userData.idNumber}", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
fun DriverLicenseDocument(userData: UserData) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(24.dp)
        ) {
            Text(text = "Licencia de Conducir", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Nombre: ${userData.name}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Edad: ${userData.age}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Licencia No.: ${userData.driverLicenseNumber}", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
fun SocialSecurityDocument(userData: UserData) {
    Card(
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        modifier = Modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(24.dp)
        ) {
            Text(text = "Seguro Social", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(16.dp))
            Text(text = "Nombre: ${userData.name}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "Edad: ${userData.age}", style = MaterialTheme.typography.bodyLarge)
            Text(text = "SSN: ${userData.socialSecurityNumber}", style = MaterialTheme.typography.bodyLarge)
        }
    }
}
