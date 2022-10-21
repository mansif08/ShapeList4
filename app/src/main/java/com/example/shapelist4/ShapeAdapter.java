package com.example.shapelist4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ShapeAdapter extends RecyclerView.Adapter<ShapeAdapter.ViewHolder>
{
    private ArrayList<Shape> recyclingArrayList;
    private OnItemListener onItemListener;
    public ShapeAdapter(ArrayList<Shape>recyclingArrayList, OnItemListener onItemListener)
    {
        this.recyclingArrayList=recyclingArrayList;
        this.onItemListener=onItemListener;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.shape_cell, parent, false);
        return new ViewHolder(view,onItemListener);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.recycleImage.setImageResource(recyclingArrayList.get(position).getImage());
        holder.recycleText.setText(recyclingArrayList.get(position).getName());
    }
    @Override
    public int getItemCount() {
        return recyclingArrayList.size();
    }


    public interface OnItemListener
    {
        void onItemClick(int position);

    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private ImageView recycleImage;
        private TextView recycleText;
        private OnItemListener onItemListener;

        public ViewHolder(@NonNull View itemView, OnItemListener onItemListener)
        {
            super(itemView);
            recycleImage = (ImageView) itemView.findViewById(R.id.shapeImage);
            recycleText = (TextView) itemView.findViewById(R.id.shapeName);
            this.onItemListener = onItemListener;
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view)
        {
            onItemListener.onItemClick(getAdapterPosition());
        }
    }


}
