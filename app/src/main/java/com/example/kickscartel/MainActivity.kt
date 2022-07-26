package com.example.kickscartel

import android.os.Bundle
import android.content.Context
import android.graphics.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.kickscartel.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.io.IOException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val browseFragment = BrowseFragment()
        val favoritesFragment = favoritesFragment()
        val searchFragment = SearchFragment()
        val cartFragment = cartFragment()

        makeCurrentFragment(browseFragment)
        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener {
            when (it.itemId) {
                R.id.ic_home -> makeCurrentFragment(browseFragment)
                R.id.ic_favorites -> makeCurrentFragment(favoritesFragment)
                R.id.ic_search -> makeCurrentFragment(searchFragment)
                R.id.ic_cart -> makeCurrentFragment(cartFragment)
                R.id.ic_profile -> makeProfileFragment()
            }
            true
        }
    }

    private fun makeProfileFragment() {
        val profileFragment = ProfileFragment()
        val loginFragment = LoginFragment()
        if (Firebase.auth.currentUser == null) {
            makeCurrentFragment(loginFragment)
        } else {
            makeCurrentFragment(profileFragment)
        }
    }
    private fun makeCurrentFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.ic_wrapper, fragment)
            commit()
        }
    }
}

fun Bitmap.toRoundedCorners(
    topLeftRadius: Float = 0F,
    topRightRadius: Float = 0F,
    bottomRightRadius: Float = 0F,
    bottomLeftRadius: Float = 0F
):Bitmap?{
    val bitmap = Bitmap.createBitmap(
        width, // width in pixels
        height, // height in pixels
        Bitmap.Config.ARGB_8888
    )
    val canvas = Canvas(bitmap)

    // the bounds of a round-rectangle to add to the path
    val rectF = RectF(0f,0f,width.toFloat(),height.toFloat())

    // float array of 8 values, 4 pairs of [x,y] radii
    val radii = floatArrayOf(
        topLeftRadius,topLeftRadius, // top left corner
        topRightRadius,topRightRadius, // top right corner
        bottomRightRadius,bottomRightRadius, // bottom right corner
        bottomLeftRadius,bottomLeftRadius // bottom left corner
    )

    // path to draw rounded corners bitmap
    val path = Path().apply {
        // add a closed round-rectangle contour to the path
        // each corner receives two radius values [x, y]
        addRoundRect(
            rectF,
            radii,
            // the direction to wind the round-rectangle's contour
            Path.Direction.CCW
        )
    }

    // intersect the current clip with the specified path
    canvas.clipPath(path)

    // draw the rounded corners bitmap on canvas
    canvas.drawBitmap(this,0f,0f,null)
    return bitmap
}


// extension function to get bitmap from assets
fun Context.assetsToBitmap(fileName:String): Bitmap?{
    return try {
        with(assets.open(fileName)){
            BitmapFactory.decodeStream(this)
        }
    } catch (e: IOException) { null }
}