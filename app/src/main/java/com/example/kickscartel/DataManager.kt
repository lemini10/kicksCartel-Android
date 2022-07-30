package com.example.kickscartel

import android.util.Log
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
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
}