import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.verticalScroll
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.splashscreenbaskit.R
import com.example.splashscreenbaskit.SlideImg

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
    Vendor("Vendor 1", R.drawable.testimg),
    Vendor("Vendor 2", R.drawable.testimg),
    Vendor("Vendor 3", R.drawable.testimg),
    Vendor("Vendor 4", R.drawable.testimg),

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
fun CategoryRow(selectedCategory: MutableState<String?>) {
    val categories = listOf("SHOP", "Vegetables", "Fruits", "Meats", "Fish", "Spices", "Frozen Foods")

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
                    color = if (selectedCategory.value == category) Color.Black else Color.Gray,
                    fontSize = 14.sp,
                    fontWeight = if (selectedCategory.value == category) FontWeight.Bold else FontWeight.Normal
                )
            }
        }
    }
}

@Composable
fun VendorGrid(products: List<Vendor>) {
    Column(modifier = Modifier.fillMaxWidth()) {
        products.chunked(2).forEach { rowVendor ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                rowVendor.forEach { vendor ->
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp),
                        shape = RoundedCornerShape(10.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = vendor.imageRes),
                                contentDescription = "Product Image",
                                modifier = Modifier.size(120.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(vendor.name, fontWeight = FontWeight.Bold)
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
fun FruitGrid(fruits: List<Fruits>) {
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
                            .padding(4.dp),
                        shape = RoundedCornerShape(10.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .padding(8.dp),
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
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
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
                AccountScreen()
            }
            composable("vendorDetail/{vendorName}") { backStackEntry ->
                val vendorName = backStackEntry.arguments?.getString("vendorName") ?: "Unknown Vendor"
                VendorDetailScreen(vendorName)
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
                .fillMaxWidth()
                .verticalScroll(scrollState)
                .padding(horizontal = 30.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(45.dp))

            Image(
                painter = painterResource(id = R.drawable.baskit_logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(80.dp)
                    .padding(bottom = 16.dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Shop Smarter, Not Harder",
                fontSize = 12.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.offset(y = (-20).dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            SearchBar()

            Spacer(modifier = Modifier.height(30.dp))

            SliderCard()

            Spacer(modifier = Modifier.height(20.dp))

            LocationSelector(selectedLocation)

            CategoryRow(selectedCategory)

            when (selectedCategory.value) {
                "Fruits" -> FruitGrid(listOf(
                    Fruits("Apple", R.drawable.testimg),
                    Fruits("Orange", R.drawable.testimg)
                ))
                else -> VendorGrid(sampleProducts, navController)
            }

            Spacer(modifier = Modifier.height(80.dp))
        }
    }
}

@Composable
fun VendorDetailScreen(vendorName: String) {
    val products = listOf(
        Fruits("Apple", R.drawable.testimg),
        Fruits("Orange", R.drawable.testimg),
        Fruits("Banana", R.drawable.testimg)
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "$vendorName's Products", fontSize = 20.sp, fontWeight = FontWeight.Bold)
        Spacer(modifier = Modifier.height(10.dp))
        FruitGrid(products)
    }
}

@Composable
fun VendorGrid(products: List<Vendor>, navController: NavController) {
    Column(modifier = Modifier.fillMaxWidth()) {
        products.chunked(2).forEach { rowVendor ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                rowVendor.forEach { vendor ->
                    Card(
                        modifier = Modifier
                            .weight(1f)
                            .padding(4.dp)
                            .clickable {
                                navController.navigate("vendorDetail/${vendor.name}")
                            },
                        shape = RoundedCornerShape(10.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(
                            modifier = Modifier.padding(8.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Image(
                                painter = painterResource(id = vendor.imageRes),
                                contentDescription = "Product Image",
                                modifier = Modifier.size(120.dp)
                            )
                            Spacer(modifier = Modifier.height(8.dp))
                            Text(vendor.name, fontWeight = FontWeight.Bold)
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
            .height(50.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
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

        Icon(
            painter = painterResource(id = R.drawable.notification),
            contentDescription = "Notifications",
            modifier = Modifier.size(20.dp)
        )
    }
}

@Composable
fun SliderCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .height(155.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            SlideImg(modifier = Modifier.fillMaxSize())
            PageIndicator(currentPage = 0, totalScreens = 3)
        }
    }
}

@Composable
fun LocationSelector(selectedLocation: MutableState<String?>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        horizontalArrangement = Arrangement.Start
    ) {
        TextButton(
            onClick = { selectedLocation.value = "Dagupan" },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                "DAGUPAN",
                color = if (selectedLocation.value == "Dagupan") Color.Black else Color.Gray,
                fontSize = 14.sp
            )
        }

        TextButton(
            onClick = { selectedLocation.value = "Calasiao" },
            modifier = Modifier.padding(8.dp)
        ) {
            Text(
                "CALASIAO",
                color = if (selectedLocation.value == "Calasiao") Color.Black else Color.Gray,
                fontSize = 14.sp
            )
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
        BottomBarScreen.Home,
        BottomBarScreen.Cart,
        BottomBarScreen.Account,
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
                        contentDescription = screen.title
                    )
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