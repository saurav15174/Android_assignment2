package com.example.saurav.contacts;

import android.content.ContentProvider;
import android.content.ContentProviderOperation;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import static com.example.saurav.contacts.display.user_email;
import static com.example.saurav.contacts.display.user_name;
import static com.example.saurav.contacts.display.user_number;

public class contact extends AppCompatActivity {
    private EditText phone;
    private EditText name;
    private EditText email;
    private Button b;
    private Button b1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        phone  = findViewById(R.id.phone);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        b= findViewById(R.id.addconatact);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (phone.getText().toString().isEmpty() == true || name.getText().toString().isEmpty() == true || email.getText().toString().isEmpty() == true) {
                    Toast.makeText(contact.this, "One of the field is empty!", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    user_name.add(name.getText().toString());
                    user_email.add(email.getText().toString());
                    user_number.add(phone.getText().toString());
                    write(user_email,getResources().getString(R.string.useremailfile));
                    write(user_name,getResources().getString(R.string.usernamefile));
                    write(user_number,getResources().getString(R.string.usernumberfile));
                    Toast.makeText(getBaseContext(), "Contact is successfully added", Toast.LENGTH_SHORT).show();
                    name.setText("");
                    email.setText("");
                    phone.setText("");
                    Intent in = new Intent(contact.this,display.class);
                    in.putExtra("n_name",name.getText().toString());
                    in.putExtra("p_phone",phone.getText().toString());
                    in.putExtra("e_email",email.getText().toString());
                    startActivity(in);
                    finish();
                }
            }
          });
        }
    public void write(ArrayList<String> text_lines,String name){
        try {
            //Modes: MODE_PRIVATE, MODE_WORLD_READABLE, MODE_WORLD_WRITABLE
            FileOutputStream output = openFileOutput(name,MODE_PRIVATE);
            DataOutputStream dout = new DataOutputStream(output);
            dout.writeInt(text_lines.size()); // Save line count
            for(String line : text_lines) // Save lines
                dout.writeUTF(line);
            dout.flush();
            dout.close();
        }
        catch (IOException exc) { exc.printStackTrace(); }
    }
    public void onBackPressed()
    {

        this.startActivity(new Intent(contact.this,display.class));

        return;
    }



}
