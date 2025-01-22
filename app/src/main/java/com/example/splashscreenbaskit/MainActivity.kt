package com.example.splashscreenbaskit

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
/*import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen*/
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.myapplication.design.loginregister.LogInScreen
import com.example.splashscreenbaskit.ui.theme.SplashScreenBaskitTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : ComponentActivity() {
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()

        //CoroutineScope(Dispatchers.Main).launch {
            //delay(3000L)
        //}


        setContent {
            LogInScreen()
            SplashScreenBaskitTheme { }
            //SignUpActivity()
            //AccountActivity()

        }



    }
}


/*@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SplashScreenBaskitTheme {
        Greeting("Android")
    }
}*/