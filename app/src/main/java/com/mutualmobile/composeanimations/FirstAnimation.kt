package com.mutualmobile.composeanimations

import androidx.compose.animation.core.animateIntAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.mutualmobile.composeanimations.ButtonState.IDLE
import com.mutualmobile.composeanimations.ButtonState.PRESSED
import com.mutualmobile.composeanimations.ui.theme.ComposeAnimationsTheme

@Composable
@Preview
fun MainContent() {
  ComposeAnimationsTheme {
    Surface(color = Color.White) {
      Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        AnimatedButton()
      }
    }
  }
}

@Composable
fun AnimatedButton() {
  var buttonState = remember { mutableStateOf(ButtonState.IDLE) }
  val animateWidth: Int by animateIntAsState(
    targetValue = if (buttonState.value == IDLE) {
      300
    } else {
      60
    }, animationSpec = tween(4500)
  )

  fun onClickFun() {
    if (buttonState.value == IDLE) {
      buttonState.value = PRESSED
    } else {
      buttonState.value = IDLE
    }
  }
  FavButton(animateWidth) { onClickFun() }
}

@Composable
fun FavButton(
  animateWidth: Int,
  func: () -> Unit
) {
  Button(
    onClick = { func() },
    shape = RoundedCornerShape(6.dp),
    modifier = Modifier.size(animateWidth.dp, 60.dp),
    colors = ButtonDefaults.buttonColors(backgroundColor = Color.Yellow)
  ) {
    ButtonContent()
  }
}

@Composable
fun ButtonContent() {
  Row(horizontalArrangement = Arrangement.Center, verticalAlignment = Alignment.CenterVertically) {
    Icon(
      imageVector = Icons.Default.FavoriteBorder,
      contentDescription = "Icon",
      tint = Color.Magenta
    )
    Spacer(modifier = Modifier.width(16.dp))
    Text(text = "Hello Animations", color = Color.Magenta, maxLines = 1)
  }
}

enum class ButtonState {
  IDLE,
  PRESSED
}
