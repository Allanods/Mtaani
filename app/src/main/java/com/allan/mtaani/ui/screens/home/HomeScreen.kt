package com.allan.mtaani.ui.screens.home

import android.util.Log
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
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.WaterDrop
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.allan.mtaani.navigation.ROUT_PROFILE
import com.allan.mtaani.navigation.ROUT_REPORT

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.MutableData
import com.google.firebase.database.Transaction
import com.google.firebase.database.ValueEventListener


// ============================================================
// HOME SCREEN
// ============================================================

@Composable
fun HomeScreen(navController: NavController) {

    // --------------------------------------------------------
    // SEARCH
    // --------------------------------------------------------

    var searchText by remember {
        mutableStateOf("")
    }

    // --------------------------------------------------------
    // SCROLL
    // --------------------------------------------------------

    val scrollState = rememberScrollState()

    // --------------------------------------------------------
    // REPORTS
    // --------------------------------------------------------

    var reports by remember {
        mutableStateOf<List<FirebaseReport>>(emptyList())
    }

    // --------------------------------------------------------
    // COLORS
    // --------------------------------------------------------

    val mtaaniGreen = Color(0xFF0B5D45)
    val darkText = Color(0xFF1B1B1B)
    val hintText = Color(0xFF777777)
    val backgroundColor = Color(0xFFF7F9F8)


    // ========================================================
    // SEARCH FILTER
    // ========================================================

    /*
     * The search bar now searches through:
     *
     * 1. Report category
     * 2. Report description
     * 3. Report location
     *
     * The search is case-insensitive.
     *
     * If searchText is empty, all reports are displayed.
     */

    val filteredReports = reports.filter { report ->

        searchText.isBlank() ||

                report.category.contains(
                    searchText,
                    ignoreCase = true
                ) ||

                report.description.contains(
                    searchText,
                    ignoreCase = true
                ) ||

                report.location.contains(
                    searchText,
                    ignoreCase = true
                )
    }


    // ========================================================
    // FIREBASE REPORT LISTENER
    // ========================================================

    DisposableEffect(Unit) {

        val reportsReference =
            FirebaseDatabase
                .getInstance()
                .getReference("reports")

        val listener = object : ValueEventListener {

            override fun onDataChange(
                snapshot: DataSnapshot
            ) {

                val currentTime =
                    System.currentTimeMillis()

                // ------------------------------------------------
                // 7 DAYS IN MILLISECONDS
                // ------------------------------------------------

                val sevenDays =
                    7L *
                            24L *
                            60L *
                            60L *
                            1000L

                val reportList =
                    mutableListOf<FirebaseReport>()


                // ------------------------------------------------
                // READ ALL REPORTS
                // ------------------------------------------------

                for (reportSnapshot in snapshot.children) {

                    val reportId =
                        reportSnapshot.key ?: ""

                    val category =
                        reportSnapshot
                            .child("category")
                            .getValue(String::class.java)
                            ?: ""

                    val description =
                        reportSnapshot
                            .child("description")
                            .getValue(String::class.java)
                            ?: ""

                    val location =
                        reportSnapshot
                            .child("location")
                            .getValue(String::class.java)
                            ?: ""


                    // ------------------------------------------------
                    // TIMESTAMP
                    // ------------------------------------------------

                    val timestamp =
                        (
                                reportSnapshot
                                    .child("timestamp")
                                    .value as? Number
                                )
                            ?.toLong()
                            ?: 0L


                    // ------------------------------------------------
                    // UP VOTES
                    // ------------------------------------------------

                    val upVotes =
                        (
                                reportSnapshot
                                    .child("upVotes")
                                    .value as? Number
                                )
                            ?.toInt()
                            ?: 0


                    // ------------------------------------------------
                    // DOWN VOTES
                    // ------------------------------------------------

                    val downVotes =
                        (
                                reportSnapshot
                                    .child("downVotes")
                                    .value as? Number
                                )
                            ?.toInt()
                            ?: 0


                    // ------------------------------------------------
                    // VERIFIED
                    // ------------------------------------------------

                    val verified =
                        reportSnapshot
                            .child("verified")
                            .getValue(Boolean::class.java)
                            ?: false


                    // =================================================
                    // DELETE REPORT AFTER 7 DAYS
                    // =================================================

                    if (
                        timestamp > 0L &&
                        currentTime - timestamp >= sevenDays
                    ) {

                        reportSnapshot.ref.removeValue()

                    } else {

                        reportList.add(

                            FirebaseReport(

                                reportId =
                                    reportId,

                                category =
                                    category,

                                description =
                                    description,

                                location =
                                    location,

                                timestamp =
                                    timestamp,

                                upVotes =
                                    upVotes,

                                downVotes =
                                    downVotes,

                                verified =
                                    verified
                            )
                        )
                    }
                }


                // =================================================
                // NEWEST REPORT FIRST
                // =================================================

                reports =
                    reportList.sortedByDescending {
                        it.timestamp
                    }
            }


            override fun onCancelled(
                error: DatabaseError
            ) {

                Log.e(
                    "MTAANI",
                    "Failed to load reports",
                    error.toException()
                )
            }
        }


        // --------------------------------------------------------
        // ADD FIREBASE LISTENER
        // --------------------------------------------------------

        reportsReference.addValueEventListener(listener)


        // --------------------------------------------------------
        // REMOVE LISTENER
        // --------------------------------------------------------

        onDispose {

            reportsReference.removeEventListener(listener)
        }
    }


    // ============================================================
    // MAIN SCREEN
    // ============================================================

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {


        // ========================================================
        // SCROLLABLE CONTENT
        // ========================================================

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(scrollState)
                .padding(bottom = 85.dp)
        ) {


            // ====================================================
            // TOP HEADER
            // ====================================================

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

                verticalAlignment =
                    Alignment.CenterVertically
            ) {


                Column(
                    modifier = Modifier.weight(1f)
                ) {

                    Text(
                        text = "MTAANI",

                        color = Color.White,

                        fontSize = 25.sp,

                        fontWeight =
                            FontWeight.ExtraBold,

                        letterSpacing = 2.sp
                    )


                    Spacer(
                        modifier =
                            Modifier.height(5.dp)
                    )


                    Row(
                        verticalAlignment =
                            Alignment.CenterVertically
                    ) {

                        Icon(
                            imageVector =
                                Icons.Default.LocationOn,

                            contentDescription =
                                "Location",

                            tint =
                                Color.White.copy(
                                    alpha = 0.85f
                                ),

                            modifier =
                                Modifier.size(16.dp)
                        )


                        Spacer(
                            modifier =
                                Modifier.width(4.dp)
                        )


                        Text(
                            text = "Karuri",

                            color =
                                Color.White.copy(
                                    alpha = 0.85f
                                ),

                            fontSize = 13.sp
                        )
                    }
                }


                // =================================================
                // NOTIFICATIONS
                // =================================================

                IconButton(
                    onClick = {

                        // TODO:
                        // Open notifications screen
                    }
                ) {

                    Icon(
                        imageVector =
                            Icons.Default.Notifications,

                        contentDescription =
                            "Notifications",

                        tint =
                            Color.White
                    )
                }


                // =================================================
                // PROFILE ICON
                // =================================================

                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .background(
                            Color.White.copy(
                                alpha = 0.18f
                            ),
                            CircleShape
                        )
                        .clickable {

                            // Go to Profile Screen

                            navController.navigate(
                                ROUT_PROFILE
                            )
                        },

                    contentAlignment =
                        Alignment.Center
                ) {

                    Icon(
                        imageVector =
                            Icons.Default.Person,

                        contentDescription =
                            "Profile",

                        tint =
                            Color.White
                    )
                }
            }


            // ====================================================
            // MAIN CONTENT
            // ====================================================

            Column(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
            ) {


                Spacer(
                    modifier =
                        Modifier.height(25.dp)
                )


                // =================================================
                // WELCOME
                // =================================================

                Text(
                    text = "Hello 👋",

                    color =
                        hintText,

                    fontSize =
                        15.sp
                )


                Spacer(
                    modifier =
                        Modifier.height(3.dp)
                )


                Text(
                    text =
                        "What's happening in Karuri?",

                    color =
                        darkText,

                    fontSize =
                        25.sp,

                    fontWeight =
                        FontWeight.Bold
                )


                Spacer(
                    modifier =
                        Modifier.height(18.dp)
                )


                // =================================================
                // SEARCH BAR
                // =================================================

                OutlinedTextField(

                    value =
                        searchText,

                    onValueChange = {

                        searchText = it
                    },

                    modifier =
                        Modifier.fillMaxWidth(),

                    singleLine = true,

                    placeholder = {

                        Text(
                            text =
                                "Search local updates..."
                        )
                    },

                    leadingIcon = {

                        Icon(
                            imageVector =
                                Icons.Default.Search,

                            contentDescription =
                                "Search"
                        )
                    },

                    shape =
                        RoundedCornerShape(16.dp),

                    colors =
                        OutlinedTextFieldDefaults.colors(

                            focusedTextColor =
                                darkText,

                            unfocusedTextColor =
                                darkText,

                            focusedBorderColor =
                                mtaaniGreen,

                            unfocusedBorderColor =
                                Color(0xFFD5DAD8),

                            focusedLeadingIconColor =
                                mtaaniGreen,

                            unfocusedLeadingIconColor =
                                hintText,

                            cursorColor =
                                mtaaniGreen
                        )
                )


                Spacer(
                    modifier =
                        Modifier.height(25.dp)
                )


                // =================================================
                // REPORT ISSUE
                // =================================================

                Text(
                    text =
                        "Report an Issue",

                    color =
                        darkText,

                    fontSize =
                        19.sp,

                    fontWeight =
                        FontWeight.Bold
                )


                Spacer(
                    modifier =
                        Modifier.height(12.dp)
                )


                Row(
                    modifier =
                        Modifier.fillMaxWidth(),

                    horizontalArrangement =
                        Arrangement.SpaceBetween
                ) {


                    // =================================================
                    // ELECTRICITY
                    // =================================================

                    IssueCategory(

                        icon =
                            Icons.Default.Bolt,

                        title =
                            "Electricity",

                        background =
                            Color(0xFFFFF4D8),

                        iconColor =
                            Color(0xFFE5A900),

                        onClick = {

                            navController.navigate(
                                ROUT_REPORT
                            )
                        }
                    )


                    // =================================================
                    // WATER
                    // =================================================

                    IssueCategory(

                        icon =
                            Icons.Default.WaterDrop,

                        title =
                            "Water",

                        background =
                            Color(0xFFE3F2FD),

                        iconColor =
                            Color(0xFF1976D2),

                        onClick = {

                            navController.navigate(
                                ROUT_REPORT
                            )
                        }
                    )


                    // =================================================
                    // WASTE
                    // =================================================

                    IssueCategory(

                        icon =
                            Icons.Default.Delete,

                        title =
                            "Waste",

                        background =
                            Color(0xFFE8F4EF),

                        iconColor =
                            mtaaniGreen,

                        onClick = {

                            navController.navigate(
                                ROUT_REPORT
                            )
                        }
                    )


                    // =================================================
                    // OTHER
                    // =================================================

                    IssueCategory(

                        icon =
                            Icons.Default.Warning,

                        title =
                            "Other",

                        background =
                            Color(0xFFFDEAEA),

                        iconColor =
                            Color(0xFFD32F2F),

                        onClick = {

                            navController.navigate(
                                ROUT_REPORT
                            )
                        }
                    )
                }


                Spacer(
                    modifier =
                        Modifier.height(28.dp)
                )


                // =================================================
                // RECENT UPDATES
                // =================================================

                Row(
                    modifier =
                        Modifier.fillMaxWidth(),

                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Text(
                        text =
                            if (searchText.isBlank()) {
                                "Recent Updates"
                            } else {
                                "Search Results"
                            },

                        color =
                            darkText,

                        fontSize =
                            19.sp,

                        fontWeight =
                            FontWeight.Bold,

                        modifier =
                            Modifier.weight(1f)
                    )


                    Text(
                        text =
                            "See all",

                        color =
                            mtaaniGreen,

                        fontSize =
                            13.sp,

                        fontWeight =
                            FontWeight.Bold,

                        modifier =
                            Modifier.clickable {

                                // TODO:
                                // Open all updates
                            }
                    )
                }


                Spacer(
                    modifier =
                        Modifier.height(12.dp)
                )


                // =================================================
                // REPORTS
                // =================================================

                if (filteredReports.isEmpty()) {


                    // =================================================
                    // EMPTY / NO SEARCH RESULTS
                    // =================================================

                    Card(

                        modifier =
                            Modifier.fillMaxWidth(),

                        shape =
                            RoundedCornerShape(20.dp),

                        colors =
                            CardDefaults.cardColors(
                                containerColor =
                                    Color.White
                            ),

                        elevation =
                            CardDefaults.cardElevation(
                                defaultElevation =
                                    2.dp
                            )
                    ) {


                        Column(

                            modifier =
                                Modifier
                                    .fillMaxWidth()
                                    .padding(25.dp),

                            horizontalAlignment =
                                Alignment.CenterHorizontally
                        ) {


                            // ------------------------------------------------
                            // ICON
                            // ------------------------------------------------

                            Icon(

                                imageVector =
                                    if (searchText.isBlank()) {
                                        Icons.Default.Notifications
                                    } else {
                                        Icons.Default.Search
                                    },

                                contentDescription =
                                    "No results",

                                tint =
                                    Color(0xFF999999),

                                modifier =
                                    Modifier.size(35.dp)
                            )


                            Spacer(
                                modifier =
                                    Modifier.height(10.dp)
                            )


                            // ------------------------------------------------
                            // TITLE
                            // ------------------------------------------------

                            Text(

                                text =
                                    if (searchText.isBlank()) {

                                        "No recent updates yet"

                                    } else {

                                        "No matching reports"
                                    },

                                color =
                                    darkText,

                                fontSize =
                                    15.sp,

                                fontWeight =
                                    FontWeight.Bold
                            )


                            Spacer(
                                modifier =
                                    Modifier.height(5.dp)
                            )


                            // ------------------------------------------------
                            // MESSAGE
                            // ------------------------------------------------

                            Text(

                                text =
                                    if (searchText.isBlank()) {

                                        "Reports from the Karuri community will appear here."

                                    } else {

                                        "Try searching for electricity, water, waste, or a location."
                                    },

                                color =
                                    hintText,

                                fontSize =
                                    12.sp
                            )
                        }
                    }


                } else {


                    // =================================================
                    // REPORT LIST
                    // =================================================

                    filteredReports.forEach { report ->


                        // ------------------------------------------------
                        // GET ICON FOR REPORT
                        // ------------------------------------------------

                        val iconData =
                            getReportIcon(
                                report.category
                            )


                        // ------------------------------------------------
                        // UPDATE CARD
                        // ------------------------------------------------

                        UpdateCard(

                            reportId =
                                report.reportId,

                            icon =
                                iconData.first,

                            iconBackground =
                                iconData.second,

                            iconColor =
                                iconData.third,

                            title =
                                report.category,

                            description =
                                "${report.description}\n📍 ${report.location}",

                            time =
                                formatReportTime(
                                    report.timestamp
                                ),

                            verified =
                                report.verified,

                            upVotes =
                                report.upVotes,

                            downVotes =
                                report.downVotes
                        )


                        Spacer(
                            modifier =
                                Modifier.height(12.dp)
                        )
                    }
                }


                Spacer(
                    modifier =
                        Modifier.height(20.dp)
                )
            }
        }


        // ========================================================
        // FLOATING REPORT BUTTON
        // ========================================================

        Box(

            modifier =
                Modifier
                    .align(
                        Alignment.BottomEnd
                    )

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

                        navController.navigate(
                            ROUT_REPORT
                        )
                    },

            contentAlignment =
                Alignment.Center
        ) {

            Icon(

                imageVector =
                    Icons.Default.Add,

                contentDescription =
                    "Report Issue",

                tint =
                    Color.White,

                modifier =
                    Modifier.size(30.dp)
            )
        }


        // ========================================================
        // BOTTOM NAVIGATION
        // ========================================================

        Row(

            modifier =
                Modifier
                    .align(
                        Alignment.BottomCenter
                    )

                    .fillMaxWidth()

                    .height(70.dp)

                    .background(
                        Color.White
                    )

                    .padding(
                        horizontal = 25.dp
                    ),

            horizontalArrangement =
                Arrangement.SpaceBetween,

            verticalAlignment =
                Alignment.CenterVertically
        ) {


            // =================================================
            // HOME
            // =================================================

            BottomNavigationItem(

                icon =
                    Icons.Default.Home,

                label =
                    "Home",

                selected =
                    true,

                onClick = {

                    // Already on Home Screen
                }
            )


            // =================================================
            // REPORT
            // =================================================

            BottomNavigationItem(

                icon =
                    Icons.Default.Add,

                label =
                    "Report",

                selected =
                    false,

                onClick = {

                    navController.navigate(
                        ROUT_REPORT
                    )
                }
            )


            // =================================================
            // PROFILE
            // =================================================

            BottomNavigationItem(

                icon =
                    Icons.Default.Person,

                label =
                    "Profile",

                selected =
                    false,

                onClick = {

                    navController.navigate(
                        ROUT_PROFILE
                    )
                }
            )
        }
    }
}


// ============================================================
// FIREBASE REPORT MODEL
// ============================================================

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


// ============================================================
// GET REPORT ICON
// ============================================================

fun getReportIcon(
    category: String
):
        Triple<
                androidx.compose.ui.graphics.vector.ImageVector,
                Color,
                Color
                > {

    return when (category) {

        "Electricity" -> {

            Triple(

                Icons.Default.Bolt,

                Color(0xFFFFF4D8),

                Color(0xFFE5A900)
            )
        }


        "Water" -> {

            Triple(

                Icons.Default.WaterDrop,

                Color(0xFFE3F2FD),

                Color(0xFF1976D2)
            )
        }


        "Waste" -> {

            Triple(

                Icons.Default.Delete,

                Color(0xFFE8F4EF),

                Color(0xFF0B5D45)
            )
        }


        else -> {

            Triple(

                Icons.Default.Warning,

                Color(0xFFFDEAEA),

                Color(0xFFD32F2F)
            )
        }
    }
}


// ============================================================
// FORMAT REPORT DATE
// ============================================================

fun formatReportTime(
    timestamp: Long
): String {

    if (timestamp == 0L) {

        return "Unknown time"
    }


    val dateFormat =
        java.text.SimpleDateFormat(

            "dd MMM yyyy, hh:mm a",

            java.util.Locale.getDefault()
        )


    return dateFormat.format(

        java.util.Date(
            timestamp
        )
    )
}


// ============================================================
// ISSUE CATEGORY
// ============================================================

@Composable
fun IssueCategory(

    icon:
    androidx.compose.ui.graphics.vector.ImageVector,

    title: String,

    background: Color,

    iconColor: Color,

    onClick: () -> Unit

) {

    Column(

        modifier =
            Modifier
                .width(75.dp)
                .clickable {

                    onClick()
                },

        horizontalAlignment =
            Alignment.CenterHorizontally
    ) {


        Box(

            modifier =
                Modifier
                    .size(55.dp)
                    .background(

                        background,

                        RoundedCornerShape(
                            16.dp
                        )
                    ),

            contentAlignment =
                Alignment.Center
        ) {

            Icon(

                imageVector =
                    icon,

                contentDescription =
                    title,

                tint =
                    iconColor,

                modifier =
                    Modifier.size(26.dp)
            )
        }


        Spacer(

            modifier =
                Modifier.height(7.dp)
        )


        Text(

            text =
                title,

            color =
                darkTextColor(),

            fontSize =
                11.sp,

            fontWeight =
                FontWeight.Medium
        )
    }
}


// ============================================================
// UPDATE CARD
// ============================================================

@Composable
fun UpdateCard(

    reportId: String,

    icon:
    androidx.compose.ui.graphics.vector.ImageVector,

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

        modifier =
            Modifier.fillMaxWidth(),

        shape =
            RoundedCornerShape(20.dp),

        colors =
            CardDefaults.cardColors(

                containerColor =
                    Color.White
            ),

        elevation =
            CardDefaults.cardElevation(

                defaultElevation =
                    2.dp
            )
    ) {


        Column(

            modifier =
                Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
        ) {


            // ====================================================
            // TOP PART OF CARD
            // ====================================================

            Row(

                modifier =
                    Modifier.fillMaxWidth(),

                verticalAlignment =
                    Alignment.Top
            ) {


                // ------------------------------------------------
                // REPORT ICON
                // ------------------------------------------------

                Box(

                    modifier =
                        Modifier
                            .size(48.dp)
                            .background(

                                iconBackground,

                                RoundedCornerShape(
                                    14.dp
                                )
                            ),

                    contentAlignment =
                        Alignment.Center
                ) {

                    Icon(

                        imageVector =
                            icon,

                        contentDescription =
                            title,

                        tint =
                            iconColor,

                        modifier =
                            Modifier.size(24.dp)
                    )
                }


                Spacer(

                    modifier =
                        Modifier.width(14.dp)
                )


                Column(

                    modifier =
                        Modifier.weight(1f)
                ) {


                    // =================================================
                    // TITLE
                    // =================================================

                    Row(

                        verticalAlignment =
                            Alignment.CenterVertically
                    ) {

                        Text(

                            text =
                                title,

                            color =
                                Color(0xFF1B1B1B),

                            fontSize =
                                15.sp,

                            fontWeight =
                                FontWeight.Bold
                        )


                        // ------------------------------------------------
                        // VERIFIED LABEL
                        // ------------------------------------------------

                        if (verified) {

                            Spacer(

                                modifier =
                                    Modifier.width(7.dp)
                            )


                            Text(

                                text =
                                    "✓ Verified",

                                color =
                                    Color(0xFF0B7A55),

                                fontSize =
                                    10.sp,

                                fontWeight =
                                    FontWeight.Bold
                            )
                        }
                    }


                    Spacer(

                        modifier =
                            Modifier.height(5.dp)
                    )


                    // =================================================
                    // DESCRIPTION
                    // =================================================

                    Text(

                        text =
                            description,

                        color =
                            Color(0xFF777777),

                        fontSize =
                            13.sp,

                        lineHeight =
                            18.sp
                    )


                    Spacer(

                        modifier =
                            Modifier.height(7.dp)
                    )


                    // =================================================
                    // TIME
                    // =================================================

                    Text(

                        text =
                            time,

                        color =
                            Color(0xFF999999),

                        fontSize =
                            11.sp
                    )
                }
            }


            Spacer(

                modifier =
                    Modifier.height(12.dp)
            )


            // ====================================================
            // VOTING SECTION
            // ====================================================

            Row(

                modifier =
                    Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.Start,

                verticalAlignment =
                    Alignment.CenterVertically
            ) {


                // =================================================
                // UPVOTE
                // =================================================

                Row(

                    modifier =
                        Modifier
                            .clickable {

                                voteOnReport(

                                    reportId =
                                        reportId,

                                    voteType =
                                        "up"
                                )
                            }

                            .padding(

                                horizontal = 8.dp,

                                vertical = 6.dp
                            ),

                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Icon(

                        imageVector =
                            Icons.Default.ThumbUp,

                        contentDescription =
                            "Upvote",

                        tint =
                            Color(0xFF0B7A55),

                        modifier =
                            Modifier.size(20.dp)
                    )


                    Spacer(

                        modifier =
                            Modifier.width(5.dp)
                    )


                    Text(

                        text =
                            upVotes.toString(),

                        color =
                            Color(0xFF0B7A55),

                        fontSize =
                            13.sp,

                        fontWeight =
                            FontWeight.Bold
                    )
                }


                Spacer(

                    modifier =
                        Modifier.width(18.dp)
                )


                // =================================================
                // DOWNVOTE
                // =================================================

                Row(

                    modifier =
                        Modifier
                            .clickable {

                                voteOnReport(

                                    reportId =
                                        reportId,

                                    voteType =
                                        "down"
                                )
                            }

                            .padding(

                                horizontal = 8.dp,

                                vertical = 6.dp
                            ),

                    verticalAlignment =
                        Alignment.CenterVertically
                ) {

                    Icon(

                        imageVector =
                            Icons.Default.ThumbDown,

                        contentDescription =
                            "Downvote",

                        tint =
                            Color(0xFFD32F2F),

                        modifier =
                            Modifier.size(20.dp)
                    )


                    Spacer(

                        modifier =
                            Modifier.width(5.dp)
                    )


                    Text(

                        text =
                            downVotes.toString(),

                        color =
                            Color(0xFFD32F2F),

                        fontSize =
                            13.sp,

                        fontWeight =
                            FontWeight.Bold
                    )
                }
            }
        }
    }
}


// ============================================================
// VOTE ON REPORT
// ============================================================

fun voteOnReport(

    reportId: String,

    voteType: String

) {

    // --------------------------------------------------------
    // GET CURRENT LOGGED-IN USER
    // --------------------------------------------------------

    val currentUser =
        FirebaseAuth
            .getInstance()
            .currentUser


    // --------------------------------------------------------
    // USER MUST BE LOGGED IN
    // --------------------------------------------------------

    if (currentUser == null) {

        Log.e(

            "MTAANI_VOTE",

            "User is not logged in."
        )

        return
    }


    val userId =
        currentUser.uid


    // --------------------------------------------------------
    // GET REPORT REFERENCE
    // --------------------------------------------------------

    val reportReference =
        FirebaseDatabase
            .getInstance()
            .getReference("reports")
            .child(reportId)


    // ========================================================
    // TRANSACTION
    // ========================================================

    reportReference.runTransaction(

        object : Transaction.Handler {

            override fun doTransaction(

                currentData: MutableData

            ): Transaction.Result {


                // ------------------------------------------------
                // CHECK REPORT EXISTS
                // ------------------------------------------------

                if (currentData.value == null) {

                    return Transaction.abort()
                }


                // ------------------------------------------------
                // CURRENT UP VOTES
                // ------------------------------------------------

                val currentUpVotes =
                    (

                            currentData
                                .child("upVotes")
                                .value as? Number

                            )

                        ?.toInt()

                        ?: 0


                // ------------------------------------------------
                // CURRENT DOWN VOTES
                // ------------------------------------------------

                val currentDownVotes =
                    (

                            currentData
                                .child("downVotes")
                                .value as? Number

                            )

                        ?.toInt()

                        ?: 0


                // ------------------------------------------------
                // GET USER CURRENT VOTE
                // ------------------------------------------------

                val existingVote =
                    currentData
                        .child("votes")
                        .child(userId)
                        .getValue(
                            String::class.java
                        )


                // ------------------------------------------------
                // COPY CURRENT COUNTS
                // ------------------------------------------------

                var newUpVotes =
                    currentUpVotes

                var newDownVotes =
                    currentDownVotes


                // =================================================
                // USER ALREADY VOTED
                // =================================================

                if (
                    existingVote ==
                    voteType
                ) {


                    // ------------------------------------------------
                    // REMOVE EXISTING VOTE
                    // ------------------------------------------------

                    if (
                        voteType ==
                        "up"
                    ) {

                        newUpVotes =
                            (
                                    newUpVotes - 1
                                    )
                                .coerceAtLeast(
                                    0
                                )

                    } else {

                        newDownVotes =
                            (
                                    newDownVotes - 1
                                    )
                                .coerceAtLeast(
                                    0
                                )
                    }


                    // ------------------------------------------------
                    // REMOVE USER VOTE
                    // ------------------------------------------------

                    currentData
                        .child("votes")
                        .child(userId)
                        .value =
                        null


                } else {


                    // =================================================
                    // USER CHANGES OR MAKES NEW VOTE
                    // =================================================

                    if (
                        existingVote ==
                        "up"
                    ) {

                        newUpVotes =
                            (
                                    newUpVotes - 1
                                    )
                                .coerceAtLeast(
                                    0
                                )
                    }


                    if (
                        existingVote ==
                        "down"
                    ) {

                        newDownVotes =
                            (
                                    newDownVotes - 1
                                    )
                                .coerceAtLeast(
                                    0
                                )
                    }


                    // ------------------------------------------------
                    // ADD NEW VOTE
                    // ------------------------------------------------

                    if (
                        voteType ==
                        "up"
                    ) {

                        newUpVotes++

                    } else {

                        newDownVotes++
                    }


                    // ------------------------------------------------
                    // SAVE USER VOTE
                    // ------------------------------------------------

                    currentData
                        .child("votes")
                        .child(userId)
                        .value =
                        voteType
                }


                // =================================================
                // SAVE NEW COUNTS
                // =================================================

                currentData
                    .child("upVotes")
                    .value =
                    newUpVotes


                currentData
                    .child("downVotes")
                    .value =
                    newDownVotes


                // =================================================
                // RETURN SUCCESS
                // =================================================

                return Transaction.success(
                    currentData
                )
            }


            override fun onComplete(

                error: DatabaseError?,

                committed: Boolean,

                currentData: DataSnapshot?

            ) {


                if (
                    error != null
                ) {

                    Log.e(

                        "MTAANI_VOTE",

                        "Vote failed: ${error.message}"
                    )

                    return
                }


                if (
                    committed
                ) {

                    Log.d(

                        "MTAANI_VOTE",

                        "Vote successfully saved."
                    )

                } else {

                    Log.d(

                        "MTAANI_VOTE",

                        "Vote transaction was not committed."
                    )
                }
            }
        }
    )
}


// ============================================================
// BOTTOM NAVIGATION ITEM
// ============================================================

@Composable
fun BottomNavigationItem(

    icon:
    androidx.compose.ui.graphics.vector.ImageVector,

    label: String,

    selected: Boolean,

    onClick: () -> Unit

) {


    val color =

        if (
            selected
        ) {

            Color(0xFF0B5D45)

        } else {

            Color(0xFF999999)
        }


    Column(

        modifier =
            Modifier.clickable {

                onClick()
            },

        horizontalAlignment =
            Alignment.CenterHorizontally
    ) {


        Icon(

            imageVector =
                icon,

            contentDescription =
                label,

            tint =
                color,

            modifier =
                Modifier.size(23.dp)
        )


        Spacer(

            modifier =
                Modifier.height(3.dp)
        )


        Text(

            text =
                label,

            color =
                color,

            fontSize =
                11.sp,

            fontWeight =

                if (
                    selected
                ) {

                    FontWeight.Bold

                } else {

                    FontWeight.Normal
                }
        )
    }
}


// ============================================================
// TEXT COLOR HELPER
// ============================================================

@Composable
fun darkTextColor(): Color {

    return Color(0xFF1B1B1B)
}


// ============================================================
// PREVIEW
// ============================================================

@Preview(
    showBackground = true
)
@Composable
fun HomeScreenPreview() {

    HomeScreen(

        navController =
            rememberNavController()
    )
}