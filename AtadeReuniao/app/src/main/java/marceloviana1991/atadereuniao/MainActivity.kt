package marceloviana1991.atadereuniao

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import marceloviana1991.atadereuniao.databinding.ActivityMainBinding
import java.math.BigDecimal

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

        binding.buttonEnviar.setOnClickListener {
            try {
                val numeroDaReuniao = binding.numeroDaReuniao.editText?.text.toString().toIntOrNull() ?: 0
                val quantidadeDeParticipantes = binding.quantidadeDeParticipantes.editText?.text.toString().toIntOrNull() ?: 0
                val quantidadeDeIngressos = binding.quantidadeDeIngressos.editText?.text.toString().toIntOrNull() ?: 0
                val quantidadeDeVisitantes = binding.quantidadeDeVisitantes.editText?.text.toString().toIntOrNull() ?: 0
                val aberta = binding.reuniaoAberta.isChecked
                val servico = binding.reuniaoDeServico.isChecked
                val secretario = binding.secretario.editText?.text.toString().ifEmpty { "N/A" }
                val tesoureiro = binding.tesoureiro.editText?.text.toString().ifEmpty { "N/A" }
                val rsg = binding.rsg.editText?.text.toString().ifEmpty { "N/A" }
                val rsgSuplente = binding.rsgSuplente.editText?.text.toString().ifEmpty { "N/A" }
                val setimaTradicao = binding.setimaTradicao.editText?.text.toString().toBigDecimalOrNull() ?: BigDecimal.ZERO
                val despesas = binding.desepesas.editText?.text.toString().toBigDecimalOrNull() ?: BigDecimal.ZERO
                val observacoes = binding.observacoes.text.toString().ifEmpty { "Sem observações" }

                val ata = AtaRequest(
                    numeroDaReuniao,
                    quantidadeDeParticipantes,
                    quantidadeDeIngressos,
                    quantidadeDeVisitantes,
                    aberta,
                    servico,
                    secretario,
                    tesoureiro,
                    rsg,
                    rsgSuplente,
                    setimaTradicao,
                    despesas,
                    observacoes
                )

                val mainScope = MainScope()
                mainScope.launch {
                    try {
                        RetrofitClient.instance.cadastrarAta(ata)
                        Log.d("POST", "Ata cadastrada com sucesso!")
                        Toast.makeText(
                            this@MainActivity,
                            "Ata cadastrada com sucesso!",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    } catch (e: Exception) {
                        Log.e("POST", "Erro ao cadastrar ata: ${e.message}")
                        Toast.makeText(
                            this@MainActivity,
                            "Erro ao cadastrar ata!",
                            Toast.LENGTH_SHORT
                        )
                            .show()
                    }
                }
            } catch (e: Exception) {
                Log.e("INPUT", "Erro ao processar os dados: ${e.message}")
                Toast.makeText(
                    this@MainActivity,
                    "Erro de input!",
                    Toast.LENGTH_SHORT
                )
                    .show()
            }
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