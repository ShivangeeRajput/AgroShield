package com.example.agroshield.activity

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.example.agroshield.R
import com.example.agroshield.User

class RegisterActivity : AppCompatActivity() {

    private lateinit var mEmail: EditText
    private lateinit var mPass: EditText
    private lateinit var username: EditText
    private lateinit var phone: EditText
    private lateinit var signUpBtn: Button

    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        mEmail = findViewById(R.id.reditTextEmail)
        mPass = findViewById(R.id.reditTextPassword)
        username = findViewById(R.id.reditTextName)
        phone = findViewById(R.id.reditTextMobile)
        signUpBtn = findViewById(R.id.cirRegisterButton)

        mAuth = FirebaseAuth.getInstance()

        signUpBtn.setOnClickListener {
            createUser()
        }
    }

    private fun createUser() {
        val email = mEmail.text.toString().trim()
        val pass = mPass.text.toString()
        val name = username.text.toString()
        val mobile = phone.text.toString()

        if (name.isEmpty()) {
            username.error = "Enter the full name"
            username.requestFocus()
            return
        }

        if (mobile.isEmpty()) {
            phone.error = "Enter the mobile number"
            phone.requestFocus()
            return
        }

        if (email.isEmpty()) {
            mEmail.error = "Empty Fields Are not Allowed"
            mEmail.requestFocus()
            return
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmail.error = "Please enter the Email Correctly!!"
            mEmail.requestFocus()
            return
        }

        if (pass.isEmpty()) {
            mPass.error = "Empty Fields Are not Allowed"
            mPass.requestFocus()
            return
        }

        if (pass.length < 6) {
            mPass.error = "Enter password of at least 6 characters"
            return
        }

        mAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val user = User(name, mobile, email)

                FirebaseDatabase.getInstance().getReference("User")
                    .child(FirebaseAuth.getInstance().currentUser!!.uid)
                    .setValue(user).addOnCompleteListener { task ->
                        if (task.isSuccessful) {
                            Toast.makeText(this@RegisterActivity, "Registered Successfully !!", Toast.LENGTH_SHORT).show()
                            startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                            finish()
                        } else {
                            Toast.makeText(this@RegisterActivity, "Registration Error !!", Toast.LENGTH_SHORT).show()
                        }
                    }
            } else {
                Toast.makeText(this@RegisterActivity, "Registration Error !!", Toast.LENGTH_SHORT).show()
            }
        }
    }
}
