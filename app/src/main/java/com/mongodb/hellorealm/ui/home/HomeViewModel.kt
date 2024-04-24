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
import java.util.*


class HomeViewModel : ViewModel() {

    private val db = Realm.getDefaultInstance()



    var _dataSample: MutableStateFlow<List<DummyData>> = MutableStateFlow(emptyList())
    val dataSample1: StateFlow<List<DummyData>> = _dataSample

    init {
        createSampleEntries()
//        deleteAllDummyData()
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
            val dummy1 = createDummyData("Test Doe", "John Doe Street", 1, 10, 12345, "Dun", realm)
            val dummy2 = createDummyData("Jane Test", "Jane Street", 2, 20, 12345, "Majra", realm)
            val dummy3 = createDummyData("Doe TEst", "== Doe Street", 3, 30, 12345, "Rajpur", realm)
            val dummy4 = createDummyData("JaneDoe", "J Street", 4, 40, 12345, "Raipur", realm)

            // Insert the data into the Realm database if they are not null
            if (dummy1 != null) realm.insertOrUpdate(dummy1)
            if (dummy2 != null) realm.insertOrUpdate(dummy2)
            if (dummy3 != null) realm.insertOrUpdate(dummy3)
            if (dummy4 != null) realm.insertOrUpdate(dummy4)
        }

        /*db.executeTransactionAsync { realm ->
//            val existingIds = mutableListOf<String>()
//            val existingData = realm.where(DummyData::class.java).findAll()
//            existingData.forEach { existingIds.add(it.id)
//            }
            // Create a new instance of MyData
            val existingData = realm.where(DummyData::class.java).findAll()

            if (existingData.isEmpty()) {
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



//            val dummy1 = createDummyData("Test Doe", "John Doe Street", 1, 10, 12345, "Dun", existingIds)
//            val dummy2 = createDummyData("Jane Test", "Jane Street", 2, 20, 12345, "Majra", existingIds)
//            val dummy3 = createDummyData("Doe TEst", "== Doe Street", 3, 30, 12345, "Rajpur", existingIds)
//            val dummy4 = createDummyData("JaneDoe", "J Street", 4, 40, 12345, "Raipur", existingIds)


                // Insert the data into the Realm database
                realm.insertOrUpdate(dummy1)
                realm.insertOrUpdate(dummy2)
                realm.insertOrUpdate(dummy3)
                realm.insertOrUpdate(dummy4)
            }




        }*/
    }



    fun deleteAllDummyData() {
        val realm = Realm.getDefaultInstance()

        realm.executeTransactionAsync { realm ->
            realm.delete(DummyData::class.java)
        }

        realm.close()
    }

    private fun createDummyData(fullName: String, street: String, houseNumber: Int, age: Int, zip: Int, city: String, realm: Realm): DummyData? {
        // Check if there's an existing entry with the same properties
        val existingData = realm.where(DummyData::class.java)
            .equalTo("fullName", fullName)
            .equalTo("street", street)
            .equalTo("houseNumber", houseNumber)
            .equalTo("age", age)
            .equalTo("zip", zip)
            .equalTo("city", city)
            .findFirst()

        // If an existing entry is found, return null to indicate that no new object should be created
        if (existingData != null) {
            return null
        }

        // If no existing entry is found, create a new UUID
        val newId = UUID.randomUUID().toString()

        // Create the DummyData object with the unique ID
        return DummyData().apply {
            id = newId
            this.fullName = fullName
            this.street = street
            this.houseNumber = houseNumber
            this.age = age
            this.zip = zip
            this.city = city
        }
    }



    override fun onCleared() {
        super.onCleared()
        db.close()
    }

}