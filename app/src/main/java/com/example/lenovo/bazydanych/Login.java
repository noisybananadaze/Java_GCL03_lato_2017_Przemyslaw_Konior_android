package com.example.lenovo.bazydanych;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    DataBase dataBase = new DataBase(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void onButLoginClick(View view) {

        Log.d("wtf", "dzialaj");

        EditText a = (EditText)findViewById(R.id.editUsername);
        String str = a.getText().toString();
        EditText b = (EditText)findViewById(R.id.editTextLogPass);
        String strPass = b.getText().toString();

        String password = dataBase.searchPass(str,strPass);

        Log.d("wtf", "dzialaj2");

        if(strPass.equals(password)) {


            Intent i = new Intent(Login.this, MainActivity.class);
            i.putExtra("Username", str);
            Log.d("wrong pass", "wrong pass");
            startActivity(i);
        }else{

            Toast passToast = Toast.makeText(Login.this, "Password is not correct!", Toast.LENGTH_SHORT);
            passToast.show();

        }

    }

    public void onButSignupClick(View view) {

        Intent i = new Intent(Login.this, SignupActivity.class );

        startActivity(i);


    }
}
