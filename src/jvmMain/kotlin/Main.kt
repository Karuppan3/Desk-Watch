import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application

fun main() = application {

    Window(
        title = "Desk-Watch",
        onCloseRequest = ::exitApplication
    ) {

        MaterialTheme {

            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.background,
                contentColor = MaterialTheme.colors.onBackground
            ) {

                MainScreen()
            }
        }
    }
}
