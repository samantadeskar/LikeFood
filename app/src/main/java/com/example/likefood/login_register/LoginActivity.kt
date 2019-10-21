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
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    var auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        button_login.setOnClickListener(clickListener)
    }

    val clickListener = View.OnClickListener { view ->
        when (view.getId()) {
            R.id.button_login -> loginUser()
        }
    }

    fun loginUser() {
        val email = edittext_email_login.text.toString()
        val password = edittext_password_login.text.toString()
        if (password.isNotEmpty() && email.isNotEmpty()) {
            auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, OnCompleteListener { task ->
                    if (task.isSuccessful) {
                        var intent = Intent(this@LoginActivity, FoodActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(
                            this@LoginActivity,
                            "Error: ${task.exception?.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                })
        }else{
            Toast.makeText(
                this@LoginActivity,
                "Please input all fields",
                Toast.LENGTH_SHORT
            ).show()
        }
    }
}
