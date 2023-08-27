import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.*
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import kotlin.time.Duration.Companion.milliseconds

@OptIn(ExperimentalCoroutinesApi::class)
class MainViewModel {

    private val _elapsedTime = MutableStateFlow(
        value = 0L
    )

    private val _timeState = MutableStateFlow(
        value = TimeState.RESET
    )

    val timeState = _timeState.asStateFlow()

    private val timeScope = CoroutineScope(context = Dispatchers.Default)

    private val timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss:SSS")

    val timeText = _elapsedTime.map { timeMillis: Long ->

        LocalTime.ofNanoOfDay(
            timeMillis * 1_000_000
        ).format(timeFormatter)
    }.stateIn(
        scope = timeScope,
        started = SharingStarted.WhileSubscribed(stopTimeoutMillis = 5000),
        initialValue = "00:00:00:000"
    )

    init {

        _timeState.flatMapLatest { state: TimeState ->

            getTime(isRun = state == TimeState.RUNNING)
        }.onEach { timeDifference: Long ->

            _elapsedTime.update { time: Long ->

                time + timeDifference
            }
        }.launchIn(scope = timeScope)
    }

    private fun getTime(
        isRun: Boolean
    ) : Flow<Long> {

        return flow {

            var startTime = System.currentTimeMillis()

            while (isRun) {

                val currentTime = System.currentTimeMillis()

                val timeDifference = if (currentTime > startTime) {

                    currentTime - startTime
                } else {

                    0L
                }

                emit(value = timeDifference)

                startTime = System.currentTimeMillis()

                delay(duration = 10.milliseconds)
            }
        }
    }

    fun setIsRunning() {

        when(timeState.value) {

            TimeState.RUNNING -> {

                _timeState.update {

                    TimeState.PAUSED
                }
            }

            TimeState.PAUSED,

            TimeState.RESET -> {

                _timeState.update {

                    TimeState.RUNNING
                }
            }
        }
    }

    fun resetTime() {

        _timeState.update {

            TimeState.RESET
        }

        _elapsedTime.update {

            0L
        }
    }
}