package com.example.revathid.music;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyService extends Service {

    private  final IBinder myBinder = new MyLocalBinder();
    public MyService() {
    }

    public class MyLocalBinder extends Binder{
        MyService getService(){
            return MyService.this;
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return myBinder;
    }
}
