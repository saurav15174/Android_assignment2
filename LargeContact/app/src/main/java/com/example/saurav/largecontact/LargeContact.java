package com.example.saurav.largecontact;

import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class LargeContact extends AppCompatActivity {

    FragmentDetail detail = new FragmentDetail();
    Fragmentlist list = new Fragmentlist();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_large);
        getSupportFragmentManager().beginTransaction().add(R.id.first,list,"listfrag").commit();
        getSupportFragmentManager().beginTransaction().add(R.id.second,detail,"listdet").commit();

    }
}
