package com.example.agroshield.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.example.agroshield.ForgotPassword
import com.example.agroshield.MainActivity
import com.example.agroshield.R

class LoginActivity : AppCompatActivity() {

    private lateinit var mEmail: EditText
    private lateinit var mPass: EditText
    private lateinit var fpass: TextView
    private lateinit var signInBtn: Button
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // for changing status bar icon color
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        setContentView(R.layout.activity_login)

        mEmail = findViewById(R.id.leditTextEmail)
        mPass = findViewById(R.id.leditTextPassword)
        fpass = findViewById(R.id.forgot)
        signInBtn = findViewById(R.id.circularButton)

        mAuth = FirebaseAuth.getInstance()

//        signInBtn.setOnClickListener {
//            val intent = Intent(this@LoginActivity, MainActivity::class.java)
//            startActivity(intent)
//        }



        signInBtn.setOnClickListener {
            loginUser()
        }
//
        fpass.setOnClickListener {
            startActivity(Intent(this@LoginActivity, ForgotPassword::class.java))
        }
    }



//    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
//        super.onActivityResult(requestCode, resultCode, data)
//
//        if (requestCode == 100) {
//            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
//            try {
//                task.getResult(ApiException::class.java)
//                HomeActivity()
//            } catch (e: ApiException) {
//                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }

    private fun HomeActivity() {
        finish()
        val intent = Intent(applicationContext, MainActivity::class.java)
        startActivity(intent)
    }

    fun onLoginClick(view: View) {
        startActivity(Intent(this, RegisterActivity::class.java))
        overridePendingTransition(R.anim.slide_in_right, R.anim.stay)
    }

    private fun loginUser() {
        val email = mEmail.text.toString()
        val pass = mPass.text.toString()

        if (!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            if (!pass.isEmpty()) {
                mAuth.signInWithEmailAndPassword(email, pass)
                    .addOnSuccessListener { authResult ->
                        Toast.makeText(this@LoginActivity, "Login Successfully !!", Toast.LENGTH_SHORT).show()
                        startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                        finish()
                    }
                    .addOnFailureListener { e ->
                        Toast.makeText(this@LoginActivity, "Login Failed !!", Toast.LENGTH_SHORT).show()
                    }
            } else {
                mPass.setError("Empty Fields Are not Allowed")
            }
        } else if (email.isEmpty()) {
            mEmail.setError("Empty Fields Are not Allowed")
        } else {
            mEmail.setError("Pleas Enter Correct Email")
        }
    }
}
