package com.kbk.practice_dialog;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ViewHolder> { //RecyclerView 상속
    ArrayList<Client> items = new ArrayList<Client>();



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.client_item, parent, false);

        return new ViewHolder(itemView);
    }

    //ViewHolder와 데이터 연결 (뷰에 데이터를 연결하는게 바인딩)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Client item = items.get(position);
        int pos= holder.getAdapterPosition();
        Client clientcurrent = items.get(pos);
        holder.textView100.setText(clientcurrent.getName());
        holder.textView101.setText(clientcurrent.getJob());
        holder.textView102.setText(clientcurrent.getMobile());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void addItem(Client item) {
        items.add(item);
    }

    public void setItems(ArrayList<Client> items) { this.items = items;
    }

    public Client getItem(int position) {
        return items.get(position);
    }

    public void setItem(int position, Client item) {
        items.set(position, item);
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView100;
        TextView textView101;
        TextView textView102;

        public ViewHolder(View itemView) {
            super(itemView);
            textView100 = itemView.findViewById(R.id.textView100);
            textView101 = itemView.findViewById(R.id.textView101);
            textView102 = itemView.findViewById(R.id.textView102);

        }

        public void setItem(Client item) {
            textView100.setText(item.getName());
            textView101.setText(item.getJob());
            textView102.setText(item.getMobile());
        }
    }
}
