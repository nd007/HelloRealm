package com.mongodb.hellorealm


import android.util.Log
import io.realm.DynamicRealm
import io.realm.FieldAttribute
import io.realm.RealmMigration
import java.util.*

class MyMigration : RealmMigration {
    override fun migrate(realm: DynamicRealm, oldVersion: Long, newVersion: Long) {
        val schema = realm.schema

        // Check if the DummyData class already exists
        val dummyDataExists = schema.get("DummyData") != null

        if (!dummyDataExists) {
            // Add the DummyData class to the schema
            val dummyDataSchema =schema.get("DummyData")
//                ?.addField("tempId", Long::class.java)
//                ?.transform { obj ->
//                    // Convert the old 'id' field (String) to 'tempId' (Long)
//                    obj.setLong("tempId", UUID.fromString(obj.getString("id")).mostSignificantBits)
//                }
//                ?.removeField("id")
//                ?.renameField("tempId", "id")


            dummyDataSchema!!.addField("id", Long::class.java, FieldAttribute.PRIMARY_KEY,FieldAttribute.REQUIRED)
            dummyDataSchema!!.addField("fullName", String::class.java, FieldAttribute.REQUIRED)
            dummyDataSchema!!.addField("street", String::class.java, FieldAttribute.REQUIRED)
            dummyDataSchema!!.addField("houseNumber", Int::class.java)
            dummyDataSchema!!.addField("zip", Int::class.java)
            dummyDataSchema!!.addField("age", Int::class.java)
            dummyDataSchema!!.addField("city", String::class.java, FieldAttribute.REQUIRED)
            // Set primary key
//            dummyDataSchema.addPrimaryKey("id")
        } else {
            // DummyData class already exists, no migration needed
            Log.e("## Migration", "DummyData class already exists, no migration needed")
        }
    }
}
