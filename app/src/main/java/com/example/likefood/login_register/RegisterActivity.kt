package com.example.likefood.login_register

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.likefood.FoodActivity
import com.example.likefood.R
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity() {

    var auth = FirebaseAuth.getInstance()
    var user: FirebaseUser? = null
    var database = FirebaseDatabase.getInstance().reference.child("users")

    val clickListener = View.OnClickListener { view ->
        when (view.getId()) {
            R.id.textview_loginHere -> goToLogin()
            R.id.button_register -> registerUser()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        textview_loginHere.setOnClickListener(clickListener)
        button_register.setOnClickListener(clickListener)

        checkIfUserLoggedIn()
    }

    fun goToLogin() {
        val intent = Intent(this@RegisterActivity, LoginActivity::class.java)
        startActivity(intent)
    }

    fun registerUser() {
        val username = edittext_username_register.text.toString()
        val email = edittext_email_register.text.toString()
        val password = edittext_password_register.text.toString()

        if (email.isNotEmpty() && password.isNotEmpty() && username.isNotEmpty()) {
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, OnCompleteListener { task ->
                    if (task.isSuccessful) {
                        user = auth.currentUser
                        database.child(user!!.uid).child("username").setValue(username)
                        database.child(user!!.uid).child("email").setValue(email)
                        database.child(user!!.uid).child("userID").setValue(user!!.uid)

                        var intent = Intent(this@RegisterActivity, FoodActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(
                            this@RegisterActivity,
                            "Error: ${task.exception?.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
        } else {
            Toast.makeText(
                this@RegisterActivity,
                "Please enter in all fields",
                Toast.LENGTH_SHORT
            ).show()
        }

    }

    fun checkIfUserLoggedIn() {
        user = auth.currentUser
        if (user != null) {
            var intent = Intent(this@RegisterActivity, FoodActivity::class.java)
            startActivity(intent)
        }
    }

}
