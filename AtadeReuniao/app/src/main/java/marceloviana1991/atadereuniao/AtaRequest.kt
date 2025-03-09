package marceloviana1991.atadereuniao

import com.google.gson.annotations.SerializedName
import java.math.BigDecimal

data class AtaRequest(
    @SerializedName("numeroDaReuniao") val numeroDaReuniao: Int,
    @SerializedName("quantidadeDeParticipantes") val quantidadeDeParticipantes: Int,
    @SerializedName("quantidadeDeIngressos") val quantidadeDeIngressos: Int,
    @SerializedName("quantidadeDeVisitantes") val quantidadeDeVisitantes: Int,
    @SerializedName("aberta") val aberta: Boolean,
    @SerializedName("servico") val servico: Boolean,
    @SerializedName("secretario") val secretario: String,
    @SerializedName("tesoureiro") val tesoureiro: String,
    @SerializedName("rsg") val rsg: String,
    @SerializedName("rsgSuplente") val rsgSuplente: String,
    @SerializedName("setimaTradicao") val setimaTradicao: BigDecimal,
    @SerializedName("despesas") val despesas: BigDecimal,
    @SerializedName("observacoes") val observacoes: String,
)