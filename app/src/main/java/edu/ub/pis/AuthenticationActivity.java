package edu.ub.pis;

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

public class AuthenticationActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private EditText mEmail;
    private EditText mPassword;
    private Button mLogIn;
    private Button mSignUp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        mAuth = FirebaseAuth.getInstance();

        mEmail = (EditText) findViewById(R.id.idEmail);
        mPassword = (EditText) findViewById(R.id.idPassword);
        mLogIn =  (Button) findViewById(R.id.logIn_Button);
        mSignUp = (Button) findViewById(R.id.signUp_Button);


        mLogIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        mSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signUp(mEmail.toString(), mPassword.toString());

            }
        });
    }

    protected void signUp(String email, String password){
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Intent intent = new Intent(AuthenticationActivity.this, HomeActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Sign Up failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}