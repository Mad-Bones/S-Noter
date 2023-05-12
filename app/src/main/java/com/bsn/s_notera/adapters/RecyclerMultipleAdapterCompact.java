package com.bsn.s_notera.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bsn.s_notera.R;

import java.util.ArrayList;

public class RecyclerMultipleAdapterCompact extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {
    Activity activity;
    ArrayList<String> filteredArrayList = new ArrayList<>();
    ArrayList<String> titleArrayList = new ArrayList<>();
    RecyclerMultipleListener multipleListeners;

    public RecyclerMultipleAdapterCompact(
            Activity activity,
            ArrayList<String> listArrayList,
            RecyclerMultipleListener multipleListeners){
        this.activity = activity;
        this.titleArrayList = listArrayList;
        this.filteredArrayList = listArrayList;
        this.multipleListeners = multipleListeners;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemeView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_title,parent,false);
        RecyclerView.ViewHolder holder=new Holder(itemeView);
        holder.setIsRecyclable(false);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Holder classHolder = (Holder) holder;
        try{
            classHolder.title_title_text.setText(filteredArrayList.get(position));
        }catch (Exception e){
            e.printStackTrace();
        }
        classHolder.touchable.setOnClickListener(v->{
            multipleListeners.onTitleClicked(filteredArrayList.get(position), position);
        });
    }

    @Override
    public int getItemCount() {
        return filteredArrayList.size();
    }


    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView title_title_text;
        LinearLayout touchable;

        public Holder(View itemView) {
            super(itemView);
            touchable = itemView.findViewById(R.id.item_comp);
            title_title_text = itemView.findViewById(R.id.titulo_item_comp);
        }
        @Override
        public void onClick(View v) {
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    filteredArrayList = titleArrayList;
                } else {
                    ArrayList<String> filteredList = new ArrayList<>();
                    for (String row : titleArrayList) {
                        if (row.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    filteredArrayList = filteredList;
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = filteredArrayList;
                return filterResults;
            }

            @SuppressLint("NotifyDataSetChanged")
            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                filteredArrayList = (ArrayList<String>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}