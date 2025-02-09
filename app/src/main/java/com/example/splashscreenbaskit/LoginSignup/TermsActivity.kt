package com.example.splashscreenbaskit.LoginSignup

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.splashscreenbaskit.ui.theme.poppinsFontFamily

@Preview(showBackground = true)
@Composable
fun TermsActivityPreview() {
    TermsActivity(navController = rememberNavController())
}

@Composable
fun TermsActivity(navController: NavController) {
    Column(
        modifier = Modifier
            .padding(top = 70.dp, start = 40.dp, end = 40.dp, bottom = 80.dp)
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Terms and Conditions",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            fontFamily = poppinsFontFamily,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text(
            text = "Welcome to Baskit!\nThese Terms and Conditions govern your use\nof our delivery app and services.".trimIndent(),
            fontSize = 12.sp,
            fontFamily = poppinsFontFamily,
            textAlign = TextAlign.Center,
            color = Color.Black
        )

        Spacer(modifier = Modifier.height(40.dp))

        Text (text = "Terms",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = poppinsFontFamily
        )

        Spacer(modifier = Modifier.height(5.dp))

        Text(
            text = """
                      You must be at least 18 years old to use Baskit and agree to provide accurate personal information when creating an account.
                      You are responsible for maintaining the confidentiality of your login details.
                      Orders placed through the app are subject to availability and acceptance by merchants.
                      Prices displayed on the app include applicable charges unless stated otherwise, and payment must be completed before order confirmation.

                      Delivery times are estimated and may vary due to unforeseen circumstances.
                      Users must provide accurate delivery addresses, and if a recipient is unavailable, the order may be canceled or rescheduled at the user’s cost.
                      Orders can only be canceled before they are accepted by the merchant.
                      Refunds, if applicable, will be processed according to Baskit’s refund policy.

                      Users must not misuse the app, engage in fraud, or harass others.
                      Baskit reserves the right to suspend or terminate accounts that violate these terms.
                      We act as an intermediary between users and merchants and are not responsible for product quality.
                      Additionally, we are not liable for delays or losses due to factors beyond our control.

                      By using Baskit, you agree to our Privacy Policy regarding data collection and usage.
                      We reserve the right to update these terms at any time, and continued use of the app signifies acceptance of any modifications.
                      If you have any questions or concerns, please contact us at Baskit.
                      """.trimIndent(),
            fontSize = 12.sp,
            fontFamily = poppinsFontFamily,
            color = Color.Black
        )

    }
}