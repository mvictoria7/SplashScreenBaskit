import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.splashscreenbaskit.R
import com.example.splashscreenbaskit.SlideImg

// Data classes
data class Product(
    val name: String,
    val imageRes: Int
)

data class Fruits(
    val name: String,
    val imageRes: Int
)

data class Vegetables(
    val name: String,
    val imageRes: Int
)

val sampleProducts = listOf(
    Product("Product 1", R.drawable.lettuce),
    Product("Product 2", R.drawable.testimg),
    Product("Product 3", R.drawable.lettuce),
    Product("Product 4", R.drawable.testimg)
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

//Categories
@Composable
fun CategoryRow(selectedCategory: MutableState<String?>) {
    val categories = listOf("SHOP", "Vegetables", "Fruits", "Meat", "Fish", "Spices", "Frozen Foods")

    LazyRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 2.dp),
        horizontalArrangement = Arrangement.spacedBy(2.dp)
    ) {
        items(categories) { category ->
            TextButton(
                onClick = { selectedCategory.value = category },
                modifier = Modifier.padding(end = 2.dp)
            ) {
                Text(
                    text = category,
                    color = if (selectedCategory.value == category) Color(0xFF8C8C8C) else Color(0xFFBFBFBF),
                    fontSize = 14.sp,
                    fontWeight = if (selectedCategory.value == category) FontWeight.Bold else FontWeight.Bold
                )
            }
        }
    }
}



//products
@Composable
fun ProductGrid(products: List<Product>) {
    Column(modifier = Modifier.fillMaxWidth()
    ) {
        products.chunked(2).forEach { rowProducts ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                rowProducts.forEach { product ->
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0)),
                        modifier = Modifier
                            .size(170.dp)
                            .weight(1f),
                        shape = RoundedCornerShape(10.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 5.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,

                        ) {
                            Image(
                                painter = painterResource(id = product.imageRes),
                                contentDescription = "Product Image",
                                modifier = Modifier
                                    .height(100.dp)
                                    .width(135.dp)
                                    .padding(2.dp)
                                    .clip(RoundedCornerShape(10.dp))
                            )
                            Spacer(modifier = Modifier.height(15.dp))

                            Text(product.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        }
                    }
                }
                if (rowProducts.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}

//veggies
/*@Composable
fun VegetablesGrid(products: List<Vegetables>) {
    Column(modifier = Modifier.fillMaxWidth()
    ) {
        vegetables.chunked(2).forEach { rowVegetables ->
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                rowVegetables.forEach { vegetables ->
                    Card(
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0)),
                        modifier = Modifier
                            .size(170.dp)
                            .weight(1f),
                        shape = RoundedCornerShape(10.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 5.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,

                            ) {
                            Image(
                                painter = painterResource(id = vegetables.imageRes),
                                contentDescription = "Product Image",
                                modifier = Modifier
                                    .height(100.dp)
                                    .width(135.dp)
                                    .padding(2.dp)
                                    .clip(RoundedCornerShape(10.dp))
                            )
                            Spacer(modifier = Modifier.height(15.dp))

                            Text(vegetables.name, fontWeight = FontWeight.Bold, fontSize = 18.sp)
                        }
                    }
                }
                if (rowVegetables.size == 1) {
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        }
    }
}*/

//Fruits
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
                        colors = CardDefaults.cardColors(containerColor = Color(0xFFF0F0F0)),
                        modifier = Modifier
                            .size(170.dp)
                            .weight(1f)
                            .padding(2.dp),
                        shape = RoundedCornerShape(10.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 15.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center,

                            ) {
                            Image(
                                painter = painterResource(id = fruit.imageRes),
                                contentDescription = "Product Image",
                                modifier = Modifier
                                    .height(100.dp)
                                    .width(135.dp)
                                    .padding(10.dp)
                                    .clip(RoundedCornerShape(10.dp))
                            )
                            //Spacer(modifier = Modifier.height(8.dp))
                            Text(fruit.name, fontWeight = FontWeight.Bold)
                        }
                    }
                    Spacer(modifier = Modifier.width(16.dp))
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
                HomeContent()
            }
            composable(BottomBarScreen.Cart.route) {
                CartScreen()
            }
            composable(BottomBarScreen.Account.route) {
                AccountScreen()
            }
        }
    }
}

@Composable
fun HomeContent() {
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

            // Logo
            Image(
                painter = painterResource(id = R.drawable.baskit_logo),
                contentDescription = "Logo",
                modifier = Modifier
                    .size(80.dp)
                    .padding(bottom = 16.dp)
            )

            // Title
            Text(
                text = "Shop Smarter, Not Harder",
                fontSize = 12.sp,
                color = Color.Black,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.offset(y = (-20).dp)
            )

            Spacer(modifier = Modifier.height(10.dp))

            // Search Bar
            SearchBar()

            Spacer(modifier = Modifier.height(30.dp))

            // Slider Card
            SliderCard()

            Spacer(modifier = Modifier.height(20.dp))

            // Location Selection
            LocationSelector(selectedLocation)

            // Category Row
            CategoryRow(selectedCategory)

            when (selectedCategory.value) {
                "Fruits" -> FruitGrid(listOf(
                    Fruits("Apple", R.drawable.testimg),
                    Fruits("Orange", R.drawable.testimg),
                    Fruits("Mango", R.drawable.testimg),
                    Fruits("Banana", R.drawable.testimg),
                    Fruits("Grapes", R.drawable.testimg),
                    Fruits("Orange", R.drawable.testimg)
                ))
                else -> ProductGrid(sampleProducts)
            }

            /*when (selectedCategory.value) {
                "Vegetables" -> VegetablesGrid(listOf(
                    Vegetables("Lettuce", R.drawable.testimg),
                    Vegetables("Cabbage", R.drawable.testimg),
                    Vegetables("Kangkong", R.drawable.testimg),
                    Vegetables("Eggplant", R.drawable.testimg)
                ))
                else -> ProductGrid(sampleProducts)
            }*/


            Spacer(modifier = Modifier.height(80.dp))
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
            .padding(2.dp)
            .background(Color.White),
        horizontalArrangement = Arrangement.Start
    ) {
        TextButton(
            onClick = { selectedLocation.value = "Dagupan" },
            modifier = Modifier.padding(end = 8.dp)
        ) {
            Text(
                "DAGUPAN",
                color = if (selectedLocation.value == "Dagupan") Color (0xFF8C8C8C) else Color(0xFFBFBFBF),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
            )
        }

        TextButton(
            onClick = { selectedLocation.value = "Calasiao" },
            modifier = Modifier.padding(end = 8.dp)
        ) {
            Text(
                "CALASIAO",
                color = if (selectedLocation.value == "Calasiao") Color(0xFF8C8C8C) else Color(0xFFBFBFBF),
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold
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