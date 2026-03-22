package proyecto.personal.proyectointegradorii.data.remote.api

import proyecto.personal.proyectointegradorii.data.remote.dto.LoginRequest
import proyecto.personal.proyectointegradorii.data.remote.dto.RegisterRequest
import proyecto.personal.proyectointegradorii.data.model.Usuario
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {

    @POST("api/auth/login")
    suspend fun login(@Body request: LoginRequest): Usuario

    @POST("api/auth/register")
    suspend fun register(@Body request: RegisterRequest): Usuario
}