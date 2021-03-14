package com.example.recyclerviewfirestore

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.item_with_image.view.*

class PostListAdapter(
    var postListItems: List<PostModel>
) : RecyclerView.Adapter<PostListAdapter.ImageViewHolder>() {

    inner class ImageViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImageViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.item_with_image, parent, false)
        return ImageViewHolder(view)
    }

    override fun getItemCount(): Int {
        return postListItems.size
    }
    override fun onBindViewHolder(holder: ImageViewHolder, position: Int) {
        val post= postListItems[position]

        holder.itemView.titlePost.text = post.title
        Glide.with(holder.itemView).load(post.image).into(holder.itemView.imagePost)
    }


}
