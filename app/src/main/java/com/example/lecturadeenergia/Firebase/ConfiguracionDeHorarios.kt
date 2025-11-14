package com.example.lecturadeenergia.Firebase

data class ConfiguracionDeHorarios(
    var horarioDeActivacion: Double = 0.0,
    var rangoMaximo: Double = 0.0
){
    constructor() : this(0.0, 0.0)
}

