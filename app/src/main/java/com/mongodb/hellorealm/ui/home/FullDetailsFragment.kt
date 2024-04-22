package com.mongodb.hellorealm.ui.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.navGraphViewModels
import androidx.recyclerview.widget.RecyclerView
import com.mongodb.hellorealm.R
import com.mongodb.hellorealm.databinding.FragmentHomeBinding
import com.mongodb.hellorealm.databinding.FullDetailsFragmentBinding
import com.mongodb.hellorealm.ui.home.model.DummyData

class FullDetailsFragment:Fragment() {

    private var  fullDetailsbinding:FullDetailsFragmentBinding? = null

    var age=""
    var fullname=""
    var city=""
    var zip=""
    var hosueNo=""
    var street=""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        fullDetailsbinding = FullDetailsFragmentBinding.inflate(inflater, container, false)
        return fullDetailsbinding!!.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fullDetailsbinding!!.topHeading.headerText.text="FULL DETAILS FRAGMENT"

        age=arguments?.getString("age").toString()
        fullname=arguments?.getString("fullname").toString()
        city=arguments?.getString("city").toString()
        zip=arguments?.getString("zip").toString()
        hosueNo=arguments?.getString("hosueNo").toString()
        street=arguments?.getString("street").toString()




        setData()


    }

    private fun setData() {
        fullDetailsbinding!!.CityTV.text=city
        fullDetailsbinding!!.AgeTV.text=age
        fullDetailsbinding!!.ZipTV.text=zip
        fullDetailsbinding!!.houseTV.text=hosueNo
        fullDetailsbinding!!.StreetTV.text=street
        fullDetailsbinding!!.nameTV.text=fullname
    }




}

