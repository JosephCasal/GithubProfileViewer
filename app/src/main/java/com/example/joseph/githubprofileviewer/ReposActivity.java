package com.example.joseph.githubprofileviewer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.joseph.githubprofileviewer.model.GithubProfileResponse;
import com.example.joseph.githubprofileviewer.model.GithubRepoResponse;

import java.util.ArrayList;
import java.util.List;

import static com.example.joseph.githubprofileviewer.R.id.tvcreatedat;
import static com.example.joseph.githubprofileviewer.R.id.tvfollowers;
import static com.example.joseph.githubprofileviewer.R.id.tvlogin;
import static com.example.joseph.githubprofileviewer.R.id.tvrepos;

public class ReposActivity extends AppCompatActivity {

    String user = "JosephCasal";
    private static final String TAG = "ReposActivity";
    RecyclerView rvRepoList;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.ItemAnimator itemAnimator;
    List<GithubRepoResponse> repolist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos);

        rvRepoList = findViewById(R.id.rvRepoList);

        getRepos();



    }

    public void getRepos(){

        retrofit2.Call<List<GithubRepoResponse>> myResponseCallV = RetrofitHelper.getRepos(user);
        myResponseCallV.enqueue(new retrofit2.Callback<List<GithubRepoResponse>>(){

            @Override
            public void onResponse(retrofit2.Call<List<GithubRepoResponse>> call, retrofit2.Response<List<GithubRepoResponse>> response) {

                repolist = response.body();

                layoutManager = new LinearLayoutManager(getApplicationContext());
                itemAnimator = new DefaultItemAnimator();
                ReposListAdapter reposListAdapter = new ReposListAdapter(repolist);

                rvRepoList.setLayoutManager(layoutManager);
                rvRepoList.setItemAnimator(itemAnimator);
                rvRepoList.setAdapter(reposListAdapter);

//                for (GithubRepoResponse g: repolist){
//                    Log.d(TAG, "onResponse: " + g.getName() + " " + g.getCreatedAt() + " " + g.getLanguage());
//                }

//                Log.d(TAG, "onResponse2: " + response.body().getLogin() + " followers: " + response.body().getFollowers() +
//                        " repos: " + response.body().getPublicRepos() + " created at: " + response.body().getCreatedAt());
//                tvlogin.setText(response.body().getLogin());
//                tvfollowers.setText(response.body().getFollowers().toString());
//                tvrepos.setText(response.body().getPublicRepos().toString());
//                tvcreatedat.setText(response.body().getCreatedAt());

            }

            @Override
            public void onFailure(retrofit2.Call<List<GithubRepoResponse>> call, Throwable t) {
                Log.d(TAG, "onFailure2: " + t.toString());
//                tvlogin.setText(t.toString());
            }
        });


//        GithubRepoResponse r1 = new GithubRepoResponse();
//        GithubRepoResponse r2 = new GithubRepoResponse();
//        r1.setName("name1");
//        r2.setName("name2");
//        repolist.add(r1);
//        repolist.add(r2);

    }

}
