package com.eure.traveling;

import com.eure.traveling.entity.Shot;

import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ShotListFragment extends ListFragment implements AbsListView.OnScrollListener {

    public static final String TAG = ShotListFragment.class.getSimpleName();

    /**
     * ページングのMAX値
     */
    private final static int MAX_COUNT = 50;
    /**
     * 現在のページ数
     */
    private int mCount = 1;

    private String mType;

    private ShotListAdapter mAdapter;
    private ListView mListView;
    private View mFooter;

    public ShotListFragment() {
    }

    public static ShotListFragment newInstance(String type) {
        ShotListFragment fragment = new ShotListFragment();
        Bundle args = new Bundle();
        args.putString("type", type);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mListView = getListView();
        mListView.addFooterView(getFooter());
        mListView.setOnScrollListener(this);

        try {
            DribbbleService.main(new Callback<List<Shot>>() {
                @Override
                public void onResponse(Call<List<Shot>> call, Response<List<Shot>> response) {
                    addListData(response.body());
                }

                @Override
                public void onFailure(Call<List<Shot>> call, Throwable t) {

                }
            }, getType(), mCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Log.i(TAG, "onListItemClick:");
        Log.i(TAG, "position = " + position);
        Log.i(TAG, "id = " + id);
        super.onListItemClick(l, v, position, id);
        DetailDialogFragment detailDialogFragment = DetailDialogFragment.newInstance(mAdapter.getItem(position).getImage().getNormal());
        detailDialogFragment.show(getFragmentManager(), TAG);
    }

    @Override
    public void onScrollStateChanged(AbsListView absListView, int i) {

    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        // コンテンツが追加される前に呼ばれることもある
        if (mAdapter == null) {
            return;
        }

        // 最後尾までスクロールした場合
        if (totalItemCount == firstVisibleItem + visibleItemCount) {
            additionalReading();
        }
    }


    private String getType() {
        if (mType == null) {
            mType = getArguments().getString("type");
        }
        return mType;
    }

    private ListView getMyListView() {
        if (mListView == null) {
            mListView = getListView();
        }
        return mListView;
    }

    private View getFooter() {
        if (mFooter == null) {
            mFooter = getLayoutInflater(getArguments()).inflate(R.layout.listview_footer, null);
        }
        return mFooter;
    }

    private void visibleFooter() {
        getMyListView().addFooterView(getFooter());
    }

    private void invisibleFooter() {
        getMyListView().removeFooterView(getFooter());
    }

    private void addListData(List<Shot> shots) {
        if (mAdapter == null) {
            mAdapter = new ShotListAdapter(getActivity(), shots);
            setListAdapter(mAdapter);
        } else {
            mAdapter.add(shots);
        }
    }

    private void additionalReading() {
        if (mCount >= MAX_COUNT) {
            invisibleFooter();
            return;
        }
        mCount++;
        try {
            DribbbleService.main(new Callback<List<Shot>>() {
                @Override
                public void onResponse(Call<List<Shot>> call, Response<List<Shot>> response) {
                    addListData(response.body());
                }

                @Override
                public void onFailure(Call<List<Shot>> call, Throwable t) {

                }
            }, getType(), mCount);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
