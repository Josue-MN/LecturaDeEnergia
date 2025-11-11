package com.example.lecturadeenergia.Screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Login(){
    var usuario by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    // --> SCAFFOLD ES EL MARCO SUPERIOR DE LA PANTALLA
    // --> PADDINGVALUES SON LOS VALORES O PARAMETROS PARA EL
    // ESTILO DE LA APP COMO UN CSS PARA MANEJAR LA PANTALLA
    Scaffold { paddingValues ->
        // --> COLUMN MANEJARA TTODO EL CONTENIDO QUE SE MOSTRARA
        //  EN PANTALLA DE FORMA VERTICAL
        // --> EL MODIFIER SE LEE EN ORDEN
        Column(modifier = Modifier
            .padding(paddingValues) // MANEJA UN MARGEN PARA EL CONTENIDO
            .fillMaxSize(), // EXPANDE EL MARGEN A TODA LA PANTALLA
            verticalArrangement = Arrangement.Center, //CENTRADO AL CENTRO VERTICAL
            horizontalAlignment = Alignment.CenterHorizontally //CENTRADO AL CENTRO HORIZONTAL
        ) {
            // --> TEXT ES EL TEXTO QUE SE VA A MOSTRAR
            Text(
                text = "Acceso a Cuenta",
                // --> MODIFIER PARA EL ESTILO DEL TEXTO
                modifier = Modifier
                    .padding(10.dp)
                    .padding(bottom = 16.dp),
                // --> FONTSIZE ES EL TAMAÑO DE LA LETRA
                fontSize = 30.sp,
            )
            // --> OutlinedTextField LE DA UN CONTORNO DE COLOR Y FONDO TRANSPARENTE
            OutlinedTextField(
                // --> VALUE ES EL TEXTO QUE SE DEBE MOSTRAR EN OUTLINEDTEXTFIELD
                value = usuario,
                // --> VALOR DE LA VARIABLE QUE SE MODIFICA CON EL VALUE Y IT
                onValueChange = {usuario = it},
                // --> LABEL ES LA ETIQUETA FANTSAMA QUE SE MUESTRA
                label = { Text("Usuario") }
            )
            OutlinedTextField(
                value = password,
                onValueChange = {password = it},
                label = {Text("Password")}
            )
            Button(
                onClick = {
                    println("Le diste al boton")
                }
            ) {
                Text("Iniciar sesion")
            }
        }
    }
}