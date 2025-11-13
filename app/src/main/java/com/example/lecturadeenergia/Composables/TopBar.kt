package com.example.lecturadeenergia.Composables

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable

@ExperimentalMaterial3Api
@Composable
//SE LLAMA A LA FUNCION QUE MEDIANTE UN DATO QUE ESPERA RECIBIR QUE ES UN STRING
//GENERA UNA BARRA TOP (TOPAPPBAR) LA CUAL ES UNA FUNCION EXPERIMENTAL, QUE MEDIANTE
//UN TITULO SE GENERA EL TITULODE LA APP QUE APARECRA ARRIBA DE LA APLICACION
fun BarraDeArriba(tituloApp: String){
    TopAppBar(
        title = {
            Text(tituloApp)
        }
    )
}