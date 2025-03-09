package marceloviana1991.atadereuniao

import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("ata")
    suspend fun cadastrarAta(@Body ata: AtaRequest)
}