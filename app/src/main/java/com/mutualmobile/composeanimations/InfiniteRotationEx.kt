package com.mutualmobile.composeanimations

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode.Reverse
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mutualmobile.composeanimations.R.drawable

@Preview
@Composable
fun InfiniteRotationEx() {
  val infiniteTransition = rememberInfiniteTransition()

  val angleRotation by infiniteTransition.animateFloat(
    initialValue = 0f, targetValue = 360f, animationSpec = infiniteRepeatable(
    animation = tween(durationMillis = 5000, delayMillis = 1000, easing = LinearEasing),
    repeatMode = Reverse
  )
  )

  Sun(angleRotation)
}

@Composable
fun Sun(angleRotation: Float) {
  Row(
    modifier = Modifier.fillMaxSize(), horizontalArrangement = Arrangement.Center,
    verticalAlignment = Alignment.CenterVertically
  ) {
    Icon(
      painter = painterResource(id = drawable.ic_sun),
      contentDescription = null,
      modifier = Modifier
        .size(300.dp, 300.dp)
        .rotate(angleRotation),
      tint = Color(0xfff9d71c)
    )
  }
}