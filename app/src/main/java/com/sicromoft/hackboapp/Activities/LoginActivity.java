package com.sicromoft.hackboapp.Activities;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.sicromoft.hackboapp.R;

public class LoginActivity extends AppCompatActivity {

    private final String TAG = LoginActivity.class.getSimpleName();
    private FirebaseAuth auth;
    private EditText user, password;
    private TextView join;
    private Button logButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = findViewById(R.id.user);
        password = findViewById(R.id.password);
        join = findViewById(R.id.joinTextView);
        logButton = findViewById(R.id.logButton);

        join.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });

        auth = FirebaseAuth.getInstance();

        logButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateForm()){
                    logIn(user.getText().toString(), password.getText().toString());
                }
            }
        });

    }

    private void logIn(String email, String password){
        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.d(TAG, "signInWithEmail:success");
                            startApp();
                        }else{
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            showDialog("Error", "No se pudo verificar el correo o contraseña");
                        }
                    }
                });
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
