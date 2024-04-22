package com.mongodb.hellorealm

import android.app.Application
import com.google.firebase.FirebaseApp
import com.mongodb.hellorealm.ui.home.model.DummyData
import io.realm.Realm
import io.realm.RealmConfiguration
import java.net.URI.create
import java.nio.channels.AsynchronousFileChannel.open

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)

        FirebaseApp.initializeApp(this)

        val config = RealmConfiguration.Builder()
            .schemaVersion(13)
            .migration(MyMigration())
            .build()

        Realm.setDefaultConfiguration(config)
    }

}