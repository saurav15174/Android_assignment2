package com.example.saurav.contacts;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

import static com.example.saurav.contacts.display.user_email;
import static com.example.saurav.contacts.display.user_name;
import static com.example.saurav.contacts.display.user_number;

/**
 * Created by saurav on 31/3/18.
 */

public class adapter extends RecyclerView.Adapter<adapter.Recyclerviewholder>{

    ArrayList<user> arrayList = new ArrayList<>();
    Context con;
    public adapter(ArrayList<user> arrayList, Context con)

    {
        this.arrayList = arrayList;
        this.con=con;
    }
    public Recyclerviewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_layout,parent,false);
        Recyclerviewholder recycle = new Recyclerviewholder(view,con,arrayList);
        return  recycle;
    }

    @Override
    public void onBindViewHolder(Recyclerviewholder holder, int position) {
        user User = arrayList.get(position);
        holder.name.setText(User.getName());
        holder.phone.setText(User.getPhone());

    }

    @Override
    public int getItemCount()   {
        return arrayList.size();
    }
    public class Recyclerviewholder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name, phone;
        ArrayList<user> users = new ArrayList<user>();
        Context con;
        public Recyclerviewholder(View itemView,Context con,ArrayList<user> users) {
            super(itemView);
            this.users=users;
            this.con = con;
            itemView.setOnClickListener(this);
            name = itemView.findViewById(R.id.name);
            phone = itemView.findViewById(R.id.phone);
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            user per = this.users.get(position);


            Intent in = new Intent(con,profile.class);
            in.putExtra("n",user_name.get(position));
            in.putExtra("p",user_number.get(position));
            in.putExtra("e",user_email.get(position));
            con.startActivity(in);
        }
    }


}
