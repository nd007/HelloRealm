package com.mongodb.hellorealm.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mongodb.hellorealm.HomeActivity
import com.mongodb.hellorealm.R
import com.mongodb.hellorealm.databinding.FragmentHomeBinding
import com.mongodb.hellorealm.ui.home.Adapter.realmAdapter
import com.mongodb.hellorealm.ui.home.model.DummyData

class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()
    private var adapter: realmAdapter? = null

    private var _binding: FragmentHomeBinding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.topHeading.headerText.text = "HOME FRAGMENT"
        Log.e("## 1->", homeViewModel.visitInfoListAsList.toString())
        showData(homeViewModel.visitInfoListAsList!!)

        adapter?.onItemClick = {
            val activity = context as HomeActivity
            val fullDetailsFragment = FullDetailsFragment()
            val fullDetailsFragmentData = Bundle()

            fullDetailsFragmentData.putString("age", it.age.toString())
            fullDetailsFragmentData.putString("fullname", it.fullName.toString())
            fullDetailsFragmentData.putString("city", it.city.toString())
            fullDetailsFragmentData.putString("zip", it.zip.toString())
            fullDetailsFragmentData.putString("hosueNo", it.houseNumber.toString())
            fullDetailsFragmentData.putString("street", it.street.toString())
            fullDetailsFragment.setArguments(fullDetailsFragmentData)
            activity.supportFragmentManager.beginTransaction()
                .replace(R.id.frag_container, fullDetailsFragment)
                .addToBackStack(null).commit()
        }


        binding.textViewSortByAge.setOnClickListener {
            sortByAge()

        }
        binding.textViewSortByCity.setOnClickListener {
            sortByCity()
        }

        binding.textViewSortByName.setOnClickListener {
            sortByName()

        }


    }

    private fun sortByName() {
        var  visitInfoList = homeViewModel.visitInfoListAsList!!.sortedBy {it.fullName }
        adapter!!.updateData(visitInfoList)    }

    private fun sortByCity() {
        var  visitInfoList = homeViewModel.visitInfoListAsList!!.sortedBy {it.city }
        adapter!!.updateData(visitInfoList)    }

    private fun sortByAge() {
       var  visitInfoList = homeViewModel.visitInfoListAsList!!.sortedBy {it.age }
        adapter!!.updateData(visitInfoList)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private fun showData(arrayListOfSDummyData: List<DummyData>) {
        binding.recyclerView.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        adapter = realmAdapter(
            requireContext(),
//            arrayListOfSDummyData as ArrayList<DummyData>,
            arrayListOfSDummyData as ArrayList<DummyData>
        )
        binding.recyclerView.adapter = adapter
    }

}