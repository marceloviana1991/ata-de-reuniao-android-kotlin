package marceloviana1991.atadereuniao

import java.math.BigDecimal

data class AtaResponse(
    val id: Long,
    val numeroDaReuniao: Int,
    val data: String,
    val quantidadeDeParticipantes: Int,
    val quantidadeDeIngressos: Int,
    val quantidadeDeVisitantes: Int,
    val aberta: Boolean,
    val servico: Boolean,
    val secretario: String,
    val tesoureiro: String,
    val rsg: String,
    val rsgSuplente: String,
    val setimaTradicao: BigDecimal,
    val despesas: BigDecimal,
    val observacoes: String,
) {
    override fun toString(): String {
        return numeroDaReuniao.toString() + " - " + data
    }
}
