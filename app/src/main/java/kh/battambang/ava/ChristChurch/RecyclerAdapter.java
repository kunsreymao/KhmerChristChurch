package kh.battambang.ava.ChristChurch;

/**
 * Created by PHIE on 5/12/2018.
 */

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private ArrayList<Data> listdata;
    private Activity activity;
    private Context context;

    public RecyclerAdapter(Activity activity, ArrayList<Data> listdata) {
        this.listdata = listdata;
        this.activity = activity;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //holder.mImage.setImageResource(listdata.get(position).getThubnail());
        holder.id.setText(listdata.get(position).getId());
        holder.title.setText(listdata.get(position).getTitle());
        holder.desc.setText(listdata.get(position).getDescription());
        final ViewHolder x=holder;
        Glide.with(activity)
                .load(listdata.get(position).getThubnail())
                .into(holder.thumbnail);
        holder.id.setVisibility(View.GONE);
    }
    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private CardView cv;
        private TextView id,title,desc;
        private ImageView thumbnail;

        public ViewHolder(View v) {
            super(v);
            cv=(CardView)v.findViewById(R.id.card_view);
            id=(TextView)v.findViewById(R.id.id);
            title=(TextView)v.findViewById(R.id.title);
            desc=(TextView)v.findViewById(R.id.description);
            thumbnail=(ImageView)v.findViewById(R.id.thumbnail);
        }
    }

}