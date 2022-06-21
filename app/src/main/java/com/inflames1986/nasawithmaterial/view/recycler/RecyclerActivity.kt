package com.inflames1986.nasawithmaterial.view.recycler

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ItemTouchHelper
import com.gb.m_1919_1872_1.view.recycler.ItemTouchHelperCallback
import com.inflames1986.nasawithmaterial.databinding.ActivityRecyclerBinding


class RecyclerActivity : AppCompatActivity(), OnListItemClickListener {


    private var isNewList = false


    lateinit var binding: ActivityRecyclerBinding
    lateinit var adapter: RecyclerActivityAdapter

    private val list = arrayListOf(
        Pair(Data(0,"HEADER", "", TYPE_HEADER), false),
        Pair(Data(1,"Earth", "Earth des", TYPE_EARTH), false),
        Pair(Data(2,"Earth", "Earth des", TYPE_EARTH), false),
        Pair(Data(3,"Mars", "Mars des", TYPE_MARS), false),
        Pair(Data(4,"Earth", "Earth des", TYPE_EARTH), false),
        Pair(Data(5,"Earth", "Earth des", TYPE_EARTH), false),
        Pair(Data(6,"Earth", "Earth des", TYPE_EARTH), false),
        Pair(Data(7,"Mars", "Mars des", TYPE_MARS), false)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRecyclerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lon = 1
        val lat = 2
        val temp = 20
        val loc = Pair(lon, lat)
        val loc2 = lon to lat
        loc.first
        loc.second
        val weather = Triple(lon, lat, temp)
        val weather2 = lon to lat to temp


        adapter = RecyclerActivityAdapter(list,this)
        adapter.setList(list)
        binding.recyclerView.adapter = adapter

        binding.recyclerActivityFAB.setOnClickListener {
            onAddBtnClick(list.size)
        }
        ItemTouchHelper(ItemTouchHelperCallback(adapter)).attachToRecyclerView(binding.recyclerView)

        binding.recyclerActivityDiffUtilFAB.setOnClickListener {
            changeAdapterData()
        }
    }

    private fun changeAdapterData() {
        adapter.setList(createItemList(isNewList).map { it })
        isNewList = !isNewList
    }

    private fun createItemList(instanceNumber: Boolean): List<Pair<Data, Boolean>> {
        return when (instanceNumber) {
            false -> listOf(
                Pair(Data(0, "Header"), false),
                Pair(Data(1, "Mars", ""), false),
                Pair(Data(2, "Mars", ""), false),
                Pair(Data(3, "Mars", ""), false),
                Pair(Data(4, "Mars", ""), false),
                Pair(Data(5, "Mars", ""), false),
                Pair(Data(6, "Mars", ""), false)
            )
            true -> listOf(
                Pair(Data(0, "Header"), false),
                Pair(Data(1, "Mars", ""), false),
                Pair(Data(2, "Jupiter", ""), false),
                Pair(Data(3, "Mars", ""), false),
                Pair(Data(4, "Neptune", ""), false),
                Pair(Data(5, "Saturn", ""), false),
                Pair(Data(6, "Mars", ""), false)
            )
        }
    }



    override fun onItemClick(data: Data) {

    }

    override fun onAddBtnClick(position: Int) {
        list.add(position, Pair(Data(0,"Mars", "Mars des", TYPE_MARS), false))
        adapter.setAddToList(list, position)
    }

    override fun onRemoveBtnClick(position: Int) {
        list.removeAt(position)
        adapter.setRemoveToList(list, position)
    }
}