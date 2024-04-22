package com.mongodb.hellorealm.ui.home.model

import io.realm.RealmObject
import io.realm.annotations.PrimaryKey
import java.util.*


open class DummyData: RealmObject() {

    @PrimaryKey
    var id = UUID.randomUUID().toString()
    var fullName: String = ""
    var street: String = ""
    var houseNumber: Int = 0
    var zip: Int = 0
    var age: Int = 0
    var city: String = ""
}