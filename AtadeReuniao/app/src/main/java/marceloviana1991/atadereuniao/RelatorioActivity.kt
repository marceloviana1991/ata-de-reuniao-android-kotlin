package marceloviana1991.atadereuniao

import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import marceloviana1991.atadereuniao.databinding.ActivityRelatorioBinding

class RelatorioActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityRelatorioBinding.inflate(layoutInflater)
    }
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

        val mainScope = MainScope()
        mainScope.launch {
            listaDeAtasPorMesEAno = RetrofitClient.instance.listarPorMesEAno(mes, ano)
            binding.totalDeParticipantes.editText?.setText(
                listaDeAtasPorMesEAno.sumOf { it.quantidadeDeParticipantes }.toString()
            )
            binding.totalDeVisitantes.editText?.setText(
                listaDeAtasPorMesEAno.sumOf { it.quantidadeDeVisitantes }.toString()
            )
            binding.secretario.editText?.setText(listaDeAtasPorMesEAno.last().secretario)
            binding.tesoureiro.editText?.setText(listaDeAtasPorMesEAno.last().tesoureiro)
            binding.rsg.editText?.setText(listaDeAtasPorMesEAno.last().rsg)
            binding.rsgSuplente.editText?.setText(listaDeAtasPorMesEAno.last().rsgSuplente)
            binding.totalDeSetimaTradicao.editText?.setText(
                listaDeAtasPorMesEAno.sumOf { it.setimaTradicao }.toString()
            )
            binding.totalDeDesepesas.editText?.setText(
                listaDeAtasPorMesEAno.sumOf { it.despesas }.toString()
            )
        }

    }
}