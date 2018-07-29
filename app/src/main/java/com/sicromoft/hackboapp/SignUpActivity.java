package com.sicromoft.hackboapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

public class SignUpActivity extends AppCompatActivity {

    private final String TAG = SignUpActivity.class.getSimpleName();
    private EditText name, email, password, password2;
    private Button createAccount;
    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        password2 = findViewById(R.id.password2);
        createAccount = findViewById(R.id.createAccount);

        auth = FirebaseAuth.getInstance();

        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateForm()){
                    createAccount(email.getText().toString(), password.getText().toString());
                }
            }
        });
    }

    private boolean validateForm() {

        if (TextUtils.isEmpty(name.getText().toString())) {
            name.requestFocus();
            name.setError("Campo obligatorio");

        } else if (TextUtils.isEmpty(email.getText().toString())) {
            email.requestFocus();
            email.setError("Campo obligatorio");

        } else if (!password.getText().toString().equals(password2.getText().toString()) || TextUtils.isEmpty(password.getText().toString())) {
            password.requestFocus();
            password2.requestFocus();
            password.setError("Las contraseñas no son iguales");
        } else {
            return true;
        }
        return false;
    }

    private void createAccount(String email, String password){
        auth.createUserWithEmailAndPassword(email,password).
                addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            startLogInActivity();
                        }else{
                            FirebaseAuthException e = (FirebaseAuthException) task.getException();
                            showDialog("Error de verificación", "Failed Registration: " + e.getMessage());
                        }
                    }
                });
    }

    private void startLogInActivity(){
        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("signed_recently", false);
        startActivity(intent);
    }

    private void showDialog(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage(message)
                .setTitle(title)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Log.d(TAG, "User agrees");
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
