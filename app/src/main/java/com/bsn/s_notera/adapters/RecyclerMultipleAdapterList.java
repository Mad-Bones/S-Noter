package com.bsn.s_notera.adapters;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bsn.s_notera.R;
import com.bsn.s_notera.pages.database.Objeto;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class RecyclerMultipleAdapterList extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {
    Activity activity;
    ArrayList<Objeto> listArrayList = new ArrayList<>();
    ArrayList<Objeto> filteredArrayList = new ArrayList<>();
    RecyclerMultipleListener multipleListeners;

    public RecyclerMultipleAdapterList(
            Activity activity,
            ArrayList<Objeto> listArrayList,
            RecyclerMultipleListener multipleListeners){
        this.activity = activity;
        this.listArrayList = listArrayList;
        this.filteredArrayList = listArrayList;
        this.multipleListeners = multipleListeners;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemeView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        RecyclerView.ViewHolder holder=new Holder(itemeView);
        holder.setIsRecyclable(false);
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        final Holder classHolder = (Holder) holder;
        classHolder.setNote(filteredArrayList.get(position));
        classHolder.touchable.setOnClickListener(v->{
            multipleListeners.onTextClicked(filteredArrayList.get(position), position);
            multipleListeners.onTextLongClicked(filteredArrayList.get(position), position);
        });
    }

    @Override
    public int getItemCount() {
        return filteredArrayList.size();
    }


    class Holder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView list_title_text;
        TextView list_content_text;
        TextView list_date_text;
        ImageView list_checked;
        LinearLayout touchable;

        public Holder(View itemView) {
            super(itemView);
            touchable = itemView.findViewById(R.id.item_touch);
            list_title_text = itemView.findViewById(R.id.item_titulo);
            list_content_text = itemView.findViewById(R.id.item_texto);
            list_date_text = itemView.findViewById(R.id.item_fecha);

            list_checked = itemView.findViewById(R.id.item_check);
        }
        void setNote(Objeto note) {
            if(note.isChecked()){
                list_checked.setVisibility(View.VISIBLE);
            }else{
                list_checked.setVisibility(View.GONE);
            }
            long fecha = note.getCreado();
            String fechaText =  new SimpleDateFormat("dd/MM/yy kk:mm", Locale.getDefault()).format(new Date(fecha));
            list_date_text.setText(fechaText);
            list_title_text.setText(note.getTitulo());
            list_content_text.setText(note.getTexto());
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
                    filteredArrayList = listArrayList;
                } else {
                    ArrayList<Objeto> filteredList = new ArrayList<>();
                    for (Objeto row : listArrayList) {

                        if (
                                row.getTexto().toLowerCase().contains(charString.toLowerCase()) ||
                                row.getTitulo().toLowerCase().contains(charString.toLowerCase())) {
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
                filteredArrayList = (ArrayList<Objeto>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}