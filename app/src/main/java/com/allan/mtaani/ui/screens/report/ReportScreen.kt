package com.allan.mtaani.ui.screens.report

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
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Description
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

@Composable
fun ReportScreen(navController: NavController) {

    var description by remember { mutableStateOf("") }
    var location by remember { mutableStateOf("") }
    var selectedCategory by remember { mutableStateOf("") }

    // Validation error message
    var showError by remember { mutableStateOf(false) }

    val mtaaniGreen = Color(0xFF0B5D45)
    val darkText = Color(0xFF1B1B1B)
    val hintText = Color(0xFF777777)
    val backgroundColor = Color(0xFFF7F9F8)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(
                    start = 20.dp,
                    end = 20.dp,
                    top = 45.dp,
                    bottom = 35.dp
                )
        ) {

            // HEADER
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .background(
                            mtaaniGreen,
                            CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Text(
                        text = "M",
                        color = Color.White,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.ExtraBold
                    )
                }

                Spacer(modifier = Modifier.width(13.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = "Report an Issue",
                        color = darkText,
                        fontSize = 23.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Location",
                            tint = mtaaniGreen,
                            modifier = Modifier.size(15.dp)
                        )

                        Spacer(modifier = Modifier.width(3.dp))

                        Text(
                            text = "Karuri",
                            color = hintText,
                            fontSize = 12.sp
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            // INTRODUCTION CARD
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        mtaaniGreen,
                        RoundedCornerShape(22.dp)
                    )
                    .padding(20.dp)
            ) {

                Column {

                    Text(
                        text = "Help your community",
                        color = Color.White,
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(7.dp))

                    Text(
                        text = "Report a local issue so people in Karuri can stay informed.",
                        color = Color.White.copy(alpha = 0.85f),
                        fontSize = 13.sp,
                        lineHeight = 19.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            // CATEGORY
            Text(
                text = "What is the issue?",
                color = darkText,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(12.dp))

            // CATEGORY ROW 1
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                ReportCategory(
                    icon = Icons.Default.Bolt,
                    title = "Electricity",
                    selected = selectedCategory == "Electricity",
                    iconColor = Color(0xFFE5A900),
                    modifier = Modifier.weight(1f),
                    onClick = {
                        selectedCategory = "Electricity"
                        showError = false
                    }
                )

                ReportCategory(
                    icon = Icons.Default.WaterDrop,
                    title = "Water",
                    selected = selectedCategory == "Water",
                    iconColor = Color(0xFF1976D2),
                    modifier = Modifier.weight(1f),
                    onClick = {
                        selectedCategory = "Water"
                        showError = false
                    }
                )
            }

            Spacer(modifier = Modifier.height(10.dp))

            // CATEGORY ROW 2
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {

                ReportCategory(
                    icon = Icons.Default.Delete,
                    title = "Waste",
                    selected = selectedCategory == "Waste",
                    iconColor = mtaaniGreen,
                    modifier = Modifier.weight(1f),
                    onClick = {
                        selectedCategory = "Waste"
                        showError = false
                    }
                )

                ReportCategory(
                    icon = Icons.Default.Warning,
                    title = "Other",
                    selected = selectedCategory == "Other",
                    iconColor = Color(0xFFD32F2F),
                    modifier = Modifier.weight(1f),
                    onClick = {
                        selectedCategory = "Other"
                        showError = false
                    }
                )
            }

            Spacer(modifier = Modifier.height(25.dp))

            // LOCATION
            Text(
                text = "Where is it happening?",
                color = darkText,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = location,
                onValueChange = {
                    location = it
                    showError = false
                },
                modifier = Modifier.fillMaxWidth(),
                singleLine = true,
                label = {
                    Text("Area / Location")
                },
                placeholder = {
                    Text("e.g. Near Karori Centre")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Location"
                    )
                },
                shape = RoundedCornerShape(17.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = darkText,
                    unfocusedTextColor = darkText,
                    focusedLabelColor = mtaaniGreen,
                    unfocusedLabelColor = hintText,
                    focusedBorderColor = mtaaniGreen,
                    unfocusedBorderColor = Color(0xFFB8BFBC),
                    focusedLeadingIconColor = mtaaniGreen,
                    unfocusedLeadingIconColor = hintText,
                    cursorColor = mtaaniGreen
                )
            )

            Spacer(modifier = Modifier.height(22.dp))

            // DESCRIPTION
            Text(
                text = "Describe the issue",
                color = darkText,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(10.dp))

            OutlinedTextField(
                value = description,
                onValueChange = {
                    description = it
                    showError = false
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(145.dp),
                label = {
                    Text("Description")
                },
                placeholder = {
                    Text(
                        "Briefly explain what happened..."
                    )
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Description,
                        contentDescription = "Description"
                    )
                },
                shape = RoundedCornerShape(17.dp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedTextColor = darkText,
                    unfocusedTextColor = darkText,
                    focusedLabelColor = mtaaniGreen,
                    unfocusedLabelColor = hintText,
                    focusedBorderColor = mtaaniGreen,
                    unfocusedBorderColor = Color(0xFFB8BFBC),
                    focusedLeadingIconColor = mtaaniGreen,
                    unfocusedLeadingIconColor = hintText,
                    cursorColor = mtaaniGreen
                )
            )

            Spacer(modifier = Modifier.height(22.dp))

            // VERIFICATION NOTICE
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(
                        Color(0xFFE8F4EF),
                        RoundedCornerShape(18.dp)
                    )
                    .padding(15.dp),
                verticalAlignment = Alignment.Top
            ) {

                Icon(
                    imageVector = Icons.Default.CheckCircle,
                    contentDescription = "Verification",
                    tint = mtaaniGreen,
                    modifier = Modifier.size(22.dp)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Column {

                    Text(
                        text = "Community Verification",
                        color = mtaaniGreen,
                        fontSize = 14.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.height(4.dp))

                    Text(
                        text = "Your report may be reviewed and verified before it is marked as verified for other residents.",
                        color = Color(0xFF527067),
                        fontSize = 12.sp,
                        lineHeight = 17.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(25.dp))

            // VALIDATION MESSAGE
            if (showError) {

                Text(
                    text = "Please select an issue, enter the location, and describe the issue before submitting.",
                    color = Color(0xFFD32F2F),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            start = 4.dp,
                            bottom = 10.dp
                        )
                )
            }

            // SUBMIT BUTTON
            Button(
                onClick = {

                    // Check whether every required field has been filled
                    if (
                        selectedCategory.isBlank() ||
                        location.isBlank() ||
                        description.isBlank()
                    ) {

                        // Show error instead of submitting
                        showError = true

                    } else {

                        showError = false

                        // TODO: Validate the form and save the report to Firebase
                        // TODO: Navigate back to Home Screen after successful submission
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

                Icon(
                    imageVector = Icons.Default.Send,
                    contentDescription = "Submit Report",
                    modifier = Modifier.size(20.dp)
                )

                Spacer(modifier = Modifier.width(10.dp))

                Text(
                    text = "SUBMIT REPORT",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    letterSpacing = 1.sp
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "MTAANI • KARURI • COMMUNITY FIRST",
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFF999999),
                fontSize = 10.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
        }
    }
}


// REPORT CATEGORY
@Composable
fun ReportCategory(
    icon: ImageVector,
    title: String,
    selected: Boolean,
    iconColor: Color,
    modifier: Modifier,
    onClick: () -> Unit
) {

    val backgroundColor =
        if (selected)
            Color(0xFFE8F4EF)
        else
            Color.White

    Column(
        modifier = modifier
            .height(92.dp)
            .background(
                backgroundColor,
                RoundedCornerShape(17.dp)
            )
            .clickable {
                onClick()
            }
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Icon(
            imageVector = icon,
            contentDescription = title,
            tint = iconColor,
            modifier = Modifier.size(27.dp)
        )

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = title,
            color = Color(0xFF1B1B1B),
            fontSize = 12.sp,
            fontWeight = FontWeight.Medium
        )

        if (selected) {

            Spacer(modifier = Modifier.height(3.dp))

            Text(
                text = "Selected",
                color = Color(0xFF0B5D45),
                fontSize = 9.sp,
                fontWeight = FontWeight.Bold
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun ReportScreenPreview() {

    ReportScreen(
        navController = rememberNavController()
    )
}