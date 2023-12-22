package com.capstone.nusalingo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.capstone.nusalingo.databinding.AdapterContentBinding
import kotlin.collections.ArrayList

class ContentAdapter(
    var results: ArrayList<ContentModel.Result>,
    val listener: OnAdapterListener
) : RecyclerView.Adapter<ContentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AdapterContentBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ViewHolder(binding)
    }

    override fun getItemCount() = results.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(results[position])
    }

    fun setData(data: List<ContentModel.Result>) {
        results.clear()
        results.addAll(data)
        notifyDataSetChanged()
    }

    inner class ViewHolder(private val binding: AdapterContentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(result: ContentModel.Result) {
            binding.tvTitle.text = result.title
            binding.tvHeader.text = result.desc_one
            Glide.with(binding.root)
                .load(result.image)
                .placeholder(R.drawable.img_placeholder)
                .error(R.drawable.img_placeholder)
                .centerCrop()
                .into(binding.imageView)

            binding.root.setOnClickListener { listener.onClick(result) }
        }
    }

    interface OnAdapterListener {
        fun onClick(result: ContentModel.Result)
    }
}
