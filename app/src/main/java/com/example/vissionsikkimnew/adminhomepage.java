package com.example.vissionsikkimnew;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


public class adminhomepage extends AppCompatActivity {
    Button btnUploadScheme,btnUpdateScheme,btnShowData,btnCustomerPortal,btnDocVerify,btnMyInfo;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adminhomepage);

        btnMyInfo = findViewById(R.id.btnMyInfo);
        btnUploadScheme = findViewById(R.id.btnUploadScheme);
        btnUpdateScheme = findViewById(R.id.btnUpdateScheme);
        btnCustomerPortal = findViewById(R.id.btnCustomerPortal);

        btnMyInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), UpdateScheme.class));
            }
        });

        btnUploadScheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText ac = new EditText(view.getContext());
                AlertDialog.Builder conformationCode = new AlertDialog.Builder(view.getContext());
                conformationCode.setTitle("Security Check");
                conformationCode.setMessage("Enter Your Security Pin");
                conformationCode.setView(ac);

                conformationCode.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // extract entered pin ------
                        String mail = ac.getText().toString().trim();
                        // check in firebase for ac
//                        firebaseAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>(){
//                            @Override
//                            public void onSuccess(Void aVoid){
//                                Toast.makeText(Login.this, "Reset Link Set Your Email", Toast.LENGTH_SHORT).show();
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(Login.this, "ERROR ! Reset Link Is Not Sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        });
                        if (mail.equals("101")) {


                            startActivity(new Intent(getApplicationContext(), UploadNewSchemes.class));
                        }else{

                            Toast.makeText(adminhomepage.this, "Correct pin is 101 so plz enter the pin : 101 ", Toast.LENGTH_LONG).show();
                        }
                    }
                });

                conformationCode.setNegativeButton("Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // close the dialog

                    }
                });

                conformationCode.create().show();
            }
        });

        btnUpdateScheme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final EditText ac = new EditText(view.getContext());
                AlertDialog.Builder conformationCode = new AlertDialog.Builder(view.getContext());
                conformationCode.setTitle("Security Check");
                conformationCode.setMessage("Enter Your Security Pin");
                conformationCode.setView(ac);

                conformationCode.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // extract entered pin ------
                        String mail = ac.getText().toString().trim();
                        // check in firebase for ac
//                        firebaseAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>(){
//                            @Override
//                            public void onSuccess(Void aVoid){
//                                Toast.makeText(Login.this, "Reset Link Set Your Email", Toast.LENGTH_SHORT).show();
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(Login.this, "ERROR ! Reset Link Is Not Sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
//                            }
//                        });
                        if (mail.equals("101")) {


                            startActivity(new Intent(getApplicationContext(), ListOfRunningSchemes.class));
                        }else{

                            Toast.makeText(adminhomepage.this, "Enter the pin : 101 ", Toast.LENGTH_LONG).show();
                        }
                    }
                });

                conformationCode.setNegativeButton("Back", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // close the dialog

                    }
                });

                conformationCode.create().show();
            }
        });


        btnCustomerPortal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), ListOfRunningSchemes.class));
            }
        });




    }
}