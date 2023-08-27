
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Stop
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun StopWatch(
    timeState: TimeState,
    timeText: String,
    onStartPauseClick: () -> Unit,
    onResetClick: () -> Unit
) {

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement
            .spacedBy(space = 16.dp),
        horizontalAlignment = Alignment
            .CenterHorizontally
    ) {

        Text(
            text = timeText,
            fontSize = 32.sp,
            style = MaterialTheme.typography.body2
        )

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment
                .CenterVertically,
            horizontalArrangement = Arrangement
                .Center
        ) {

            IconButton(
                onClick = {

                    onStartPauseClick()
                }
            ) {

                Icon(
                    imageVector = when(timeState) {

                        TimeState.RUNNING -> {

                            Icons.Filled.Pause
                        }

                        else -> {

                            Icons.Filled.PlayArrow
                        }
                    },
                    contentDescription = "Start Stop Time"
                )
            }

            Spacer(modifier = Modifier.width(width = 12.dp))

            IconButton(
                enabled = timeState != TimeState.RESET,
                onClick = {

                    onResetClick()
                }
            ) {

                Icon(
                    imageVector = Icons.Filled.Stop,
                    contentDescription = "Reset Time"
                )
            }
        }
    }
}