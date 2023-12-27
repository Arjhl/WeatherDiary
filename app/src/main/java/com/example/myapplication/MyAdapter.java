package com.example.myapplication;

// YourAdapter.java
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private final List<Item> objList;
    private Context context;

    public MyAdapter(ArrayList<Item> yourObjectList, Context context) {
        this.objList = yourObjectList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item yourObject = objList.get(position);
        holder.nameTextView.setText(yourObject.getDes());

        // Using Picasso library to load image from URL
        Picasso.get().load(yourObject.getImageUrl()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if(objList == null) return 0;
        return objList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.img_card); // Replace with your actual ImageView ID
            nameTextView = (TextView) itemView.findViewById(R.id.tv_card); // Replace with your actual TextView ID
        }
    }
}
