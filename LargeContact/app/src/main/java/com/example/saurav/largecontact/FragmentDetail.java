package com.example.saurav.largecontact;

/**
 * Created by saurav on 3/4/18.
 */
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;


public class FragmentDetail extends Fragment{

    private Bundle args;
    TextView name;
    TextView email;
    TextView phone;
    Button Phone;
    Button Email;
    Button Eedit;
    String useremail;
    String usernumber;
    String username;
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_detail, container, false);
        name = view.findViewById(R.id.Naam);
        email = view.findViewById(R.id.mail);
        phone = view.findViewById(R.id.call);
        Phone = view.findViewById(R.id.calluser);
        Email = view.findViewById(R.id.emailuser);
        Eedit = view.findViewById(R.id.edituser);
        args=getArguments();
        if(args!=null) {
            ArrayList<String> data = args.getStringArrayList("data");
            name.setText(data.get(0));
            phone.setText(data.get(1));
            email.setText(data.get(2));
            username = data.get(0);
            usernumber = data.get(1);
            useremail = data.get(2);
        }
        Phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_DIAL);
                callIntent.setData(Uri.parse("tel:"+usernumber));
                startActivity(callIntent);
            }
        });
        Email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent email = new Intent(Intent.ACTION_SENDTO);
                email.setData(Uri.parse("mailto:"+useremail));
                startActivity(email);
            }
        });
        Eedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(),Edit.class);
                intent.putExtra("extra_n",username);
                intent.putExtra("extra_p",usernumber);
                intent.putExtra("extra_e",useremail);
                startActivity(intent);

            }
        });
        return view;
    }
}
