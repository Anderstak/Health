package com.example.health

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class MenuAdapter(
    private val context: Context,
    private val onItemClickListener: OnItemClickListener
) : RecyclerView.Adapter<MenuAdapter.MenuItemViewHolder>() {

    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private val menuListItems: MutableList<String> = Repository.getInstance().menuCategory as MutableList<String>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuItemViewHolder {
        val view = inflater.inflate(R.layout.menu_item, parent, false)
        return MenuItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: MenuItemViewHolder, position: Int) {
        val menu = menuListItems[position]
        holder.bind(menu)
    }

    override fun getItemCount(): Int {
        return menuListItems.size
    }

    inner class MenuItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val view: View = itemView
        private val menuHeadline: TextView = itemView.findViewById(R.id.tv_menu_item)

        fun bind(menuItemModel: String) {
            menuHeadline.text = menuItemModel
            view.setOnClickListener {
                onItemClickListener.invoke(menuItemModel)
            }
        }
    }
}
