package com.sicromoft.hackboapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sicromoft.hackboapp.Activities.CreateProjectActivity;
import com.sicromoft.hackboapp.R;

import java.util.ArrayList;

/**
 * Created by Joaco99 on 29/07/2018.
 */

public class TagAdapter extends RecyclerView.Adapter<TagAdapter.TagHolder>{

    private ArrayList<String> tags = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;
    private CreateProjectActivity activity;

    public TagAdapter(Context context, CreateProjectActivity activity){
        this.context = context;
        this.activity = activity;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public TagHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.tag_preview, parent, false);
        return new TagHolder(view);
    }

    @Override
    public void onBindViewHolder(TagHolder holder, int position) {
        final String name = tags.get(position);
        holder.name.setText(name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TagAdapter.this.activity.deleteFromList(name);
            }
        });
    }

    @Override
    public int getItemCount() {
        return tags.size();
    }

    public void setListContent(ArrayList<String> tags) {
        this.tags = tags;
        notifyItemRangeChanged(0, tags.size());
    }

    static class TagHolder extends RecyclerView.ViewHolder {

        private View itemView;
        private TextView name;

        public TagHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            name = itemView.findViewById(R.id.nameTag);
        }
    }
}
