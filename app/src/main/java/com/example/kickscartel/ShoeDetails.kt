package com.example.kickscartel

import android.content.ClipData
import android.media.Image

data class ShoeDetails (
        private val name: String,
        private val brand: String,
        private val size: Double,
        private val imageDetail: Image,
        private val description: String
    ) {

}