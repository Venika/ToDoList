package com.example.todolist;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

//Responsible for displaying data from model into a row in recycler view
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder>{

    public interface OnLongClickListener{
        void onItemLongClicked(int position);
    }

    List<String> items;
    OnLongClickListener longClickListener;

    public ItemsAdapter(List<String> items, OnLongClickListener lClickListener) {
        this.items = items;
        this.longClickListener = lClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //Use layout inflator to inflate a view

        View todoView = LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false);

        //wrap it inside a View Holder and return it
        return new ViewHolder(todoView);

    }

    //resposible for binding data to a particular viewholder
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        //grab the item at the position
        String item = items.get(position);

        //Bind the item into the specified view holder
        holder.bind(item);
    }

    //tells the recycler view how many items are in the list
    @Override
    public int getItemCount() {
        return items.size();
    }

    //container to provide easy access to view that represent each row of the list
    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvItem;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            tvItem = itemView.findViewById(android.R.id.text1);
        }

        //update the view inside the view holder with this data
        public void bind(String item) {
            tvItem.setText(item);
            tvItem.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    //Notifies the listener which position was long pressed
                    longClickListener.onItemLongClicked(getAdapterPosition());
                    return true;
                }
            });
        }
    }
}
