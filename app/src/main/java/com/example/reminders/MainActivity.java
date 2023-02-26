package com.example.reminders;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.LoaderManager;
import android.app.PendingIntent;
import android.content.ContentUris;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toolbar mToolbar;
    RecyclerView recyclerView;
    List<Reminder> reminders;
    public static final String URL ="https://api.jsonserve.com/rbGrwE";
    public static final int NOTIFICATION_ID = 0;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mToolbar = findViewById(R.id.my_toolbar);
        setSupportActionBar(mToolbar);
    recyclerView = findViewById(R.id.Reminderlist);
    reminders = new ArrayList<>();

    extractReminders();

    Intent intent = new Intent(this,MyReceiver.class);
    PendingIntent pendingIntent = PendingIntent.getBroadcast(this, NOTIFICATION_ID, intent, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager alarmManager = (AlarmManager) this.getSystemService(this.ALARM_SERVICE);
        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP,  System.currentTimeMillis(),
                1000*60*60*24, pendingIntent);

    }

    private void extractReminders() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                    for(int i = 0; i< response.length(); i++){
                        try {
                            JSONObject jsonObject = response.getJSONObject(i);
                            Reminder reminder = new Reminder();
                            reminder.setNote(jsonObject.getString("note").toString());
                            reminder.setDate(jsonObject.getString("date").toString());
                            reminder.setTime(jsonObject.getString("time").toString());
                            reminders.add(reminder);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new Adapter(getApplicationContext(),reminders);
                recyclerView.setAdapter(adapter);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("DARE", "there is a problem" + error.getMessage());
            }
        });
            queue.add(request);
    }


}

