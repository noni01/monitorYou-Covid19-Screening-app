package com.example.monitoryou;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.annotations.NotNull;

import java.util.HashMap;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{

    private FirebaseAuth mAuth;
    private TextView banner, registerUser;
    private EditText editTextFullname, editTextEmail, editTextPassword, editTextEmpid;
    private ProgressBar progressBar;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        banner = (TextView) findViewById(R.id.banner);
        banner.setOnClickListener(this);

        registerUser = (Button)findViewById(R.id.register);
        registerUser.setOnClickListener(this);
        editTextFullname=(EditText) findViewById(R.id.uid);
        editTextEmail=(EditText)findViewById(R.id.email);
        editTextPassword =(EditText)findViewById(R.id.password);
        editTextEmpid =(EditText)findViewById(R.id.empid);

        progressBar=(ProgressBar)findViewById(R.id.progressbar);


    }

    @Override
    public void onClick( View v ) {
        switch (v.getId()){
            case R.id.banner:
                startActivity(new Intent(this, MainActivity.class));
                break;
            case R.id.register:
                registerUser();
                break;
        }

    }

    private void registerUser() {
        String email= editTextEmail.getText().toString().trim();
        String passwords= editTextPassword.getText().toString().trim();
        String fullname= editTextFullname.getText().toString().trim();
        String empid = editTextEmpid.getText().toString().trim();

        if(fullname.isEmpty()) {
            editTextFullname.setError("Enter your Fullname");
            editTextFullname.requestFocus();
            return;
        }
        if(email.isEmpty()) {
            editTextEmail.setError("Enter email");
            editTextEmail.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("please provide valid email");
            editTextEmail.requestFocus();
            return;
        }
        if(passwords.isEmpty()) {
            editTextPassword.setError("Enter password");
            editTextPassword.requestFocus();
            return;
        }
        if(passwords.length()<6){
            editTextPassword.setError("Min password lenght should be 6 characters:");
            editTextPassword.requestFocus();
            return;
        } else {
            register(empid,fullname,email,passwords);
        }






    }

    private void register(final String empid, String fullname, String email, String passwords ) {
        Object dialog = Utils.showLoader(RegisterActivity.this);

        mAuth.createUserWithEmailAndPassword(email, passwords)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            FirebaseUser firebaseUser = mAuth.getCurrentUser();
                            assert firebaseUser != null;
                            String userid = firebaseUser.getUid();
                            progressBar.setVisibility(View.VISIBLE);

                            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users").child(userid);

                            HashMap<String, String> hashMap = new HashMap<>();
                            hashMap.put("id", userid);
                            hashMap.put("empId", empid);
                            hashMap.put("full name", fullname );
                            hashMap.put("imageURL", "default");
                            hashMap.put("status","");

                            reference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if (task.isSuccessful()){
                                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                                        startActivity(intent);
                                        finish();
                                    }
                                }
                            });
                        } else {
                            Toast.makeText(RegisterActivity.this, "You can't register woth this email or password", Toast.LENGTH_SHORT).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }
}



