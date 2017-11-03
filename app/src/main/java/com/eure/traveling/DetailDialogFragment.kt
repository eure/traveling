package com.eure.traveling

import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.squareup.picasso.Picasso

class DetailDialogFragment : DialogFragment() {

    companion object {

        fun newInstance(imgUrl: String): DetailDialogFragment {
            return DetailDialogFragment().apply {
                arguments = Bundle().apply {
                    putString("url", imgUrl)
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val v = inflater.inflate(R.layout.detail, container, false)
        Picasso.with(context).load(arguments.getString("url")).into(v.findViewById<View>(R.id.image) as ImageView)
        return v
    }
}
