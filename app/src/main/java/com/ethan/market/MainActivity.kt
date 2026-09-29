package com.ethan.market

import android.content.Intent
import android.os.Bundle
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.ethan.market.R.id
import com.google.android.material.card.MaterialCardView
import com.google.firebase.auth.FirebaseAuth

class MainActivity : AppCompatActivity() {

    lateinit var etEmail: EditText

    lateinit var etConfPass: EditText

    lateinit var etPass: EditText

    lateinit var btnSignIp: Button

    lateinit var tvRedirectLogin: TextView

    private lateinit var auth: FirebaseAuth


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Animation
        val cardView = findViewById<MaterialCardView>(id.cardContainer)
        val fadeIn = AnimationUtils.loadAnimation(this,R.anim.fade_in)
        cardView.startAnimation(fadeIn)

        //View Bindings
        etEmail = findViewById(R.id.etSEmailAddress)
        etConfPass = findViewById(R.id.etSConfPassword)
        etPass = findViewById(R.id.etSPassword)
        btnSignIp = findViewById(R.id.btnSigned)
        tvRedirectLogin = findViewById(R.id.tvRedirectLogin)

        //Initialise
        auth = FirebaseAuth.getInstance()

        //Animation
        val clickAnim = AnimationUtils.loadAnimation(this, R.anim.button_click)
        btnSignIp.setOnClickListener {
            it.startAnimation(clickAnim)
            signUpUser()
        }
    }

    private fun signUpUser()
    {
        val email = etEmail.text.toString()
        val pass = etPass.text.toString()
        val confirmPassword = etConfPass.text.toString()

        //checks if any fields are blank
        if (email.isBlank() || pass.isBlank() || confirmPassword.isBlank())
        {
            //display toast if any fields are blank
            Toast.makeText(this, "Email and Password can't be blank", Toast.LENGTH_SHORT).show()
            return
        }

        // checks if pass == confirmPass
        if (pass != confirmPassword)
        {
            Toast.makeText(this, "Password and Confirm Password do not match", Toast.LENGTH_SHORT).show()
            return
        }

        val intent = Intent(this, LoginActivity::class.java)
        auth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(this)
        {
            if (it.isSuccessful)
            {
                Toast.makeText(this, "Successfully Signed Up", Toast.LENGTH_SHORT).show()
                startActivity(intent)
            }
            else
            {
                Toast.makeText(this, "Sign up Failed!", Toast.LENGTH_SHORT).show()
            }
        }
    }

}