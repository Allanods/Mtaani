package com.allan.mtaani.ui.screens.authentication

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
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
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
import com.allan.mtaani.navigation.ROUT_LOGIN

@Composable
fun RegisterScreen(navController: NavController) {

    var fullName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmPassword by remember { mutableStateOf("") }

    var passwordVisible by remember { mutableStateOf(false) }
    var confirmPasswordVisible by remember { mutableStateOf(false) }

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

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(
                    start = 24.dp,
                    end = 24.dp,
                    top = 40.dp,
                    bottom = 40.dp
                ),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            // Mtaani Logo
            Box(
                modifier = Modifier
                    .size(100.dp)
                    .background(
                        Color.White.copy(alpha = 0.18f),
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

            Spacer(modifier = Modifier.height(20.dp))

            Text(
                text = "Create Account",
                color = Color.White,
                fontSize = 34.sp,
                fontWeight = FontWeight.ExtraBold
            )

            Spacer(modifier = Modifier.height(6.dp))

            Text(
                text = "Join your local community",
                color = Color.White.copy(alpha = 0.9f),
                fontSize = 16.sp
            )

            Spacer(modifier = Modifier.height(35.dp))

            // Form Card
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color.White,
                        RoundedCornerShape(35.dp)
                    )
                    .padding(25.dp)
            ) {

                Text(
                    text = "Your Details",
                    color = darkText,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(22.dp))

                // Full Name
                OutlinedTextField(
                    value = fullName,
                    onValueChange = {
                        fullName = it
                        errorMessage = ""
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    label = {
                        Text("Full Name")
                    },
                    placeholder = {
                        Text("Enter your full name")
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Name"
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

                Spacer(modifier = Modifier.height(15.dp))

                // Email
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

                Spacer(modifier = Modifier.height(15.dp))

                // Password
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

                Spacer(modifier = Modifier.height(15.dp))

                // Confirm Password
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = {
                        confirmPassword = it
                        errorMessage = ""
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    label = {
                        Text("Confirm Password")
                    },
                    placeholder = {
                        Text("Confirm your password")
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Lock,
                            contentDescription = "Confirm Password"
                        )
                    },
                    trailingIcon = {
                        IconButton(
                            onClick = {
                                confirmPasswordVisible =
                                    !confirmPasswordVisible
                            }
                        ) {
                            Icon(
                                imageVector =
                                    if (confirmPasswordVisible)
                                        Icons.Default.VisibilityOff
                                    else
                                        Icons.Default.Visibility,
                                contentDescription = "Show password"
                            )
                        }
                    },
                    visualTransformation =
                        if (confirmPasswordVisible)
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

                Spacer(modifier = Modifier.height(15.dp))

                // Error message
                if (errorMessage.isNotEmpty()) {
                    Text(
                        text = errorMessage,
                        color = Color(0xFFD32F2F),
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Medium,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // Create Account Button
                Button(
                    onClick = {

                        when {

                            // Check empty fields
                            fullName.isBlank() ||
                                    email.isBlank() ||
                                    password.isBlank() ||
                                    confirmPassword.isBlank() -> {

                                errorMessage =
                                    "Please fill in all fields"
                            }

                            // Check email format
                            !Patterns.EMAIL_ADDRESS
                                .matcher(email)
                                .matches() -> {

                                errorMessage =
                                    "Please enter a valid email address"
                            }

                            // Check password length
                            password.length < 6 -> {

                                errorMessage =
                                    "Password must be at least 6 characters"
                            }

                            // Check passwords match
                            password != confirmPassword -> {

                                errorMessage =
                                    "Passwords do not match"
                            }

                            // Everything is correct
                            else -> {

                                errorMessage = ""

                                // Firebase registration will be added here
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
                        text = "CREATE ACCOUNT",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        letterSpacing = 1.sp
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Icon(
                        imageVector = Icons.Default.ArrowForward,
                        contentDescription = "Create Account"
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))

                // Already Have An Account
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Already have an account? ",
                        color = hintText,
                        fontSize = 14.sp
                    )

                    Text(
                        text = "Log in",
                        color = mtaaniGreen,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {
                            navController.navigate(ROUT_LOGIN)
                        }
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterScreenPreview() {

    RegisterScreen(
        navController = rememberNavController()
    )
}