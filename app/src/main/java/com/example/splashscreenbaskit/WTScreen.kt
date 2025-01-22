package com.example.splashscreenbaskit

import android.media.Image
import android.media.audiofx.AudioEffect.Descriptor
import androidx.annotation.DrawableRes

sealed class WTScreen (
    @DrawableRes
    val image: Int,
    val title: String,
    val description: String
) {

    data object FirstSlide : WTScreen(
        image = R.drawable.basket_icon,
        title = "Shop Smarter",
        description = "Lorem ipsum"
    )

    data object SecondSlide : WTScreen(
        image = R.drawable.person_icon,
        title = "Not Harder",
        description = "Lorem ipsum"
    )

    data object ThirdSlide : WTScreen(
        image = R.drawable.qr_icon,
        title = "Life's Easier!",
        description = "Lorem ipsum"
    )


    /*companion object {
    val image: Int
    }*/
    companion object {
        val image: Int = 0
    }

}

