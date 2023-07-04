package com.eutraining.llolluni.androidfourthassignment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {
    EditText input;
    TextView output;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        input = findViewById(R.id.editTextText);
        output = findViewById(R.id.textView);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("message");
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String previousMessage = output.getText().toString();
                output.setText(previousMessage + "\n" + snapshot.getValue().toString());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void send(View view) {
        if (!input.getText().toString().isEmpty()) {
            reference.setValue(input.getText().toString());
            DatabaseReference reference2 = database.getReference("AllMessages");
            reference2.push().setValue(input.getText().toString());
            input.setText("");
        } else {
            Toast.makeText(this, "Please write something", Toast.LENGTH_LONG).show();
        }
    }
}