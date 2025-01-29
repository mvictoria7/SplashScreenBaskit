import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.TextButton
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import com.example.splashscreenbaskit.AccountActivity
import com.example.splashscreenbaskit.R
import com.example.splashscreenbaskit.SlideImg

@Composable
fun PageIndicator(currentPage: Int, totalScreens: Int) {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(top = 170.dp, bottom = 20.dp)
            .fillMaxWidth()
    ) {
        repeat(totalScreens) { index ->
            Box(
                modifier = Modifier
                    .size(15.dp)
                    .padding(horizontal = 2.dp)
                    .background(
                        color = if (index == currentPage) Color(0xAA1d7151) else Color.Gray,
                        shape = CircleShape
                    )
            )
        }
    }
}

@Composable
fun CategoryRow(selectedCategory: MutableState<String?>) {
    val categories = listOf("SHOP", "Vegetables", "Fruits", "Meats", "Fish", "Spices", "Frozen Foods")
    val selectedCategoryLocal = selectedCategory.value

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(categories) { category ->
            TextButton(
                onClick = { selectedCategory.value = category },
                modifier = Modifier.padding(4.dp)
            ) {
                Text(
                    text = category,
                    color = if (selectedCategoryLocal == category) Color.Black else Color.Gray,
                    fontSize = 14.sp,
                    fontWeight = if (selectedCategoryLocal == category) FontWeight.Bold else FontWeight.Normal
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = BottomBarActivity.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(BottomBarActivity.Home.route) {
                HomeContent()
            }
            composable(BottomBarActivity.Cart.route) {
                CartScreen()
            }
            composable(BottomBarActivity.Account.route) {
                AccountActivity()
            }
        }
    }
}

@Composable
fun HomeContent() {
    // Remembering selected categories
    val selectedCategory = remember { mutableStateOf<String?>(null) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(45.dp))

            // Logo
            Image(
                painter = painterResource(id = R.drawable.baskit_logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(80.dp)
                    .padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Title
            Text(
                text = "Shop Smarter, Not Harder",
                fontSize = 12.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.offset(y = (-20).dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Search Bar with Notification Icon
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                var searchText by remember { mutableStateOf(TextFieldValue("")) }

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
                    singleLine = true,
                    decorationBox = { innerTextField ->
                        if (searchText.text.isEmpty()) {
                            Text(
                                text = "Search food, vegetable, etc.",
                                color = Color.Gray,
                                fontSize = 12.sp
                            )
                        }
                        innerTextField()
                    }
                )

                Spacer(modifier = Modifier.width(10.dp))

                // Notification Icon
                Icon(
                    painter = painterResource(id = R.drawable.notification),
                    contentDescription = "Notifications",
                    modifier = Modifier.size(20.dp)
                )
            }

            Spacer(modifier = Modifier.height(30.dp))

            // Move Page Indicator and SlideImg below search bar
            Card(
                modifier = Modifier
                    .fillMaxWidth().height(155.dp),
                shape = RoundedCornerShape(10.dp),
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
                    PageIndicator(currentPage = 0, totalScreens = 3)
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // Category Row for Dagupan and Calasiao
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                horizontalArrangement = Arrangement.Start
            ) {
                // Colors for Dagupan and Calasiao based on selection
                val dagupanColor = if (selectedCategory.value == "Dagupan") Color.Black else Color.Gray
                val calasiaoColor = if (selectedCategory.value == "Calasiao") Color.Black else Color.Gray

                TextButton(
                    onClick = {
                        selectedCategory.value = "Dagupan" // When Dagupan is clicked, set it as selected
                    },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text("DAGUPAN", color = dagupanColor, fontSize = 14.sp)
                }

                TextButton(
                    onClick = {
                        selectedCategory.value = "Calasiao" // When Calasiao is clicked, set it as selected
                    },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text("CALASIAO", color = calasiaoColor, fontSize = 14.sp)
                }
            }


            // Category Row (For other categories like Fish, Spices, etc.)
            CategoryRow(selectedCategory)
        }
    }
}

@Composable
fun CartScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Cart Screen", fontSize = 20.sp)
    }
}

@Composable
fun AccountScreen() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Text(text = "Account Screen", fontSize = 20.sp)
    }
}

@Composable
fun BottomBar(navController: NavController) {
    val screens = listOf(
        BottomBarActivity.Home,
        BottomBarActivity.Cart,
        BottomBarActivity.Account,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(backgroundColor = Color(0xFF1d7151)) {
        screens.forEach { screen ->
            BottomNavigationItem(
                label = { Text(screen.title) },
                icon = {
                    Icon(
                        imageVector = screen.icon,
                        contentDescription = null
                    )
                },
                selected = currentDestination?.route == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) { saveState = true }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

object BottomBarActivity {
    val Home = Screen("home", "Home", Icons.Default.Home)
    val Cart = Screen("cart", "Cart", Icons.Default.ShoppingCart)
    val Account = Screen("account", "Account", Icons.Default.AccountCircle)

    data class Screen(val route: String, val title: String, val icon: ImageVector)
}
