package com.example.finalapps

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalapps.databinding.ItempostBinding

class Adapter(private var User : List<personRespon>, val listener : Adapter.Click1):RecyclerView.Adapter<Adapter.UserViewHolder> (){
    inner class UserViewHolder (val binding : ItempostBinding):
            RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(ItempostBinding.inflate(LayoutInflater.from(parent.context),parent, false))
    }
    override fun getItemCount(): Int {
        return User.size
    }
    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.binding.apply {
            TVText.text = User[position].nama
            dataa.setOnClickListener{
                listener.click1(User[position])
            }
            edit.setOnClickListener {
                listener.Edit1(User[position])
            }
        }
    }
//    interface Click{
//        fun click (post : DataResponse)
//        fun Edit (post : DataResponse)
//
//    }
    interface Click1{
        fun click1 (post : personRespon)
        fun Edit1 (post : personRespon)
    }
}


