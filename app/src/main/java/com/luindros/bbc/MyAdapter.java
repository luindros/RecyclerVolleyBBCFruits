package com.luindros.bbc;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<Fruit> fruits;
    private OnItemClickListener listener;

    public MyAdapter(List<Fruit> fruits) {
        this.fruits = fruits;
    }

    public MyAdapter(List<Fruit> fruits, OnItemClickListener listener) {
        this.fruits = fruits;
        this.listener = listener;
    }

    ///////////////////////////////////////
    @NonNull
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_tiem, parent, false);
        ViewHolder vh=new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        holder.bind(fruits.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return fruits == null ? 0 : fruits.size();
    }

   ///////////////////////////////////////


    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView type;

        public ViewHolder(View itemView) {
            super(itemView);
            type=itemView.findViewById(R.id.type);
        }
        public void bind(final Fruit f, final OnItemClickListener listener) {
            type.setText(f.getType());
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(f, getAdapterPosition());
                }
            });
        }
    }



    public interface OnItemClickListener{
        void onItemClick(Fruit fruit, int position);
    }

}
