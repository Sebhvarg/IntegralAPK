package com.cv.integral

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.core.widget.addTextChangedListener


class MainActivity : AppCompatActivity() {
    private lateinit var editText1: EditText
    private lateinit var editText2: EditText
    private lateinit var button: Button
    private lateinit var resultado: TextView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }
        editText1 = findViewById(R.id.campoA)
        editText2 = findViewById(R.id.campoN)
        button = findViewById(R.id.btnCalcular)
        resultado = findViewById(R.id.resultado)
        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Verifica si ambos campos están llenos
                val text1 = editText1.text.toString().trim()
                val text2 = editText2.text.toString().trim()
                button.isEnabled = text1.isNotEmpty() && text2.isNotEmpty()
            }

            override fun afterTextChanged(s: Editable?) {}
        }

        // Añade el TextWatcher a ambos EditText
        editText1.addTextChangedListener(textWatcher)
        editText2.addTextChangedListener(textWatcher)

        button.setOnClickListener{
            val a = editText1.text.toString().toInt()
            val n = editText2.text.toString().toInt()
            val r =obtenerValor(a,n).toString()
            resultado.text = r


        }







    }
    private fun obtenerValor(a: Int, b:Int): Double{

        val cal = Calculo()
        return cal.sumaDeRiemann(a,b)



    }


}