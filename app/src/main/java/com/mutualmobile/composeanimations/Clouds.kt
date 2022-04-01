package com.mutualmobile.composeanimations

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode.Restart
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mutualmobile.composeanimations.R.drawable
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
@Preview
fun Clouds() {

  val widthDP = LocalConfiguration.current.screenWidthDp

  val could1 = widthDP / 5f
  val cloud1XPositions =
    mutableListOf(0f, could1, (could1 * 2), (could1 * 3), (could1 * 4), (could1 * 5))

  val could1KeyFrames = keyframes<Float> {
    durationMillis = 5000
    cloud1XPositions[0] at 0 with LinearEasing
    cloud1XPositions[1] at 1000 with LinearEasing
    cloud1XPositions[2] at 2000 with LinearEasing
    cloud1XPositions[3] at 3000 with LinearEasing
    cloud1XPositions[4] at 4000 with LinearEasing
    cloud1XPositions[5] at 5000 with LinearEasing
  }

  val cloud1Animate = remember { mutableStateOf(true) }

  fun onClick() {
    cloud1Animate.value = !cloud1Animate.value
  }

  val cloud1Animation by animateFloatAsState(
    targetValue = if (cloud1Animate.value) cloud1XPositions[0] else cloud1XPositions[4],
    animationSpec = could1KeyFrames
  )

  val coroutineScope = rememberCoroutineScope()

  LaunchedEffect(key1 = true) {
    coroutineScope.launch {
      while (true) {
        onClick()
        delay(5000)
      }
    }
  }

  val infiniteTransition = rememberInfiniteTransition()

  val cloud2Animation by infiniteTransition.animateFloat(
    initialValue = 0f, targetValue = widthDP.toFloat(),
    animationSpec = infiniteRepeatable(
      animation = tween(durationMillis = 6000, easing = LinearEasing),
      repeatMode = Restart
    )
  )

  val cloud3Animation by infiniteTransition.animateFloat(
    initialValue = 0f, targetValue = widthDP.toFloat(),
    animationSpec = infiniteRepeatable(
      animation = tween(durationMillis = 4000, easing = LinearEasing),
      repeatMode = Restart
    )
  )

  val cloud4Animation by infiniteTransition.animateFloat(
    initialValue = 0f, targetValue = widthDP.toFloat(),
    animationSpec = infiniteRepeatable(
      animation = tween(durationMillis = 7000, easing = LinearEasing),
      repeatMode = Restart
    )
  )

  MainCloud(cloud1Animation, cloud2Animation, cloud3Animation, cloud4Animation)
}

@Composable

fun MainCloud(
  cloud1Animation: Float,
  cloud2Animation: Float,
  cloud3Animation: Float,
  cloud4Animation: Float
) {
  Column(
    modifier = Modifier
      .fillMaxSize()
      .background(color = Color.Transparent), verticalArrangement = Arrangement.SpaceAround
  ) {
    Cloud(cloud1Animation, drawable.cloud5)
    Cloud(cloudAnimation = cloud2Animation, drawableId = drawable.cloud3)
    Spacer(modifier = Modifier.height(100.dp))
    Cloud(cloudAnimation = cloud4Animation, drawableId = drawable.cloud4)
    Cloud(cloudAnimation = cloud3Animation, drawableId = drawable.cloud5)
  }
}

@Composable
fun Cloud(
  cloudAnimation: Float,
  drawableId: Int
) {
  Image(
    painter = getDrawablePainter(drawableId = drawableId),
    modifier = Modifier
      .size(150.dp, 100.dp)
      .offset(x = cloudAnimation.dp),
    contentDescription = "",
    contentScale = ContentScale.FillBounds,
  )
}
