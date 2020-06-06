package com.example.myapplicationdatabase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private Button Login;
    private EditText Name;
    private EditText password;
    private TextView Info;
    private int count = 5;
    private Button signup;
    private FirebaseAuth firebaseauth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Name =  (EditText)findViewById(R.id.etName);
        password =  (EditText)findViewById(R.id.etPass);
        Info = (TextView)findViewById(R.id.tS);
        Login = (Button)findViewById(R.id.Login);
        signup = (Button)findViewById(R.id.signup);

        Info.setText("No of attempts remaining: 5");




        firebaseauth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseauth.getCurrentUser();
        if(user!=null){
            finish();
            Intent obj = new Intent(this,SecondActivity.class);
            startActivity(obj);
        }
        progressDialog = new ProgressDialog(this);
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                validate(Name.getText().toString(), password.getText().toString());
            }
        });
//        Login.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                check();
//            }
//        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signup();
            }
        });

    }
    /*public void Check(String username, String userpassword){
        if((username == "Admin") || (userpassword == "1234")){
            Intent intent=new Intent(this, SecondActivity.class);
            startActivity(intent);

        }
        else{
            count--;

            Info.setText("No of Atempts Remaining" + String.valueOf(count));

            if(count == 0){
                Login.setEnabled(false);

            }
        }
    }*/
    public void check(){
        Intent intent=new Intent(this, SecondActivity.class);
        startActivity(intent);
    }
    public void signup(){
        Intent intent=new Intent(this,RegistrationActivity.class);
        startActivity(intent);
    }

    private void validate(String username,String userpassword){
        firebaseauth.createUserWithEmailAndPassword(username,userpassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(MainActivity.this,"LoginSuccessfull",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,SecondActivity.class));
                }
                else{
                    Toast.makeText(MainActivity.this,"LoginFailure",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }


}
