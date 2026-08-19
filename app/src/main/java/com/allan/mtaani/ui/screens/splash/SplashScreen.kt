package com.allan.mtaani.ui.screens.splash

import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.allan.mtaani.navigation.ROUT_HOME
import com.allan.mtaani.navigation.ROUT_REGISTER


@Composable
fun SplashScreen(navController: NavController) {

    // Logo animation
    val infiniteTransition = rememberInfiniteTransition(
        label = "logo"
    )

    val scale by infiniteTransition.animateFloat(
        initialValue = 0.95f,
        targetValue = 1.05f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = LinearEasing
            ),
            repeatMode = RepeatMode.Reverse
        ),
        label = "logoScale"
    )

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF0B3D2E),
                        Color(0xFF126B50),
                        Color(0xFF08251D)
                    )
                )
            )
    ) {

        // Decorative circle - top right
        Box(
            modifier = Modifier
                .size(280.dp)
                .background(
                    Color.White.copy(alpha = 0.03f),
                    CircleShape
                )
                .align(Alignment.TopEnd)
        )

        // Decorative circle - bottom left
        Box(
            modifier = Modifier
                .size(220.dp)
                .background(
                    Color.White.copy(alpha = 0.03f),
                    CircleShape
                )
                .align(Alignment.BottomStart)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {

            // Mtaani logo
            Box(
                modifier = Modifier
                    .size(125.dp)
                    .scale(scale)
                    .background(
                        Color.White.copy(alpha = 0.12f),
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "M",
                    color = Color.White,
                    fontSize = 64.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            // App name
            Text(
                text = "MTAANI",
                color = Color.White,
                fontSize = 38.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 5.sp
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Tagline
            Text(
                text = "What's happening around you?",
                color = Color.White.copy(alpha = 0.85f),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium
            )

            Spacer(modifier = Modifier.height(35.dp))

            // Loading dots
            Row(
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                LoadingDot()

                Spacer(modifier = Modifier.width(7.dp))

                LoadingDot()

                Spacer(modifier = Modifier.width(7.dp))

                LoadingDot()
            }

            Spacer(modifier = Modifier.height(40.dp))

            // CREATE ACCOUNT BUTTON
            Button(
                onClick = {
                    navController.navigate(ROUT_REGISTER)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color(0xFF126B50)
                )
            ) {

                Text(
                    text = "CREATE ACCOUNT",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
            }

            Spacer(modifier = Modifier.height(15.dp))

            // CONTINUE AS GUEST BUTTON
            Button(
                onClick = {
                    navController.navigate(ROUT_HOME)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White.copy(alpha = 0.12f),
                    contentColor = Color.White
                )
            ) {

                Text(
                    text = "CONTINUE",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
            }
        }

        // Bottom branding
        Text(
            text = "DISCOVER • VERIFY • INFORM",
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 35.dp),
            color = Color.White.copy(alpha = 0.6f),
            fontSize = 10.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.5.sp
        )
    }
}


@Composable
fun LoadingDot() {

    Box(
        modifier = Modifier
            .size(7.dp)
            .background(
                color = Color.White,
                shape = CircleShape
            )
    )
}


@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {

    SplashScreen(
        navController = rememberNavController()
    )
}