package com.eutraining.llolluni.androidfourthassignment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity2 extends AppCompatActivity {
    EditText email, password;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        email = findViewById(R.id.editTextTextEmailAddress);
        password = findViewById(R.id.editTextTextPassword);
        auth = FirebaseAuth.getInstance();
    }

    public void signUp(View view) {
        if (!email.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
            auth.createUserWithEmailAndPassword(email.getText().toString(),
                    password.getText().toString()
            ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        showMessage("Success", "User created!");
                        email.setText("");
                        password.setText("");
                    } else {
                        showMessage("Error", task.getException().getMessage());
                    }
                }
            });
        }
    }

    public void signIn(View view) {
        if (!email.getText().toString().isEmpty() && !password.getText().toString().isEmpty()) {
            auth.signInWithEmailAndPassword(email.getText().toString(),
                    password.getText().toString()
            ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        showMessage("Success", "Loged in successfully!");
                        email.setText("");
                        password.setText("");
                    } else {
                        showMessage("Error", task.getException().getMessage());
                    }
                }
            });
        }
    }

    private void showMessage(String title, String message) {
        new AlertDialog.Builder(this).setTitle(title).setMessage(message).setCancelable(true).show();
    }
}