package com.login.aplicationlogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setup()
    }

    private fun setup(){
        var email = findViewById<TextView>(R.id.editTextTextEmailAddress)
        var password = findViewById<TextView>(R.id.editTextTextPassword)
        var sign = findViewById<Button>(R.id.buttonRegistrar)
        var login = findViewById<Button>(R.id.buttonLogin)


        val builder = AlertDialog.Builder(this)
        sign.setOnClickListener {
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener{
                    if (it.isSuccessful){
                        builder.setTitle("Ok")
                        builder.setMessage("Usuario Creado")

                    }else{
                        builder.setTitle("Error")
                        builder.setMessage("Usuario no Creado")
                        Log.e("firebase", it.exception.toString())
                    }
                    builder.setPositiveButton("Aceptar", null)
                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                }

        }


        login.setOnClickListener {
            FirebaseAuth.getInstance()
                .signInWithEmailAndPassword(email.text.toString(), password.text.toString())
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        builder.setTitle("Ok")
                        builder.setMessage("Authenticacion exitosa")
                    }else{
                        builder.setTitle("Error")
                        builder.setMessage("Usuario o Contraseña incorrectos")
                    }
                    builder.setPositiveButton("Aceptar", null)
                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                }
        }
    }
}