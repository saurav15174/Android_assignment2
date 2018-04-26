package com.example.saurav.contacts;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class display extends AppCompatActivity {
    RecyclerView recyclerview;
    RecyclerView.LayoutManager lm;
    adapter Adapter;
    String n;
    String e;
    String p;
    ArrayList<user> u=new ArrayList<>();
    public static  ArrayList<String> user_name = new ArrayList<>();
    public static  ArrayList<String> user_number = new ArrayList<>();
    public static  ArrayList<String> user_email  = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(display.this,contact.class);
                startActivity(in);
            }
        });

        user_number.clear();
        user_email.clear();
        user_name.clear();
            try {
                read(user_email,getResources().getString(R.string.useremailfile));
                read(user_number,getResources().getString(R.string.usernumberfile));
                read(user_name,getResources().getString(R.string.usernamefile));
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        recyclerview = (RecyclerView)findViewById(R.id.recycle);
        lm = new LinearLayoutManager(display.this);
        recyclerview.setLayoutManager(lm);
        if(user_name.isEmpty()!=true && user_email.isEmpty()!=true && user_name.isEmpty()!=true)
        {
            for(int i=0;i< user_email.size();i++)
            {
                /*Log.d("saurav","useremail = "+user_email.get(i));
                Log.d("saurav","usernumber = "+user_number.get(i));
                Log.d("saurav","username = "+user_name.get(i));*/
                user Us = new user();
                Us.setName(user_name.get(i));
                Us.setPhone(user_number.get(i));
                Us.setE_mail(user_email.get(i));
                u.add(Us);
            }
            Adapter = new adapter(u,this);
            recyclerview.setAdapter(Adapter);

        }
        else
        {
            user_number.add("7678153882");
            user_email.add("saurav15174@iiitd.ac.in");
            user_name.add("Saurav");

        }
        write(user_email,getResources().getString(R.string.useremailfile));
        write(user_name,getResources().getString(R.string.usernamefile));
        write(user_number,getResources().getString(R.string.usernumberfile));
    }
    public void write(ArrayList<String> text_lines,String name){
        try {
            //Modes: MODE_PRIVATE, MODE_WORLD_READABLE, MODE_WORLD_WRITABLE
            FileOutputStream output = openFileOutput(name,MODE_PRIVATE);
            DataOutputStream dout = new DataOutputStream(output);
            dout.writeInt(text_lines.size()); // Save line count
            for(String line : text_lines){
                    dout.writeUTF(line);
            }
            dout.flush();
            dout.close();
        }
        catch (IOException exc) { exc.printStackTrace(); }
    }
    public  void  read(ArrayList<String> text,String name) throws IOException {
        FileInputStream input = openFileInput(name); // Open input stream
        DataInputStream din = new DataInputStream(input);
        int sz = din.readInt();
        for (int i=0;i<sz;i++) {
            String line = din.readUTF();
            text.add(line);
        }
        din.close();
    }

}
