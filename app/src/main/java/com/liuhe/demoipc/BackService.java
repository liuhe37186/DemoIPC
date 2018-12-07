package com.liuhe.demoipc;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

public class BackService extends Service {
    public BackService() {
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        startService(new Intent(BackService.this,BookManagerService.class));
    }
}
