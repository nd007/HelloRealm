package com.mongodb.hellorealm.ui.home.Adapter

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.RecyclerView
import com.mongodb.hellorealm.R
import com.mongodb.hellorealm.databinding.FragmentHomeBinding
import com.mongodb.hellorealm.ui.home.model.DummyData

class realmAdapter(context: Context, var listOfSampleData:List<DummyData>)
    : RecyclerView.Adapter<realmAdapter.ViewHolder>()
{

    var onItemClick: ((DummyData) -> Unit)? = null

    private var filteredList: List<DummyData> = listOfSampleData


    fun updateData(newData: List<DummyData>) {
        listOfSampleData = newData
        filteredList = newData
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_layout, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(listOfSampleData[position], position)

        holder.itemView.setOnClickListener {
             onItemClick?.invoke(listOfSampleData[position])
        }
    }

    override fun getItemCount(): Int {
        return listOfSampleData.size
    }



    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(dummyData: DummyData, position: Int) {

            var Age_TV: TextView = itemView.findViewById(R.id.Age_TV)
            var City_TV: TextView = itemView.findViewById(R.id.City_TV)
            var Street_TV: TextView = itemView.findViewById(R.id.Street_TV)
            var Zip_TV: TextView = itemView.findViewById(R.id.Zip_TV)
            var name_TV: TextView = itemView.findViewById(R.id.name_TV)
            var house_TV: TextView = itemView.findViewById(R.id.house_TV)

            Age_TV.text=dummyData.age.toString()
            City_TV.text=dummyData.city.toString()
            Street_TV.text=dummyData.street.toString()
            Zip_TV.text=dummyData.zip.toString()
            name_TV.text=dummyData.fullName.toString()
            house_TV.text=dummyData.houseNumber.toString()

        }


    }
}