package com.example.vissionsikkimnew;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class loginactivity extends AppCompatActivity {
    EditText aadhar_number, phone_number;
    Button sendotp, Register, admin;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_loginactivity);
        aadhar_number = findViewById(R.id.editText);
        phone_number = findViewById(R.id.editText2);
        sendotp = findViewById(R.id.button);
        Register = findViewById(R.id.button2);
        admin = findViewById((R.id.button5));
        firebaseAuth = FirebaseAuth.getInstance();
        validateaadharno();

        sendotp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String num = phone_number.getText().toString();
                validno();
                Intent intent = new Intent(loginactivity.this, otpscreen.class);
                intent.putExtra("mobile", num);
                startActivity(intent);
                Toast.makeText(loginactivity.this, "", Toast.LENGTH_SHORT).show();
            }
        });

        //to go to signup page
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginactivity.this, MainActivity.class);
                startActivity(intent);
            }
        });


        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginactivity.this, adminlogin.class);
                startActivity(intent);
            }
        });
    }


    private Boolean validateaadharno() {
        String val = aadhar_number.getText().toString();
        if (val.isEmpty()) {
            aadhar_number.setError("please enter your aadhar no.");
            return false;
        } else {
            aadhar_number.setError(null);
            return true;
        }
    }

    private Boolean validno() {
        String num = phone_number.getText().toString();
        if (num.isEmpty()) {
            phone_number.setError("please enter your password");
            return false;
        } else {
            phone_number.setError(null);
            return true;
        }

    }
    public void loginuser(View v){
            if (!validateaadharno() | !validno()) {
                return;
            } else {
                isUser();
            }
        }
        private void isUser() {

            final String userEnteredaadhar = aadhar_number.getText().toString().trim();
            final String userEntermobile = phone_number.getText().toString().trim();
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("users");
            Query checkuser = reference.orderByChild("aadhar_number").equalTo(userEnteredaadhar);
            ((Query) checkuser).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {

                        aadhar_number.setError(null);

                        String aadharFromDB = snapshot.child(userEnteredaadhar).child("aadhar_number").getValue(String.class);
                        if (aadharFromDB.equals((userEnteredaadhar))) {
                            String numberfromdDB = snapshot.child(userEntermobile).child("mobile").getValue(String.class);

                            Intent intent = new Intent(loginactivity.this, userhomepage.class);
                            intent.putExtra("aadhar", aadharFromDB);
                            intent.putExtra("number", numberfromdDB);


                            startActivity(intent);

                        } else {
                            aadhar_number.setError("Wrong aadhar");
                            aadhar_number.requestFocus();
                        }
                    } else {
                        phone_number.setError("not a user");
                        phone_number.requestFocus();
                    }

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });


        }

    }



















