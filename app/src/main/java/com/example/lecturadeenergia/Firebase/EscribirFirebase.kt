package com.example.lecturadeenergia.Firebase

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.database.database

//OPERACION ASINCRONICA CON PARAMETROS A RECIBIR
fun escribirFirebase(
    field: String, //RUTA DE LA BASE DE DATOS
    value: String, //OBJETO DE DATOS QUE QUIERO GUARDAR
    onSuccess: () -> Unit = {}, //FUNCION CALLBACK QUE GUARDA SI SE GUARDO EL DATO
    onError: (String) -> Unit = {} //FUNCION CALLBACK QUE GUARDA NO GUARDO EL DATO DEL ERROR
) {
    val database = Firebase.database //OBTIENE LA INSTANCIA DE LA REALTIME DATABASE DE FIREBASE
    val myRef = database.getReference(field) //OBTIENE LA REFERENCIA DE FIELD DEL DATO

    //MUESTRA UNA ESCRITURA Y EL OBJETO A ESCRIBIR
    Log.d("FirebaseWrite", "Iniciando escritura en: $field")
    Log.d("FirebaseWrite", "Objeto a escribir: $value")

    //MYRED HACE REFERENCIA DE DONDE SE VA A GUARDAR EL DATO Y VALUE LE VALOR
    myRef.setValue(value)
        //SE EJECUTA SI FUE UN EXITO
        .addOnSuccessListener {
            Log.d("FirebaseWrite", "Datos escritos correctamente en: $field")
            //LLAMA A LA FUNCION SUCCES PARA GUARDAR LO RECIBIDO
            onSuccess()
        }
        //SE EJECUTA SI FUE UN ERRROR, RECIBIENDO EL MISMO
        .addOnFailureListener { error ->
            //REGISTRA EL ERROR EN EL LOGCAT E
            Log.e("FirebaseWrite", "Error de escritura en: $field", error)
            //LLAMA A LA FUNCION Y GUARDA EL ERROR
            onError("Error: ${error.message}")
        }
}