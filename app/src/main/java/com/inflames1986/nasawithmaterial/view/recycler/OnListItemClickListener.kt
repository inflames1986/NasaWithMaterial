package com.inflames1986.nasawithmaterial.view.recycler

interface OnListItemClickListener {
    fun onItemClick(data: Data)
    fun onAddBtnClick(position: Int)
    fun onRemoveBtnClick(position: Int)
}