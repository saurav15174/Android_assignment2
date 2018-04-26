package com.example.saurav.contacts;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import static com.example.saurav.contacts.display.user_email;
import static com.example.saurav.contacts.display.user_name;
import static com.example.saurav.contacts.display.user_number;

public class Edit extends AppCompatActivity {
    private Button b;
    private EditText name;
    private EditText num;
    private EditText email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_edit);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        b = findViewById(R.id.edit);
        name = findViewById(R.id.n);
        num = findViewById(R.id.p);
        email= findViewById(R.id.e);
        super.onCreate(savedInstanceState);
        name.setHint(R.string.namehint);
        num.setHint(R.string.numhint);
        email.setHint(R.string.emailhint);
        b = findViewById(R.id.edit);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().isEmpty()==true && num.getText().toString().isEmpty()==true && email.getText().toString().isEmpty()==true){
                    Toast.makeText(Edit.this, "all the fields are empty!", Toast.LENGTH_SHORT).show();
                }
                if (!name.getText().toString().isEmpty())
                {
                    user_name.set(user_name.indexOf(getIntent().getExtras().getString("extra_n")),name.getText().toString());
                    write(user_name,getResources().getString(R.string.usernamefile));

                }
                if (!num.getText().toString().isEmpty())
                {
                    user_number.set(user_number.indexOf(getIntent().getExtras().getString("extra_p")),num.getText().toString());
                    write(user_number,getResources().getString(R.string.usernumberfile));

                }
                if (!email.getText().toString().isEmpty())
                {
                    user_email.set(user_email.indexOf(getIntent().getExtras().getString("extra_n")),email.getText().toString());
                    write(user_name,getResources().getString(R.string.useremailfile));

                }
                Intent in = new Intent(Edit.this,display.class);
                startActivity(in);
            }
        });

    }
    public void write(ArrayList<String> text_lines, String name){
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

}
