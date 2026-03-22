package proyecto.personal.proyectointegradorii.viewmodels.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import proyecto.personal.proyectointegradorii.data.repositories.MockUserRepository
import proyecto.personal.proyectointegradorii.data.repositories.UserRepository

class LoginViewModel(
    private val repository: UserRepository = MockUserRepository()
) : ViewModel() {

    private val _email = MutableStateFlow("")
    val email = _email.asStateFlow()
    private val _password = MutableStateFlow("")
    val password = _password.asStateFlow()
    private val _isLoggedIn = MutableStateFlow(false)
    val isLoggedIn = _isLoggedIn.asStateFlow()
    private val _emailError = MutableStateFlow<String?>(null)
    val emailError = _emailError.asStateFlow()
    private val _passwordError = MutableStateFlow<String?>(null)
    val passwordError = _passwordError.asStateFlow()
    private val _generalErrorMessage = MutableStateFlow<String?>(null)
    val generalErrorMessage = _generalErrorMessage.asStateFlow()

    fun onEmailChange(newEmail: String){
        _email.value = newEmail
        _emailError.value = null
        _generalErrorMessage.value = null
    }

    fun onPasswordChange(newPassword: String){
        _password.value = newPassword
        _passwordError.value = null
        _generalErrorMessage.value = null
    }

    fun login() {
        var isValid = true

        //Comprobaciones para el email
        if (_email.value.isBlank()) {
            _emailError.value = "El correo electrónico no puede estar vacío"
            isValid = false
        } else if (!_email.value.endsWith("@gmail.com")){
            _emailError.value = "El correo electrónico debe terminar en @gmail.com"
            isValid = false
        }

        //Comprobaciones para la contraseña
        if (_password.value.isBlank()) {
            _passwordError.value = "La contraseña no puede estar vacía"
            isValid = false
        }

        if (!isValid) return

        viewModelScope.launch {
            _isLoggedIn.value = true

            val resultado = repository.login(
                _email.value,
                _password.value
            )

            resultado.onSuccess { usuarioRegistrado ->
                println("¡Login exitoso! Bienvenido ${usuarioRegistrado.nombreCompleto}")
                _generalErrorMessage.value = null
            }.onFailure { excepcion ->
                _generalErrorMessage.value = excepcion.message ?: "Error desconocido"
            }

            _isLoggedIn.value = false
        }
    }
}