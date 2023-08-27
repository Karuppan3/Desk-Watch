import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun MainScreen() {

    val mainViewModel = remember { MainViewModel() }

    val timeState by mainViewModel.timeState.collectAsState()
    val timeText by mainViewModel.timeText.collectAsState()

    Column(
        modifier = Modifier.fillMaxSize()
            .padding(
                paddingValues = PaddingValues(
                    vertical = 16.dp,
                    horizontal = 16.dp
                )
            ),
        verticalArrangement = Arrangement
            .Center,
        horizontalAlignment = Alignment
            .CenterHorizontally
    ) {

        StopWatch(
            timeState = timeState,
            timeText = timeText,
            onStartPauseClick = mainViewModel::setIsRunning,
            onResetClick = mainViewModel::resetTime
        )
    }
}