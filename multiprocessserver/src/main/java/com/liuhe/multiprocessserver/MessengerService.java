package com.liuhe.multiprocessserver;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

public class MessengerService extends Service {
    public MessengerService() {
    }

    private final Messenger mMessenger = new Messenger(new MessengerHandler());

    private static class MessengerHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){
                case 0:
                    Log.i("xxxx","收到client消息："+msg.getData().getString("msg"));
                    Messenger client = msg.replyTo;
                    Message replyMessage =  Message.obtain(null,1);
                    Bundle bundle = new Bundle();
                    bundle.putString("reply","server 收到消息");
                    replyMessage.setData(bundle);

                    try {
                        client.send(replyMessage);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
                 default:
                     super.handleMessage(msg);
            }


        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mMessenger.getBinder();
    }


}
