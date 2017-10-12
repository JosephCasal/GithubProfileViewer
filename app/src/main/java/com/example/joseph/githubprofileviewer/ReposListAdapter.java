package com.example.joseph.githubprofileviewer;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.joseph.githubprofileviewer.model.GithubRepoResponse;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by joseph on 10/12/17.
 */

public class ReposListAdapter extends RecyclerView.Adapter<ReposListAdapter.ViewHolder> {

    List<GithubRepoResponse> repoList = new ArrayList<>();

    public ReposListAdapter(List<GithubRepoResponse> repoList) {
        int reposize = repoList.size();
        this.repoList = repoList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);

//        Log.d(TAG, "onCreateViewHolder: ");

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GithubRepoResponse repo = repoList.get(position);
        holder.tvname.setText(repo.getName());
        holder.tvupdatedat.setText(repo.getUpdatedAt());
        holder.tvlanguage.setText(repo.getLanguage());

//        Log.d(TAG, "onBindViewHolder: ");
    }

    @Override
    public int getItemCount() {
//        Log.d(TAG, "getItemCount: ");

        return repoList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvname;
        TextView tvupdatedat;
        TextView tvlanguage;
        public ViewHolder(View itemView) {
            super(itemView);
            tvname = itemView.findViewById(R.id.tvname);
            tvupdatedat = itemView.findViewById(R.id.tvupdatedat);
            tvlanguage = itemView.findViewById(R.id.tvlanguage);
        }
    }
}
