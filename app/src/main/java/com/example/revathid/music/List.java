package com.example.revathid.music;


import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import com.example.revathid.music.MyService.MyLocalBinder;
public class List extends AppCompatActivity {
    public static  String EXTRA_MESSAGE = "com.example.createmusicapp.MESSAGE";
    private ArrayList<Music> arrayList;
    private CustomMusicAdapter adapter;
    private ListView songsList;

    private BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //Log.i("@music", "Airplane mode changed");
            Toast.makeText(getApplicationContext(),"Modified Airplane mode : "+intent.getBooleanExtra("state", false),Toast.LENGTH_SHORT).show();
        }
    };

    MyService myService;
    boolean isBound = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        registerReceiver(receiver,filter);

        Intent i = new Intent(this,MyService.class);
        bindService(i,myConnection, Context.BIND_AUTO_CREATE);

        songsList = (ListView) findViewById(R.id.songsList);
        arrayList = new ArrayList<>();
        arrayList.add(new Music("kalimba", R.raw.kalimba));
        arrayList.add(new Music("maidwiththeflaxenhair", R.raw.maidwiththeflaxenhair));
        arrayList.add(new Music("sleepaway", R.raw.sleepaway));

        adapter = new CustomMusicAdapter(this, R.layout.custom_music_item, arrayList);
        songsList.setAdapter(adapter);
        songsList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String Templist = arrayList.get(position).getName();
                Intent intent = new Intent(List.this, Player.class);
                intent.putExtra(EXTRA_MESSAGE, Templist);
                startActivity(intent);
            }
        });
    }
    private ServiceConnection myConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            MyLocalBinder binder = (MyLocalBinder) service;
            myService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isBound = false;
        }
    };

}

