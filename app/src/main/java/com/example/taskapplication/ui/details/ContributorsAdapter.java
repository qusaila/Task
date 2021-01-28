package com.example.taskapplication.ui.details;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.taskapplication.R;

import java.util.ArrayList;

public class ContributorsAdapter extends RecyclerView.Adapter<ContributorsAdapter.ContribtursHolder> {

    ArrayList<Contributors> contributors;
    Context context;

    public ContributorsAdapter(ArrayList<Contributors> contributors, Context context) {
        this.contributors = contributors;
        this.context = context;
    }

    @NonNull
    @Override
    public ContribtursHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contribtours_row, parent, false);
        return new ContribtursHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContribtursHolder holder, int position) {
        Contributors contributor = contributors.get(position);
        Glide.with(context)
                .load(contributor.getAvatar_url())
                .centerCrop()

                .into(holder.img);
        holder.nameTv.setText(contributor.getLogin());
    }

    public void notifyList(ArrayList<Contributors> contributors) {
        this.contributors = contributors;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return contributors.size();
    }

    public class ContribtursHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView nameTv;

        public ContribtursHolder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.img);
            nameTv = itemView.findViewById(R.id.nameTv);

        }
    }
}
