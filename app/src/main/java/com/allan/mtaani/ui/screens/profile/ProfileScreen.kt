package com.allan.mtaani.ui.screens.profile

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Login
import androidx.compose.material.icons.filled.Logout
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.allan.mtaani.navigation.ROUT_ABOUT
import com.allan.mtaani.navigation.ROUT_LOGIN
import com.google.firebase.auth.FirebaseAuth


@Composable
fun ProfileScreen(navController: NavController) {

    // ============================================================
    // COLORS
    // ============================================================

    val mtaaniGreen = Color(0xFF0B5D45)
    val darkText = Color(0xFF1B1B1B)
    val grayText = Color(0xFF777777)
    val backgroundColor = Color(0xFFF7F9F8)


    // ============================================================
    // CURRENT FIREBASE USER
    // ============================================================

    val currentUser = FirebaseAuth
        .getInstance()
        .currentUser

    val userName = currentUser
        ?.displayName
        ?.takeIf { it.isNotBlank() }
        ?: "Mtaani User"

    val userEmail = currentUser
        ?.email
        ?: "No email available"


    // ============================================================
    // LOGOUT DIALOG STATE
    // ============================================================

    var showLogoutDialog by remember {
        mutableStateOf(false)
    }


    // ============================================================
    // PROFILE SCREEN
    // ============================================================

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {

        // ========================================================
        // TOP HEADER
        // ========================================================

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(mtaaniGreen)
                .padding(
                    start = 8.dp,
                    end = 20.dp,
                    top = 40.dp,
                    bottom = 20.dp
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {

            IconButton(
                onClick = {
                    navController.popBackStack()
                }
            ) {

                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White
                )
            }

            Spacer(
                modifier = Modifier.width(5.dp)
            )

            Text(
                text = "Profile",
                color = Color.White,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        }


        // ========================================================
        // PROFILE CONTENT
        // ========================================================

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(
                modifier = Modifier.height(15.dp)
            )


            // ====================================================
            // PROFILE AVATAR
            // ====================================================

            Box(
                modifier = Modifier
                    .size(90.dp)
                    .background(
                        mtaaniGreen,
                        CircleShape
                    ),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = Icons.Default.Person,
                    contentDescription = "Profile",
                    tint = Color.White,
                    modifier = Modifier.size(48.dp)
                )
            }


            Spacer(
                modifier = Modifier.height(12.dp)
            )


            // ====================================================
            // USER NAME
            // ====================================================

            Text(
                text = userName,
                color = darkText,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )


            Spacer(
                modifier = Modifier.height(4.dp)
            )


            // ====================================================
            // USER EMAIL
            // ====================================================

            Text(
                text = userEmail,
                color = grayText,
                fontSize = 14.sp
            )


            Spacer(
                modifier = Modifier.height(30.dp)
            )


            // ====================================================
            // ACCOUNT INFORMATION
            // ====================================================

            Text(
                text = "Account Information",
                color = darkText,
                fontSize = 19.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.fillMaxWidth()
            )


            Spacer(
                modifier = Modifier.height(12.dp)
            )


            // ====================================================
            // ACCOUNT INFORMATION CARD
            // ====================================================

            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(18.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 2.dp
                )
            ) {

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(18.dp)
                ) {

                    // --------------------------------------------
                    // NAME
                    // --------------------------------------------

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .size(42.dp)
                                .background(
                                    Color(0xFFE8F4EF),
                                    RoundedCornerShape(12.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {

                            Icon(
                                imageVector = Icons.Default.Person,
                                contentDescription = "Name",
                                tint = mtaaniGreen,
                                modifier = Modifier.size(22.dp)
                            )
                        }

                        Spacer(
                            modifier = Modifier.width(14.dp)
                        )

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {

                            Text(
                                text = "Full Name",
                                color = grayText,
                                fontSize = 12.sp
                            )

                            Spacer(
                                modifier = Modifier.height(3.dp)
                            )

                            Text(
                                text = userName,
                                color = darkText,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }


                    Spacer(
                        modifier = Modifier.height(20.dp)
                    )


                    // --------------------------------------------
                    // EMAIL
                    // --------------------------------------------

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Box(
                            modifier = Modifier
                                .size(42.dp)
                                .background(
                                    Color(0xFFE3F2FD),
                                    RoundedCornerShape(12.dp)
                                ),
                            contentAlignment = Alignment.Center
                        ) {

                            Icon(
                                imageVector = Icons.Default.Email,
                                contentDescription = "Email",
                                tint = Color(0xFF1976D2),
                                modifier = Modifier.size(22.dp)
                            )
                        }

                        Spacer(
                            modifier = Modifier.width(14.dp)
                        )

                        Column(
                            modifier = Modifier.weight(1f)
                        ) {

                            Text(
                                text = "Email Address",
                                color = grayText,
                                fontSize = 12.sp
                            )

                            Spacer(
                                modifier = Modifier.height(3.dp)
                            )

                            Text(
                                text = userEmail,
                                color = darkText,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Medium
                            )
                        }
                    }
                }
            }


            Spacer(
                modifier = Modifier.height(20.dp)
            )


            // ====================================================
            // ABOUT MTAANI BUTTON
            // ====================================================

            Button(
                onClick = {

                    // ============================================
                    // GO TO ABOUT SCREEN
                    // ============================================

                    navController.navigate(
                        ROUT_ABOUT
                    )
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),

                shape = RoundedCornerShape(16.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White
                )
            ) {

                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = "About Mtaani",
                    tint = mtaaniGreen
                )

                Spacer(
                    modifier = Modifier.width(8.dp)
                )

                Text(
                    text = "About Mtaani",
                    color = mtaaniGreen,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }


            Spacer(
                modifier = Modifier.height(12.dp)
            )


            // ====================================================
            // GO TO LOGIN BUTTON
            // ====================================================

            Button(
                onClick = {

                    // ============================================
                    // GO TO LOGIN SCREEN
                    // ============================================

                    navController.navigate(
                        ROUT_LOGIN
                    ) {

                        launchSingleTop = true
                    }
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),

                shape = RoundedCornerShape(16.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFE8F4EF)
                )
            ) {

                Icon(
                    imageVector = Icons.Default.Login,
                    contentDescription = "Login",
                    tint = mtaaniGreen
                )

                Spacer(
                    modifier = Modifier.width(8.dp)
                )

                Text(
                    text = "Go to Login",
                    color = mtaaniGreen,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }


            Spacer(
                modifier = Modifier.height(15.dp)
            )


            // ====================================================
            // LOGOUT BUTTON
            // ====================================================

            Button(
                onClick = {

                    // Do NOT log out immediately.
                    // Show confirmation dialog first.

                    showLogoutDialog = true
                },

                modifier = Modifier
                    .fillMaxWidth()
                    .height(55.dp),

                shape = RoundedCornerShape(16.dp),

                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xFFD32F2F)
                )
            ) {

                Icon(
                    imageVector = Icons.Default.Logout,
                    contentDescription = "Logout",
                    tint = Color.White
                )

                Spacer(
                    modifier = Modifier.width(8.dp)
                )

                Text(
                    text = "Log Out",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }


            Spacer(
                modifier = Modifier.height(20.dp)
            )
        }
    }


    // ============================================================
    // LOGOUT CONFIRMATION DIALOG
    // ============================================================

    if (showLogoutDialog) {

        AlertDialog(

            onDismissRequest = {
                showLogoutDialog = false
            },

            icon = {

                Icon(
                    imageVector = Icons.Default.Warning,
                    contentDescription = "Warning",
                    tint = Color(0xFFD32F2F),
                    modifier = Modifier.size(32.dp)
                )
            },

            title = {

                Text(
                    text = "Log Out?",
                    fontWeight = FontWeight.Bold
                )
            },

            text = {

                Text(
                    text = "Are you sure you want to log out of your Mtaani account?"
                )
            },

            confirmButton = {

                Button(

                    onClick = {

                        // ========================================
                        // USER CONFIRMED LOGOUT
                        // ========================================

                        FirebaseAuth
                            .getInstance()
                            .signOut()

                        showLogoutDialog = false

                        // ========================================
                        // GO TO LOGIN SCREEN
                        // ========================================

                        navController.navigate(
                            ROUT_LOGIN
                        ) {

                            popUpTo(0) {
                                inclusive = true
                            }

                            launchSingleTop = true
                        }
                    },

                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFD32F2F)
                    )
                ) {

                    Text(
                        text = "Yes, Log Out",
                        color = Color.White
                    )
                }
            },

            dismissButton = {

                TextButton(
                    onClick = {
                        showLogoutDialog = false
                    }
                ) {

                    Text(
                        text = "Cancel",
                        color = mtaaniGreen
                    )
                }
            }
        )
    }
}


// ============================================================
// PREVIEW
// ============================================================

@Preview(
    showBackground = true
)
@Composable
fun ProfileScreenPreview() {

    ProfileScreen(
        navController = rememberNavController()
    )
}