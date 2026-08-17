package com.allan.mtaani.ui.screens.authentication

import android.os.Handler
import android.os.Looper
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
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.allan.mtaani.navigation.ROUT_HOME
import com.allan.mtaani.navigation.ROUT_LOGIN
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.userProfileChangeRequest
import com.google.firebase.database.FirebaseDatabase

@Composable
fun RegisterScreen(navController: NavController) {

    // Stores the full name entered by the user
    var fullName by remember { mutableStateOf("") }

    // Stores the email entered by the user
    var email by remember { mutableStateOf("") }

    // Stores the password entered by the user
    var password by remember { mutableStateOf("") }

    // Stores the confirmation password entered by the user
    var confirmPassword by remember { mutableStateOf("") }

    // Controls whether the password is visible or hidden
    var passwordVisible by remember { mutableStateOf(false) }

    // Controls whether the confirmation password is visible or hidden
    var confirmPasswordVisible by remember { mutableStateOf(false) }

    // Stores validation or Firebase error messages
    var errorMessage by remember { mutableStateOf("") }

    // Stores the successful registration message
    var successMessage by remember { mutableStateOf("") }

    // Main green color used throughout the screen
    val mtaaniGreen = Color(0xFF0B5D45)

    // Main dark text color
    val darkText = Color(0xFF1B1B1B)

    // Color used for hints and secondary text
    val hintText = Color(0xFF777777)

    // Gets the Firebase Authentication service
    // This creates the user's Firebase account using email and password
    val auth = if (LocalInspectionMode.current) null else FirebaseAuth.getInstance()

    // Gets the Firebase Realtime Database reference
    // This is used to save the user's information under "users"
    val database = if (LocalInspectionMode.current) null else FirebaseDatabase.getInstance().reference

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

                // FULL NAME
                OutlinedTextField(
                    value = fullName,
                    onValueChange = {
                        // Updates the full name when the user types
                        fullName = it

                        // Clears any previous error
                        errorMessage = ""

                        // Clears the success message when the user edits the form
                        successMessage = ""
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

                // EMAIL
                OutlinedTextField(
                    value = email,
                    onValueChange = {
                        // Updates the email when the user types
                        email = it

                        // Clears any previous error
                        errorMessage = ""

                        // Clears the success message
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

                Spacer(modifier = Modifier.height(15.dp))

                // PASSWORD
                OutlinedTextField(
                    value = password,
                    onValueChange = {
                        // Updates the password when the user types
                        password = it

                        // Clears any previous error
                        errorMessage = ""

                        // Clears the success message
                        successMessage = ""
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
                                // Changes between showing and hiding the password
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

                // CONFIRM PASSWORD
                OutlinedTextField(
                    value = confirmPassword,
                    onValueChange = {
                        // Updates the confirmation password
                        confirmPassword = it

                        // Clears any previous error
                        errorMessage = ""

                        // Clears the success message
                        successMessage = ""
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
                                // Changes between showing and hiding the confirmation password
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

                // ERROR MESSAGE
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

                // SUCCESS MESSAGE
                if (successMessage.isNotEmpty()) {
                    Text(
                        text = successMessage,
                        color = Color(0xFF2E7D32),
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp)
                    )
                }

                Spacer(modifier = Modifier.height(10.dp))

                // CREATE ACCOUNT BUTTON
                Button(
                    onClick = {

                        when {

                            // Checks whether any required field is empty
                            fullName.isBlank() ||
                                    email.isBlank() ||
                                    password.isBlank() ||
                                    confirmPassword.isBlank() -> {

                                errorMessage = "Please fill in all fields"
                                successMessage = ""
                            }

                            // Checks whether the email has a valid format
                            !Patterns.EMAIL_ADDRESS
                                .matcher(email)
                                .matches() -> {

                                errorMessage =
                                    "Please enter a valid email address"
                                successMessage = ""
                            }

                            // Checks whether the password has at least 6 characters
                            password.length < 6 -> {

                                errorMessage =
                                    "Password must be at least 6 characters"
                                successMessage = ""
                            }

                            // Checks whether both passwords are the same
                            password != confirmPassword -> {

                                errorMessage =
                                    "Passwords do not match"
                                successMessage = ""
                            }

                            else -> {

                                // Clears previous messages before registration
                                errorMessage = ""
                                successMessage = ""

                                // Creates the user's account in Firebase Authentication
                                auth?.createUserWithEmailAndPassword(
                                    email.trim(),
                                    password
                                )
                                    ?.addOnCompleteListener { task ->

                                        if (task.isSuccessful) {

                                            // Gets the newly created Firebase user
                                            val user = auth?.currentUser

                                            // Gets the unique ID assigned to the user by Firebase
                                            val uid = user?.uid

                                            if (uid != null) {

                                                // Creates the data that will be stored
                                                // for this user in Realtime Database
                                                val userData = hashMapOf(
                                                    "email" to email.trim(),
                                                    "password" to password,
                                                    "username" to fullName.trim(),
                                                    "uid" to uid,
                                                    "role" to "user"
                                                )

                                                // Saves the user information under:
                                                // users -> user's UID
                                                database
                                                    ?.child("users")
                                                    ?.child(uid)
                                                    ?.setValue(userData)
                                                    ?.addOnCompleteListener { databaseTask ->

                                                        if (databaseTask.isSuccessful) {

                                                            // Creates an update for the Firebase
                                                            // Authentication user's display name
                                                            val profileUpdates =
                                                                userProfileChangeRequest {
                                                                    displayName =
                                                                        fullName.trim()
                                                                }

                                                            // Saves the user's full name
                                                            // as their Firebase display name
                                                            user?.updateProfile(
                                                                profileUpdates
                                                            )

                                                            // Shows the success message
                                                            successMessage =
                                                                "Account registered successfully!"

                                                            // Waits 1.5 seconds before
                                                            // moving to the Home Screen
                                                            Handler(
                                                                Looper.getMainLooper()
                                                            ).postDelayed({

                                                                // Takes the user to the Home Screen
                                                                navController.navigate(
                                                                    ROUT_HOME
                                                                )

                                                            }, 1500)

                                                        } else {

                                                            // Shows the database error
                                                            // if saving the user information fails
                                                            errorMessage =
                                                                databaseTask.exception?.message
                                                                    ?: "Failed to save user information"

                                                            successMessage = ""
                                                        }
                                                    }

                                            } else {

                                                // Handles the situation where
                                                // Firebase did not return a user ID
                                                errorMessage =
                                                    "Unable to get user information"

                                                successMessage = ""
                                            }

                                        } else {

                                            // Shows the Firebase registration error
                                            errorMessage =
                                                task.exception?.message
                                                    ?: "Registration failed"

                                            successMessage = ""
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

                // ALREADY HAVE AN ACCOUNT
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

                            // Takes the user to the Login Screen
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

    // Creates a navigation controller for the Preview
    RegisterScreen(
        navController = rememberNavController()
    )
}