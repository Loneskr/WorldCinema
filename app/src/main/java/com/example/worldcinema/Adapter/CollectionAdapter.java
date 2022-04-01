package com.example.worldcinema.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.worldcinema.R;
import com.example.worldcinema.UI.Collections.Collection;

import java.util.ArrayList;

public class CollectionAdapter extends RecyclerView.Adapter<CollectionAdapter.ViewHolder> {

    private ArrayList<Collection> collections;
    private LayoutInflater inflater;
    private Context context;

    public CollectionAdapter(@NonNull Context context, ArrayList<Collection> collections
    ) {
        this.context = context;
        this.collections = collections;
        this.inflater = LayoutInflater.from(context);;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_collection, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.setTextCinema(collections.get(position).getCol_Title());
        holder.iconCollection.setImageResource(collections.get(position).getCol_ItemId());
    }

    @Override
    public int getItemCount() {
        return collections.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        final private ImageView iconCollection;
        final private TextView txtCollection;

        private ViewHolder(View view) {
            super(view);
            this.iconCollection = (ImageView) view.findViewById(R.id.imgCollection);
            this.txtCollection = (TextView) view.findViewById(R.id.txtCollection);
        }

        public void setTextCinema(String text){
            this.txtCollection.setText(text);
        }
    }
}
