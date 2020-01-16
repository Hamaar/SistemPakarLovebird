package com.example.hilmi.sistempakar.view.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.example.hilmi.sistempakar.IndexUtama;
import com.example.hilmi.sistempakar.R;
import com.example.hilmi.sistempakar.adapters.GejalaItemAdapter;
import com.example.hilmi.sistempakar.database.SQLiteHelper;
import com.example.hilmi.sistempakar.models.Gejala;
import com.example.hilmi.sistempakar.models.GejalaListModels;
import com.example.hilmi.sistempakar.models.Keputusan;
import com.example.hilmi.sistempakar.models.Penyakit;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class HasilKonsultasi extends AppCompatActivity implements View.OnClickListener {

    private String[] results;
    private String[] resultRulebaseTdkAktiv;
    private HashMap<String, ArrayList<String>> chains = new HashMap<>();
    private TextView txtNamaPenyakit, txtListGejala, txtHasilSolusi;
    Button btnUlang, btnSelesai;

    private List<Gejala> listgejalas;
    private GejalaItemAdapter gejalaItemAdapter;
    final String TAG = "hasilKonsultasi.java";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_hasil_diagnosa);

        txtNamaPenyakit = (TextView)findViewById(R.id.tvResultNamaPenyakit);
        txtListGejala = (TextView)findViewById(R.id.tvResultNamaGejala);
        txtHasilSolusi = (TextView)findViewById(R.id.tvResultSolusi);
        btnUlang = (Button)findViewById(R.id.btnPeriksaUlang);
        btnSelesai = (Button)findViewById(R.id.btnSelesai);

        listgejalas = SQLiteHelper.getInstance(this).getAllGejala();
        gejalaItemAdapter = new GejalaItemAdapter(listgejalas, this);


        btnUlang.setOnClickListener(this);
        btnSelesai.setOnClickListener(this);
        getBundle();
        chainProcess();
        setupData();
    }


    private void getBundle(){
        if (getIntent().hasExtra("selectedItems")){
            Bundle b = getIntent().getExtras();
            results = b.getStringArray("selectedItems");
        }
        else if (getIntent().hasExtra("RulebaseTidakAktiv")){
            Bundle b = getIntent().getExtras();
            resultRulebaseTdkAktiv = b.getStringArray("RulebaseTidakAktiv");
        }

    }

    private void chainProcess(){
        for(String code : results){
            for(Keputusan keputusan : SQLiteHelper.getInstance(this).getAllKeputusan()){
                if(keputusan.getKode_gejala().contains(code + ",")){
                    if(chains.containsKey(keputusan.getPid())){
                        chains.get(keputusan.getPid()).add(code);
                    } else {
                        ArrayList<String> str = new ArrayList<>();
                        str.add(keputusan.getKode_gejala().split(",").length + "");
                        str.add(code);
                        chains.put(keputusan.getPid(), str);
                    }
                }
            }
        }
    }

    private void setupData(){
        Set<String> keySet = chains.keySet();
        float top = -1;
         String keyset = "";
        for (String key : keySet) {
            float ms = Float.parseFloat(chains.get(key).get(0));
            float ma = chains.get(key).size() - 1;
            float current = ma / ms;
            if (current >= top) {
                top = current;
                keyset = key;
                Log.d(TAG, "setupData: " + keyset);
                System.out.println(keyset);
            }
        }
        if(keyset==""){
            keyset="P09";
        }
        else if (keyset==null){
            keyset="P09";
        }
//        else if (keySet == null){
//            keyset="P09";
//        }

        Penyakit penyakit = SQLiteHelper.getInstance(this).getPenyakit(keyset);
//        Gejala gejala = SQLiteHelper.getInstance(this).getGejala(keyset);

        if (getIntent().hasExtra("selectedItemsName")){
            Bundle b = getIntent().getExtras();
            String[] gjl_name = b.getStringArray("selectedItemsName");
            if(gjl_name.length>0) {
                int i=1;
                StringBuilder aa = new StringBuilder();
                for (String a : gjl_name) {
                    aa.append(i).append(". ").append(a).append("\n");
                    i++;
                }
                txtListGejala.setText(aa);
            }
        }
        //hasil rulebase
        txtNamaPenyakit.setText(penyakit.getNama_penyakit());
        txtHasilSolusi.setText(penyakit.getCara());

        System.out.println("Hasil Konsultasi" + penyakit.getNama_penyakit());
        System.out.println("Hasil Konsultasi" + penyakit.getCara());
//        Log.d(TAG, "setupData: " + gejala);

        if (keyset=="") {
            keyset="P09";
            System.out.println("Hasil" + penyakit.getNama_penyakit());
            System.out.println("Hasil" + penyakit.getCara());
        }


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnPeriksaUlang:
                Intent iUlang = new Intent(getApplicationContext(), Konsultasi.class);
                startActivity(iUlang);
                break;
            case R.id.btnSelesai:
                Intent iSelesai = new Intent(getApplicationContext(), IndexUtama.class);
                startActivity(iSelesai);
                break;
            default:

        }

    }
}

