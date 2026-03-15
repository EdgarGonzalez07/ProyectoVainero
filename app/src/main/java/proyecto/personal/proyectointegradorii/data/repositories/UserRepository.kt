package proyecto.personal.proyectointegradorii.data.repositories

import proyecto.personal.proyectointegradorii.data.model.Usuario

interface UserRepository {
    suspend fun login(correo: String, password: String): Result<Usuario>

    suspend fun register(nombreCompleto: String, correo: String, password: String): Result<Usuario>
}