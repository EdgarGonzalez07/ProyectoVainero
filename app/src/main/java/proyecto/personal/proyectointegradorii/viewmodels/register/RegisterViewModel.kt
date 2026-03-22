package proyecto.personal.proyectointegradorii.viewmodels.register

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import proyecto.personal.proyectointegradorii.data.repositories.MockUserRepository
import proyecto.personal.proyectointegradorii.data.repositories.UserRepository

class RegisterViewModel(
    private val repository: UserRepository = MockUserRepository()
): ViewModel() {

    private val _name = MutableStateFlow("")
    val name = _name.asStateFlow()
    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()
    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()
    private val _confirmPassword = MutableStateFlow("")
    val confirmPassword = _confirmPassword.asStateFlow()
    private val _isRegister = MutableStateFlow(false)
    val isRegister = _isRegister.asStateFlow()
    private val _errorMessage = MutableStateFlow<String?>(null)
    val errorMessage = _errorMessage.asStateFlow()
    private val _errorName = MutableStateFlow<String?>(null)
    val errorName = _errorName.asStateFlow()
    private val _errorEmail = MutableStateFlow<String?>(null)
    val errorEmail = _errorEmail.asStateFlow()
    private val _errorPassword = MutableStateFlow<String?>(null)
    val errorPassword = _errorPassword.asStateFlow()
    private val _errorConfirmPassword = MutableStateFlow<String?>(null)
    val errorConfirmPassword = _errorConfirmPassword.asStateFlow()

    fun onNameChange(newName: String){
        _name.value = newName
        _errorName.value = null
    }

    fun onEmailChange(newEmail: String){
        _email.value = newEmail
        _errorEmail.value = null
    }

    fun onPasswordChange(newPassword: String){
        _password.value = newPassword
        _errorPassword.value = null
    }

    fun onConfirmPasswordChange(newConfirmPassword: String){
        _confirmPassword.value = newConfirmPassword
        _errorConfirmPassword.value = null
    }

    fun registrar() {
        var isValid = true

        //Validacion del Nombre
        if (_name.value.isBlank()) {
            _errorName.value = "Este campo es obligatorio."
            isValid = false
        } else if (!_name.value.trim().contains(" ")) {
            _errorName.value = "El campo debe contener un nombre y un apellido"
            isValid = false
        }

        //Validacion del Email
        if (_email.value.isBlank()) {
            _errorEmail.value = "El correo electrónico es obligatorio."
            isValid = false
        } else if (!_email.value.endsWith("@gmail.com")) {
            _errorEmail.value = "El correo electrónico debe ser @gmail.com"
            isValid = false
        }

        //Validacion de la Contraseña
        if (_password.value.isBlank()) {
            _errorPassword.value = "La contraseña es obligatoria."
            isValid = false
        } else if (_password.value.length < 8) {
            _errorPassword.value = "La contraseña debe tener al menos 8 caracteres"
            isValid = false
        }

        //Validacion de la Confirmacion de la Contraseña
        if (_confirmPassword.value.isBlank()) {
            _errorConfirmPassword.value = "Confirma tu contraseña."
            isValid = false
        } else if (_password.value != _confirmPassword.value) {
            _errorConfirmPassword.value = "Las contraseñas no coinciden."
            isValid = false
        }

        if (!isValid) return

        viewModelScope.launch {
            _isRegister.value = true

            val resultado = repository.register(
                _name.value.trim(),
                _email.value,
                _password.value
            )

            resultado.onSuccess { nuevoUsuario ->
                println("¡Registro exitoso! Bienvenido ${nuevoUsuario.nombreCompleto}, tu ID es el ${nuevoUsuario.id}")
            }.onFailure { excepcion ->
                _errorMessage.value = excepcion.message ?: "Error desconocido"
            }

            _isRegister.value = false
        }
    }
}