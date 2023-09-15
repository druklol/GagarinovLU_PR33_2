package com.example.gagarinovlu_pr_33_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.TextView

class SecondActivity : AppCompatActivity() {
    private lateinit var enter_digit_et: EditText
    private lateinit var from_spinner: Spinner
    private lateinit var to_spinner: Spinner
    private lateinit var result_tw: TextView
    private lateinit var schet_btn: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        enter_digit_et = findViewById(R.id.enter_digit)
        from_spinner = findViewById(R.id.from_spinner)
        to_spinner = findViewById(R.id.to_spinner)
        result_tw = findViewById(R.id.result_tw)
        schet_btn = findViewById(R.id.schet_btn)

        val adapter = ArrayAdapter.createFromResource(
            this,
            R.array.bytesNames,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        from_spinner.adapter = adapter
        to_spinner.adapter = adapter

        to_spinner.setSelection(2)

        schet_btn.setOnClickListener {
            val intent = Intent(this, FierdActivity::class.java)

            var startBytes = from_spinner.selectedItemPosition
            var endBytes = to_spinner.selectedItemPosition
            var result = enter_digit_et.text.toString().toLong()

            intent.putExtra("startBytes", startBytes)
            intent.putExtra("endBytes", endBytes)
            intent.putExtra("enteredDigit", result)

            if (startBytes > endBytes) {
                while (startBytes != endBytes) {
                    result *= 1024
                    startBytes -= 1
                }
            } else {
                while (startBytes != endBytes) {
                    result /= 1024
                    endBytes -= 1
                }
            }



            intent.putExtra("result", result)

            startActivity(intent)
        }
    }


}