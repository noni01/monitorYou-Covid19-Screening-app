package com.example.monitoryou;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DashboardActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageButton assess, checkup;

    @Override
    protected void onCreate( @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        assess= (ImageButton) findViewById(R.id.selfassess);
        checkup=(ImageButton) findViewById(R.id.health);

        assess.setOnClickListener(this);
        checkup.setOnClickListener(this);



    }
@Override
    public void onClick( View v ) {
        switch (v.getId()){
            case R.id.selfassess:
                startActivity(new Intent(this, SelfAssess.class));
                Toast.makeText(this, "self assessment", Toast.LENGTH_SHORT).show();
                break;

            case R.id.health:
                startActivity(new Intent(this, heath.class));
                Toast.makeText(this, "health checkup.", Toast.LENGTH_SHORT).show();

                break;
        }
    }
}
