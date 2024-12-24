package com.example.rest

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Switch
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var pastelDeChoclo: Pedido
    private lateinit var cazuela: Pedido
    private var incluirPropina: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        pastelDeChoclo = Pedido(Platillo("Pastel de Choclo", 12000))
        cazuela = Pedido(Platillo("Cazuela", 10000))

        // Inicializa las vistas
        val editTextCantidadChoclo: EditText = findViewById(R.id.editTextCantidadChoclo)
        val editTextCantidadCazuela: EditText = findViewById(R.id.editTextCantidadCazuela)
        val switchPropina: Switch = findViewById(R.id.switchPropina)
        val textViewSubtotal: TextView = findViewById(R.id.textViewSubtotal)
        val textViewPropina: TextView = findViewById(R.id.textViewPropina)
        val textViewTotal: TextView = findViewById(R.id.textViewTotal)

        // Configura los listeners de eventos
        editTextCantidadChoclo.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val cantidad = s.toString().toIntOrNull() ?: 0
                pastelDeChoclo.cantidad = cantidad
                actualizarMontos(textViewSubtotal, textViewPropina, textViewTotal)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        editTextCantidadCazuela.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                val cantidad = s.toString().toIntOrNull() ?: 0
                cazuela.cantidad = cantidad
                actualizarMontos(textViewSubtotal, textViewPropina, textViewTotal)
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })

        switchPropina.setOnCheckedChangeListener { _, isChecked ->
            incluirPropina = isChecked
            actualizarMontos(textViewSubtotal, textViewPropina, textViewTotal)
        }
    }

    private fun actualizarMontos(textViewSubtotal: TextView, textViewPropina: TextView, textViewTotal: TextView) {
        val subtotal = pastelDeChoclo.subtotal() + cazuela.subtotal()
        val propina = if (incluirPropina) subtotal * 0.1 else 0.0
        val total = subtotal + propina

        textViewSubtotal.text = "Subtotal: $subtotal"
        textViewPropina.text = "Propina: $propina"
        textViewTotal.text = "Total: $total"
    }
}