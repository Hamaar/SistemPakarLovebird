package com.example.hilmi.sistempakar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.hilmi.sistempakar.adminpanel.ScreenAdmin;
import com.example.hilmi.sistempakar.database.SQLiteHelper;
import com.example.hilmi.sistempakar.database.SessionHelper;
import com.example.hilmi.sistempakar.models.Keputusan;
import com.example.hilmi.sistempakar.models.User;

public class ScreenLogin extends AppCompatActivity {

    TextInputEditText edtUser, edtPass;
    Button btnLogin;
    ProgressDialog pd;

    SQLiteHelper sqLiteHelper;
    String TAG = "LOGIN";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_login);

        setTitle("Halaman User Admin");
        pd = new ProgressDialog(this);
        pd.setMessage("Loading......");


        sqLiteHelper = SQLiteHelper.getInstance(this);


        btnLogin = (Button)findViewById(R.id.btnLogin);
        edtUser = (TextInputEditText)findViewById(R.id.edtUsername);
        edtPass = (TextInputEditText)findViewById(R.id.edtPassword);




        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                prosess();
            }
        });
    }
    public void prosess(){
        String keyUserId = edtUser.getText().toString();
        String keyPass = edtPass.getText().toString();

        boolean isExist = sqLiteHelper.checkUserExist(edtUser.getText().toString(), edtPass.getText().toString());
        if (isExist){
            Intent intent = new Intent(getApplicationContext(), ScreenAdmin.class);
            intent.putExtra("fullname", edtUser.getText().toString());
            startActivity(intent);
            Log.d(TAG, "prosess: " + isExist);
        }
        else if (keyUserId.isEmpty() & keyPass.isEmpty()) {
            Snackbar.make(findViewById(R.id.edtPassword), "Data Tidak boleh kosong !!!....", Snackbar.LENGTH_LONG).show();
        }
        else {
            Snackbar.make(findViewById(R.id.edtPassword), "Silahkan Cek Username dan Password....", Snackbar.LENGTH_LONG).show();
        }
    }}

