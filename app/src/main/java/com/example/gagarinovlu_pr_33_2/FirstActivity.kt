package com.example.gagarinovlu_pr_33_2

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class FirstActivity : AppCompatActivity() {
    private lateinit var start_btn: ImageButton
    private lateinit var password_ed: EditText
    private lateinit var login_ed: EditText
    val APP_PREFERENCES = "mysettings"



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        start_btn = findViewById(R.id.start_btn)
        password_ed = findViewById(R.id.password_ed)
        login_ed = findViewById(R.id.login_ed)

        val sp = getSharedPreferences(
            APP_PREFERENCES,
            MODE_PRIVATE
        )




        start_btn.setOnClickListener {
            if (password_ed.text.toString() == "" || login_ed.text.toString() == "") {
                Toast.makeText(applicationContext, R.string.error_empty_fields, Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            val hasVisited = sp.getBoolean("hasVisited", false)

            if (!hasVisited) {
                val editor = sp.edit()
                editor.putBoolean("hasVisited", true)
                editor.putString("Login", login_ed.text.toString())
                editor.putString("Password", password_ed.text.toString())
                editor.apply()
            }

            val sharedLogin = sp.getString("Login","")
            val sharedPassword = sp.getString("Password","")

            if(password_ed.text.toString() != sharedPassword || login_ed.text.toString() != sharedLogin)
            {
                Toast.makeText(applicationContext, R.string.error_not_correct_fields, Toast.LENGTH_SHORT)
                    .show()
                return@setOnClickListener
            }

            val intent = Intent(this,SecondActivity::class.java)
            startActivity(intent)
        }
    }


}