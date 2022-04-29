package com.login.aplicationlogin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
        var email = findViewById<TextView>(R.id.textViewCorreo)
        var password = findViewById<TextView>(R.id.textViewPass)
        var sign = findViewById<Button>(R.id.buttonRegistrar)

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
                    }
                    builder.setPositiveButton("Aceptar", null)
                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                }

        }
    }
}