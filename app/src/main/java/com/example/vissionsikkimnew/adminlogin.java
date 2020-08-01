package com.example.vissionsikkimnew;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class adminlogin extends AppCompatActivity {
    EditText unique_id, password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminlogin);
        unique_id = findViewById(R.id.editText);
        password = findViewById(R.id.editText2);
        login = findViewById(R.id.button);
        String unique = unique_id.getText().toString();
        validateuniqueid();

        validpassword();
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(adminlogin.this, adminhomepage.class);
                startActivity(intent);
            }
        });

    }


    private Boolean validateuniqueid() {
        String val = unique_id.getText().toString();
        if (val.isEmpty()) {
            unique_id.setError("please enter your unique id");
            return false;
        } else {
            unique_id.setError(null);
            return true;
        }
    }

    private Boolean validpassword() {
        String val1 = password.getText().toString();
        if (val1.isEmpty()) {
            password.setError("please enter your password");
            return false;
        } else {
            password.setError(null);
            return true;
        }

    }

    public void loginuser(View view) {
        if (!validateuniqueid() | !validpassword()) {
            return;
        } else {
            isUser();
        }
    }

    private void isUser() {

        final String userEnterid = unique_id.getText().toString().trim();
        final String userEnteredpassword = password.getText().toString().trim();
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
        Query checkuser = reference.orderByChild("unique_id").equalTo(userEnterid);
        checkuser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {

                    unique_id.setError(null);

                    String uniqueidFromDB = snapshot.child(userEnterid).child("password").getValue(String.class);
                    if (uniqueidFromDB.equals((userEnterid))) {

                        String passwordfromdDB = snapshot.child(userEnteredpassword).child("password").getValue(String.class);

                        Intent intent = new Intent(adminlogin.this, userhomepage.class);
                        intent.putExtra("password", uniqueidFromDB);


                        startActivity(intent);

                    } else {
                        unique_id.setError("Wrong password");
                        unique_id.requestFocus();
                    }
                } else {
                    password.setError("no such user exist");
                    password.requestFocus();
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}