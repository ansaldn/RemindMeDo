package com.example.remindmedo;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

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
            }

    private boolean validate() {
        return password.equals(verifyPassword);
    }

}