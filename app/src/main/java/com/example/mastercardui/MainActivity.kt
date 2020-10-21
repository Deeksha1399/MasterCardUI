package com.example.mastercardui

import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var addCardButton : Button
    private lateinit var updateButton : Button
    private lateinit var clearButton : Button

    private lateinit var tvCardNumber :TextView
    private lateinit var tvCardHolderName :TextView
    private lateinit var tvExpiryDate :TextView

    private lateinit var etCardNumber : EditText
    private lateinit var etCardHolderName : EditText
    private lateinit var etExpiryDate : EditText
    private lateinit var etCvv : EditText

    var length = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setUIComponents()

        clearButton.setOnClickListener {
            etCardNumber.setText("")
        }

        updateButton.setOnClickListener {
            val cardNumber = etCardNumber.text.toString()
            val cardHolderName = etCardHolderName.text.toString()
            val expiryDate = etExpiryDate.text.toString()
            val cvv = etCvv.text.toString()

            if(TextUtils.isEmpty(cardNumber) || TextUtils.isEmpty(cardHolderName) || TextUtils.isEmpty(expiryDate) || TextUtils.isEmpty(cvv)) {
                Toast.makeText(applicationContext, "Invalid input. Please Enter all fields.", Toast.LENGTH_SHORT).show()
            }
            else{
                tvCardNumber.text = cardNumber
                tvCardHolderName.text = cardHolderName
                tvExpiryDate.text = expiryDate
            }
        }

        etCardNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {

                val inputLength = etCardNumber.text.length
                if (length <= inputLength && inputLength == 4 || inputLength == 9 || inputLength == 14) {
                    val cardNumber =etCardNumber.text.toString()+" "
                    etCardNumber.setText(cardNumber)
                    val pos = etCardNumber.text.length
                    etCardNumber.setSelection(pos)
                } else if (length >= inputLength && (inputLength == 4 || inputLength == 16)) {
                    etCardNumber.setText(etCardNumber.text.toString()
                            .substring(0, etCardNumber.text
                                    .toString().length - 1))
                    val pos = etCardNumber.text.length
                    etCardNumber.setSelection(pos)
                }
                length = etCardNumber.text.length
            }
        })

        etExpiryDate.addTextChangedListener(object : TextWatcher{
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

            override fun afterTextChanged(s: Editable?) {
                val inputLength = etExpiryDate.text.toString().length
                if (length <= inputLength && inputLength == 2) {

                    //etExpiryDate.setText(etExpiryDate.getText().toString() + "/");
                    etExpiryDate.setText(String.format("%s/", etExpiryDate.text.toString()))
                    val pos = etExpiryDate.text.length
                    etExpiryDate.setSelection(pos)
                } else if (length >= inputLength && inputLength == 2) {
                    etExpiryDate.setText(etExpiryDate.text.toString()
                            .substring(0, etExpiryDate.text
                                    .toString().length - 1))
                    val pos = etExpiryDate.text.length
                    etExpiryDate.setSelection(pos)
                }
                length = etExpiryDate.text.toString().length
            }

        })

    }

    private fun setUIComponents(){
        addCardButton = findViewById(R.id.btnAddNewCard)
        updateButton = findViewById(R.id.btnUpdateProfile)
        clearButton = findViewById(R.id.clearTextButton)
        tvCardNumber = findViewById(R.id.tvCardNumber)
        tvCardHolderName = findViewById(R.id.tvCardHolderName)
        tvExpiryDate = findViewById(R.id.tvDate)
        etCardNumber = findViewById(R.id.etCardNumber)
        etCardHolderName = findViewById(R.id.etCardHolderName)
        etExpiryDate = findViewById(R.id.etExpiryDate)
        etCvv = findViewById(R.id.etCvv)
    }
}