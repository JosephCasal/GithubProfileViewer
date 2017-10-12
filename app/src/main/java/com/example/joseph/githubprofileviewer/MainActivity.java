package com.example.joseph.githubprofileviewer;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.joseph.githubprofileviewer.model.GithubProfileResponse;

import okhttp3.OkHttpClient;
import okhttp3.Request;

public class MainActivity extends AppCompatActivity {

//    private static final String BASE_URL = "http://www.mocky.io/v2/59de72541000000213a8514e";
    private static final String TAG = "MainActivity";
    public static final String user = "JosephCasal";
    private TextView tvlogin;
    private TextView tvfollowers;
    private TextView tvrepos;
    private TextView tvcreatedat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvlogin = (TextView) findViewById(R.id.tvlogin);
        tvfollowers = (TextView) findViewById(R.id.tvfollowers);
        tvrepos = (TextView) findViewById(R.id.tvrepos);
        tvcreatedat = (TextView) findViewById(R.id.tvcreatedat);

        getGithubProfile();

    }

    public void getGithubProfile(){
        retrofit2.Call<GithubProfileResponse> myResponseCallV = RetrofitHelper.getUserProfile(user);
        myResponseCallV.enqueue(new retrofit2.Callback<GithubProfileResponse>(){

            @Override
            public void onResponse(retrofit2.Call<GithubProfileResponse> call, retrofit2.Response<GithubProfileResponse> response) {
                Log.d(TAG, "onResponse2: " + response.body().getLogin() + " followers: " + response.body().getFollowers() +
                        " repos: " + response.body().getPublicRepos() + " created at: " + response.body().getCreatedAt());
                tvlogin.setText(response.body().getLogin());
                tvfollowers.setText(response.body().getFollowers().toString());
                tvrepos.setText(response.body().getPublicRepos().toString());
                tvcreatedat.setText(response.body().getCreatedAt());
            }

            @Override
            public void onFailure(retrofit2.Call<GithubProfileResponse> call, Throwable t) {
                Log.d(TAG, "onFailure2: " + t.toString());
                tvlogin.setText(t.toString());
            }
        });
    }

    public void getRepos(View view) {

        Intent intent = new Intent(this, ReposActivity.class);
        startActivity(intent);

    }
}
