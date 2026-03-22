package proyecto.personal.proyectointegradorii.data.repositories

import kotlinx.coroutines.delay
import proyecto.personal.proyectointegradorii.data.model.Usuario

interface UserRepository {
    suspend fun login(correo: String, password: String): Result<Usuario>
    suspend fun register(nombreCompleto: String, correo: String, password: String): Result<Usuario>
}

class MockUserRepository : UserRepository {
    override suspend fun login(correo: String, password: String): Result<Usuario> {
        delay(2000)

        return if (correo == "gama123@gmail.com" && password == "gama123") {
            val usuarioFalse = Usuario(1, "Gamaliel Leonel", correo)
            Result.success(usuarioFalse)
        } else {
            Result.failure(Exception("Credenciales incorrectas"))
        }
    }

    override suspend fun register(
        nombreCompleto: String,
        correo: String,
        password: String
    ): Result<Usuario> {
        delay(2000)

        val nuevoUsuario = Usuario(
            id = (2..1000).random(),
            nombreCompleto,
            correo
        )
        return Result.success(nuevoUsuario)
    }
}