package com.eure.traveling;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;


public class DetailDialogFragment extends DialogFragment {

    static DetailDialogFragment newInstance(String imgUrl) {
        DetailDialogFragment f = new DetailDialogFragment();
        Bundle args = new Bundle();
        args.putString("url", imgUrl);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NO_TITLE, 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.detail, container, false);
        Bundle arguments = getArguments();
        if (arguments != null) {
            String imgUrl = arguments.getString("url");
            if (imgUrl != null) {
                NetworkImageView networkImageView = (NetworkImageView) v.findViewById(R.id.image);
                ImageLoader imageLoader = AppController.getInstance().getImageLoader();
                networkImageView.setImageUrl(imgUrl, imageLoader);
            }
        }
        return v;
    }

}
