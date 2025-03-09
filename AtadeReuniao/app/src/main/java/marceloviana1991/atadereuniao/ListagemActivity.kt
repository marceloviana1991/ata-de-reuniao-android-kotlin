package marceloviana1991.atadereuniao

import android.content.Intent
import android.os.Bundle
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import marceloviana1991.atadereuniao.databinding.ActivityListagemBinding

class ListagemActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityListagemBinding.inflate(layoutInflater)
    }

    private lateinit var adapter: ArrayAdapter<AtaResponse>
    private lateinit var listaDeAtasPorMesEAno: List<AtaResponse>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val mes = intent.getIntExtra("mes", 0)
        val ano = intent.getIntExtra("ano", 0)

        val listView = binding.listView

        val mainScope = MainScope()
        mainScope.launch {
            listaDeAtasPorMesEAno = RetrofitClient.instance.listarPorMesEAno(mes, ano)
            adapter = ArrayAdapter(
                this@ListagemActivity,
                android.R.layout.simple_list_item_1,
                listaDeAtasPorMesEAno
            )
            listView.adapter = adapter
        }

        listView.onItemClickListener = AdapterView.OnItemClickListener {  _, _, position, _ ->
            val intent = Intent(this, DetalhamentoActivity::class.java)
            val id = listaDeAtasPorMesEAno[position].id
            intent.putExtra("id", id)
            startActivity(intent)
        }

    }
}