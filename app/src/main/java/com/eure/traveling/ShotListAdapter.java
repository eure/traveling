package com.eure.traveling;


import com.eure.traveling.entity.Shot;
import com.squareup.picasso.Picasso;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ShotListAdapter extends BaseAdapter {

    private Context context;
    private List<Shot> shots;

    public ShotListAdapter(Context context, List<Shot> shots) {
        this.context = context;
        this.shots = shots;
    }

    @Override
    public int getCount() {
        return shots.size();
    }

    @Override
    public Shot getItem(int position) {
        return shots.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_row, parent, false);
        }

        TextView title = (TextView) view.findViewById(R.id.title);
        TextView playerName = (TextView) view.findViewById(R.id.player_name);
        TextView likesCount = (TextView) view.findViewById(R.id.likes_count);
        ImageView imageView = (ImageView) view.findViewById(R.id.image);

        title.setText(getItem(position).getTitle());
        playerName.setText(getItem(position).getDesigner().getName());
        likesCount.setText(Integer.toString(getItem(position).getLikesCount()));
        Picasso.with(context).load(getItem(position).getImage().getTeaser()).into(imageView);

        return view;
    }

    public boolean add(List<Shot> shots) {
        boolean ress = this.shots.addAll(shots);
        if (ress) {
            notifyDataSetChanged();
        }
        return ress;
    }
}
