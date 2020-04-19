package com.example.exampleproj

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_rv.view.*


class Adapter(private var delegate: (model: Hero) -> Unit) :
    RecyclerView.Adapter<Adapter.MyViewHolder>() {
    private var data = listOf<Hero>()
    fun attachDelegete(delegate: (model: Hero) -> Unit) {
        this.delegate = delegate
    }

    fun setData(data: List<Hero>) {
        this.data = data
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = data.count()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder =
        MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rv, parent, false), delegate
        )


    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(data[position])
    }

    class MyViewHolder(itemView: View, private val delegate: (model: Hero) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val txtId: TextView = itemView.textId
        private val txtName: TextView = itemView.textName
        private val attackType: ImageView = itemView.attrImage
        private fun mapAtr(attr: String): String {
            return when (attr) {
                "agi" ->
                    "https://gamepedia.cursecdn.com/dota2_gamepedia/thumb/2/2d/Agility_attribute_symbol.png/20px-Agility_attribute_symbol.png?version=4022c3f8fd6aec761efd859358f8c078"
                "str" ->
                    "https://gamepedia.cursecdn.com/dota2_gamepedia/thumb/7/7a/Strength_attribute_symbol.png/20px-Strength_attribute_symbol.png?version=ffbadd5d5525cbe8e2cf8e4c24a2d115"
                "int" ->
                    "https://gamepedia.cursecdn.com/dota2_gamepedia/thumb/5/56/Intelligence_attribute_symbol.png/20px-Intelligence_attribute_symbol.png?version=794665f4224738e861793a93815a022b"
                else -> ""
            }
        }

        fun bind(model: Hero) {
            txtId.text = model.id.toString()
            txtName.text = model.localized_name
            Picasso.get().load(mapAtr(model.primary_attr)).into(attackType)
            itemView.setOnClickListener {
                delegate.invoke(model)
            }
        }

    }
}