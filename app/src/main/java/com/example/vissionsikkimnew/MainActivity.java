package com.example.vissionsikkimnew;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    EditText aadhar;
    EditText fullname;
    EditText phonenumber;
    Button otpget;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase rootnode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        aadhar = findViewById(R.id.editText);
        fullname= findViewById(R.id.editText3);
        phonenumber= findViewById(R.id.editText4);
        otpget = findViewById(R.id.button);

        firebaseAuth = FirebaseAuth.getInstance();

        otpget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                rootnode = FirebaseDatabase.getInstance();
                reference = rootnode.getReference("users");

                String mobile = phonenumber.getText().toString().trim();
                String name = fullname.getText().toString();
                String adhar = aadhar.getText().toString();


                validNo(mobile);
                Intent intent = new Intent(MainActivity.this, otpscreen.class);
                intent.putExtra("mobile", mobile);
                startActivity(intent);
                Toast.makeText(MainActivity.this, mobile, Toast.LENGTH_LONG).show();
                firebaseAuth = FirebaseAuth.getInstance();
                UserHelperClass helperClass = new UserHelperClass(adhar, mobile,name);
                reference.child(adhar).setValue(helperClass);


                if(adhar.isEmpty() && adhar.length()!=12){
                    aadhar.setError(" Aadhar number is Required.");
                    aadhar.requestFocus();
                    return;
                }

                if(name.isEmpty() && name.length()>20){
                    fullname.setError("Password is Required.");
                    fullname.requestFocus();
                    return;
                }
            }
        });
    }

    private void validNo(String mobile) {
        if (mobile.isEmpty() || mobile.length() != 10) {
            phonenumber.setError("enter a valid phone number:");
            phonenumber.requestFocus();
            return;
        }
    }


}































