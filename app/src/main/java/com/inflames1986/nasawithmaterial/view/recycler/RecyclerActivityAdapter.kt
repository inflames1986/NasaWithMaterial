package com.inflames1986.nasawithmaterial.view.recycler

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.inflames1986.nasawithmaterial.R
import com.inflames1986.nasawithmaterial.databinding.ActivityRecyclerItemEarthBinding
import com.inflames1986.nasawithmaterial.databinding.ActivityRecyclerItemHeaderBinding
import com.inflames1986.nasawithmaterial.databinding.ActivityRecyclerItemMarsBinding

const val TYPE_EARTH = 1
const val TYPE_MARS = 2
const val TYPE_HEADER = 3

class RecyclerActivityAdapter(
    private var list: MutableList<Pair<Data, Boolean>>,
    private var onListItemClickListener: OnListItemClickListener
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(), ItemTouchHelperAdapter {


    fun setList(newList: List<Pair<Data, Boolean>>) {
        val result = DiffUtil.calculateDiff(DiffUtilCallback(list, newList))
        result.dispatchUpdatesTo(this)
        this.list = newList.toMutableList()
    }

    fun setAddToList(newList: List<Pair<Data, Boolean>>, position: Int) {
        this.list = newList.toMutableList()
        notifyItemChanged(position)
    }

    fun setRemoveToList(newList: List<Pair<Data, Boolean>>, position: Int) {
        this.list = newList.toMutableList()
        notifyItemRemoved(position)
    }

    override fun getItemViewType(position: Int): Int {
        return list[position].first.type
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            TYPE_EARTH -> {
                val view =
                    ActivityRecyclerItemEarthBinding.inflate(LayoutInflater.from(parent.context))
                EarthViewHolder(view.root)
            }
            TYPE_MARS -> {
                val view =
                    ActivityRecyclerItemMarsBinding.inflate(LayoutInflater.from(parent.context))
                MarsViewHolder(view.root)
            }
            TYPE_HEADER -> {
                val view =
                    ActivityRecyclerItemHeaderBinding.inflate(LayoutInflater.from(parent.context))
                HeaderViewHolder(view.root)
            }
            else -> {
                val view =
                    ActivityRecyclerItemMarsBinding.inflate(LayoutInflater.from(parent.context))
                MarsViewHolder(view.root)
            }
        }

    }

    override fun onBindViewHolder(
        holder: RecyclerView.ViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            when (getItemViewType(position)) { // TODO WH создать BaseViewHolder
                TYPE_EARTH -> {
                    //(holder as EarthViewHolder).itemView.findViewById<TextView>(R.id.title).text =
                }
                TYPE_MARS -> {
                    val res = createCombinedPayload(payloads as List<Change<Pair<Data, Boolean>>>)
                    if (res.oldData.first.someText != res.newData.first.someText)
                        (holder as MarsViewHolder).itemView.findViewById<TextView>(R.id.title).text =
                            res.newData.first.someText
                }
                TYPE_HEADER -> {
                    // (holder as HeaderViewHolder).myBind(list[position])
                }
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (getItemViewType(position)) { // TODO WH создать BaseViewHolder
            TYPE_EARTH -> {
                (holder as EarthViewHolder).myBind(list[position])
            }
            TYPE_MARS -> {
                (holder as MarsViewHolder).myBind(list[position])
            }
            TYPE_HEADER -> {
                (holder as HeaderViewHolder).myBind(list[position])
            }
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class EarthViewHolder(view: View) : RecyclerView.ViewHolder(view), ItemTouchHelperViewHolder { // TODO WH :BaseViewHolder
        fun myBind(listItem: Pair<Data, Boolean>) {
            /*(itemView as ConstraintLayout).findViewById<TextView>(R.id.title).text = data.someText
            (itemView as ConstraintLayout).findViewById<TextView>(R.id.descriptionTextView).text = data.someDescription*/

            /*val binding = ActivityRecyclerItemEarthBinding.bind(itemView)
            binding.title.text =data.someText
            binding.descriptionTextView.text = data.someDescription*/

            (ActivityRecyclerItemEarthBinding.bind(itemView)).apply {
                title.text = listItem.first.someText
                descriptionTextView.text = listItem.first.someDescription
            }
        }

        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY)
        }

        override fun onItemClear() {
            itemView.setBackgroundColor(0)
        }
    }

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view) { // TODO WH :BaseViewHolder
        fun myBind(listItem: Pair<Data, Boolean>) {
            (ActivityRecyclerItemHeaderBinding.bind(itemView)).apply {
                header.text = listItem.first.someText
            }
        }
    }

    inner class MarsViewHolder(view: View) :
        RecyclerView.ViewHolder(view), ItemTouchHelperViewHolder { // TODO WH :BaseViewHolder
        fun myBind(listItem: Pair<Data, Boolean>) {
            (ActivityRecyclerItemMarsBinding.bind(itemView)).apply {
                title.text = listItem.first.someText
                marsImageView.load(R.drawable.bg_mars)
                addItemImageView.setOnClickListener {
                    onListItemClickListener.onAddBtnClick(layoutPosition)
                }
                removeItemImageView.setOnClickListener {
                    onListItemClickListener.onRemoveBtnClick(layoutPosition)
                }
                moveItemDown.setOnClickListener { // TODO IndexOutOfBoundsException
                    list.removeAt(layoutPosition).apply {
                        list.add(layoutPosition + 1, this)
                    }
                    notifyItemMoved(layoutPosition, layoutPosition + 1)
                }
                moveItemUp.setOnClickListener { // TODO IndexOutOfBoundsException: Index: -1
                    list.removeAt(layoutPosition).apply {
                        list.add(layoutPosition - 1, this)
                    }
                    notifyItemMoved(layoutPosition, layoutPosition - 1)
                }

                marsImageView.setOnClickListener {
                    list[layoutPosition] = list[layoutPosition].let {
                        it.first to !it.second
                    }
                    marsDescriptionTextView.visibility =
                        if (list[layoutPosition].second) View.VISIBLE else View.GONE
                }
            }
        }

        override fun onItemSelected() {
            itemView.setBackgroundColor(Color.LTGRAY)
        }

        override fun onItemClear() {
            itemView.setBackgroundColor(0)
        }
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        list.removeAt(fromPosition).apply {
            list.add(toPosition, this)
        }
        notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        list.removeAt(position)
        notifyItemRemoved(position)
    }
}