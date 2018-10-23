package com.liuhe.demoipc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListActivity extends AppCompatActivity {

    ListView listView;
    String[] strings = {"AIDL","Messenger","Binder","Bundle","ContentProvider","Socket","ShareFile","启动Server"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        listView = findViewById(R.id.list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,strings);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        startActivity(new Intent(ListActivity.this,AidlActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(ListActivity.this,MessengerActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(ListActivity.this,MainActivity.class));
                        break;
                    case 6:
                        Intent intent = new Intent();
                        intent.setClassName("com.liuhe.multiprocessserver","com.liuhe.multiprocessserver.MainActivity");
                        startActivity(intent);
                        break;
                }
            }
        });


    }
}
