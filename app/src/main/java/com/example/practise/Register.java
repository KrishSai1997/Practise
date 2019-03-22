package com.example.practise;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class Register extends AppCompatActivity {

    private EditText userName, userEmail, userPassword;
    private Button regButton;
    private TextView userLogin;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        setupUIViews();

        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog =new ProgressDialog((this));

        mDatabase = FirebaseDatabase.getInstance().getReference().child("Users");

        regButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(validate()){
                    //Upload data to Database
                    final String user_email = userEmail.getText().toString().trim();
                    String user_password = userPassword.getText().toString().trim();
                    final String user_name = userName.getText().toString().trim();

                    firebaseAuth.createUserWithEmailAndPassword(user_email,user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){
                                String user_id = firebaseAuth.getCurrentUser().getUid();
                                DatabaseReference current_user_db = mDatabase.child(user_id);
                                current_user_db.child("Name").setValue(user_name);
                                current_user_db.child("E-Mail").setValue(user_email);


                                Toast.makeText(Register.this,"Registration Successfull",Toast.LENGTH_SHORT).show();

                                //Last Edit From Here
                                //String user_id = ;

                                startActivity(new Intent(Register.this, MainActivity.class));
                            }else{

                                if(task.getException() instanceof FirebaseAuthUserCollisionException){
                                    Toast.makeText(Register.this,"Email Is Already Registered",Toast.LENGTH_LONG).show();
                                }
                                Toast.makeText(Register.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                            }

                        }
                    });
                }

            }
        });

      userLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Register.this, MainActivity.class));
            }
        });
    }

    private void setupUIViews(){
        userName = (EditText)findViewById(R.id.etUserName);
        userEmail = (EditText)findViewById(R.id.etUserEmail);
        userPassword = (EditText)findViewById(R.id.etUserPassword);
        regButton = (Button)findViewById(R.id.btnRegister);
        userLogin = (TextView) findViewById(R.id.tvLogin);
    }

    private Boolean validate(){
        Boolean result = false;

        String name = userName.getText().toString();
        String email = userEmail.getText().toString();
        String password = userPassword.getText().toString();
        /*progressDialog.setMessage("Loading... Please Wait");
        progressDialog.show();*/

        if ( name.isEmpty() || email.isEmpty() || password.isEmpty() ){
            Toast.makeText(this, "Please Enter All The Details", Toast.LENGTH_SHORT).show();
        }
        else{
            result = true;
        }

        return result;
    }

}
