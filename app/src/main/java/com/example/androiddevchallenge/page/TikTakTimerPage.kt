package com.example.androiddevchallenge.page

import android.util.Log
import android.widget.Space
import android.widget.Toast
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.*
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathEffect
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.lerp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.getStatusBarHeight
import com.example.androiddevchallenge.runOnUiThread
import com.example.androiddevchallenge.ui.theme.*
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.TickerMode
import kotlinx.coroutines.channels.ticker
import java.util.*

private var sJob: Job? = null

@Composable
fun TimerPage() {

    var remainTime = 0L

    val timeState = mutableStateOf(remainTime)
    val totalTimeState = mutableStateOf(0L)

    rememberUpdatedState(newValue = timeState)
    rememberUpdatedState(newValue = totalTimeState)

    Surface(modifier = Modifier.fillMaxSize()) {
        Scaffold(
            topBar = {
                TopAppBar(title = { Text(text = "CountDownTimer", style = MaterialTheme.typography.h6) },
                backgroundColor = Color.White)
            },
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xF8F8F8))
                .padding(top = getStatusBarHeight(LocalContext.current)),
        ) {
            Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = CenterHorizontally, verticalArrangement = Arrangement.Center) {
                CountDownCustomTimer(
                    remainingTimeSeconds = timeState.value,
                    totalTimeSeconds = totalTimeState.value
                )
                Spacer(modifier = Modifier.padding(top = 20.dp))
                InputArea(totalTimeState, timeState)
            }
        }
    }
}

@Composable
fun CountDownCustomTimer(remainingTimeSeconds: Long, totalTimeSeconds: Long) {
    val printableMinutes = remainingTimeSeconds / 60
    val printableSeconds = remainingTimeSeconds - printableMinutes * 60
    val colorState = remember { mutableStateOf(Color(0xe9c46a)) }

    Box(
        modifier = Modifier
            .size(320.dp)
            .clip(CircleShape),
        contentAlignment = Alignment.Center,
    ) {
        CountdownArc(totalTimeSeconds, remainingTimeSeconds) { currentColor ->
            colorState.value = currentColor
        }

        val animateBubbleSize by animateIntAsState(
            targetValue = if (remainingTimeSeconds % 2 == 0L) 220 else 240,
            animationSpec = infiniteRepeatable(tween(250), repeatMode = RepeatMode.Reverse)
        )

        Box(
            modifier = Modifier
                .size(animateBubbleSize.dp)
                .clip(CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Column(horizontalAlignment = CenterHorizontally) {
                val timeText = with(AnnotatedString.Builder("$printableMinutes")) {
                    pushStyle(SpanStyle(fontSize = 24.sp))
                    append("m")
                    pop()
                    append(" $printableSeconds")
                    pushStyle(SpanStyle(fontSize = 24.sp))
                    append("s")
                    toAnnotatedString()
                }
                Text(
                    timeText,
                    style = MaterialTheme.typography.h3.copy(fontWeight = FontWeight.Black),
                    color = textColorBlack
                )
                Text("剩余", style = MaterialTheme.typography.button, color = textColorBlack)
            }
        }
    }
}

@Composable
fun CountdownArc(
    totalTimeSeconds: Long,
    remainingTimeSeconds: Long,
    onCurrentColorUpdate: (Color) -> Unit
) {
    var remainingPercent = 0F
    if (totalTimeSeconds > 0L) {
        remainingPercent = remainingTimeSeconds.toFloat() / totalTimeSeconds
    }
    val animatedSweep by animateFloatAsState(targetValue = remainingPercent * 360f)

    val arcWidth = with(LocalDensity.current) { 16.dp.toPx() }

    val currentColor: Color = lerp(secondaryLight, secondary, 1 - remainingPercent)
    onCurrentColorUpdate(currentColor)
    Canvas(
        modifier = Modifier
            .aspectRatio(1f)
            .padding(8.dp),
        onDraw = {
            drawArc(
                brush = Brush.sweepGradient(listOf(backgroundColorDeepAlpha, backgroundColorLightAlpha)),
                startAngle = 0f,
                sweepAngle = 360f,
                useCenter = false,
                style = Stroke(
                    width = arcWidth,
                    pathEffect = PathEffect.dashPathEffect(floatArrayOf(5f, 5f))
                )
            )

            if (totalTimeSeconds > 0L) {
                drawArc(
                    brush = Brush.sweepGradient(listOf(secondary, secondaryLight)),
                    startAngle = 0f,
                    sweepAngle = animatedSweep,
                    useCenter = false,
                    style = Stroke(
                        width = arcWidth,
                        pathEffect = PathEffect.dashPathEffect(
                            floatArrayOf(5f, 5f)
                        )
                    )
                )
            }
        }
    )
}

@Composable
private fun InputArea(totalTimeSeconds: MutableState<Long>, state: MutableState<Long>) {
    val inputState = mutableStateOf("")
    val context = LocalContext.current
    Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = CenterHorizontally) {
        Input(state = inputState) {
            inputState.value = it
        }
        Spacer(modifier = Modifier.padding(top = 20.dp))
        IconButton(modifier = Modifier
            .width(40.dp)
            .height(40.dp)
            .clip(CircleShape)
            .background(secondary)
            , onClick = {
                totalTimeSeconds.value = convertToLong(inputState.value)
                // 清空value
                inputState.value = ""
                doTimerTask(totalTimeSeconds.value, state) {
                    runOnUiThread {
                        Toast.makeText(context, "Time's up!", Toast.LENGTH_SHORT).show()
                    }
                }
        }) {
            Icon(painter = painterResource(id = android.R.drawable.ic_media_play), contentDescription = "play")
        }

    }

}

@Composable
private fun Input(state: MutableState<String>, onInputChanged: (String) -> Unit) {
    Box(modifier = Modifier
        .fillMaxWidth(), contentAlignment = Alignment.Center) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            TextField(modifier = Modifier
                .width(200.dp)
                .height(50.dp)
                .background(Color.Transparent, shape = RoundedCornerShape(8.dp)),
                value = state.value, onValueChange = onInputChanged, keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
            )
            Spacer(modifier = Modifier.padding(start = 10.dp))
            Text(text = "秒")
        }
    }
}

private fun convertToTime(text: String): String {
    if (text.isEmpty()) return ""
    val result = text.replace("\\D".toRegex(), "")
    var longValue = result.toLong()
    if (longValue > 300L) {
        // 大于300s 则默认选择300s
        longValue = 300L
    }
    val minutes = longValue / 60
    val seconds = longValue % (minutes * 60)
    return "$minutes:$seconds"
}

private fun convertToLong(text: String): Long {
    if (text.isEmpty()) return 0L
    val result = text.replace("\\D".toRegex(), "")
    var longValue = result.toLong()
    if (longValue > 300L) {
        // 大于300s 则默认选择300s
        longValue = 300L
    }
    return longValue
}

private fun doTimerTask(totalTimeSeconds: Long, state: MutableState<Long>, endAction: () -> Unit) {
    val interval = 1000L
    if (sJob != null) {
        sJob?.cancel()
    }
    sJob = GlobalScope.launch {
        repeat(totalTimeSeconds.toInt() + 1) { seconds ->
            state.value = totalTimeSeconds - seconds.toLong()
            delay(interval)
        }
        endAction.invoke()
    }
}