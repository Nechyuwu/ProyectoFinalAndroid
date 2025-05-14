import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyectofinal.ApiAdvice.Advice
import com.example.proyectofinal.ApiAdvice.RetrofitClient
import kotlinx.coroutines.launch
import retrofit2.Response

class AdviceViewModel : ViewModel() {

    private val _advice = mutableStateOf<String?>(null)
    val advice: State<String?> = _advice

    fun fetchRandomAdvice() {
        viewModelScope.launch {
            val response: Response<Advice> = RetrofitClient.adviceApiService.getRandomAdvice()
            if (response.isSuccessful) {
                _advice.value = response.body()?.slip?.advice
            } else {
                _advice.value = "No se pudo obtener el consejo."
            }
        }
    }
}