package com.example.myapplicationdatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class RegistrationActivity extends AppCompatActivity {
    private EditText Name, Password, Emailid;
    private Button Button1, Signup;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Assigning();

        firebaseAuth = FirebaseAuth.getInstance(); //it is like creating object to the firebase (or) seting instance for the firebase.

        Button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    //upload to the database
                    String user_email = Emailid.getText().toString().trim();
                    String user_password = Password.getText().toString().trim();
                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(RegistrationActivity.this,"Registration Successful",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegistrationActivity.this,MainActivity.class));
                            }
                            else{
                                Toast.makeText(RegistrationActivity.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }
            }
        });
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, MainActivity.class));
            }
        });
    }
    public void Assigning(){
        Name = (EditText) findViewById(R.id.etName);
        Password = (EditText)findViewById(R.id.etpassword);
        Emailid = (EditText)findViewById(R.id.etemail);
        Button1 = (Button)findViewById(R.id.button);
        Signup = (Button)findViewById(R.id.button2);
    }

    public Boolean validate(){
        Boolean result = false;
        String name = Name.getText().toString();
        String password = Password.getText().toString();
        String email = Emailid.getText().toString();
        if(name.isEmpty() && password.isEmpty() && email.isEmpty()){
            Toast.makeText(this,"Please enter all the data",Toast.LENGTH_SHORT).show();
        }
        else{
            result = true;
        }
        return result;

    }
}