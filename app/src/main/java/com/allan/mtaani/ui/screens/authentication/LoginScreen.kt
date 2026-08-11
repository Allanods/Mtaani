package com.allan.mtaani.ui.screens.authentication

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun LoginScreen(navController: NavController) {

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordVisible by remember { mutableStateOf(false) }

    var errorMessage by remember { mutableStateOf("") }

    val mtaaniGreen = Color(0xFF0B5D45)
    val darkText = Color(0xFF1B1B1B)
    val hintText = Color(0xFF777777)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(
                        Color(0xFF0B5D45),
                        Color(0xFF78B7A6),
                        Color.White
                    )
                )
            )
    ) {

        // Decorative circles
        Box(
            modifier = Modifier
                .size(250.dp)
                .background(
                    Color.White.copy(alpha = 0.04f),
                    CircleShape
                )
                .align(Alignment.TopEnd)
        )

        Box(
            modifier = Modifier
                .size(190.dp)
                .background(
                    Color.White.copy(alpha = 0.04f),
                    CircleShape
                )
                .align(Alignment.BottomStart)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(
                    start = 24.dp,
                    end = 24.dp,
                    top = 45.dp,
                    bottom = 40.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // MTAANI LOGO
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(
                        Color.White.copy(alpha = 0.16f),
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {

                Text(
                    text = "M",
                    color = Color.White,
                    fontSize = 52.sp,
                    fontWeight = FontWeight.ExtraBold
                )
            }

            Spacer(modifier = Modifier.height(22.dp))

            Text(
                text = "Welcome Back",
                color = Color.White,
                fontSize = 34.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Stay connected to your community",
                color = Color.White.copy(alpha = 0.88f),
                fontSize = 15.sp
            )

            Spacer(modifier = Modifier.height(35.dp))

            // LOGIN CARD
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color.White,
                        RoundedCornerShape(35.dp)
                    )
                    .padding(26.dp),
                horizontalAlignment = Alignment.Start
            ) {

                Text(
                    text = "Login",
                    color = darkText,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Enter your details to continue",
                    color = hintText,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                // EMAIL
                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                        errorMessage = ""
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    label = {
                        Text("Email")
                    },
                    placeholder = {
                        Text("Enter your email")
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Email,
                            contentDescription = "Email"
                        )
                    },
                    shape = RoundedCornerShape(18.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = darkText,
                        unfocusedTextColor = darkText,
                        focusedPlaceholderColor = hintText,
                        unfocusedPlaceholderColor = hintText,
                        focusedLabelColor = mtaaniGreen,
                        unfocusedLabelColor = hintText,
                        focusedBorderColor = mtaaniGreen,
                        unfocusedBorderColor = Color(0xFF999999),
                        focusedLeadingIconColor = mtaaniGreen,
                        unfocusedLeadingIconColor = Color(0xFF777777),
                        cursorColor = mtaaniGreen
                    )
                )

                Spacer(modifier = Modifier.height(16.dp))

                // PASSWORD
                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        password = it
                        errorMessage = ""
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    label = {
                        Text("Password")
                    },
                    placeholder = {
                        Text("Enter your password")
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Password"
                        )
                    },
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                passwordVisible = !passwordVisible
                            }
                        ) {
                            Icon(
                                imageVector =
                                    if (passwordVisible)
                                        Icons.Default.VisibilityOff
                                    else
                                        Icons.Default.Visibility,
                                contentDescription = "Show password"
                            )
                        }
                    },
                    visualTransformation =
                        if (passwordVisible)
                            VisualTransformation.None
                        else
                            PasswordVisualTransformation(),
                    shape = RoundedCornerShape(18.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = darkText,
                        unfocusedTextColor = darkText,
                        focusedPlaceholderColor = hintText,
                        unfocusedPlaceholderColor = hintText,
                        focusedLabelColor = mtaaniGreen,
                        unfocusedLabelColor = hintText,
                        focusedBorderColor = mtaaniGreen,
                        unfocusedBorderColor = Color(0xFF999999),
                        focusedLeadingIconColor = mtaaniGreen,
                        unfocusedLeadingIconColor = Color(0xFF777777),
                        focusedTrailingIconColor = mtaaniGreen,
                        unfocusedTrailingIconColor = Color(0xFF777777),
                        cursorColor = mtaaniGreen
                    )
                )

                Spacer(modifier = Modifier.height(10.dp))

                // FORGOT PASSWORD
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {

                    Text(
                        text = "Forgot password?",
                        color = mtaaniGreen,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold
                    )
                }

                // ERROR MESSAGE
                if (errorMessage.isNotEmpty()) {

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = errorMessage,
                        color = Color(0xFFD32F2F),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(25.dp))

                // LOGIN BUTTON
                Button(
                    onClick = {

                        when {

                            email.isBlank() || password.isBlank() -> {
                                errorMessage =
                                    "Please fill in all fields"
                            }

                            password.length < 6 -> {
                                errorMessage =
                                    "Password must be at least 6 characters"
                            }

                            else -> {
                                errorMessage = ""

                                // Login functionality will be added here
                            }
                        }
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(58.dp),
                    shape = RoundedCornerShape(18.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = mtaaniGreen,
                        contentColor = Color.White
                    )
                ) {

                    Text(
                        text = "LOGIN",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Login"
                    )
                }

                Spacer(modifier = Modifier.height(22.dp))

                // REGISTER
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Don't have an account? ",
                        color = hintText,
                        fontSize = 14.sp
                    )

                    Text(
                        text = "Create Account",
                        color = mtaaniGreen,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            Text(
                text = "MTAANI • YOUR COMMUNITY • YOUR VOICE",
                color = Color.White.copy(alpha = 0.65f),
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {

    LoginScreen(
        navController = rememberNavController()
    )
}