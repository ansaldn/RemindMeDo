package com.example.remindmedo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;


public class empty extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_empty);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        View signOut = findViewById(R.id.signOut);
        signOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.signOut) {
                    signOut();
                    Intent intent = new Intent(empty.this, newLogOn.class);
                    startActivity(intent);

                    Toast.makeText(empty.this, "Success", Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(empty.this, newLogOn.class);
                    startActivity(intent);

                    Toast.makeText(empty.this, "Failed", Toast.LENGTH_SHORT).show();
                }
            }
            private void signOut(){
                mGoogleSignInClient.signOut()
                        .addOnCompleteListener(empty.this, new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                            }
                        });
            }



        });
    }
}