package com.example.joseph.githubprofileviewer;

import com.example.joseph.githubprofileviewer.model.GithubProfileResponse;
import com.example.joseph.githubprofileviewer.model.GithubRepoResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by joseph on 10/12/17.
 */

public class RetrofitHelper {

    private static final String BASE_URL = "https://api.github.com/";

    public static Retrofit create(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return retrofit;

    }

//    public static Call<GithubProfileResponse> getCall(){
//        Retrofit retrofit = create();
//        RetrofitService service = retrofit.create(RetrofitService.class);
//        return service.getResponse();
//    }

    public static Call<GithubProfileResponse> getUserProfile(String string){
        Retrofit retrofit = create();
        RetrofitService service = retrofit.create(RetrofitService.class);
        return service.getUserProfile(string);
    }

    public static Call<List<GithubRepoResponse>> getRepos(String string){
        Retrofit retrofit = create();
        RetrofitService service = retrofit.create(RetrofitService.class);
        return service.getRepos(string);
    }

    public interface RetrofitService {

//        @GET("v2/59de72541000000213a8514e")
//        Call<GithubProfileResponse> getResponse();

        @GET("users/{user}")
        Call<GithubProfileResponse> getUserProfile(@Path("user") String user);

        @GET("users/{user}/repos")
        Call<List<GithubRepoResponse>> getRepos(@Path("user") String user);



        //for query parameters
//        @GET("v2/59de72541000000213a8514e?user=manny")
//        Call<MyResponse> getResponse(@Query("user") String user);


        //for array of objects returned use Call<List<MyResponse>>
//        @GET("{version}/59de72541000000213a8514e")
//        Call<List<MyResponse>> getResponseForV(@Path("version") String version);
//        [
//            {
//                "name":"John",
//                    "age":30,
//                    "cars":[ "Ford", "BMW", "Fiat" ]
//            },
//            {
//                "name":"John",
//                    "age":30,
//                    "cars":[ "Ford", "BMW", "Fiat" ]
//            }
//        ]

    }

}
