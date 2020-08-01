package com.example.vissionsikkimnew;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class userhomepage extends AppCompatActivity {
    public Button category1;
    public Button category2;
    public Button category3;
    public Button myprofile;
    public Button helpdesk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userhomepage);
        category1=(Button) findViewById(R.id.button);
        category1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(userhomepage.this, category1.class);
                startActivity(intent);
            }
        });
        category2=(Button) findViewById(R.id.button2);
        category2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(userhomepage.this, category.class);
                startActivity(intent);
            }
        });
        category3=(Button) findViewById(R.id.button3);
        category3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(userhomepage.this,category3.class);
                startActivity(intent);
            }
        });
        myprofile=(Button) findViewById(R.id.button4);
        myprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(userhomepage.this, userprofile1.class);
                startActivity(intent);
            }
        });
        helpdesk=(Button) findViewById(R.id.button5);
        helpdesk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(userhomepage.this,helpdesk1.class);
                startActivity(intent);
            }
        });
    }
}