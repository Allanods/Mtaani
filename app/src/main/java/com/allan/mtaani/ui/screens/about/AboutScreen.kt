package com.allan.mtaani.ui.screens.about

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
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.ThumbDown
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.material.icons.filled.Warning
import androidx.compose.material.icons.filled.WaterDrop
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController


@Composable
fun AboutScreen(navController: NavController) {

    // ============================================================
    // COLORS
    // ============================================================

    val mtaaniGreen = Color(0xFF0B5D45)
    val darkText = Color(0xFF1B1B1B)
    val grayText = Color(0xFF777777)
    val backgroundColor = Color(0xFFF7F9F8)


    // ============================================================
    // MAIN SCREEN
    // ============================================================

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(backgroundColor)
    ) {

        // ========================================================
        // TOP BAR
        // ========================================================

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .background(mtaaniGreen)
                .padding(
                    top = 40.dp,
                    bottom = 18.dp,
                    start = 8.dp,
                    end = 20.dp
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
                text = "About Mtaani",
                color = Color.White,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold
            )
        }


        // ========================================================
        // SCROLLABLE CONTENT
        // ========================================================

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(
                    rememberScrollState()
                )
                .padding(20.dp)
        ) {

            // ====================================================
            // APP ICON
            // ====================================================

            Box(
                modifier = Modifier
                    .fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {

                Box(
                    modifier = Modifier
                        .size(90.dp)
                        .background(
                            color = mtaaniGreen,
                            shape = CircleShape
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = Icons.Default.LocationOn,
                        contentDescription = "Mtaani",
                        tint = Color.White,
                        modifier = Modifier.size(48.dp)
                    )
                }
            }


            Spacer(
                modifier = Modifier.height(15.dp)
            )


            // ====================================================
            // APP NAME
            // ====================================================

            Text(
                text = "MTAANI",
                modifier = Modifier.fillMaxWidth(),
                color = mtaaniGreen,
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                textAlign = TextAlign.Center,
                letterSpacing = 3.sp
            )

            Spacer(
                modifier = Modifier.height(5.dp)
            )

            Text(
                text = "Your community. Your voice. Your Mtaani.",
                modifier = Modifier.fillMaxWidth(),
                color = grayText,
                fontSize = 14.sp,
                textAlign = TextAlign.Center
            )


            Spacer(
                modifier = Modifier.height(25.dp)
            )


            // ====================================================
            // WHAT IS MTAANI?
            // ====================================================

            AboutCard(
                title = "What is Mtaani?",
                icon = Icons.Default.Info,
                iconColor = mtaaniGreen
            ) {

                Text(
                    text = "Mtaani is a community reporting application " +
                            "designed to help people in Karuri report " +
                            "and stay informed about local issues.",
                    color = grayText,
                    fontSize = 14.sp,
                    lineHeight = 21.sp
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Text(
                    text = "It provides a dedicated place where community " +
                            "members can report problems and see recent " +
                            "updates affecting their area.",
                    color = grayText,
                    fontSize = 14.sp,
                    lineHeight = 21.sp
                )
            }


            Spacer(
                modifier = Modifier.height(16.dp)
            )


            // ====================================================
            // WHAT CAN YOU REPORT?
            // ====================================================

            AboutCard(
                title = "What can you report?",
                icon = Icons.Default.Warning,
                iconColor = Color(0xFFD32F2F)
            ) {

                ReportType(
                    icon = Icons.Default.Bolt,
                    title = "Electricity",
                    description = "Report power outages and electricity issues.",
                    background = Color(0xFFFFF4D8),
                    iconColor = Color(0xFFE5A900)
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                ReportType(
                    icon = Icons.Default.WaterDrop,
                    title = "Water",
                    description = "Report water outages and water-related problems.",
                    background = Color(0xFFE3F2FD),
                    iconColor = Color(0xFF1976D2)
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                ReportType(
                    icon = Icons.Default.Delete,
                    title = "Waste",
                    description = "Report garbage and waste-related issues.",
                    background = Color(0xFFE8F4EF),
                    iconColor = mtaaniGreen
                )

                Spacer(
                    modifier = Modifier.height(12.dp)
                )

                ReportType(
                    icon = Icons.Default.Warning,
                    title = "Other",
                    description = "Report other important community issues.",
                    background = Color(0xFFFDEAEA),
                    iconColor = Color(0xFFD32F2F)
                )
            }


            Spacer(
                modifier = Modifier.height(16.dp)
            )


            // ====================================================
            // HOW REPORTING WORKS
            // ====================================================

            AboutCard(
                title = "How reporting works",
                icon = Icons.Default.Info,
                iconColor = mtaaniGreen
            ) {

                StepRow(
                    number = "1",
                    title = "Choose an issue",
                    description = "Select the category of the problem."
                )

                Spacer(
                    modifier = Modifier.height(14.dp)
                )

                StepRow(
                    number = "2",
                    title = "Describe the issue",
                    description = "Explain what is happening."
                )

                Spacer(
                    modifier = Modifier.height(14.dp)
                )

                StepRow(
                    number = "3",
                    title = "Add the location",
                    description = "Tell the community where the issue is happening."
                )

                Spacer(
                    modifier = Modifier.height(14.dp)
                )

                StepRow(
                    number = "4",
                    title = "Submit your report",
                    description = "The report appears in Recent Updates."
                )
            }


            Spacer(
                modifier = Modifier.height(16.dp)
            )


            // ====================================================
            // COMMUNITY VERIFICATION
            // ====================================================

            AboutCard(
                title = "Community Verification",
                icon = Icons.Default.CheckCircle,
                iconColor = Color(0xFF0B7A55)
            ) {

                Text(
                    text = "Mtaani allows community members to vote on " +
                            "reports. This helps the community indicate " +
                            "whether a report appears genuine.",
                    color = grayText,
                    fontSize = 14.sp,
                    lineHeight = 21.sp
                )

                Spacer(
                    modifier = Modifier.height(15.dp)
                )

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {

                    VoteItem(
                        icon = Icons.Default.ThumbUp,
                        title = "Upvote",
                        description = "Agree",
                        color = Color(0xFF0B7A55)
                    )

                    VoteItem(
                        icon = Icons.Default.ThumbDown,
                        title = "Downvote",
                        description = "Disagree",
                        color = Color(0xFFD32F2F)
                    )
                }

                Spacer(
                    modifier = Modifier.height(15.dp)
                )

                Text(
                    text = "Please vote responsibly and only vote based " +
                            "on what you know about the reported issue.",
                    color = grayText,
                    fontSize = 13.sp,
                    lineHeight = 20.sp
                )
            }


            Spacer(
                modifier = Modifier.height(16.dp)
            )


            // ====================================================
            // RECENT UPDATES
            // ====================================================

            AboutCard(
                title = "Recent Updates",
                icon = Icons.Default.LocationOn,
                iconColor = mtaaniGreen
            ) {

                Text(
                    text = "Recent Updates displays the latest reports " +
                            "submitted by members of the Karuri community.",
                    color = grayText,
                    fontSize = 14.sp,
                    lineHeight = 21.sp
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Text(
                    text = "Each report can show its category, description, " +
                            "location, time, votes and verification status.",
                    color = grayText,
                    fontSize = 14.sp,
                    lineHeight = 21.sp
                )
            }


            Spacer(
                modifier = Modifier.height(16.dp)
            )


            // ====================================================
            // REPORT EXPIRATION
            // ====================================================

            AboutCard(
                title = "Report Expiration",
                icon = Icons.Default.Info,
                iconColor = Color(0xFFE5A900)
            ) {

                Text(
                    text = "Mtaani keeps Recent Updates focused on current " +
                            "community issues.",
                    color = grayText,
                    fontSize = 14.sp,
                    lineHeight = 21.sp
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(
                    text = "Reports are automatically removed after 7 days.",
                    color = darkText,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Bold
                )
            }


            Spacer(
                modifier = Modifier.height(16.dp)
            )


            // ====================================================
            // RESPONSIBLE USE
            // ====================================================

            AboutCard(
                title = "Be a responsible community member",
                icon = Icons.Default.CheckCircle,
                iconColor = mtaaniGreen
            ) {

                Text(
                    text = "Please submit reports that are accurate, useful " +
                            "and relevant to the community.",
                    color = grayText,
                    fontSize = 14.sp,
                    lineHeight = 21.sp
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Text(
                    text = "Avoid submitting false or misleading information. " +
                            "Your reports and votes help other people understand " +
                            "what is happening around them.",
                    color = grayText,
                    fontSize = 14.sp,
                    lineHeight = 21.sp
                )
            }


            Spacer(
                modifier = Modifier.height(25.dp)
            )


            // ====================================================
            // FOOTER
            // ====================================================

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = "Karuri",
                    tint = mtaaniGreen,
                    modifier = Modifier.size(18.dp)
                )

                Spacer(
                    modifier = Modifier.width(5.dp)
                )

                Text(
                    text = "Serving the Karuri community",
                    color = grayText,
                    fontSize = 13.sp
                )
            }

            Spacer(
                modifier = Modifier.height(8.dp)
            )

            Text(
                text = "MTAANI • Community Reporting",
                modifier = Modifier.fillMaxWidth(),
                color = mtaaniGreen,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center
            )

            Spacer(
                modifier = Modifier.height(20.dp)
            )
        }
    }
}


// ============================================================
// ABOUT CARD
// ============================================================

@Composable
fun AboutCard(
    title: String,
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    iconColor: Color,
    content: @Composable () -> Unit
) {

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
                .padding(18.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {

                Box(
                    modifier = Modifier
                        .size(42.dp)
                        .background(
                            color = iconColor.copy(alpha = 0.12f),
                            shape = RoundedCornerShape(12.dp)
                        ),
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        imageVector = icon,
                        contentDescription = title,
                        tint = iconColor,
                        modifier = Modifier.size(22.dp)
                    )
                }

                Spacer(
                    modifier = Modifier.width(12.dp)
                )

                Text(
                    text = title,
                    color = Color(0xFF1B1B1B),
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(
                modifier = Modifier.height(15.dp)
            )

            content()
        }
    }
}


// ============================================================
// REPORT TYPE
// ============================================================

@Composable
fun ReportType(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    description: String,
    background: Color,
    iconColor: Color
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {

        Box(
            modifier = Modifier
                .size(45.dp)
                .background(
                    color = background,
                    shape = RoundedCornerShape(12.dp)
                ),
            contentAlignment = Alignment.Center
        ) {

            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = iconColor,
                modifier = Modifier.size(23.dp)
            )
        }

        Spacer(
            modifier = Modifier.width(12.dp)
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = title,
                color = Color(0xFF1B1B1B),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(2.dp)
            )

            Text(
                text = description,
                color = Color(0xFF777777),
                fontSize = 12.sp,
                lineHeight = 17.sp
            )
        }
    }
}


// ============================================================
// STEP ROW
// ============================================================

@Composable
fun StepRow(
    number: String,
    title: String,
    description: String
) {

    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.Top
    ) {

        Box(
            modifier = Modifier
                .size(32.dp)
                .background(
                    color = Color(0xFF0B5D45),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {

            Text(
                text = number,
                color = Color.White,
                fontSize = 13.sp,
                fontWeight = FontWeight.Bold
            )
        }

        Spacer(
            modifier = Modifier.width(12.dp)
        )

        Column(
            modifier = Modifier.weight(1f)
        ) {

            Text(
                text = title,
                color = Color(0xFF1B1B1B),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )

            Spacer(
                modifier = Modifier.height(2.dp)
            )

            Text(
                text = description,
                color = Color(0xFF777777),
                fontSize = 12.sp,
                lineHeight = 17.sp
            )
        }
    }
}


// ============================================================
// VOTE ITEM
// ============================================================

@Composable
fun VoteItem(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    description: String,
    color: Color
) {

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .size(48.dp)
                .background(
                    color = color.copy(alpha = 0.10f),
                    shape = CircleShape
                ),
            contentAlignment = Alignment.Center
        ) {

            Icon(
                imageVector = icon,
                contentDescription = title,
                tint = color,
                modifier = Modifier.size(24.dp)
            )
        }

        Spacer(
            modifier = Modifier.height(6.dp)
        )

        Text(
            text = title,
            color = color,
            fontSize = 13.sp,
            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(2.dp)
        )

        Text(
            text = description,
            color = Color(0xFF777777),
            fontSize = 11.sp
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
fun AboutScreenPreview() {

    AboutScreen(
        navController = rememberNavController()
    )
}