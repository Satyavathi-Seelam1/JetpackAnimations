package com.mutualmobile.composeanimations

import androidx.compose.animation.core.FastOutLinearInEasing
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode.Restart
import androidx.compose.animation.core.RepeatMode.Reverse
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.google.accompanist.drawablepainter.rememberDrawablePainter
import com.mutualmobile.composeanimations.R.drawable

@Composable
@Preview
fun SunAndClouds() {

  val infiniteTransition = rememberInfiniteTransition()

  val tongueHeight by infiniteTransition.animateFloat(
    initialValue = 18f, targetValue = 10f, animationSpec = infiniteRepeatable(
    repeatMode = Reverse,
    animation = tween(durationMillis = 500, easing = FastOutLinearInEasing)
  )
  )

  val mouthHeight by infiniteTransition.animateFloat(
    initialValue = 30f, targetValue = 20f, animationSpec = infiniteRepeatable(
    repeatMode = Reverse,
    animation = tween(durationMillis = 500, easing = FastOutLinearInEasing)
  )
  )

  val tongueOffset by infiniteTransition.animateFloat(
    initialValue = 25f, targetValue = 20f, animationSpec = infiniteRepeatable(
    repeatMode = Reverse,
    animation = tween(durationMillis = 500, easing = FastOutLinearInEasing)
  )
  )

  val animateOffset by infiniteTransition.animateFloat(
    initialValue = 2f, targetValue = 14f, animationSpec = infiniteRepeatable(
    animation = tween(durationMillis = 2000, easing = LinearEasing),
    repeatMode = Reverse
  )
  )

  val shineRotation by infiniteTransition.animateFloat(
    initialValue = 0f, targetValue = 360f, animationSpec = infiniteRepeatable(
    animation = tween(durationMillis = 5000, easing = LinearEasing),
    repeatMode = Restart
  )
  )

  Main(animateOffset, tongueHeight, tongueOffset, mouthHeight, shineRotation)
}

@Composable
fun Main(
  animateOffset: Float,
  tongueHeight: Float,
  tongueOffset: Float,
  mouthHeight: Float,
  shineRotation: Float
) {
  Box(
    modifier = Modifier
      .fillMaxSize()
      .background(color = colorResource(R.color.bg))
  ) {
    SunFace(animateOffset, tongueHeight, tongueOffset, mouthHeight, shineRotation)
    Clouds()
  }
}

@Composable
fun SunFace(
  animateOffset: Float,
  tongueHeight: Float,
  tongueOffset: Float,
  mouthHeight: Float,
  shineRotation: Float
) {

  Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

    Image(
      painter = getDrawablePainter(drawableId = drawable.shine1),
      modifier = Modifier
        .size(300.dp, 300.dp)
        .rotate(shineRotation),
      contentDescription = ""
    )

    Image(
      painter = getDrawablePainter(drawableId = drawable.sun_face),
      contentDescription = ""
    )

    Row(
      modifier = Modifier
        .wrapContentSize()
        .offset(y = -20.dp)
    ) {
      SunEye(animateOffset)
      Spacer(modifier = Modifier.width(36.dp))
      SunEye(animateOffset)
    }

    SunMouth(tongueHeight, tongueOffset, mouthHeight)

  }
}

@Composable
fun SunMouth(
  tongueHeight: Float,
  tongueOffset: Float,
  mouthHeight: Float
) {
  Box(modifier = Modifier.wrapContentSize(), contentAlignment = Alignment.Center) {
    Image(
      painter = getDrawablePainter(drawableId = drawable.sun_mouth),
      modifier = Modifier
        .size(20.dp, mouthHeight.dp)
        .offset(y = 20.dp),
      contentDescription = "",
      contentScale = ContentScale.FillBounds,
    )

    Row(
      modifier = Modifier
        .wrapContentSize()
        .offset(y = tongueOffset.dp),
      horizontalArrangement = Arrangement.Center,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Image(
        painter = getDrawablePainter(drawableId = drawable.sun_tongue),
        contentDescription = null,
        modifier = Modifier.size(20.dp, tongueHeight.dp),
        contentScale = ContentScale.FillBounds,
      )
    }

  }
}

@Composable
fun SunEye(animateOffset: Float) {
  Box(
    modifier = Modifier
      .wrapContentSize()
  ) {
    Image(
      painter = getDrawablePainter(drawableId = drawable.sun_eye),
      contentDescription = ""
    )

    Image(
      painter = getDrawablePainter(drawableId = drawable.sun_eye_ball),
      modifier = Modifier.offset(animateOffset.dp, 4.dp),
      contentDescription = ""
    )
  }
}

@Composable
fun getDrawablePainter(drawableId: Int) =
  rememberDrawablePainter(drawable = ContextCompat.getDrawable(LocalContext.current, drawableId))
