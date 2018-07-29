package com.sicromoft.hackboapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.sicromoft.hackboapp.Activities.ShowProjectActivity;
import com.sicromoft.hackboapp.Beans.Project;
import com.sicromoft.hackboapp.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Joaco99 on 29/07/2018.
 */

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectHolder>{

    private List<Project> projects = new ArrayList<>();
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
        Project project = projects.get(position);

        holder.name.setText(project.getName());
        holder.overview.setText(project.getOverview());
        holder.recyclerView.setLayoutManager(new GridLayoutManager(context, 4, GridLayoutManager.VERTICAL, false));
        adapter.setListContent((ArrayList<String>) project.getTags());
        holder.recyclerView.setAdapter(adapter);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ShowProjectActivity.class);
                intent.putExtra("Project", project);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return projects.size();
    }

    public void setListContent(List<Project> projects) {
        this.projects = projects;
        notifyItemRangeChanged(0, projects.size());
    }

    static class ProjectHolder extends RecyclerView.ViewHolder {
        private View itemView;
        private TextView name, overview;
        private RecyclerView recyclerView;

        public ProjectHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            name = itemView.findViewById(R.id.name);
            overview = itemView.findViewById(R.id.overview);
            recyclerView = itemView.findViewById(R.id.recyclerView);
        }
    }
}
