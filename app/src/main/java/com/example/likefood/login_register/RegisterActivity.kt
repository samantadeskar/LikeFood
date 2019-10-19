package com.example.likefood.login_register

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.likefood.R
import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), View.OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        textview_loginHere.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
        finish()
    }

}
