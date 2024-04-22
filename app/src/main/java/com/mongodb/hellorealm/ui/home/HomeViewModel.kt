package com.mongodb.hellorealm.ui.home


import androidx.lifecycle.ViewModel
import com.mongodb.hellorealm.MyMigration
import com.mongodb.hellorealm.ui.home.model.DummyData

import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.kotlin.where
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.map


class HomeViewModel : ViewModel() {

    private val db = Realm.getDefaultInstance()



    var _dataSample: MutableStateFlow<List<DummyData>> = MutableStateFlow(emptyList())
    val dataSample1: StateFlow<List<DummyData>> = _dataSample

    init {
        createSampleEntries()

        val config = RealmConfiguration.Builder()
            .schemaVersion(12)
            .migration(MyMigration())
            .build()
        Realm.setDefaultConfiguration(config)

    }

    val visitInfo = db.where(DummyData::class.java).findAll()
    val visitInfoListAsList = visitInfo.toList()



//    val dataSample = db.where<DummyData>().findAll().asFlow().map { results ->
////        results.list.toList()
//        results
//    }


    private fun createSampleEntries() {

        db.executeTransactionAsync { realm ->
            // Create a new instance of MyData
            val dummy1 = DummyData().apply {
                //id = 0
                fullName = "Test Doe"
                street = "John Doe Street"
                houseNumber = 1
                age = 10
                zip = 12345
                city = "Dun"
            }
            val dummy2 = DummyData().apply {
               // id = 1
                age = 20
                fullName = "Jane Test"
                street = "Jane Street"
                houseNumber = 2
                zip = 12345
                city = "Majra"
            }

            val dummy3 = DummyData().apply {
//                id = 2
                age = 30
                fullName = "Doe TEst"
                street = "== Doe Street"
                houseNumber = 3
                zip = 12345
                city = "Rajpur"
            }
            val dummy4 = DummyData().apply {
//                id = 3
                age = 40
                fullName = "JaneDoe"
                street = "J Street"
                houseNumber = 4
                zip = 12345
                city = "Raipur"
            }

            // Insert the data into the Realm database
            realm.insertOrUpdate(dummy1)
            realm.insertOrUpdate(dummy2)
            realm.insertOrUpdate(dummy3)
            realm.insertOrUpdate(dummy4)

        }
    }


    override fun onCleared() {
        super.onCleared()
        db.close()
    }

}