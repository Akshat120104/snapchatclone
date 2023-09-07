package com.example.snapchat;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class MainActivity extends AppCompatActivity {

    EditText edtmail, edtpass;
    Button btn, btn2;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtmail = findViewById(R.id.edtmail);
        edtpass = findViewById(R.id.edtpass);
        btn = findViewById(R.id.btn);
        btn2 = findViewById(R.id.btn2);
        mAuth = FirebaseAuth.getInstance();
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp();
            }
        });
    }

    public void login() {
        mAuth.signInWithEmailAndPassword(edtmail.getText().toString(), edtpass.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Toast.makeText(MainActivity.this, "LOGIN SUCCESSFUL", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, homeActivity.class);
                            startActivity(intent);



                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(MainActivity.this, "LOGIN UNSUCCESSFUL", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
    }

    public void signUp() {
        mAuth.createUserWithEmailAndPassword(edtmail.getText().toString(), edtpass.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(MainActivity.this, "User Signed In", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, homeActivity.class);
                            startActivity(intent);
                            addUserToDatabase();

                        } else {
                            Toast.makeText(MainActivity.this, "SignUp Failed", Toast.LENGTH_SHORT).show();

                        }
                    }

                });

    }
    private void addUserToDatabase()
    {
        String uid = mAuth.getUid();
        FirebaseDatabase.getInstance().getReference().child("user").child(uid).child("email").setValue(edtmail.getText().toString());
    }
}