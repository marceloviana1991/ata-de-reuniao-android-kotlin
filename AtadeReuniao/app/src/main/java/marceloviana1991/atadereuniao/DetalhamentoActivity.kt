package marceloviana1991.atadereuniao

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import marceloviana1991.atadereuniao.databinding.ActivityDetalhamentoBinding

class DetalhamentoActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityDetalhamentoBinding.inflate(layoutInflater)
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

        val id = intent.getLongExtra("id", 0)

        val mainScope = MainScope()
        mainScope.launch {
            val ataResponse = RetrofitClient.instance.detalharAta(id)
            binding.numeroDaReuniao.editText?.setText(ataResponse.numeroDaReuniao.toString())
            binding.quantidadeDeParticipantes.editText?.setText(ataResponse.quantidadeDeParticipantes.toString())
            binding.quantidadeDeIngressos.editText?.setText(ataResponse.quantidadeDeIngressos.toString())
            binding.quantidadeDeVisitantes.editText?.setText(ataResponse.quantidadeDeVisitantes.toString())

            binding.reuniaoAberta.isChecked = ataResponse.aberta
            binding.reuniaoDeServico.isChecked = ataResponse.servico

            binding.secretario.editText?.setText(ataResponse.secretario)
            binding.tesoureiro.editText?.setText(ataResponse.tesoureiro)
            binding.rsg.editText?.setText(ataResponse.rsg)
            binding.rsgSuplente.editText?.setText(ataResponse.rsgSuplente)

            binding.setimaTradicao.editText?.setText(ataResponse.setimaTradicao.toString())
            binding.desepesas.editText?.setText(ataResponse.despesas.toString())
            binding.observacoes.setText(ataResponse.observacoes)
        }
    }
}