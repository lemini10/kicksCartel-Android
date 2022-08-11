package com.example.kickscartel

import android.util.Log
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable
import java.lang.reflect.Type

class DataManager {


    private var database: DatabaseReference = Firebase.database.reference

    fun fetchSneakers(completion: () -> Unit): ArrayList<FetchedSneaker> {
        var fetchedSneaker = ArrayList<FetchedSneaker>()
        database.child("Sneakers").get().addOnSuccessListener {
            val sneakers = it.value as Map<String, FetchedSneaker>
            val gson = Gson()
            val json = Gson().toJson(sneakers)
            val mapType: Type = object : TypeToken<Map<String, FetchedSneaker>>() {}.type
            var data  = gson.fromJson<Map<String, FetchedSneaker>>(json, mapType)
            for (session in data.values) {
                fetchedSneaker.add(session)
            }
            completion()
        }.addOnFailureListener {
            Log.d("EXCEPTION➡️", it.toString())
        }
        return fetchedSneaker
    }

    fun fetchNews(completion: () -> Unit): ArrayList<FetchedNews> {
        var fetchedNews = ArrayList<FetchedNews>()
        database.child("News").get().addOnSuccessListener {
            val newsRaw = it.value as Map<String, FetchedSneaker>
            val gson = Gson()
            val json = Gson().toJson(newsRaw)
            val mapType: Type = object : TypeToken<Map<String, FetchedNews>>() {}.type
            var data  = gson.fromJson<Map<String, FetchedNews>>(json, mapType)
            for (session in data.values) {
                fetchedNews.add(session)
            }
            completion()
        }.addOnFailureListener {
            Log.d("EXCEPTION➡️", it.toString())
        }
        return fetchedNews
    }

    fun fetchUserSneakers(savedIn: UserPreferences, completion: () -> Unit): ArrayList<FetchedSneaker> {
        var fetchedSneaker = ArrayList<FetchedSneaker>()
        val userID = Firebase.auth.currentUser
        if (userID != null) {
                database.child("users").child(userID.toString()).child(savedIn.raw).get().addOnSuccessListener {
                    val sneakers = it.value as Map<String, FetchedSneaker>
                    val gson = Gson()
                    val json = Gson().toJson(sneakers)
                    val mapType: Type = object : TypeToken<Map<String, FetchedSneaker>>() {}.type
                    var data  = gson.fromJson<Map<String, FetchedSneaker>>(json, mapType)
                    for (session in data.values) {
                        fetchedSneaker.add(session)
                    }
                    completion()
                }.addOnFailureListener {
                    Log.d("EXCEPTION➡️", it.toString())
                }
            }
        return fetchedSneaker
    }

    fun add(sneaker: FetchedSneaker, to: UserPreferences, completion: () -> Unit, completionFailure: () -> Unit) {
        val userID = Firebase.auth.currentUser
        if (userID != null) {
            database.child("users").child(userID.uid).child(to.raw).child(sneaker.id).setValue(sneaker)
                .addOnSuccessListener {
                completion()
            }
                .addOnFailureListener {
                    Log.d("EXCEPTION➡️", it.toString())
                }
        } else {
            completionFailure()
        }
    }

}

enum class UserPreferences(val raw: String): Serializable {
    Favorites("Favorites"),
    Cart("Cart")
}