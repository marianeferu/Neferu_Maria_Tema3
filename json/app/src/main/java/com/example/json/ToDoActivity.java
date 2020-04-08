package com.example.json;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class ToDoActivity extends AppCompatActivity implements ToDoAdapter.OnItemClickListener{
    private RecyclerView mRecyclerView;
    private ToDoAdapter mToDoAdapter;
    private ArrayList<ToDo> mExampleList;
    private RequestQueue mRequestQueue;


    public static final String EXTRA_USERID = "UserId";
    public static final String EXTRA_ID = "Id";
    public static final String EXTRA_TITLE = "Title";
    public static final String EXTRA_COMPLETED = "Completed";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo);

        mRecyclerView = findViewById(R.id.recycler_view2);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExampleList = new ArrayList<>();

        mRequestQueue = Volley.newRequestQueue(this);

        parseJSON();
    }

    private void parseJSON() {

        String user_id = getIntent().getStringExtra("Id");
        String url = "https://jsonplaceholder.typicode.com/todos?userId=" + user_id;


        JsonArrayRequest request = new JsonArrayRequest(url,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                            try {

                                for (int i = 0; i < response.length(); i++) {

                                JSONObject hit = response.getJSONObject(i);


                                String userId = hit.getString("userId");
                                String id = hit.getString("id");
                                String title = hit.getString("title");
                                String completed = hit.getString("completed");
                                mExampleList.add(new ToDo(userId, id, title, completed));}


                                mToDoAdapter = new ToDoAdapter(ToDoActivity.this, mExampleList);
                                mToDoAdapter.setOnItemClickListener(ToDoActivity.this);
                                mRecyclerView.setAdapter(mToDoAdapter);

                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });

        mRequestQueue.add(request);
    }

    @Override
    public void onItemClick(int position) {
        Intent detailIntent = new Intent(this, ButtonsActivity.class);
        ToDo clickedItem = mExampleList.get(position);

        detailIntent.putExtra(EXTRA_USERID, clickedItem.getUserId());
        detailIntent.putExtra(EXTRA_ID, clickedItem.getId());
        detailIntent.putExtra(EXTRA_TITLE, clickedItem.getTitle());
            detailIntent.putExtra(EXTRA_COMPLETED, clickedItem.getCompleted());

        startActivity(detailIntent);
    }
}