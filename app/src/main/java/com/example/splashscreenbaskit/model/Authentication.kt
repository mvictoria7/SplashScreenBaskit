import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

data class LoginRequest(val email: String, val password: String)
data class LoginResponse(val token: String, val message: String)

interface Authentication {
    @POST("login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
}
