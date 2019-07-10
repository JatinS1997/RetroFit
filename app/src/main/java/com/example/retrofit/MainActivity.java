package com.example.retrofit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private TextView textViewresult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        textViewresult = findViewById(R.id.text_view_result);

        Retrofit retrofit = new Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com/").addConverterFactory(GsonConverterFactory.create()).build();

        ReqresApi reqresApi = retrofit.create(ReqresApi.class);

        Call<List<Post>> call = reqresApi.getPosts();
        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if(!response.isSuccessful()){
                    textViewresult.setText("Code: "+ response.code());
                    return;

                }

                List<Post> posts = response.body();
                for (Post post:posts){
                    String content="";
                    content += "ID: "+ post.getId()+"\n";
                    content += "User id: "+ post.getUserId()+"\n";
                    content += "title: "+ post.getTitle()+"\n";
                    content += "body: "+ post.getBody()+"\n\n";

                    textViewresult.append(content);

                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

                textViewresult.setText(t.getMessage());

            }
        });

    }
}
