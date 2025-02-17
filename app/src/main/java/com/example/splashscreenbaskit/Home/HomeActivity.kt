
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigation
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.BottomNavigationItem
//noinspection UsingMaterialAndMaterial3Libraries
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.splashscreenbaskit.AccountDetails.AccountActivity
import com.example.splashscreenbaskit.Products.AppleScreen
import com.example.splashscreenbaskit.Carts.CartApple
import com.example.splashscreenbaskit.Carts.CartOrangeScreen
import com.example.splashscreenbaskit.AccountDetails.NotificationsActivity
import com.example.splashscreenbaskit.Products.OrangeScreen
import com.example.splashscreenbaskit.R
import com.example.splashscreenbaskit.AccountDetails.SettingsActivity
import com.example.splashscreenbaskit.Carts.CartApple
import com.example.splashscreenbaskit.Carts.CartAppleScreen
import com.example.splashscreenbaskit.Carts.CartScreen
import com.example.splashscreenbaskit.Home.SlideImg
import com.example.splashscreenbaskit.LoginSignup.LoginActivity
import com.example.splashscreenbaskit.ui.theme.poppinsFontFamily




// Data classes
data class Vendor(
    val name: String,
    val imageRes: Int
)

data class Fruits(
    val name: String,
    val imageRes: Int
)

//data class Product(
//    val name: String,
//    val price: Double,
//    val description: String,
//    val imageRes: Int,
//    val vendorName: String
//)

val sampleProducts = listOf(
    Vendor("Product 1", R.drawable.food),
    Vendor("Product 2", R.drawable.food),
    Vendor("Product 3", R.drawable.food),
    Vendor("Product 4", R.drawable.food),
    Vendor("Product 5", R.drawable.food),
    Vendor("Product 6", R.drawable.food),
    Vendor("Product 7", R.drawable.food),
    Vendor("Product 8", R.drawable.food)

    )

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
fun CategoryRow(selectedCategory: MutableState<String?>, navController: NavController) {
    val categories = listOf("SHOP", "Vegetables", "Fruits", "Meats", "Fish", "Spices", "Frozen Foods")

    LazyRow(
        modifier = Modifier.fillMaxWidth() .padding(top = 5.dp, start = 8.dp, end = 10.dp),
    ) {
        items(categories) { category ->
            TextButton(
                modifier = Modifier
                    .background(
                        color = if (selectedCategory.value == category) Color(0xBB5CC163) else Color.Transparent,
                        shape = RoundedCornerShape(20.dp),
                    )
                    .wrapContentHeight()
                    .heightIn(min = 20.dp),
                onClick = { selectedCategory.value = category}
            ) {
                Text(
                    text = category,
                    color = if (selectedCategory.value == category) Color(0xFFFFFFFF) else Color(0xFFBFBFBF),
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    fontFamily = poppinsFontFamily,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun FruitGrid(fruits: List<Fruits>, navController: NavController) {
    Column(modifier = Modifier.fillMaxWidth()) {
        fruits.chunked(2).forEach { rowFruits ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                rowFruits.forEach { fruit ->
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp)
                            .clickable {
                                if (fruit.name == "Apple") {
                                    navController.navigate("AppleScreen")
                                }
                                if (fruit.name == "Orange") {
                                    navController.navigate("OrangeScreen")
                                }
                            },
                        shape = RoundedCornerShape(10.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = fruit.imageRes),
                                contentDescription = "Fruit Image",
                                modifier = Modifier.size(120.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(fruit.name, fontWeight = FontWeight.Bold)
                        }
                    }
                }
                if (rowFruits.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            if (currentRoute != "AppleScreen" && currentRoute != "OrangeScreen") {
                BottomBar(navController = navController)
            }

        }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = BottomBarScreen.Home.route,
            modifier = Modifier.padding(paddingValues)
        ) {
            composable(BottomBarScreen.Home.route) {
                HomeContent(navController)
            }
            composable(BottomBarScreen.Cart.route) {
                CartScreen()
            }
            composable(BottomBarScreen.Account.route) {
                AccountActivity(navController)
            }
            composable("NotificationsActivity") {
                NotificationsActivity(navController)
            }
            composable("SettingsActivity") {
                SettingsActivity(navController)
            }
            composable("AppleScreen") {
                AppleScreen(navController)
            }
            composable("OrangeScreen") {
                OrangeScreen(navController)
            }
            composable("CartScreen") {
                CartScreen()
            }
            composable("NotificationsActivity") {
                NotificationsActivity(navController)
            }
            composable("SettingsActivity") {
                SettingsActivity(navController)
            }
            composable ("LoginActivity") {
                LoginActivity(navController)
            }
        }
    }
}

@Composable
fun HomeContent(navController: NavController) {
    val selectedCategory = remember { mutableStateOf<String?>(null) }
    val selectedLocation = remember { mutableStateOf<String?>("Dagupan") }
    val scrollState = rememberScrollState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                //.padding(horizontal = 30.dp)
                .fillMaxWidth()
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(45.dp))

            Image(
                painter = painterResource(id = R.drawable.baskit_logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(70.dp)
                    .padding(bottom = 16.dp)
            )

            Text(
                text = "Shop Smarter, Not Harder",
                fontFamily = poppinsFontFamily,
                fontSize = 12.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.offset(y = (-20).dp)
            )

            //Spacer(modifier = Modifier.height(10.dp))

            SearchBar()

            Spacer(modifier = Modifier.height(30.dp))

            SliderCard()

            Spacer(modifier = Modifier.height(15.dp))

            LocationSelector(selectedLocation)

            HorizontalDivider(modifier = Modifier.padding(vertical = 4.dp, horizontal = 4.dp), thickness = 2.dp)

            CategoryRow(selectedCategory, navController)

            Spacer(modifier = Modifier.height(5.dp))

            when (selectedCategory.value) {
                "Fruits" -> FruitGrid(
                    listOf(
                        Fruits("Apple", R.drawable.apple),
                        Fruits("Orange", R.drawable.orange),
                        Fruits("Banana", R.drawable.banana),
                        Fruits("Mango", R.drawable.mango),
                        Fruits("Grapes", R.drawable.grapes),
                        Fruits("Pineapple", R.drawable.pineapple)
                    ),
                    navController
                )
                else -> VendorGrid(sampleProducts, navController)
            }
        }
    }
}

@Composable
fun VendorDetailScreen(vendorName: String) {
    val products = listOf(
        Fruits("Apple", R.drawable.apple),
        Fruits("Orange", R.drawable.orange),
        Fruits("Banana", R.drawable.banana),
        Fruits("Mango", R.drawable.mango),
        Fruits("Grapes", R.drawable.grapes),
        Fruits("Pineapple", R.drawable.pineapple)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "$vendorName's Products", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun VendorGrid(products: List<Vendor>, navController: NavController) {
    Column(modifier = Modifier.fillMaxWidth()) {
        products.chunked(2).forEach { rowVendor ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp, bottom = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                rowVendor.forEach { vendor ->
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0)),
                        modifier = Modifier
                            .weight(1f)
                            .height(170.dp)
                            .width(154.dp)
                            .padding(4.dp),
                        shape = RoundedCornerShape(10.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)

                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 5.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Image(
                                painter = painterResource(id = vendor.imageRes),
                                contentDescription = "Product Image",
                                modifier = Modifier
                                    .height(100.dp)
                                    .width(135.dp)
                                    .padding(top = 8.dp)
                                    .clip(RoundedCornerShape(10.dp))
                            )
                            Spacer(modifier = Modifier.height(8.dp))

                            Text(vendor.name,
                                fontWeight = FontWeight.SemiBold,
                                fontSize = 16.sp,
                                fontFamily = poppinsFontFamily,
                                modifier = Modifier.padding(8.dp))
                        }
                    }
                }
                if (rowVendor.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}
@Composable
fun SearchBar() {
    var searchText by remember { mutableStateOf(TextFieldValue("")) }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(50.dp)
            .padding(start = 30.dp, end = 30.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        BasicTextField(
            value = searchText,
            onValueChange = { searchText = it },
            modifier = Modifier
                .height(50.dp)
                .weight(1f)
                .background(
                    color = Color(0xFFF5F5F5),
                    shape = RoundedCornerShape(10.dp)
                )
                .padding(horizontal = 16.dp, vertical = 12.dp),
            singleLine = true,
            decorationBox = { innerTextField ->
                Box(
                    contentAlignment = Alignment.CenterStart, // Center horizontally
                    modifier = Modifier.fillMaxWidth() // Make the Box fill the available width
                ) {
                    if (searchText.text.isEmpty()) {
                        Text(
                            text = "Search food, vegetable, etc.",
                            color = Color.Gray,
                            fontSize = 14.sp,
                            fontFamily = poppinsFontFamily
                        )
                    }
                    innerTextField() // This ensures the inner text field is still drawn
                }
            }
        )

        Spacer(modifier = Modifier.width(10.dp))

        Icon(
            Icons.Default.Notifications,
            contentDescription = "Notifications Icon",
            modifier = Modifier.size(25.dp)
        )
    }
}

@Composable
fun SliderCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(155.dp)
            .padding(start = 30.dp, end = 30.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            PageIndicator(currentPage = 0, totalScreens = 3)
            SlideImg(modifier = Modifier.fillMaxSize())
        }
    }
}

@Composable
fun LocationSelector(selectedLocation: MutableState<String?>) {
    Row(
        modifier = Modifier
            .padding(start = 8.dp)
            .fillMaxWidth()
            .background(Color.White),
        horizontalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        TextButton(
            onClick = { selectedLocation.value = "Dagupan" },
        ) {
            Text(
                "DAGUPAN",
                color = if (selectedLocation.value == "Dagupan") Color.Gray else Color(0xFFBFBFBF),
                fontSize = 15.sp,fontFamily = poppinsFontFamily,
                fontWeight = FontWeight.Bold,
                style = TextStyle(fontFamily = poppinsFontFamily)
            )
        }

        TextButton(
            onClick = { selectedLocation.value = "Calasiao" },
        ) {
            Text(
                "CALASIAO",
                color = if (selectedLocation.value == "Calasiao") Color.Gray else Color(0xFFBFBFBF),
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                style = TextStyle(fontFamily = poppinsFontFamily)
            )
        }
    }
}



@Composable
fun BottomBar(navController: NavController) {
    val screens = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Cart,
        BottomBarScreen.Account,
    )
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation(
        backgroundColor = Color(0xFF1d7151),
        modifier = Modifier.height(80.dp)
    ) {
        screens.forEach { screen ->
            BottomNavigationItem(
                label = {
                    Text(
                        text = screen.title,
                        fontFamily = poppinsFontFamily,
                        color = if (currentDestination?.route == screen.route) Color.White else Color.Gray
                    )
                },

                icon = {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(50.dp) // Adjust size of the circle
                            .background(
                                color = if (currentDestination?.route == screen.route) Color.Gray else Color.White,
                                shape = CircleShape
                            )
                            .padding(6.dp) // Padding for the icon inside the circle
                    ) {
                        Icon(
                            imageVector = screen.icon,
                            contentDescription = screen.title,
                            tint = if (currentDestination?.route == screen.route) Color.White else Color.Gray
                        )
                    }
                },
                selected = currentDestination?.route == screen.route,
                onClick = {
                    navController.navigate(screen.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )

        }
    }
}

sealed class BottomBarScreen(val route: String, val title: String, val icon: ImageVector) {
    object Home : BottomBarScreen("home", "Home", Icons.Default.Home)
    object Cart : BottomBarScreen("cart", "Cart", Icons.Default.ShoppingCart)
    object Account : BottomBarScreen("account", "Account", Icons.Default.AccountCircle)
}
