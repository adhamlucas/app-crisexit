package com.example.entregaapp

import android.os.Bundle
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
        //val queue = Volley.newRequestQueue(this)

        val cache = DiskBasedCache(cacheDir, 1024 * 1024) // 1MB cap

// Set up the network to use HttpURLConnection as the HTTP client.
        val network = BasicNetwork(HurlStack())

// Instantiate the RequestQueue with the cache and network. Start the queue.
        val queue = RequestQueue(cache, network).apply {
            start()
        }
        register_button_register.setOnClickListener{
            /*val email = email_edittext_register.text.toString()
            val nome = nome_edittext_register.text.toString()
            val password = password_edittext_register.text.toString()
            val url: String = "http://172.25.11.41:8000/api/register"

            val user = HashMap<String, String>()
            user["name"] = nome
            user["email"] = email
            user["password"] = password

            val userJson = JSONObject(user)

            val registerRequest = JsonObjectRequest(Request.Method.POST, url, userJson,
                Listener { response ->
                    try{
                        Toast.makeText(this, "Resonse: $response", Toast.LENGTH_LONG).show()
                    }catch(e:Exception){

                    }

                }, Response.ErrorListener {
                    Toast.makeText(this, "Volley error: $it", Toast.LENGTH_LONG).show()
                })



            queue.add(registerRequest)*/
        }

           /* if(email.isEmpty()|| password.isEmpty()){
                Toast.makeText(this, "Please enter text in email/password", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            Log.d("MainActivity", "Emails is: " + email)
            Log.d("MainActivity", "Password is:  $password")


            //Firebase Authenticate to Create a user with email and password
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if(it.isSuccessful){
                        Log.d("MainActivity", "Sucessufly create user with uid: ${it.result?.user?.uid}")
                        Toast.makeText(this,"Sucessufly create user!", Toast.LENGTH_SHORT).show()
                    } //
                    else{
                        return@addOnCompleteListener
                    }
                }
                .addOnFailureListener {
                    Toast.makeText(this, "${it.message}", Toast.LENGTH_SHORT).show()
                    Log.d("MainActivity", "Failed to create user: ${it.message}")
                }
//
        }

        already_have_account_text_view.setOnClickListener{
            Log.d("MainActivity", "Try to show Login Activity")
            finish()
        }
        */
    }

    fun register(name: String, email:String, password: String){
        val url: String = "http://172.25.11.41:8000/api/register"
        val queue = Volley.newRequestQueue(this)

        val user = HashMap<String, String>()
        user["name"] = name
        user["email"] = email
        user["password"] = password

        val userJson = JSONObject(user)

        val registerRequest = JsonObjectRequest(Request.Method.POST, url, userJson,
            Listener { response ->
                try{
                    Toast.makeText(this, "Resonse: $response", Toast.LENGTH_LONG).show()
                }catch(e:Exception){
                    Toast.makeText(this, "Response Exception: ${e.message}", Toast.LENGTH_LONG).show()
                }

            }, Response.ErrorListener {
                Toast.makeText(this, "Volley error: $it", Toast.LENGTH_LONG).show()
            })

        queue.add(registerRequest)
    }



}