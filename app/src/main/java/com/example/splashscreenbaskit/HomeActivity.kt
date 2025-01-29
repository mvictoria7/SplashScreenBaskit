import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.splashscreenbaskit.R
import com.example.splashscreenbaskit.SlideImg

@Composable
fun PageIndicator(currentPage: Int, totalScreens: Int) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(top = 170.dp, bottom = 20.dp)
            .fillMaxWidth(),

    )
    {
        repeat(totalScreens) { index ->
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .padding(horizontal = 1.dp)
                    .background(
                        color = if (index == currentPage) Color(0xAA1d7151) else Color.Gray,
                        shape = CircleShape
                    )
                    .offset(y = 2.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreen() {
    var searchText by remember { mutableStateOf("text") }
    val totalScreens = 3
    var currentPage by remember { mutableStateOf(0)}

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.height(32.dp))

            // Logo
            Image(
                painter = painterResource(id = R.drawable.baskit_logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(80.dp)
                    .padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier.height(0.dp))

            // Title
            Text(
                text = "Shop Smarter, Not Harder",
                fontSize = 15.sp,
                color = Color.Black,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.offset(y = (-20).dp)
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Search Bar with Notification Icon
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                // Search Bar
                var searchText by remember { mutableStateOf(TextFieldValue("")) }

                Image(
                    painter = painterResource(id = R.drawable.searchq),
                    contentDescription = "Search Icon",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .size(24.dp)
                )

                BasicTextField(
                    value = searchText,
                    onValueChange = { searchText = it },
                    modifier = Modifier
                        .weight(1f)
                        .background(
                            color = Color(0xFFF5F5F5),
                            shape = RoundedCornerShape(10.dp)
                        )
                        .padding(horizontal = 16.dp, vertical = 12.dp),
                    singleLine = true
                )

                Spacer(modifier = Modifier.width(8.dp))

                // Notification Icon
                Icon(
                    painter = painterResource(id = R.drawable.notification),
                    contentDescription = "Notifications",
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Card with Image and Page Indicator
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                shape = RoundedCornerShape(16.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color.White
                ),
                elevation = CardDefaults.cardElevation(
                    defaultElevation = 4.dp
                )
            ) {
                Box(
                    modifier = Modifier.fillMaxSize()
                ) {
                    SlideImg(modifier = Modifier.fillMaxSize())
                    PageIndicator(currentPage = currentPage, totalScreens = totalScreens)
                }
            }

        }
    }
}
