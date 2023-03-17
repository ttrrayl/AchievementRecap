package com.example.achievementrecap

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ListPialaAdapter (private val listPiala: ArrayList<piala>) : RecyclerView.Adapter<ListPialaAdapter.ListViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    fun setOnItemClickCallback(onItemClickCallback: OnItemClickCallback){
        this.onItemClickCallback = onItemClickCallback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.item_piala,parent,false)
        return ListViewHolder(view)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val (peringkat,kegiatan,photo) = listPiala[position]
        holder.imgPhoto.setImageResource(photo)
        holder.tvPeringkat.text = peringkat
        holder.tvKegiatan.text = kegiatan
        holder.itemView.setOnClickListener {onItemClickCallback.onItemClicked(listPiala[holder.adapterPosition])}
    }

    override fun getItemCount(): Int = listPiala.size

    class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imgPhoto: ImageView = itemView.findViewById(R.id.imagePiala)
        val tvPeringkat: TextView = itemView.findViewById(R.id.tv_peringkat)
        val tvKegiatan: TextView = itemView.findViewById(R.id.tv_kegiatan)
    }

    interface OnItemClickCallback{
        fun onItemClicked(data: piala)
    }
}