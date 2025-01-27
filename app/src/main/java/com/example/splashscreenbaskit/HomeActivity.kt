import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.*
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
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.splashscreenbaskit.AccountActivity
import com.example.splashscreenbaskit.R

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
                    // Replace SlideImg with your image content
                    PageIndicator(currentPage = 0, totalScreens = 3)
                }
            }
        }
    }
}

@Composable
fun CartScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Cart Screen", fontSize = 20.sp)
        }
    }
}

@Composable
fun AccountScreen() {
    Surface(modifier = Modifier.fillMaxSize()) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Text(text = "Account Screen", fontSize = 20.sp)
        }
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

    BottomNavigation (
        backgroundColor = Color(0xFF1d7151)
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: BottomBarActivity.Screen,
    currentDestination: NavDestination?,
    navController: NavController
) {
    BottomNavigationItem(
        label = {
            Text(
                text = screen.title,
                color = if (currentDestination?.hierarchy?.any { it.route == screen.route } == true)
                    Color.White else Color.Gray // White when selected, Gray when unselected
            )
        },
        icon = {
            Icon(
                imageVector = screen.icon,
                contentDescription = "Navigation Icon",
                tint = if (currentDestination?.hierarchy?.any { it.route == screen.route } == true)
                    Color.White else Color.Gray // White for selected, Gray for unselected
            )
        },
        selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.startDestinationId) { saveState = true }
                launchSingleTop = true
                restoreState = true
            }
        }
    )
}

object BottomBarActivity {
    val Home = Screen("home", "Home", Icons.Default.Home)
    val Cart = Screen("cart", "Cart", Icons.Default.ShoppingCart)
    val Account = Screen("account", "Account", Icons.Default.AccountCircle)

    data class Screen(val route: String, val title: String, val icon: ImageVector)
}
