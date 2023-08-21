package com.example.athletesapplication.athletes.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.athletesapplication.R
import com.example.athletesapplication.model.Athlete
import com.squareup.picasso.Picasso


class AthletesAdapter (private val onClick:(Athlete)->Unit): ListAdapter<Athlete, AthletesAdapter.ViewHolder>(MyDifUnit()) {
    lateinit var contxt: Context


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        contxt=parent.context
        val view = LayoutInflater.from(parent.context).inflate(R.layout.athletes_item,parent,false)
        return ViewHolder(view)
    }



    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(contxt).load(getItem(position).image).placeholder(R.drawable.placeholder)
            .into(holder.img_athlete)
        holder.tv_name.text = getItem(position).name
        holder.tv_desc.text = getItem(position).brief
        holder.constraint.setOnClickListener {
            onClick(getItem(position))
        }
    }


    inner class ViewHolder (private val itemView: View): RecyclerView.ViewHolder(itemView){
        val tv_name : TextView
            get() = itemView.findViewById(R.id.tv_name)
        val tv_desc : TextView
            get() = itemView.findViewById(R.id.tv_desc)
        val img_athlete : ImageView
            get() = itemView.findViewById(R.id.img_athletes)
        val constraint : ConstraintLayout
            get() = itemView.findViewById(R.id.constraint)
    }
}


class MyDifUnit: DiffUtil.ItemCallback<Athlete>() {
    override fun areItemsTheSame(oldItem: Athlete, newItem: Athlete): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Athlete, newItem: Athlete): Boolean {
        return oldItem == newItem
    }

}