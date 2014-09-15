package com.eure.traveling;



import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

public class ShotsListAdapter extends BaseAdapter{

    private Context context;
    private List<Shots> shotsList;

    private static class ViewHolder {
        TextView title;
        TextView playerName;
        TextView likesCount;
        NetworkImageView image;
    }


    public ShotsListAdapter(Context context, List<Shots> shotsList){
        this.context = context;
        this.shotsList = shotsList;
    }

    @Override
    public int getCount() {
        return shotsList.size();
    }

    @Override
    public Shots getItem(int position) {
        return shotsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder holder;

        if(view == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view  = inflater.inflate(R.layout.list_row, parent, false);

            TextView title = (TextView)view.findViewById(R.id.title);
            TextView playerName = (TextView)view.findViewById(R.id.player_name);
            TextView likesCount = (TextView)view.findViewById(R.id.likes_count);
            NetworkImageView image = (NetworkImageView)view.findViewById(R.id.image);

            holder = new ViewHolder();
            holder.title = title;
            holder.playerName = playerName;
            holder.likesCount = likesCount;
            holder.image = image;
            view.setTag(holder);
        }else{
            holder = (ViewHolder)view.getTag();
        }

        holder.title.setText(shotsList.get(position).title);
        holder.playerName.setText(shotsList.get(position).playerName);
        holder.likesCount.setText(Integer.toString(shotsList.get(position).likesCount));

        ImageLoader imageLoader = AppController.getInstance().getImageLoader();
        holder.image.setImageUrl(shotsList.get(position).imageTeaserUrl, imageLoader);

        return view;
    }

    public boolean add (List<Shots> shots){
        boolean ress = shotsList.addAll(shots);
        if(ress){
            notifyDataSetChanged();
        }
        return ress;
    }
}
