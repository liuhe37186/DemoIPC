package com.liuhe.demoipc;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;

import java.util.List;

public interface IMusicManager extends IInterface {
    String DESCRIPTOR = "com.liuhe.demoipc.IMusicManger";
    int TRANSACTION_getMusicList = IBinder.FIRST_CALL_TRANSACTION + 0;
    List<Music> getMusicList() throws RemoteException;
}
