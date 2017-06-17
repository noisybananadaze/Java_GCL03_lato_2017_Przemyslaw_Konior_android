package com.example.lenovo.bazydanych;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {

    DataBase dataBase = new DataBase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);


    }

    public void onButSignupClick(View view) {

        EditText name = (EditText)findViewById(R.id.editTextName);
        EditText mail = (EditText)findViewById(R.id.editTextEmail);
        EditText pass1 = (EditText)findViewById(R.id.editTextPassword);
        EditText pass2 = (EditText)findViewById(R.id.editTextConfirmPassword);

        String nameStr = name.getText().toString();
        String mailStr = mail.getText().toString();
        String pass1Str = pass1.getText().toString();
        String pass2Str = pass2.getText().toString();

        if(!pass1Str.equals(pass2Str)){

            Toast wrongPass = Toast.makeText(SignupActivity.this, "Password do not match!", Toast.LENGTH_SHORT);
            wrongPass.show();
        }else{

            Contact contact = new Contact(nameStr,mailStr,pass1Str);

            dataBase.insertContact(contact);

            Toast newToast = Toast.makeText(SignupActivity.this, "You have created new account!!", Toast.LENGTH_SHORT);
            newToast.show();

            Intent i = new Intent(SignupActivity.this, Login.class );

            startActivity(i);

        }

    }
}
