package com.example.crisexit

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.util.Log
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.Response.Listener
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject
import java.lang.Exception

class LoginActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_login)


        login_button_login.setOnClickListener{
            val intent = Intent(this, BottomMenuActivity::class.java)
            val email = email_edittext_login.text.toString()
            val password = password_edittext_login.text.toString()

            startActivity(intent)
        }

        back_to_register_textview.setOnClickListener {
            Log.d("MainActivity", "Try to show Register Activity")

            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }

    fun login(email:String, password: String, intent: Intent){

    }
}