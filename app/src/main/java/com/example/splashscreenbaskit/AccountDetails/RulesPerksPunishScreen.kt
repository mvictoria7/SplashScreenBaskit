package com.example.splashscreenbaskit.AccountDetails

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.splashscreenbaskit.ui.theme.poppinsFontFamily

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun RulesPerksPunishScreen(navController: NavHostController) {
    val scrollState = rememberScrollState()
    var isScrolledToEnd by remember { mutableStateOf(false) }

    LaunchedEffect(scrollState.value) {
        isScrolledToEnd = scrollState.value >= scrollState.maxValue - 10
    }

    Column(modifier = Modifier.fillMaxSize()) {
        TopAppBar(
            title = {
                Text(
                    text = "< Back",
                    fontSize = 14.sp,
                    color = Color.Black,
                    fontWeight = FontWeight.SemiBold,
                    fontFamily = poppinsFontFamily,
                    modifier = Modifier
                        .padding(start = 20.dp)
                        .clickable { navController.popBackStack() }
                )
            }
        )

        Text(
            text = "Start your Own Store",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            fontFamily = poppinsFontFamily,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp)
        )

        Box(modifier = Modifier.weight(1f)) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(16.dp)
            ) {
                Section(title = "Rules and Regulations", color = Color(0xFFDFF2D8)) {
                    Text("1. Users must provide accurate information when registering and making purchases.")
                    Text("2. Orders must be confirmed before generating a unique code for in-store pickup.")
                    Text("3. Generated codes are valid only within the specified time frame.")
                    Text("4. Users must respect the terms and conditions of partner shops.")
                    Text("5. Fraudulent activities, such as generating codes without intent to purchase, will result in penalties.")
                }

                Section(title = "Perks of standard and partnership shops", color = Color(0xFFFFF8D6)) {
                    Text("Standard Shops:")
                    Text("1. Access to the Baskit platform with basic product listing features.")
                    Text("2. Ability to reach a broader customer base within the local market.")
                    Text("3. Notifications for incoming orders and real-time tracking.")
                    Text("4. Secure and seamless order management system.")
                    Text("5. Inclusion in seasonal promotions and offers.")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text("Partnership Shops:")
                    Text("1. All benefits of Standard Shops.")
                    Text("2. Priority listing and enhanced visibility on the app.")
                    Text("3. Access to advanced analytics and sales tracking.")
                    Text("4. Promotional support through in-app ads and featured sections.")
                    Text("5. Exclusive participation in platform-wide sales and marketing campaigns.")
                }

                Section(title = "Punishment for offenses", color = Color(0xFFE3F2FD)) {
                    Text("1. First Offense (Minor Violation) – Warning notification.")
                    Text("2. Second Offense (Repeated Violations) – Temporary suspension (3–7 days).")
                    Text("3. Third Offense (Severe Violations) – Permanent account ban.")
                    Text("4. Fraudulent Activities (Fake Orders, Code Misuse, etc.) – Immediate suspension and potential legal action.")
                    Text("5. Harassment or Abusive Behavior Towards Vendors – Immediate suspension and potential permanent ban.")
                }
            }
        }

        Button(
            onClick = { navController.navigate("ShopInformationScreen") },
            enabled = isScrolledToEnd,
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = if (isScrolledToEnd) Color(0xFF1D7151) else Color.Gray
            )
        ) {
            Text("Accept", color = Color.White)
        }
    }
}

@Composable
fun Section(title: String, color: Color, content: @Composable ColumnScope.() -> Unit) {
    Card(
        colors = CardDefaults.cardColors(containerColor = color),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                fontFamily = poppinsFontFamily
            )
            Spacer(modifier = Modifier.height(8.dp))
            content()
        }
    }
}

