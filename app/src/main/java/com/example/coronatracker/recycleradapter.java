package com.example.coronatracker;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class recycleradapter extends RecyclerView.Adapter<recycleradapter.ViewHolder> implements Filterable {
    private ArrayList<countrydata> list;
    private ArrayList<countrydata> newlist = new ArrayList<>();
    private Context context;

    recycleradapter(Context context, ArrayList<countrydata> list) {
        this.list = list;
        newlist.addAll(list);
        this.context = context;

    }

    @Override
    public Filter getFilter() {
        return adapterfilter;
    }

    private Filter adapterfilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            ArrayList<countrydata> performlist = new ArrayList<>();
            System.out.println(">>>>>>>>");

            if (charSequence.length() == 0) {
                performlist.addAll(newlist);
            } else {
                for (int i = 0; i < newlist.size(); i++) {
                    if (newlist.get(i).name.toLowerCase().contains(charSequence.toString())) {
                        performlist.add(newlist.get(i));
                    }
                }
            }
            FilterResults results = new FilterResults();
            results.values = performlist;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
            list.clear();
            list.addAll((ArrayList<countrydata>) filterResults.values);
            notifyDataSetChanged();
        }
    };

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView2, textView, textView3, textView4, textView5, textView6, textView7, textView9;
        ImageView imageView, listimageview;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            textView2 = itemView.findViewById(R.id.textView2);
            textView3 = itemView.findViewById(R.id.textView3);
            textView4 = itemView.findViewById(R.id.textView4);
            textView5 = itemView.findViewById(R.id.textView5);
            textView6 = itemView.findViewById(R.id.textView6);
            textView7 = itemView.findViewById(R.id.textView7);
            imageView = itemView.findViewById(R.id.imageView2);
            textView9 = itemView.findViewById(R.id.textView9);
            listimageview = itemView.findViewById(R.id.imageView);

        }
    }


    @NonNull
    @Override
    public recycleradapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = null;
        if (appdata.layout_type == 0)
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recylerlayout, parent, false);
        else
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_recyclerlayout, parent, false);

        return new ViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        if (appdata.layout_type == 0) {
            if (position == 0) {
                holder.textView.setText(list.get(position).name);
                holder.textView2.setText("CASES " + "\n " + list.get(position).cases);
                holder.textView3.setText("DEATHS" + "\n " + list.get(position).deaths);
                holder.textView6.setText("COUNTRIES" + "\n " + list.get(position).todaycases);
                String severity;
                if (list.get(position).active > 100000)
                    severity = "HIGH";
                else if (list.get(position).active > 50000)
                    severity = "MODERATE";
                else
                    severity = "LOW";
                holder.textView7.setText("THREAT" + "\n " + severity);
                holder.textView4.setText("RECOVERED" + "\n " + list.get(position).recoverd);
                holder.textView5.setText("ACTIVE" + "\n " + list.get(position).active);
                if (list.get(position).name.equals("TOTAL"))
                    holder.listimageview.setImageResource(R.drawable.world);
                else
                    holder.listimageview.setImageBitmap(appdata.b.get(list.get(position).name));
            } else {
                holder.textView.setText(list.get(position).name);
                holder.textView2.setText("CASES " + "\n " + list.get(position).cases);
                holder.textView3.setText("DEATHS" + "\n " + list.get(position).deaths);
                holder.textView6.setText("CASES TODAY" + "\n " + list.get(position).todaycases);
                holder.textView7.setText("DEATHS TODAY" + "\n " + list.get(position).todaydeaths);
                holder.textView4.setText("RECOVERED" + "\n " + list.get(position).recoverd);
                holder.textView5.setText("ACTIVE" + "\n " + list.get(position).active);
                //if(appdata.b.get(position-1)!=null)       holder.listimageview.setImageBitmap(appdata.b.get(position-1));
                if (appdata.b.get(list.get(position).name) != null)
                    holder.listimageview.setImageBitmap(appdata.b.get(list.get(position).name));
            }
        } else {
            if (appdata.b.get(list.get(position).name) != null)
                holder.imageView.setImageBitmap(appdata.b.get(list.get(position).name));
            holder.textView9.setText(appdata.locallist.get(position).name);
            holder.imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    appdata.curr_pos = position;
                    Intent intent = new Intent(context, details_country.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);


                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}

