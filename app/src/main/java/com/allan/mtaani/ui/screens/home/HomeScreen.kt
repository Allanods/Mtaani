package com.allan.mtaani.ui.screens.home

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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.allan.mtaani.navigation.ROUT_REPORT

@Composable
fun HomeScreen(navController: NavController) {

    var searchText by remember { mutableStateOf("") }

    val mtaaniGreen = Color(0xFF0B5D45)
    val lightGreen = Color(0xFFE8F4EF)
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
                .padding(bottom = 85.dp)
        ) {

            // TOP HEADER
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(mtaaniGreen)
                    .padding(
                        start = 22.dp,
                        end = 15.dp,
                        top = 45.dp,
                        bottom = 22.dp
                    ),
                verticalAlignment = Alignment.CenterVertically
            ) {

                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = "MTAANI",
                        color = Color.White,
                        fontSize = 25.sp,
                        fontWeight = FontWeight.ExtraBold,
                        letterSpacing = 2.sp
                    )

                    Spacer(modifier = Modifier.height(5.dp))

                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {

                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = "Location",
                            tint = Color.White.copy(alpha = 0.85f),
                            modifier = Modifier.size(16.dp)
                        )

                        Spacer(modifier = Modifier.width(4.dp))

                        Text(
                            text = "Karuri",
                            color = Color.White.copy(alpha = 0.85f),
                            fontSize = 13.sp
                        )
                    }
                }

                IconButton(
                    onClick = {
                        // TODO: Open notifications screen
                    }
                ) {

                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Notifications",
                        tint = Color.White
                    )
                }

                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .background(
                            Color.White.copy(alpha = 0.18f),
                            CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = Icons.Default.Person,
                        contentDescription = "Profile",
                        tint = Color.White
                    )
                }
            }

            Column(
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {

                Spacer(modifier = Modifier.height(25.dp))

                // WELCOME
                Text(
                    text = "Hello 👋",
                    color = hintText,
                    fontSize = 15.sp
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = "What's happening in Karuri?",
                    color = darkText,
                    fontSize = 25.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(18.dp))

                // SEARCH BAR
                OutlinedTextField(
                    value = searchText,
                    onValueChange = {
                        searchText = it
                    },
                    modifier = Modifier.fillMaxWidth(),
                    singleLine = true,
                    placeholder = {
                        Text(
                            text = "Search local updates..."
                        )
                    },
                    leadingIcon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Search"
                        )
                    },
                    shape = RoundedCornerShape(16.dp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedTextColor = darkText,
                        unfocusedTextColor = darkText,
                        focusedBorderColor = mtaaniGreen,
                        unfocusedBorderColor = Color(0xFFD5DAD8),
                        focusedLeadingIconColor = mtaaniGreen,
                        unfocusedLeadingIconColor = hintText,
                        cursorColor = mtaaniGreen
                    )
                )

                Spacer(modifier = Modifier.height(25.dp))

                // SECTION TITLE
                Text(
                    text = "Report an Issue",
                    color = darkText,
                    fontSize = 19.sp,
                    fontWeight = FontWeight.Bold
                )

                Spacer(modifier = Modifier.height(12.dp))

                // ISSUE CATEGORIES
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {

                    IssueCategory(
                        icon = Icons.Default.Bolt,
                        title = "Electricity",
                        background = Color(0xFFFFF4D8),
                        iconColor = Color(0xFFE5A900),
                        onClick = {
                            navController.navigate(ROUT_REPORT)
                        }
                    )

                    IssueCategory(
                        icon = Icons.Default.WaterDrop,
                        title = "Water",
                        background = Color(0xFFE3F2FD),
                        iconColor = Color(0xFF1976D2),
                        onClick = {
                            navController.navigate(ROUT_REPORT)
                        }
                    )

                    IssueCategory(
                        icon = Icons.Default.Delete,
                        title = "Waste",
                        background = Color(0xFFE8F4EF),
                        iconColor = mtaaniGreen,
                        onClick = {
                            navController.navigate(ROUT_REPORT)
                        }
                    )

                    IssueCategory(
                        icon = Icons.Default.Warning,
                        title = "Other",
                        background = Color(0xFFFDEAEA),
                        iconColor = Color(0xFFD32F2F),
                        onClick = {
                            navController.navigate(ROUT_REPORT)
                        }
                    )
                }

                Spacer(modifier = Modifier.height(28.dp))

                // RECENT UPDATES
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "Recent Updates",
                        color = darkText,
                        fontSize = 19.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.weight(1f)
                    )

                    Text(
                        text = "See all",
                        color = mtaaniGreen,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.clickable {
                            // TODO: Open all updates
                        }
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                // UPDATE 1
                UpdateCard(
                    icon = Icons.Default.WaterDrop,
                    iconBackground = Color(0xFFE3F2FD),
                    iconColor = Color(0xFF1976D2),
                    title = "Water Outage",
                    description = "Water interruption reported around Karuri Centre.",
                    time = "15 min ago",
                    verified = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                // UPDATE 2
                UpdateCard(
                    icon = Icons.Default.Bolt,
                    iconBackground = Color(0xFFFFF4D8),
                    iconColor = Color(0xFFE5A900),
                    title = "Power Outage",
                    description = "Electricity outage reported in part of Karuri.",
                    time = "32 min ago",
                    verified = true
                )

                Spacer(modifier = Modifier.height(12.dp))

                // UPDATE 3
                UpdateCard(
                    icon = Icons.Default.Warning,
                    iconBackground = Color(0xFFFDEAEA),
                    iconColor = Color(0xFFD32F2F),
                    title = "Road Issue",
                    description = "A pothole has been reported near the main road.",
                    time = "1 hr ago",
                    verified = false
                )

                Spacer(modifier = Modifier.height(20.dp))
            }
        }

        // FLOATING REPORT BUTTON
        Box(
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(
                    end = 20.dp,
                    bottom = 80.dp
                )
                .size(62.dp)
                .background(
                    mtaaniGreen,
                    CircleShape
                )
                .clickable {
                    navController.navigate(ROUT_REPORT)
                },
            contentAlignment = Alignment.Center
        ) {

            Icon(
                imageVector = Icons.Default.Add,
                contentDescription = "Report Issue",
                tint = Color.White,
                modifier = Modifier.size(30.dp)
            )
        }

        // BOTTOM NAVIGATION
        Row(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .fillMaxWidth()
                .height(70.dp)
                .background(Color.White)
                .padding(horizontal = 25.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            BottomNavigationItem(
                icon = Icons.Default.Home,
                label = "Home",
                selected = true,
                onClick = {
                    // Already on Home Screen
                }
            )

            BottomNavigationItem(
                icon = Icons.Default.Add,
                label = "Report",
                selected = false,
                onClick = {
                    navController.navigate(ROUT_REPORT)
                }
            )

            BottomNavigationItem(
                icon = Icons.Default.Person,
                label = "Profile",
                selected = false,
                onClick = {
                    // TODO: Navigate to Profile Screen
                }
            )
        }
    }
}


// ISSUE CATEGORY
@Composable
fun IssueCategory(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    background: Color,
    iconColor: Color,
    onClick: () -> Unit
) {

    Column(
        modifier = Modifier
            .width(75.dp)
            .clickable {
                onClick()
            },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .size(55.dp)
                .background(
                    background,
                    RoundedCornerShape(16.dp)
                ),
            contentAlignment = Alignment.Center
        ) {

            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = iconColor,
                modifier = Modifier.size(26.dp)
            )
        }

        Spacer(modifier = Modifier.height(7.dp))

        Text(
            text = title,
            color = darkTextColor(),
            fontSize = 11.sp,
            fontWeight = FontWeight.Medium
        )
    }
}


// UPDATE CARD
@Composable
fun UpdateCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconBackground: Color,
    iconColor: Color,
    title: String,
    description: String,
    time: String,
    verified: Boolean
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable {
                // TODO: Open issue details
            },
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White
        ),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 2.dp
        )
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.Top
        ) {

            Box(
                modifier = Modifier
                    .size(48.dp)
                    .background(
                        iconBackground,
                        RoundedCornerShape(14.dp)
                    ),
                contentAlignment = Alignment.Center
            ) {

                Icon(
                    imageVector = icon,
                    contentDescription = title,
                    tint = iconColor,
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(
                modifier = Modifier.weight(1f)
            ) {

                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = title,
                        color = Color(0xFF1B1B1B),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold
                    )

                    if (verified) {

                        Spacer(modifier = Modifier.width(7.dp))

                        Text(
                            text = "✓ Verified",
                            color = Color(0xFF0B7A55),
                            fontSize = 10.sp,
                            fontWeight = FontWeight.Bold
                        )
                    }
                }

                Spacer(modifier = Modifier.height(5.dp))

                Text(
                    text = description,
                    color = Color(0xFF777777),
                    fontSize = 13.sp,
                    lineHeight = 18.sp
                )

                Spacer(modifier = Modifier.height(7.dp))

                Text(
                    text = time,
                    color = Color(0xFF999999),
                    fontSize = 11.sp
                )
            }
        }
    }
}


// BOTTOM NAVIGATION ITEM
@Composable
fun BottomNavigationItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {

    val color =
        if (selected)
            Color(0xFF0B5D45)
        else
            Color(0xFF999999)

    Column(
        modifier = Modifier.clickable {
            onClick()
        },
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Icon(
            imageVector = icon,
            contentDescription = label,
            tint = color,
            modifier = Modifier.size(23.dp)
        )

        Spacer(modifier = Modifier.height(3.dp))

        Text(
            text = label,
            color = color,
            fontSize = 11.sp,
            fontWeight =
                if (selected)
                    FontWeight.Bold
                else
                    FontWeight.Normal
        )
    }
}


// SIMPLE TEXT COLOR HELPER
@Composable
fun darkTextColor(): Color {
    return Color(0xFF1B1B1B)
}


@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {

    HomeScreen(
        navController = rememberNavController()
    )
}