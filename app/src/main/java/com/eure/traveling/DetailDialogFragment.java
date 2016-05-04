package com.eure.traveling;

import com.squareup.picasso.Picasso;

import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class DetailDialogFragment extends DialogFragment {

    static DetailDialogFragment newInstance(String imgUrl) {
        DetailDialogFragment f = new DetailDialogFragment();
        Bundle args = new Bundle();
        args.putString("url", imgUrl);
        f.setArguments(args);
        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.detail, container, false);

        Picasso.with(getContext()).load(getArguments().getString("url")).into((ImageView) v.findViewById(R.id.image));
        return v;
    }

}
