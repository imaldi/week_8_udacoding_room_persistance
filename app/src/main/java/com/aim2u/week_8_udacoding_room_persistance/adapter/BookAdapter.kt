package com.aim2u.week_8_udacoding_room_persistance.adapter

import com.aim2u.week_8_udacoding_room_persistance.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.aim2u.week_8_udacoding_room_persistance.local.LocalBook
import kotlinx.android.synthetic.main.list_item.view.*

class DataAdapter (val data: List<LocalBook>?, val itemClick:OnClickListener): RecyclerView.Adapter<DataAdapter.ViewHolder>() {
//class DataAdapter (val data: List<LocalBook>?, val itemClick:OnClickListener): ListAdapter<LocalBook,DataAdapter.ViewHolder>(DataMahasiswaDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(holder: DataAdapter.ViewHolder, position: Int) {
        val item = data?.get(position)

//        val item = getItem(position)
        holder.nama.text = item?.book_name
        holder.nohp.text = item?.author
        holder.alamat.text = item?.released_year

        holder.view.setOnClickListener {
            itemClick.update(item)
        }

        holder.btnHapus.setOnClickListener{
            itemClick.hapus(item)
        }
    }

    inner class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        val nama = view.textNamaBuku
        val nohp = view.textPenulis
        val alamat = view.textTahunRilis
        val btnHapus = view.btn_hapus
    }

    interface OnClickListener{
        fun update(item: LocalBook?)
        fun hapus(item: LocalBook?)
    }
}

//class DataMahasiswaDiffCallback : DiffUtil.ItemCallback<LocalBook>(){
//    override fun areItemsTheSame(oldItem: LocalBook, newItem: LocalBook): Boolean {
//        return oldItem.id == newItem.id
//    }
//
//    override fun areContentsTheSame(oldItem: LocalBook, newItem: LocalBook): Boolean {
//        return oldItem == newItem
//    }
//
//}