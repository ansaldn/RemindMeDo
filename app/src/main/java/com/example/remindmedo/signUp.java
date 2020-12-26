package com.example.remindmedo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.common.SignInButton;

public class signUp extends AppCompatActivity {

    private Button signUpBTN;
    private EditText name;
    private EditText last;
    private EditText number;
    private EditText address;
    private EditText email;
    private EditText password;
    private EditText verifyPassword;

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
                        Toast.makeText(signUp.this, "Successful!", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

        /*
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
        }*/
    }

    private boolean validate() {
        return password.equals(verifyPassword);
    }

}