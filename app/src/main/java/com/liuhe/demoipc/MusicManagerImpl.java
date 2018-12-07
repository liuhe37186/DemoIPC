package com.liuhe.demoipc;

import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;

import java.util.List;

/**
 *
 */
class MusicManagerImpl extends Binder implements IMusicManager {

    public MusicManagerImpl() {
        this.attachInterface(this, DESCRIPTOR);
    }

    public static IMusicManager asInterface(IBinder obj) {
        if ((obj == null)) {
            return null;
        }
        android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
        if (((iin != null) && (iin instanceof com.liuhe.demoipc.IBookManager))) {
            return ((IMusicManager) iin);
        }
        return new MusicManagerImpl.Proxy(obj);
    }

    @Override
    public List<Music> getMusicList() throws RemoteException {
        return null;
    }

    @Override
    public IBinder asBinder() {
        return this;
    }

    @Override
    public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException {
        switch (code) {
            case INTERFACE_TRANSACTION: {
                reply.writeString(DESCRIPTOR);
                return true;
            }
            case TRANSACTION_getMusicList: {
                data.enforceInterface(DESCRIPTOR);
                java.util.List<Music> _result = this.getMusicList();
                reply.writeNoException();
                reply.writeTypedList(_result);
                return true;
            }

        }
        return super.onTransact(code, data, reply, flags);
    }

    private static class Proxy implements IMusicManager {
        private IBinder mRemote;

        public Proxy(IBinder mRemote) {
            this.mRemote = mRemote;
        }

        public java.lang.String getInterfaceDescriptor() {
            return DESCRIPTOR;
        }

        @Override
        public List<Music> getMusicList() throws RemoteException {
            android.os.Parcel _data = android.os.Parcel.obtain();
            android.os.Parcel _reply = android.os.Parcel.obtain();
            java.util.List<Music> _result;
            try {
                _data.writeInterfaceToken(DESCRIPTOR);
                mRemote.transact(TRANSACTION_getMusicList, _data, _reply, 0);
                _reply.readException();
                _result = _reply.createTypedArrayList(Music.CREATOR);
            } finally {
                _reply.recycle();
                _data.recycle();
            }
            return _result;
        }

        @Override
        public IBinder asBinder() {
            return mRemote;
        }
    }
}