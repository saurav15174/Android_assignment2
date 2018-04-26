package com.example.saurav.contacts;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class profile extends AppCompatActivity {
    TextView name;
    TextView phone;
    TextView mail;
    Button call;
    Button Mail;
    String useremail;
    String usernumber;
    String username;
    Button edit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        edit = findViewById(R.id.edituser);
        name = findViewById(R.id.Naam);
        phone = findViewById(R.id.call);
        mail = findViewById(R.id.mail);
        call = findViewById(R.id.calluser);
        Mail = findViewById(R.id.emailuser);
        useremail = getIntent().getStringExtra("e");
        usernumber = getIntent().getStringExtra("p");
        username = getIntent().getStringExtra("n");
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+usernumber));
                startActivity(callIntent);
            }
        });
        Mail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            Intent email = new Intent(Intent.ACTION_SENDTO);
            email.setData(Uri.parse("mailto:"+useremail));
            startActivity(email);
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(profile.this,Edit.class);
                intent.putExtra("extra_n",username);
                intent.putExtra("extra_p",usernumber);
                intent.putExtra("extra_e",useremail);
                startActivity(intent);
                finish();
            }
        });
        mail.setText(useremail);
        name.setText(username);
        phone.setText(usernumber);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.startActivity(new Intent(profile.this,display.class));

        return;
    }
}
