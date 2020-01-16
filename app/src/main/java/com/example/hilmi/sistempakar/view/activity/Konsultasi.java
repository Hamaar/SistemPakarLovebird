package com.example.hilmi.sistempakar.view.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.os.Parcelable;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hilmi.sistempakar.FlashScreen;
import com.example.hilmi.sistempakar.IndexUtama;
import com.example.hilmi.sistempakar.R;
import com.example.hilmi.sistempakar.adapters.GejalaItemAdapter;
import com.example.hilmi.sistempakar.adminpanel.ScreenUpdate;
import com.example.hilmi.sistempakar.database.SQLiteHelper;
import com.example.hilmi.sistempakar.models.Gejala;
import com.example.hilmi.sistempakar.models.GejalaListModels;

import java.util.ArrayList;
import java.util.List;

public class Konsultasi extends AppCompatActivity {

    private ListView listview;
    private GejalaItemAdapter gejalaItemAdapter;
    private Button btnDiagnosa;
    private Toolbar toolbar;
    private List<Gejala> listgejalas;
    private EditText txtSearch;


    TextInputEditText edtNamaPasien;


    //custom dialog
    AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;

    //loading
    private int loading = 3000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_diagnosa);

        listview = (ListView)findViewById(R.id.listGejala);
        btnDiagnosa = (Button)findViewById(R.id.btnDiagnosa);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        txtSearch = (EditText)findViewById(R.id.txtSearch);
        setupData();
        dialogFormUser();

    }


    public void dialogFormUser(){
        dialog = new AlertDialog.Builder(Konsultasi.this);
        inflater = getLayoutInflater();
        dialogView = inflater.inflate(R.layout.form_alert_dialog_konsultasi, null);
        dialog.setView(dialogView);
        dialog.setCancelable(true);
        dialog.setTitle("Form Pasien");

        edtNamaPasien = (TextInputEditText)findViewById(R.id.edtPasien);

        dialog.setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                final String strNamePasien = edtNamaPasien.getText().toString();


            }
        });

        dialog.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void setupData(){
        listgejalas = SQLiteHelper.getInstance(this).getAllGejala();
        gejalaItemAdapter = new GejalaItemAdapter(listgejalas, this);
        listview.setAdapter(gejalaItemAdapter);
        listview.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        if (listgejalas == null){
            Intent intent = new Intent(Konsultasi.this, HasilKonsultasi.class);
                    intent.putExtra("RULE TIDAK AKTIV", (Parcelable) listgejalas);
                    startActivity(intent);
        }


        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                            @Override
                                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                                SparseBooleanArray checked = listview.getCheckedItemPositions();

                                                final String[] outArr = new String[listgejalas.size()];
                                                for (int j = 0; j < listgejalas.size(); j++) {
                                                    outArr[j] = listgejalas.get(j).getGid();
//                                                    Toast.makeText(getApplicationContext(), "You selected: " + listgejalas , Toast.LENGTH_LONG).show();

                                                }
                                            }

        });

        btnDiagnosa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray checked = listview.getCheckedItemPositions();

                //loading


                if(checked.size()==listgejalas.size()){
                    Toast.makeText(getApplicationContext(), "Pililh gejala yang Sesuai", Toast.LENGTH_LONG).show();
                    return;
                }
                if(checked.size() <= listgejalas.size()) {
                   final ArrayList<Gejala> selectedItems = new ArrayList<Gejala>();
//                   StringBuilder aa = new StringBuilder();
                    for (int i = 0; i < checked.size(); i++) {
                        int pos = checked.keyAt(i);
                        if (checked.valueAt(i)) {
                            selectedItems.add((Gejala) gejalaItemAdapter.getItem(pos));
//                            aa.append(((Gejala) gejalaItemAdapter.getItem(pos)).getGejala()).append("\n");
                            }
                    }

//                    Toast.makeText(getApplicationContext(), "You selected: " + aa, Toast.LENGTH_LONG).show();

                    final String[] outArr = new String[selectedItems.size()];
                    final String[] outArrName = new String[selectedItems.size()];
                    for (int j = 0; j < selectedItems.size(); j++) {
                        outArr[j] = selectedItems.get(j).getGid();
                        outArrName[j] = selectedItems.get(j).getGejala();
                    }


                    final Intent i = new Intent(Konsultasi.this, HasilKonsultasi.class);
                    final Bundle b = new Bundle();

                    b.putStringArray("selectedItems", outArr);
                    b.putStringArray("selectedItemsName", outArrName);
                    i.putExtras(b);
                    startActivity(i);




                }
//                else if(checked == null){
//                    Intent intent = new Intent(Konsultasi.this, HasilKonsultasi.class);
//                    intent.putExtra("RULE TIDAK AKTIV", (Parcelable) listgejalas);
//                    startActivity(intent);
//                }
            }
        });

        txtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                gejalaItemAdapter.filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

}




