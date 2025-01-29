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
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.input.pointer.motionEventSpy
import androidx.navigation.NavDestination.Companion.hierarchy
import com.example.splashscreenbaskit.AccountActivity
import com.example.splashscreenbaskit.R
import com.example.splashscreenbaskit.SlideImg
import java.util.Locale.Category

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

    val textState = remember { mutableStateOf("") }

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

            Spacer(modifier = Modifier.height(0.dp))

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
                    //.padding(start = 30.dp, end = 30.dp)
                    .height(50.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                var searchText by remember { mutableStateOf(TextFieldValue("")) }

                /*Image(
                    painter = painterResource(id = R.drawable.searchq),
                    contentDescription = "Search Icon",
                    modifier = Modifier
                        .align(Alignment.CenterVertically)
                        .size(24.dp)
                )*/
                //Spacer(modifier = Modifier.width(10.dp))

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
                        if (textState.value.isEmpty()) {
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

            // Card with Image and Page Indicator
            Card(
                modifier = Modifier
                    .fillMaxWidth().height(155.dp),
                    //.padding(start = 30.dp, end = 30.dp),
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
        }

        Column(){
            Box(
                modifier = Modifier.padding(top = 400.dp)
            ){
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.White),
                    horizontalArrangement = Arrangement.Start
                ){
                    TextButton(
                        onClick = {  },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text("DAGUPAN", color = Color.Black, fontSize = 14.sp)
                    }

                    TextButton(
                        onClick = {  },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text("CALASIAO", color = Color.LightGray, fontSize = 14.sp)
                    }
                }
            }

            Spacer(modifier = Modifier.height(2.dp))

            Column (){
                Box(
                    modifier = Modifier.padding(10.dp),

                )
            }


            /*Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White),
                horizontalArrangement = Arrangement.Start
            ){
                TextButton(
                    onClick = {  },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text("SHOP", color = Color.Black, fontSize = 14.sp)
                }

                TextButton(
                    onClick = {  },
                    modifier = Modifier.padding(8.dp)
                ) {
                    Text("Fruits", color = Color.LightGray, fontSize = 14.sp)
                }
            }*/

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
                    Color.White else Color.LightGray
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
