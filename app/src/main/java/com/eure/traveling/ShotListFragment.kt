package com.eure.traveling

import android.os.Bundle
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AbsListView
import android.widget.ListView
import com.eure.traveling.entity.Shot
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException


class ShotListFragment : Fragment(), AbsListView.OnScrollListener {
    /**
     * 現在のページ数
     */
    private var count = 1

    private val type by lazy { arguments.getString("type") }
    private lateinit var listView: ListView
    private lateinit var adapter: ShotListAdapter
    private lateinit var footer: View

    companion object {

        val TAG = ShotListFragment::class.java.simpleName

        /**
         * ページングのMAX値
         */
        private val MAX_COUNT = 50

        fun newInstance(type: String): ShotListFragment {
            return ShotListFragment().apply {
                arguments = Bundle().apply {
                    putString("type", type)
                }
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val root = inflater.inflate(R.layout.fragment_shot_list, container, false)
        listView = root.findViewById(R.id.list)
        listView.setOnItemClickListener { _, _, position, id ->
            Log.i(TAG, "onListItemClick:")
            Log.i(TAG, "position = " + position)
            Log.i(TAG, "id = " + id)
            val detailDialogFragment = DetailDialogFragment.newInstance(adapter.getItem(position).image?.normal!!)
            detailDialogFragment.show(fragmentManager, TAG)
        }
        adapter = ShotListAdapter(activity, mutableListOf())
        footer = inflater.inflate(R.layout.listview_footer, null)
        listView.adapter = adapter
        listView.addFooterView(footer)
        listView.setOnScrollListener(this)

        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        try {
            DribbbleService.main(object : Callback<List<Shot>> {
                override fun onResponse(call: Call<List<Shot>>, response: Response<List<Shot>>) {
                    addListData(response.body())
                }

                override fun onFailure(call: Call<List<Shot>>, t: Throwable) {
                }
            }, type, count)
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }

    override fun onScrollStateChanged(p0: AbsListView?, p1: Int) {
    }

    override fun onScroll(view: AbsListView, firstVisibleItem: Int, visibleItemCount: Int, totalItemCount: Int) {
        // 最後尾までスクロールした場合
        if (totalItemCount == firstVisibleItem + visibleItemCount) {
            additionalReading()
        }
    }

    private fun visibleFooter() {
        listView.addFooterView(footer)
    }

    private fun invisibleFooter() {
        listView.removeFooterView(footer)
    }

    private fun addListData(shots: List<Shot>?) {
        adapter.add(shots)
    }

    private fun additionalReading() {
        if (count >= MAX_COUNT) {
            invisibleFooter()
            return
        }
        count++
        try {
            DribbbleService.main(object : Callback<List<Shot>> {
                override fun onResponse(call: Call<List<Shot>>, response: Response<List<Shot>>) {
                    addListData(response.body())
                }

                override fun onFailure(call: Call<List<Shot>>, t: Throwable) {
                }
            }, type, count)
        } catch (e: IOException) {
            e.printStackTrace()
        }

    }
}
