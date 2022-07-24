package com.example.kickscartel

import android.media.Image

data class FetchedSneaker(
    var brand: String,
    var sneakerImage: String,
    var completeName: String,
    var price: String,
    var description: String,
    var imageSet: ImageSet,
    var size: String?) {
    val id: String
        get() = this.completeName + (this.size)
}

data class ImageSet(
    var firstImage: String,
    var secondImage: String,
    var thirdImage: String){
}

data class FetchedNews(
    var categorie: String,
    var image: String,
    var title: String){
    val id: String
    get() = this.image
}

data class  BrowseCategoriesModel(
   var header: String,
   var title: String
) {
}

enum class Categories {
    Sneakers,Slides,Beanies,Shirt, Collectibles
}

enum class Brands {
    Adidas,Yeezy,Nike,Jordan,Supreme,NewBalance
}

