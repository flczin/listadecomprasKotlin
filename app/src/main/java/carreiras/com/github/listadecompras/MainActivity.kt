package carreiras.com.github.listadecompras

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.RecyclerView

class MainActivity : ComponentActivity() {
    @SuppressLint("MissingInflatedId", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        val itemsAdapter = ItemsAdapter()
        recyclerView.adapter = itemsAdapter

        val button = findViewById<Button>(R.id.button)
        val editText = findViewById<EditText>(R.id.editText)
        val textView = findViewById<TextView>(R.id.titulo)
        val btnClear = findViewById<Button>(R.id.clearList)

        textView.text = "Lista de Compras";

        btnClear.setOnClickListener {
            itemsAdapter.clearList()
        }

        button.setOnClickListener {
            if (editText.text.isEmpty()) {
                editText.error = "Preencha um valor"
                return@setOnClickListener
            }

            val itemName = editText.text.toString()
            val item = ItemModel(
                name = itemName,
                onRemove = {
                    itemsAdapter.removeItem(it)
                    showRemoveDialog(itemName)
                }
            )

            itemsAdapter.addItem(item)
            showAddDialog(itemName)
            editText.text.clear()
        }
    }

    private fun showAddDialog(itemName: String) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Item '$itemName' adicionado com sucesso!")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }

    private fun showRemoveDialog(itemName: String) {
        val builder = AlertDialog.Builder(this)
        builder.setMessage("Item '$itemName' removido com sucesso!")
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
            .create()
            .show()
    }
}
