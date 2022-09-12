import androidx.compose.runtime.MutableState

fun <T> MutableState<T>.mutate(function: T.() -> T) {
    this.value = this.value.function()
}
