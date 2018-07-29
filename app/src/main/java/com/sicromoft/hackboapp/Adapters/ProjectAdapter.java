package com.sicromoft.hackboapp.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sicromoft.hackboapp.Beans.Project;
import com.sicromoft.hackboapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joaco99 on 29/07/2018.
 */

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectHolder>{

    private ArrayList<Project> projects = new ArrayList<>();
    private Context context;
    private LayoutInflater inflater;

    public ProjectAdapter(Context context){
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public ProjectHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.project_overview, parent, false);
        return new ProjectHolder(view);
    }

    @Override
    public void onBindViewHolder(ProjectHolder holder, int position) {
        TagAdapter adapter = new TagAdapter(context);
        RecyclerView recyclerView;
        List<String> tags;
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class ProjectHolder extends RecyclerView.ViewHolder {
        private View itemView;
        private String name, overview, description;

        public ProjectHolder(View itemView) {
            super(itemView);

        }
    }
}
