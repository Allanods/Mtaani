package com.allan.mtaani.ui.screens.forgotpassword

import android.util.Patterns
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
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
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.allan.mtaani.navigation.ROUT_LOGIN
import com.google.firebase.auth.FirebaseAuth

@Composable
fun ForgotPasswordScreen(navController: NavController) {

    // Stores the email entered by the user
    var email by remember { mutableStateOf("") }

    // Stores an error message
    var errorMessage by remember { mutableStateOf("") }

    // Stores a success message
    var successMessage by remember { mutableStateOf("") }

    // Main Mtaani green color
    val mtaaniGreen = Color(0xFF0B5D45)

    // Main dark text color
    val darkText = Color(0xFF1B1B1B)

    // Hint and secondary text color
    val hintText = Color(0xFF777777)

    // Gets Firebase Authentication
    val auth = if (LocalInspectionMode.current) null else FirebaseAuth.getInstance()

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

        // Decorative circle at the top right
        Box(
            modifier = Modifier
                .size(250.dp)
                .background(
                    Color.White.copy(alpha = 0.04f),
                    CircleShape
                )
                .align(Alignment.TopEnd)
        )

        // Decorative circle at the bottom left
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

            // BACK BUTTON
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Start
            ) {

                IconButton(
                    onClick = {
                        navController.navigate(ROUT_LOGIN)
                    }
                ) {

                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            }

            Spacer(modifier = Modifier.height(5.dp))

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
                text = "Forgot Password?",
                color = Color.White,
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Reset your Mtaani account password",
                color = Color.White.copy(alpha = 0.88f),
                fontSize = 15.sp
            )

            Spacer(modifier = Modifier.height(35.dp))

            // FORGOT PASSWORD CARD
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
                    text = "Reset Password",
                    color = darkText,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = "Enter the email address connected to your account and we will send you a password reset link.",
                    color = hintText,
                    fontSize = 14.sp
                )

                Spacer(modifier = Modifier.height(24.dp))

                // EMAIL FIELD
                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        email = it
                        errorMessage = ""
                        successMessage = ""
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

                // SUCCESS MESSAGE
                if (successMessage.isNotEmpty()) {

                    Spacer(modifier = Modifier.height(12.dp))

                    Text(
                        text = successMessage,
                        color = Color(0xFF2E7D32),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth()
                    )
                }

                Spacer(modifier = Modifier.height(25.dp))

                // SEND RESET LINK BUTTON
                Button(
                    onClick = {

                        when {

                            email.isBlank() -> {

                                successMessage = ""

                                errorMessage =
                                    "Please enter your email address"
                            }

                            !Patterns.EMAIL_ADDRESS
                                .matcher(email.trim())
                                .matches() -> {

                                successMessage = ""

                                errorMessage =
                                    "Please enter a valid email address"
                            }

                            else -> {

                                errorMessage = ""
                                successMessage = ""

                                // Firebase sends the password reset email
                                auth?.sendPasswordResetEmail(
                                    email.trim()
                                )
                                    ?.addOnCompleteListener { task ->

                                        if (task.isSuccessful) {

                                            errorMessage = ""

                                            successMessage =
                                                "Password reset link sent! Check your email."

                                        } else {

                                            successMessage = ""

                                            errorMessage =
                                                task.exception?.message
                                                    ?: "Failed to send password reset email"
                                        }
                                    }
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
                        text = "SEND RESET LINK",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Send reset link"
                    )
                }

                Spacer(modifier = Modifier.height(22.dp))

                // BACK TO LOGIN
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Remember your password? ",
                        color = hintText,
                        fontSize = 14.sp
                    )

                    Text(
                        text = "Login",
                        color = mtaaniGreen,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .clickable {
                                navController.navigate(ROUT_LOGIN)
                            }
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
fun ForgotPasswordScreenPreview() {

    ForgotPasswordScreen(
        navController = rememberNavController()
    )
}