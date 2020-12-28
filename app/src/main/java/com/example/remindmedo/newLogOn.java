package com.example.remindmedo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class newLogOn extends AppCompatActivity {

    String TAG;
    private EditText username;
    private EditText password;
    private Button loginBTN;

    private final String Username = "Admin";
    private final String Password = "Password1";

    boolean isValid = false;
    SignInButton signin;
    int RC_SIGN_IN = 0;

    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_log_on);

        // FOR STANDARD BUTTON
        username = findViewById(R.id.usernameText);
        password = findViewById(R.id.passwordText);
        loginBTN = findViewById(R.id.standardLogin);

        loginBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputUsername = username.getText().toString();
                String inputPassword = password.getText().toString();

                if (inputUsername.isEmpty() || inputPassword.isEmpty())
                {
                    Toast.makeText(newLogOn.this, "You have not entered a username or password!", Toast.LENGTH_SHORT).show();
                }else{
                    isValid = validate(inputUsername, inputPassword);
                    if (!isValid) {
                        Toast.makeText(newLogOn.this, "The username or password is incorrect.", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(newLogOn.this, "Success.", Toast.LENGTH_SHORT).show();

                        //This should open up the next screen if the log in is successful.
                        Intent intent  = new Intent(newLogOn.this, empty.class);
                        startActivity(intent);
                    }
                }
            }
            private boolean validate(String username, String password){
                return username.equals(Username) && password.equals(Password);
            }
        });

        // FOR GOOGLE BUTTON
        // Configure sign-in to request the user's ID, email address, and basic profile. ID and basic profile are included in DEFAULT_SIGN_IN.

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        GoogleSignInClient mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        signin = findViewById(R.id.googleLogin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.getId() == R.id.googleLogin) {
                    signIn();
                    Toast.makeText(newLogOn.this, "Success", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(newLogOn.this, empty.class);
                    startActivity(intent);
                }else {
                        Toast.makeText(newLogOn.this, "Sign In Unsuccessful", Toast.LENGTH_SHORT).show();
                }
            }

            private void signIn(){
                Intent signInIntent = mGoogleSignInClient.getSignInIntent();
                startActivityForResult(signInIntent, RC_SIGN_IN);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask){
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);
        } catch (ApiException e){
            Log.w(TAG,"signInResult:failed code=" + e.getStatusCode());
        }
    }

}
