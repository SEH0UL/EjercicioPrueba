package com.sehoul.ejercicioprueba.PruebaExamen

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.sehoul.ejercicioprueba.R

class PruebaExamen_Main : AppCompatActivity() {

    // Lista de los TextView en el diseño
    private lateinit var textViewList: List<TextView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_prueba_examen_main)

        // Inicializar componentes
        initComponents()

        // Configurar listeners de clic
        initListeners()
    }

    private fun initComponents() {
        // Encuentra todos los TextView del diseño
        textViewList = listOf(
            findViewById(R.id.tv1),
            findViewById(R.id.tv2),
            findViewById(R.id.tv3),
            findViewById(R.id.tv4),
            findViewById(R.id.tv5),
            findViewById(R.id.tv6),
            findViewById(R.id.tv7),
            findViewById(R.id.tv8),
            findViewById(R.id.tv9),
            findViewById(R.id.tv10)
        )
    }

    private fun initListeners() {
        // Configura un listener de clic para cada TextView
        textViewList.forEach { textView ->
            textView.setOnClickListener {
                showColorDialog(textView)
            }
        }
    }

    /**
     * Muestra el diálogo de selección de color.
     * @param targetView El TextView al que se aplicará el color seleccionado.
     */
    private fun showColorDialog(targetView: TextView) {
        // Inflar el diseño del diálogo
        val dialogView = LayoutInflater.from(this).inflate(R.layout.dialog_pruebaexamen, null)
        val radioGroup = dialogView.findViewById<RadioGroup>(R.id.rgCategories)
        val btnChangeColor = dialogView.findViewById<Button>(R.id.btnChangeColor)

        // Crear y mostrar el diálogo
        val dialog = AlertDialog.Builder(this)
            .setView(dialogView)
            .create()

        btnChangeColor.setOnClickListener {
            // Obtener el RadioButton seleccionado
            val selectedRadioButtonId = radioGroup.checkedRadioButtonId
            if (selectedRadioButtonId != -1) {
                val selectedRadioButton = dialogView.findViewById<RadioButton>(selectedRadioButtonId)
                val selectedColor = getColorFromRadioButton(selectedRadioButton.id)

                // Cambiar el color de fondo del TextView seleccionado
                targetView.setBackgroundColor(selectedColor)

                // Cambiar el texto del TextView al nombre del color
                targetView.text = selectedRadioButton.text
            }
            dialog.dismiss()
        }


        dialog.show()
    }

    /**
     * Devuelve el color correspondiente según el RadioButton seleccionado.
     * @param radioButtonId ID del RadioButton seleccionado.
     * @return Color entero correspondiente.
     */
    private fun getColorFromRadioButton(radioButtonId: Int): Int {
        return when (radioButtonId) {
            R.id.rbBlanco -> getColor(R.color.white)
            R.id.rbRojo -> getColor(R.color.red)
            R.id.rbNaranja -> getColor(R.color.orange)
            R.id.rbAmarillo -> getColor(R.color.yellow)
            R.id.rbVerde -> getColor(R.color.green)
            R.id.rbCian -> getColor(R.color.cyan)
            R.id.rbAzul -> getColor(R.color.blue)
            R.id.rbVioleta -> getColor(R.color.violet)
            R.id.rbGris -> getColor(R.color.gray)
            R.id.rbNegro -> getColor(R.color.black)
            else -> getColor(R.color.white) // Default: blanco
        }
    }
}
