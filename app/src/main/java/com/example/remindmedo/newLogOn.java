package com.example.remindmedo;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;

public class newLogOn extends AppCompatActivity {

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
                        //Intent intent  = new Intent(newLogOn.this, splashScreen.class);
                        //startActivity(intent);
                    }

                }
            }
        });


        // FOR GOOGLE BUTTON
        // Configure sign-in to request the user's ID, email address, and basic
// profile. ID and basic profile are included in DEFAULT_SIGN_IN.
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



    private boolean validate(String username, String password){
        return username.equals(Username) && password.equals(Password);
    }
}
