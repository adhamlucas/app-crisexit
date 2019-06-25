package com.example.crisexit

import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.android.volley.Request
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.Response.Listener
import com.android.volley.toolbox.*
import com.example.crisexit.R
import kotlinx.android.synthetic.main.activity_register.*
import org.json.JSONObject
import java.lang.Exception

class RegisterActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        register_button_register.setOnClickListener{

            val email = email_edittext_register.text.toString()
            val nome = nome_edittext_register.text.toString()
            val password = password_edittext_register.text.toString()

            val intent= Intent(this, BottomMenuActivity::class.java)
            register(nome, email, password, intent)

        }

        already_have_account_text_view.setOnClickListener{
            Log.d("MainActivity", "Try to show Login Activity")
            finish()
        }


    }

    fun register(name: String, email:String, password: String, intent: Intent){
        val url: String = "http://172.25.11.144:8000/api/register"
        val queue = Volley.newRequestQueue(this)
        val user = HashMap<String, String>()
        //lateinit var token:String
        user["name"] = name
        user["email"] = email
        user["password"] = password

        val userJson = JSONObject(user)

        val registerRequest = JsonObjectRequest(Request.Method.POST, url, userJson,
            Listener { response ->
                try{
                    intent.putExtra("TOKEN", response["access_token"].toString())
                    startActivity(intent)
                    Toast.makeText(this, "Resonse: $response", Toast.LENGTH_LONG).show()
                    Log.d("Request","Response: ${response}")
                }catch(e:Exception){
                    Toast.makeText(this, "Response Exception: ${e.message}", Toast.LENGTH_LONG).show()
                    Log.d("Exception","Response: ${e.message}")
                }

            }, Response.ErrorListener {
                Toast.makeText(this, "Volley error: $it", Toast.LENGTH_LONG).show()
                Log.d("Response Error Listener","Response: ${it}")
            })

        queue.add(registerRequest)
    }



}