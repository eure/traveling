package com.eure.traveling


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.eure.traveling.entity.Shot
import com.squareup.picasso.Picasso

class ShotListAdapter(private val context: Context, private val shots: MutableList<Shot>) : BaseAdapter() {

    override fun getCount(): Int {
        return shots.size
    }

    override fun getItem(position: Int): Shot {
        return shots[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var view = convertView
        if (convertView == null) {
            val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.list_row, parent, false)
        }

        val title = view!!.findViewById<TextView>(R.id.title)
        val playerName = view.findViewById<TextView>(R.id.player_name)
        val likesCount = view.findViewById<TextView>(R.id.likes_count)
        val imageView = view.findViewById<ImageView>(R.id.image)

        title.text = getItem(position).title
        playerName.text = getItem(position).designer?.name
        likesCount.text = Integer.toString(getItem(position).likesCount)
        Picasso.with(context).load(getItem(position).image?.teaser).into(imageView)

        return view
    }

    fun add(shots: List<Shot>?): Boolean {
        shots?.let {
            this.shots.addAll(it.toMutableList())
            this.notifyDataSetChanged()
            return true
        }
        return false
    }
}
