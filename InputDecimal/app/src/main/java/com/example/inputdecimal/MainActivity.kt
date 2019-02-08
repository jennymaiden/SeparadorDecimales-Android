package com.example.inputdecimal

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.w3c.dom.Text
import java.math.BigDecimal
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

class MainActivity : AppCompatActivity() {


    private lateinit var editText : EditText
    private  lateinit var btnButton: Button
    private lateinit var txtView: TextView
    private lateinit var txtCantidad : TextView
    private lateinit var  txtTotal : TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /**
         * Inicializamos los objetos de la vista*/
        editText = findViewById(R.id.txt_number)
        editText.setText("13,000")
        btnButton = findViewById(R.id.button)
        txtView = findViewById(R.id.text_view)
        txtCantidad = findViewById(R.id.text_cantidad)
        txtTotal = findViewById(R.id.text_total)

        txtCantidad.setText("2")

        editText.addTextChangedListener(onTextChangedListener())

        /**
         * Evento del boton*/
        btnButton.setOnClickListener{
            txtView.setText(String.format("Formatted number value: %s\nOriginal input: %s",
                editText.getText().toString(),
                editText.getText().toString().replace(",", "")))
        }

    }

    /**
     * Creamos la funcion del evento de edicion del input*/
    private fun onTextChangedListener(): TextWatcher{
        return object:TextWatcher {

            override fun beforeTextChanged(s:CharSequence, start:Int, count:Int, after:Int) {
            }
            override fun onTextChanged(s:CharSequence, start:Int, before:Int, count:Int) {
            }
            override fun afterTextChanged(s: Editable) {

                editText.removeTextChangedListener(this)
                try
                {
                    var originalString = s.toString()
                    var longval:BigDecimal
                    if (originalString.contains(","))
                    {
                        originalString = originalString.replace((",").toRegex(), "")
                    }
                    longval = originalString.toBigDecimal()
                    var total = longval * txtCantidad.text.toString().toBigDecimal()
                    val formatter =  NumberFormat.getNumberInstance(Locale.US).format(longval)!!
                    val formatterTotal = NumberFormat.getNumberInstance(Locale.US).format(total)!!

                    /**
                     * Editar el total segun la cantidad*/

                    txtTotal.setText(formatterTotal)
                    editText.setText(formatter)
                    editText.setSelection(editText.getText().length)
                }
                catch (nfe:NumberFormatException) {
                    nfe.printStackTrace()
                }
                editText.addTextChangedListener(this)
            }
        }

    }
}
