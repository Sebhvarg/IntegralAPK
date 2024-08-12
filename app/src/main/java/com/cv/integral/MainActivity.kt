package com.cv.integral

import android.graphics.Color
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import androidx.core.view.isVisible
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.Double.Companion.POSITIVE_INFINITY
import kotlin.math.round


class MainActivity : AppCompatActivity() {
    private lateinit var editText1: EditText
    private lateinit var editText2: EditText
    private lateinit var button: Button
    private lateinit var resultado: TextView
    private lateinit var infinito: Switch



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
        infinito = findViewById(R.id.infinito)
        infinito.isEnabled = false

        val textWatcher = object : TextWatcher {


            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                // Verifica si ambos campos están llenos
                val text1 = editText1.text.toString().trim()
                val text2 = editText2.text.toString().trim()


                button.isEnabled = text1.isNotEmpty() && text2.isNotEmpty()
                infinito.isEnabled = text1.isNotEmpty()
            }

            override fun afterTextChanged(s: Editable?) {}
        }
        infinito.setOnCheckedChangeListener { _, isChecked ->
            when (isChecked) {
                true -> {
                    editText2.isEnabled = false
                    button.isEnabled = true
                    button.setOnClickListener{
                        val a = editText1.text.toString().toDouble()
                            val r ="El resultado es: "+ redondearDecimal(obtenerValor(a,700.00), 5).toString()
                            resultado.setTextColor(Color.rgb(90, 55,140))
                            resultado.text = r

                        }
                }
                false -> {
                    editText2.isEnabled = true
                    button.isEnabled = false
                    button.setOnClickListener{
                        val a = editText1.text.toString().toDouble()
                        val n = editText2.text.toString().toDouble()
                        if (a > 0 && n >= 10){
                            val r ="El resultado es: "+obtenerValor(a,n).toString()
                            resultado.setTextColor(Color.rgb(90, 55,140))
                            resultado.text = r

                        }
                        if (a<=0 || n<10){
                            resultado.setTextColor(Color.RED)

                            resultado.setText("Error - Revise los valores ingresados")
                        }


                    }


                }
            }
        }

        // Añade el TextWatcher a ambos EditText
        editText1.addTextChangedListener(textWatcher)
        editText2.addTextChangedListener(textWatcher)

        button.setOnClickListener{



            val a = editText1.text.toString().toDouble()
            val n = editText2.text.toString().toDouble()
            if (a > 0 && n >= 10){
                val r ="El resultado es: "+obtenerValor(a,n).toString()
                resultado.setTextColor(Color.rgb(90, 55,140))
                resultado.text = r

            }
            if (a<=0 || n<10){
                resultado.setTextColor(Color.RED)

                resultado.setText("Error - Revise los valores ingresados")
            }


        }







    }
    private fun obtenerValor(a: Double, b:Double): Double{

        val cal = Calculo()
        return cal.sumaDeRiemann(a,b)



    }
    private fun redondearDecimal(valor: Double, decimales: Int): Double {
        val decimal = BigDecimal(valor)
        return decimal.setScale(decimales, RoundingMode.HALF_UP).toDouble()
    }


}