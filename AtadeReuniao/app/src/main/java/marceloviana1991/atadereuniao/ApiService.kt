package marceloviana1991.atadereuniao

import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    @POST("ata")
    suspend fun cadastrarAta(@Body ata: AtaRequest)

    @GET("ata/{mes}/{ano}")
    suspend fun listarPorMesEAno(@Path("mes") mes: Int, @Path("ano") ano: Int): List<AtaResponse>
}