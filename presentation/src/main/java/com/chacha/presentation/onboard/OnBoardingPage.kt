package com.chacha.presentation.onboard

import androidx.annotation.DrawableRes
import com.chacha.presentation.R


sealed class OnBoardingPage(
    @DrawableRes val image: Int,
    val title: String,
    val description: String
) {
    object First: OnBoardingPage(
        image = R.drawable.arrow_back,
        title = "Make it Easy One",
        description = "Lorem Ipsum dolor sit amet, consectetur adipiscing elit, sed do elusemod tempor incididunt labore."
    )
    object Second: OnBoardingPage(
        image = R.drawable.arrow_back,
        title = "Make it Easy Two",
        description = "Lorem Ipsum dolor sit amet, consectetur adipiscing elit, sed do elusemod tempor incididunt labore."
    )
    object Third: OnBoardingPage(
        image = R.drawable.ic_menu,
        title = "Make it Easy Three",
        description = "Lorem Ipsum dolor sit amet, consectetur adipiscing elit, sed do elusemod tempor incididunt labore."
    )
}