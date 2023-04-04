package com.turtleteam.remote_database

import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

interface UpdateService {

    fun getLatestVersion()
}

internal class UpdateServiceImpl: UpdateService {

    private val firestore = Firebase.firestore

    override fun getLatestVersion(){
        println(firestore.collectionGroup(""))
    }
}