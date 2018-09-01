package ru.oktemsec.clearwater_admin_2;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ru.oktemsec.clearwater_admin_2.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "mytag";
    private FirebaseAuth mAuth;
    public FirebaseAuth.AuthStateListener mAuthListener;

    private EditText etEmail;
    private EditText etPassword;
    public Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    //User is signed in
                    Log.d(TAG, "signed_in: " + user.getUid());
                    //Call next activity
                    Intent intent = new Intent(MainActivity.this, ListActivity.class);
                    startActivity(intent);
                }
                else {
                    //User is signed out
                    Log.d(TAG, "signed_out: ");
                }
            }
        };

        etEmail = (EditText) findViewById(R.id.etsEmail);
        etPassword =  (EditText) findViewById(R.id.etsPassword);
        btn = (Button) findViewById(R.id.btn);
        btn.setOnClickListener(this);

        //Check signin
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null) {
            Intent intent = new Intent(MainActivity.this, ListActivity.class);
            startActivity(intent);
        }
        //Поменять фон etEmail на ПРОЗРАЧНЫЙ
        etEmail.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                etEmail.setHintTextColor(Color.WHITE);
            }
        });
        //Поменять фон etPassword на ПРОЗРАЧНЫЙ
        etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                etPassword.setHintTextColor(Color.WHITE);
            }
        });

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn) {

            //Проверка вводимых данных
            if (!etEmail.getText().toString().equals("") && !etPassword.getText().toString().equals("")) {
                Toast.makeText(MainActivity.this, "Попытка авторизации", Toast.LENGTH_SHORT).show();
                signin(etEmail.getText().toString(), etPassword.getText().toString());
            }
            else if (etEmail.getText().toString().equals("")){
                etEmail.setHintTextColor(getResources().getColor(R.color.colorAccent));
            }
            else if (etPassword.getText().toString().equals("")){
                etPassword.setHintTextColor(getResources().getColor(R.color.colorAccent));
            }

        }
    }

    //Метод авторизации
    public void signin(String email, String password){

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            Intent intent = new Intent(MainActivity.this, ListActivity.class);
                            startActivity(intent);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(MainActivity.this, "Проверьте введенные данные",
                                    Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
}
