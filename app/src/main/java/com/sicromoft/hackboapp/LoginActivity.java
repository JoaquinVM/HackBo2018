package com.sicromoft.hackboapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {

    private final String TAG = LoginActivity.class.getSimpleName();
    private FirebaseAuth auth;
    private EditText user, password;
    private TextView join;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = findViewById(R.id.user);
        password = findViewById(R.id.password);
        join = findViewById(R.id.joinTextView);

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        auth = FirebaseAuth.getInstance();

        auth.signInWithEmailAndPassword(user.getText().toString(), password.getText().toString())
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.d(TAG, "signInWithEmail:success");
                            startApp();
                        }else{
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void logIn(String email, String pasword){

    }

    private boolean validateForm() {
        if (user.getText().toString().equals("")) {
            user.requestFocus();
            user.setError("Campo obligatorio");
        } else if (password.getText().toString().equals("")) {
            password.requestFocus();
            password.setError("Campo obligatorio");
        } else {
            return true;
        }
        return false;
    }

    private void startApp() {
        Log.d("LoginActivity", auth.getCurrentUser().getUid());
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
