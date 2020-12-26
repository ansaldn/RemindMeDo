package com.example.remindmedo;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;

public class newLogOn extends AppCompatActivity {

    private EditText username;
    private EditText password;
    private Button loginBTN;

    private final String Username = "Admin";
    private final String Password = "Password1";

    boolean isValid = false;
    int RC_SIGN_IN = 0;
    SignInButton signin;

    GoogleSignInClient mGoogleSignInClient;


    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
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
                        Intent intent  = new Intent(newLogOn.this, Main.class);
                        startActivity(intent);
                    }

                }
            }
        });

        // FOR GOOGLE BUTTON
        // Configure sign-in to request the user's ID, email address, and basic profile. ID and basic profile are included in DEFAULT_SIGN_IN.


        signin = findViewById(R.id.googleLogin);
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()){
                    case R.id.googleLogin:
                        SignIn();
                        break;
                }
            }
        });

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

    }
    @Override
    protected void onStart() {
        super.onStart();
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        Intent intent = new Intent(this, Main.class);
        startActivity(intent);
    }



    private void SignIn() {
            Intent signInIntent = mGoogleSignInClient.getSignInIntent();
            startActivityForResult(signInIntent, RC_SIGN_IN);
        }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            handleSignInResult(task);
        }
    }

    private void handleSignInResult(Task<GoogleSignInAccount> completedTask) {
        try {
            GoogleSignInAccount account = completedTask.getResult(ApiException.class);

            // Signed in successfully, show authenticated UI.
            Intent intent = new Intent(this, Main.class);
            startActivity(intent);
        } catch (ApiException e) {
            // The ApiException status code indicates the detailed failure reason.
            // Please refer to the GoogleSignInStatusCodes class reference for more information.
            Log.w("Error", "signInResult:failed code=" + e.getStatusCode());
        }
    }

    private boolean validate(String username, String password){
        return username.equals(Username) && password.equals(Password);
    }
}
