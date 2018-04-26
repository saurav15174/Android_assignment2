package com.example.saurav.largecontact;

/**
 * Created by saurav on 3/4/18.
 */
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class Fragmentlist extends  Fragment{
    public static ArrayList<String> user_name = new ArrayList<>();
    public static  ArrayList<String> user_number = new ArrayList<>();
    public static  ArrayList<String> user_email  = new ArrayList<>();
    RecyclerView recyclerview;
    RecyclerView.LayoutManager lm;
    adapter Adapter;
    String n;
    String e;
    String p;
    ArrayList<user> u=new ArrayList<>();
    Button addcontact;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        addcontact = view.findViewById(R.id.add);
        addcontact.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent in = new Intent(getActivity(),contact.class);
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
        recyclerview = (RecyclerView)view.findViewById(R.id.recycle);
        lm = new LinearLayoutManager(getActivity());
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

        Adapter = new adapter(u,getActivity());
        Adapter.clicklistener(new adapter.listener() {
            @Override
            public void OnClickmethod(View v, int pos) {

                Log.d("ggg","activity click listener");
                FragmentDetail fragmentDetail = new FragmentDetail();
                Bundle b=new Bundle();
                ArrayList<String> temp=new ArrayList<String>();
                temp.add(user_name.get(pos));
                temp.add(user_email.get(pos));
                temp.add(user_number.get(pos));
                b.putStringArrayList("data",temp);
                fragmentDetail.setArguments(b);
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.second,fragmentDetail).commit();
            }
        });
        recyclerview.setAdapter(Adapter);

        return view;
    }
    public void write(ArrayList<String> text_lines,String name){
        try {
            //Modes: MODE_PRIVATE, MODE_WORLD_READABLE, MODE_WORLD_WRITABLE
            FileOutputStream output = getActivity().openFileOutput(name,MODE_PRIVATE);
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
        FileInputStream input = getActivity().openFileInput(name); // Open input stream
        DataInputStream din = new DataInputStream(input);
        int sz = din.readInt();
        for (int i=0;i<sz;i++) {
            String line = din.readUTF();
            text.add(line);
        }
        din.close();
    }

}
