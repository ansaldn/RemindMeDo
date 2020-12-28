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

public class signUp extends AppCompatActivity {

    private Button signUpBTN;
    private EditText name;
    private EditText last;
    private EditText number;
    private EditText address;
    private EditText email;
    private EditText password;
    private EditText verifyPassword;
    private String TAG;

    boolean isValid = false;

    int RC_SIGN_IN = 0;
    SignInButton signin;
    GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //To make sure user can only submit once the minimum amount on the form is filled.
        signUpBTN = findViewById(R.id.signUpBTN);
        name = findViewById(R.id.firstName);
        last = findViewById(R.id.lastName);
        number = findViewById(R.id.mobileNumber);
        address = findViewById(R.id.houseAddress);
        email = findViewById(R.id.emailAddress);
        password = findViewById(R.id.password);
        verifyPassword = findViewById(R.id.verifyPassword);


        signUpBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputName = name.getText().toString();
                String inputLast = last.getText().toString();
                String inputNumber = number.getText().toString();
                String inputEmail = email.getText().toString();
                String inputPassword = password.getText().toString();
                String inputverifyPassword = verifyPassword.getText().toString();

                if (inputName.isEmpty() || inputLast.isEmpty() || inputNumber.isEmpty() || inputEmail.isEmpty() || inputPassword.isEmpty()) {
                    Toast.makeText(signUp.this, "You have not completed the sign up form.", Toast.LENGTH_SHORT).show();
                }else{
                    isValid = validate();
                    if (!isValid) {
                        Toast.makeText(signUp.this, "The passwords do not match", Toast.LENGTH_SHORT).show();
                    }else{
                        Intent intent = new Intent(signUp.this, main.class);
                        startActivity(intent);
                        Toast.makeText(signUp.this, "Successful!", Toast.LENGTH_SHORT).show();
                    }
                }

            }

        });

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
                    Toast.makeText(signUp.this, "Success", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(signUp.this, empty.class);
                    startActivity(intent);
                }else {
                    Toast.makeText(signUp.this, "Sign In Unsuccessful", Toast.LENGTH_SHORT).show();
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
            Log.w(TAG, "signInResult:failed code=" + e.getStatusCode());
        }
    }

    private boolean validate() {
        return password.equals(verifyPassword);
    }

}