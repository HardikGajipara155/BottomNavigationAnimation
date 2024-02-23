package com.example.bottomnavigationanim

import android.os.Bundle
import android.view.animation.OvershootInterpolator
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.view.WindowCompat
import com.example.bottomnavigationanim.anim.AnimatedNavigationBar
import com.example.bottomnavigationanim.anim.animation.balltrajectory.Parabolic
import com.example.bottomnavigationanim.anim.animation.balltrajectory.Straight
import com.example.bottomnavigationanim.anim.animation.balltrajectory.Teleport
import com.example.bottomnavigationanim.anim.animation.indendshape.Height
import com.example.bottomnavigationanim.anim.animation.indendshape.StraightIndent
import com.example.bottomnavigationanim.anim.animation.indendshape.shapeCornerRadius
import com.example.bottomnavigationanim.anim.items.dropletbutton.DropletButton
import com.example.bottomnavigationanim.anim.items.wigglebutton.WiggleButton
import com.example.bottomnavigationanim.colorButtons.ColorButton
import com.example.bottomnavigationanim.contents.colorButtons
import com.example.bottomnavigationanim.contents.dropletButtons
import com.example.bottomnavigationanim.contents.wiggleButtonItems
import com.example.bottomnavigationanim.ui.theme.*

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        setContent {
//            val systemUiController: SystemUiController = rememberSystemUiController()
//            SideEffect {
//                systemUiController.isStatusBarVisible = false
//                systemUiController.isNavigationBarVisible = false
//            }
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(ElectricViolet)
            ) {
                Column(
                    modifier = Modifier.align(Alignment.BottomCenter)
                ) {
                    ColorButtonNavBar()
                    DropletButtonNavBar()
                    WiggleButtonNavBar()
                }
            }
        }
    }
}

@Composable
fun ColorButtonNavBar() {
    var selectedItem by remember { mutableStateOf(0) }
    var prevSelectedIndex by remember { mutableStateOf(0) }

    AnimatedNavigationBar(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 60.dp)
            .height(85.dp),
        selectedIndex = selectedItem,
        ballColor = Color.White,
        cornerRadius = shapeCornerRadius(25.dp),
        ballAnimation = Straight(
            spring(dampingRatio = 0.6f, stiffness = Spring.StiffnessVeryLow)
        ),
        indentAnimation = StraightIndent(
            indentWidth = 56.dp,
            indentHeight = 15.dp,
            animationSpec = tween(1000)
        )
    ) {
        colorButtons.forEachIndexed { index, it ->
            ColorButton(
                modifier = Modifier.fillMaxSize(),
                prevSelectedIndex = prevSelectedIndex,
                selectedIndex = selectedItem,
                index = index,
                onClick = {
                    prevSelectedIndex = selectedItem
                    selectedItem = index
                },
                icon = it.icon,
                contentDescription = stringResource(id = it.description),
                animationType = it.animationType,
                background = it.animationType.background
            )
        }
    }
}

@Composable
fun DropletButtonNavBar() {
    var selectedItem by remember { mutableStateOf(0) }
    AnimatedNavigationBar(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 40.dp)
            .height(85.dp),
        selectedIndex = selectedItem,
        ballColor = Color.White,
        cornerRadius = shapeCornerRadius(25.dp),
        ballAnimation = Parabolic(tween(Duration, easing = LinearOutSlowInEasing)),
        indentAnimation = Height(
            indentWidth = 56.dp,
            indentHeight = 15.dp,
            animationSpec = tween(
                DoubleDuration,
                easing = { OvershootInterpolator().getInterpolation(it) })
        )
    ) {
        dropletButtons.forEachIndexed { index, it ->
            DropletButton(
                modifier = Modifier.fillMaxSize(),
                isSelected = selectedItem == index,
                onClick = { selectedItem = index },
                icon = it.icon,
                dropletColor = Purple,
                animationSpec = tween(durationMillis = Duration, easing = LinearEasing)
            )
        }
    }
}

@Composable
fun WiggleButtonNavBar() {
    var selectedItem by remember { mutableStateOf(0) }

    AnimatedNavigationBar(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 40.dp)
            .height(85.dp),
        selectedIndex = selectedItem,
        ballColor = Color.White,
        cornerRadius = shapeCornerRadius(25.dp),
        ballAnimation = Teleport(tween(Duration, easing = LinearEasing)),
        indentAnimation = Height(
            indentWidth = 56.dp,
            indentHeight = 15.dp,
            animationSpec = tween(
                DoubleDuration,
                easing = { OvershootInterpolator().getInterpolation(it) })
        )
    ) {
        wiggleButtonItems.forEachIndexed { index, it ->
            WiggleButton(
                modifier = Modifier.fillMaxSize(),
                isSelected = selectedItem == index,
                onClick = { selectedItem = index },
                icon = it.icon,
                backgroundIcon = it.backgroundIcon,
                wiggleColor = LightPurple,
                outlineColor = RoyalPurple,
                contentDescription = stringResource(id = it.description),
                enterExitAnimationSpec = tween(durationMillis = Duration, easing = LinearEasing),
                wiggleAnimationSpec = spring(dampingRatio = .45f, stiffness = 35f)
            )
        }
    }
}

const val Duration = 500
const val DoubleDuration = 1000

