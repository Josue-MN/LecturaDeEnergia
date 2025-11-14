package com.example.lecturadeenergia.NavegacionControladaDeLaApp

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.lecturadeenergia.Composables.BarraDeArriba
import com.example.lecturadeenergia.Composables.BarraDeNavegacionDeBotonesAdmin
import com.example.lecturadeenergia.Composables.BarraDeNavegacionDeBotonesBasico
import com.example.lecturadeenergia.Screens.CortarScreen
import com.example.lecturadeenergia.Screens.HistorialScreen
import com.example.lecturadeenergia.Screens.HomeScreen
import com.example.lecturadeenergia.Screens.HorariosScreen
import com.example.lecturadeenergia.Screens.LoginScreen
import com.example.lecturadeenergia.Screens.RangosScreen

@Composable
//ESTE  @ SE GENERA PARA AVISARLE A KOTLIN QUE SE QUE USARE FUNCIONES EXPERIEMNTALES Y NO
//ME GENERE ERRORES
@OptIn(ExperimentalMaterial3Api::class)
fun NavegacionControladaDeLaApp(){

    //SE GENERA UNA VARIABLE PARA ALMACENAR LA NAVEGACION Y QUE RECUERDE QUE AUNQUE SE
    //GIRE LA PANTALLA DEBE MANTENERSE(rememberNavController())
    val navegacionControlada = rememberNavController()
    //SE CREA UN VALOR QUE ALMACENA EL VALOR DE LA PANTALLA ACTUAL USADA(currentBackStackEntry)
    //PARA CONVERTILA EN UN ESTADO (AsState()), QUEDANDO (currentBackStackEntryAsState())
    //BY SE USA PARA QUE MI VARIABLE SE SUSCRIBA A LO OBTENIDO
    val obsevadorDeNavegacionOculto by navegacionControlada.currentBackStackEntryAsState()
    //SE CREA UNA VARIABLE QUE LIMPIA TTODO LO ANTERIOR PARA OBTENER EL NOMBRE D EA RUTA,
    //-> SE VERIFICA QUE LO OBTENIDO NO SEA NULO(obsevadorDeNavegacionOculto?) PIDIENDOLE
    //-> EL DESTINO (destination?) SEGUN UNA RUTA(route)
    val rutaActual = obsevadorDeNavegacionOculto?.destination?.route

    // VARIABLE QUE SEGUN UN WHEN DECIDE EL TITULO DE LA APP MEDIANTE UNA RUTA OBSERVABLE
    // (rutaActual) "" -> "" EL NOMBRE QUE RENDRA
    val tituloDePantalla = when (rutaActual) {
        "homeAdmin" -> "Inicio"
        "rangosAdmin" -> "Rangos de Apagado"
        "historialAdmin" -> "Historial de Mediciones"
        "cortarAdmin" -> "Control de Energía"
        "horariosAdmin" -> "Horarios de Activacion/Suspension"
        "homeBasico" -> "Inicio"
        "historialBasico" -> "Historial de Mediciones"
        "cortarBasico" -> "Control de Energía"
           else -> "Login"
    }

    // SE GENERA UNA VARIABLE QUE CUANDO LA RUTA SEA DISTINTA DE LOGIN
    // NO DEBE MOSTRARSE LA TOPBAR
    val mostrarBarras = rutaActual != "login"
    //SE GENERA VARIABLE VACIA PARA EL CARGO
    var cargoDelUsuario by rememberSaveable { mutableStateOf("") }

    // --> SCAFFOLD ES EL MARCO SUPERIOR DE LA PANTALLA
    // --> PADDINGVALUES SON LOS VALORES O PARAMETROS PARA EL
    // ESTILO DE LA APP COMO UN CSS PARA MANEJAR LA PANTALLA
    Scaffold(
        //FUNCION QUE PONE ARRIBA LA TOPBAR (BARRADEARRIBA) SIEMPRE Y CUANDO
        //EL SEA DISTINRA DE LOGIN (if (mostrarBarras) {), YA QUE EL STRING DEFINIDO DE LA
        // FUNCION SERA LA RUTA ESTABLECIDA EN TITULOPANTALLA
        topBar = {
            if (mostrarBarras) {
                BarraDeArriba(tituloApp = tituloDePantalla)
                }
            },
        //FUNCION QUE PONE ABAJO LA BOTTOMBAR (BarraDeNavegacionDeBotones) SIEMPRE Y CUANDO
        //EL SEA DISTINRA DE LOGIN (if (mostrarBarras) {), YA QUE EL STRING DEFINIDO DE LA
        // FUNCION SERA LA RUTA ESTABLECIDA EN NAGECACIONCONTROLADA
        bottomBar = {
            //BOTTOM BAR PAR ADMIN
            if (mostrarBarras && cargoDelUsuario == "administrador") {
                BarraDeNavegacionDeBotonesAdmin(navegacionControlada = navegacionControlada)
                }
            //BOTTOM BAR PARA BASICO
            if (mostrarBarras && cargoDelUsuario == "basico") {
                BarraDeNavegacionDeBotonesBasico(navegacionControlada = navegacionControlada)
            }
            }
        // MANEJA UN MARGEN PARA EL CONTENIDO
        ) { paddingValues ->

        // ESTE ES EL CONTROL REMOTO DEFINIDO ANTES PARA SER MOSTRADON SEGUN
        NavHost(
            //SE LE DA EL CONTROL REMOTO AL NAVHOST
            navController = navegacionControlada,
            //SE ESTABLECE LA APP QUE SE DEBE MOSTRAR ANTES
            startDestination = "login",
            // --> EL MODIFIER SE LEE EN ORDEN
            modifier = Modifier
                .padding(paddingValues)// MANEJA UN MARGEN PARA EL CONTENIDO
            ) {
            //SE GENERA EL COMPOSABLE DEL LOGIN PAR AINICAR SESION
            composable("login") {
                //SE LLAMA A LA PANTALLA
                LoginScreen(
                    //SE PASA EL VALOR DEL LOGIN AQUI
                    onLoginSuccess = { cargo ->
                        //SE GUARDA EL CARGO PARA SER USADO ARRIBA
                        cargoDelUsuario = cargo

                        //DERRIBA EL TIPO DE BOTTOMBAR QU ETENDRA EL USUARIO
                        if (cargo == "administrador") {
                            navegacionControlada.navigate("homeAdmin")
                        } else if (cargo == "basico") {
                            // Un usuario básico solo ve el historial
                            navegacionControlada.navigate("homeBasico")
                        }
                    }
                )
            }
            // GENERANDO LOS COMPOSABLES SE ESTABLCE QUE RUTA ES (composable("login"))
            //PARA QUE MUESTRE LA PANTALLA (LoginScreen(navegacionControlada = navegacionControlada) })
            // QUE CORRESPONDE SEGUN LOS VALORES QUE PIDE DICHA FUNCION

            //RUTAS ADMIN
            composable("homeAdmin") { HomeScreen(navegacionControlada = navegacionControlada) }
            composable("rangosAdmin") { RangosScreen(navegacionControlada = navegacionControlada) }
            composable("historialAdmin") { HistorialScreen(navegacionControlada = navegacionControlada) }
            composable("cortarAdmin") { CortarScreen(navegacionControlada = navegacionControlada) }
            composable("horariosAdmin") { HorariosScreen(navegacionControlada = navegacionControlada) }

            //RUTAS BASICO
            composable("homeBasico") { HomeScreen(navegacionControlada = navegacionControlada) }
            composable("historialBasico") { HistorialScreen(navegacionControlada = navegacionControlada) }
            composable("cortarBasico") { CortarScreen(navegacionControlada = navegacionControlada) }
            }
        }
    }