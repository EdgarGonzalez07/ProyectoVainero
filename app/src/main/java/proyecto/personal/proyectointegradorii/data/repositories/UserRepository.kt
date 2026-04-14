package proyecto.personal.proyectointegradorii.data.repositories

import proyecto.personal.proyectointegradorii.data.model.usuario.Usuario
import proyecto.personal.proyectointegradorii.data.remote.dto.usuario.ForgotPasswordRequest
import proyecto.personal.proyectointegradorii.data.remote.dto.usuario.LoginRequest
import proyecto.personal.proyectointegradorii.data.remote.dto.usuario.LoginResponse
import proyecto.personal.proyectointegradorii.data.remote.dto.usuario.RegisterRequest
import proyecto.personal.proyectointegradorii.data.remote.dto.usuario.ResetPasswordRequest
import proyecto.personal.proyectointegradorii.data.remote.network.RetrofitClient

class UserRepository {
    suspend fun login(email: String, password: String): LoginResponse? {
        return try {
            RetrofitClient.api.login(
                LoginRequest(email, password)
            )
        } catch (e: Exception) {
            e.message
            null
        }
    }

    suspend fun register(usuario: Usuario): Boolean {
        return try {
            RetrofitClient.api.register(
                RegisterRequest(
                    nombreCompleto = usuario.nombre_completo,
                    correo = usuario.correo_electronico,
                    contrasena = usuario.contrasena
                )
            )
            true
        } catch (e: Exception) {
            e.message
            false
        }
    }

    suspend fun forgotPassword(correo: String): Result<String> {
        return try {
            val response = RetrofitClient.api.forgotPassword(ForgotPasswordRequest(correo))
            if (response.isSuccessful) {
                val body = response.body()
                Result.success(body?.message ?: "Código enviado")
            } else {
                // Leer el error del body si la respuesta no es 200 OK
                val errorBody = response.errorBody()?.string()
                Result.failure(Exception(errorBody ?: "Error al enviar el código"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    suspend fun resetPassword(correo: String, codigo: String, nuevaContrasena: String): Result<String> {
        return try {
            val response = RetrofitClient.api.resetPassword(
                ResetPasswordRequest(correo, codigo, nuevaContrasena)
            )
            if (response.isSuccessful) {
                val body = response.body()
                Result.success(body?.message ?: "Contraseña actualizada")
            } else {
                val errorBody = response.errorBody()?.string()
                Result.failure(Exception(errorBody ?: "Error al actualizar la contraseña"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    // PRUEBAS
    suspend fun getCurrentUser(): LoginResponse? {
        return try {
            val response = RetrofitClient.api.getCurrentUser()
            if (response.isSuccessful) {
                response.body()
            } else null
        } catch (e: Exception) {
            e.message
            null
        }
    }
}