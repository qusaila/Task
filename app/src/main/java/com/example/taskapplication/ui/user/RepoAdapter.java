package com.example.taskapplication.ui.user;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.taskapplication.R;
import com.example.taskapplication.listener.ItemListener;

import java.util.ArrayList;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RepoViewHolder> {
    ArrayList<User> data;
    ItemListener itemListener;

    public RepoAdapter(ArrayList<User> data, ItemListener itemListener) {
        this.data = data;
        this.itemListener = itemListener;
    }

    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.repo_rows, parent, false);
        return new RepoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder holder, int position) {
        User reposetoryData = data.get(position);
        holder.nameTv.setText(reposetoryData.getName());

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public void notifyList(ArrayList<User> data) {
        this.data = data;
        notifyDataSetChanged();

    }

    public class RepoViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView nameTv;

        public RepoViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTv = itemView.findViewById(R.id.nameTv);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            itemListener.onItemClick(data.get(getAdapterPosition()));
        }
    }
}
