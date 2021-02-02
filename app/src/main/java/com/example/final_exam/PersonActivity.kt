package com.example.final_exam

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.firebase.auth.FirebaseAuth

class PersonActivity : AppCompatActivity() {
    private lateinit var userInfoTextView: TextView
    private lateinit var changePasswordButton: Button
    private lateinit var logoutButton: Button

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_person)

        auth = FirebaseAuth.getInstance()

        userInfoTextView = findViewById(R.id.userInfoTextView)
        changePasswordButton = findViewById(R.id.passwordChangeButton)
        logoutButton = findViewById(R.id.logoutButton)
        val navView: BottomNavigationView = findViewById(R.id.bottomNavigationView)
        val navController = findNavController(R.id.nav_host_fragment)
        val appBarConfig = AppBarConfiguration(setOf(
            R.id.navigation_home, R.id.navigation_play
        ))
        setupActionBarWithNavController(navController, appBarConfig)
        navView.setupWithNavController(navController)


        userInfoTextView.text = auth.currentUser?.uid

        logoutButton.setOnClickListener{
            auth.signOut()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        changePasswordButton.setOnClickListener{
            startActivity(Intent(this, ChangePasswordActivity::class.java))
        }
    }
}