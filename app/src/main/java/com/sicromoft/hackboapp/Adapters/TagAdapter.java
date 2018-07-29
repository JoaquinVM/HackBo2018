package com.sicromoft.hackboapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sicromoft.hackboapp.Activities.CreateProjectActivity;
import com.sicromoft.hackboapp.Activities.ProfileActivity;
import com.sicromoft.hackboapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joaco99 on 29/07/2018.
 */

public class TagAdapter extends RecyclerView.Adapter<TagAdapter.TagHolder>{

    private List<String> tags = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;

    public TagAdapter(Context context){
        this.context = context;
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
    }

    @Override
    public int getItemCount() {
        return tags.size();
    }

    public void setListContent(List<String> tags) {
        this.tags = tags;
        notifyItemRangeChanged(0, tags.size());
    }

    static class TagHolder extends RecyclerView.ViewHolder {

        private TextView name;

        public TagHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.nameTag);
        }
    }
}
