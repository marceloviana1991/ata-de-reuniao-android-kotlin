package marceloviana1991.atadereuniao

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import marceloviana1991.atadereuniao.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.listarPorMes -> {
                exibeCaixaDeDialogo()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun exibeCaixaDeDialogo() {
        AlertDialog.Builder(this)
            .setTitle("Listar atas por mês")
            .setMessage("Informe o periodo das atas que deseja listar")
            .setView(R.layout.formulario_listar)
            .setPositiveButton("CONFIRMAR") { _, _ ->

            }
            .setNegativeButton("CANCELAR") { _, _ ->

            }
            .show()
    }
}