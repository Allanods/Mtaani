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
import androidx.compose.runtime.DisposableEffect
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
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

@Composable
fun HomeScreen(navController: NavController) {

    // Stores whatever the user types into the search bar.
    var searchText by remember { mutableStateOf("") }

    // Controls the vertical scrolling of the HomeScreen.
    val scrollState = rememberScrollState()

    // Stores reports retrieved from Firebase.
    var reports by remember { mutableStateOf<List<FirebaseReport>>(emptyList()) }

    // Main green color used throughout the Mtaani application.
    val mtaaniGreen = Color(0xFF0B5D45)

    // Light green color used for backgrounds and other UI elements.
    val lightGreen = Color(0xFFE8F4EF)

    // Main dark text color used for headings and important text.
    val darkText = Color(0xFF1B1B1B)

    // Grey color used for hints and secondary text.
    val hintText = Color(0xFF777777)

    // Main background color of the HomeScreen.
    val backgroundColor = Color(0xFFF7F9F8)

    /*
     * FIREBASE REPORTS
     *
     * Reports are loaded from the "reports" node.
     *
     * The reports are:
     * 1. Loaded in real time.
     * 2. Sorted from newest to oldest.
     * 3. Automatically deleted when they are more than 7 days old.
     */
    DisposableEffect(Unit) {

        val reportsReference =
            FirebaseDatabase.getInstance()
                .getReference("reports")

        val listener = object : ValueEventListener {

            override fun onDataChange(snapshot: DataSnapshot) {

                val currentTime = System.currentTimeMillis()

                // 7 days converted into milliseconds.
                val sevenDays = 7L * 24L * 60L * 60L * 1000L

                val reportList = mutableListOf<FirebaseReport>()

                for (reportSnapshot in snapshot.children) {

                    // Get the report values from Firebase.
                    val category =
                        reportSnapshot.child("category")
                            .getValue(String::class.java) ?: ""

                    val description =
                        reportSnapshot.child("description")
                            .getValue(String::class.java) ?: ""

                    val location =
                        reportSnapshot.child("location")
                            .getValue(String::class.java) ?: ""

                    val timestamp =
                        reportSnapshot.child("timestamp")
                            .getValue(Long::class.java) ?: 0L

                    val upVotes =
                        reportSnapshot.child("upVotes")
                            .getValue(Int::class.java) ?: 0

                    val downVotes =
                        reportSnapshot.child("downVotes")
                            .getValue(Int::class.java) ?: 0

                    val verified =
                        reportSnapshot.child("verified")
                            .getValue(Boolean::class.java) ?: false

                    /*
                     * DELETE REPORTS OLDER THAN 7 DAYS.
                     *
                     * Example:
                     * If a report was created 8 days ago,
                     * it will be removed from Firebase.
                     */
                    if (
                        timestamp > 0L &&
                        currentTime - timestamp >= sevenDays
                    ) {

                        reportSnapshot.ref.removeValue()

                    } else {

                        // Keep reports that are still within 7 days.
                        reportList.add(
                            FirebaseReport(
                                reportId = reportSnapshot.key ?: "",
                                category = category,
                                description = description,
                                location = location,
                                timestamp = timestamp,
                                upVotes = upVotes,
                                downVotes = downVotes,
                                verified = verified
                            )
                        )
                    }
                }

                /*
                 * SORT REPORTS
                 *
                 * Newest report comes first.
                 * Oldest report comes last.
                 */
                reports = reportList.sortedByDescending {
                    it.timestamp
                }
            }

            override fun onCancelled(error: DatabaseError) {
                // Firebase listener cancelled.
            }
        }

        reportsReference.addValueEventListener(listener)

        // Remove the listener when HomeScreen leaves the composition.
        onDispose {
            reportsReference.removeEventListener(listener)
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {

        // SCROLLABLE HOME CONTENT
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
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

                /*
                 * DISPLAY FIREBASE REPORTS
                 *
                 * Reports are already sorted newest first.
                 */
                if (reports.isEmpty()) {

                    // Empty state when there are no reports.
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
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
                                .padding(25.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {

                            Icon(
                                imageVector = Icons.Default.Notifications,
                                contentDescription = "No recent updates",
                                tint = Color(0xFF999999),
                                modifier = Modifier.size(35.dp)
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            Text(
                                text = "No recent updates yet",
                                color = darkText,
                                fontSize = 15.sp,
                                fontWeight = FontWeight.Bold
                            )

                            Spacer(modifier = Modifier.height(5.dp))

                            Text(
                                text = "Reports from the Karuri community will appear here.",
                                color = hintText,
                                fontSize = 12.sp
                            )
                        }
                    }

                } else {

                    /*
                     * SHOW EACH REPORT.
                     *
                     * The first report is always the newest one.
                     */
                    reports.forEach { report ->

                        val iconData = getReportIcon(report.category)

                        UpdateCard(
                            icon = iconData.first,
                            iconBackground = iconData.second,
                            iconColor = iconData.third,
                            title = report.category,
                            description = "${report.description}\n📍 ${report.location}",
                            time = formatReportTime(report.timestamp),
                            verified = report.verified,
                            upVotes = report.upVotes,
                            downVotes = report.downVotes
                        )

                        Spacer(modifier = Modifier.height(12.dp))
                    }
                }

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


// ------------------------------------------------------------
// FIREBASE REPORT DATA
// ------------------------------------------------------------

data class FirebaseReport(
    val reportId: String,
    val category: String,
    val description: String,
    val location: String,
    val timestamp: Long,
    val upVotes: Int,
    val downVotes: Int,
    val verified: Boolean
)


// ------------------------------------------------------------
// GET REPORT ICON
// ------------------------------------------------------------

fun getReportIcon(
    category: String
): Triple<
        androidx.compose.ui.graphics.vector.ImageVector,
        Color,
        Color
        > {

    return when (category) {

        "Electricity" -> Triple(
            Icons.Default.Bolt,
            Color(0xFFFFF4D8),
            Color(0xFFE5A900)
        )

        "Water" -> Triple(
            Icons.Default.WaterDrop,
            Color(0xFFE3F2FD),
            Color(0xFF1976D2)
        )

        "Waste" -> Triple(
            Icons.Default.Delete,
            Color(0xFFE8F4EF),
            Color(0xFF0B5D45)
        )

        else -> Triple(
            Icons.Default.Warning,
            Color(0xFFFDEAEA),
            Color(0xFFD32F2F)
        )
    }
}


// ------------------------------------------------------------
// FORMAT REPORT DATE AND TIME
// ------------------------------------------------------------

fun formatReportTime(timestamp: Long): String {

    if (timestamp == 0L) {
        return "Unknown time"
    }

    val dateFormat =
        java.text.SimpleDateFormat(
            "dd MMM yyyy, hh:mm a",
            java.util.Locale.getDefault()
        )

    return dateFormat.format(
        java.util.Date(timestamp)
    )
}


// ------------------------------------------------------------
// ISSUE CATEGORY
// ------------------------------------------------------------

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


// ------------------------------------------------------------
// UPDATE CARD
// ------------------------------------------------------------

@Composable
fun UpdateCard(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconBackground: Color,
    iconColor: Color,
    title: String,
    description: String,
    time: String,
    verified: Boolean,
    upVotes: Int,
    downVotes: Int
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

                // DATE AND TIME OF THE REPORT
                Text(
                    text = time,
                    color = Color(0xFF999999),
                    fontSize = 11.sp
                )

                Spacer(modifier = Modifier.height(10.dp))

                // VOTING INFORMATION
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    Text(
                        text = "👍 $upVotes",
                        color = Color(0xFF0B7A55),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )

                    Spacer(modifier = Modifier.width(15.dp))

                    Text(
                        text = "👎 $downVotes",
                        color = Color(0xFFD32F2F),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}


// ------------------------------------------------------------
// BOTTOM NAVIGATION ITEM
// ------------------------------------------------------------

@Composable
fun BottomNavigationItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    label: String,
    selected: Boolean,
    onClick: () -> Unit
) {

    // Determines the color of the navigation item.
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


// ------------------------------------------------------------
// SIMPLE TEXT COLOR HELPER
// ------------------------------------------------------------

@Composable
fun darkTextColor(): Color {
    return Color(0xFF1B1B1B)
}


// ------------------------------------------------------------
// PREVIEW
// ------------------------------------------------------------

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {

    HomeScreen(
        navController = rememberNavController()
    )
}