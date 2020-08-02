package com.example.vissionsikkimnew;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SuccessScreen extends AppCompatActivity {
    Button btnBackHome;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success_screen);

        btnBackHome = findViewById(R.id.btnBackHome);
        btnBackHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),adminhomepage.class));
            }
        });




    }
    public void logout(View view) {
     //   FirebaseAuth.getInstance().signOut();   logout user
        startActivity(new Intent(getApplicationContext(),adminlogin.class));
        finish();
    }
}