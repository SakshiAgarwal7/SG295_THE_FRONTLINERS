package com.example.vissionsikkimnew;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class userhomepage extends AppCompatActivity {
    public Button btnListOfSchemes,btnViewMyPro,btnListYouApply,btnHelpDesk;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userhomepage);
        btnListOfSchemes=(Button) findViewById(R.id.btnListOfSchemes);
        btnListOfSchemes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(userhomepage.this, ListOfRunningSchemes.class);
                startActivity(intent);
            }
        });
        btnViewMyPro=(Button) findViewById(R.id.btnViewMyPro);
        btnViewMyPro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(userhomepage.this, userprofile1.class);
                startActivity(intent);
            }
        });
        btnListYouApply=(Button) findViewById(R.id.btnListYouApply);
        btnListYouApply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(userhomepage.this, ListYouApply.class);
                startActivity(intent);
            }
        });
        btnHelpDesk=(Button) findViewById(R.id.btnHelpDesk);
        btnHelpDesk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(userhomepage.this, helpdesk1.class);
                startActivity(intent);
            }
        });

    }
}