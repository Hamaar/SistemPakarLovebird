package com.example.hilmi.sistempakar.adminpanel;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hilmi.sistempakar.IndexUtama;
import com.example.hilmi.sistempakar.R;
import com.example.hilmi.sistempakar.baseModels.Pager;
import com.example.hilmi.sistempakar.database.SQLiteHelper;

import java.util.List;

import io.github.yavski.fabspeeddial.FabSpeedDial;
import io.github.yavski.fabspeeddial.SimpleMenuListenerAdapter;

public class ScreenAdmin extends AppCompatActivity {

    public String TAG ="ScreenAdmin";
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private Pager pagerAdapter;


    ListView listView;
    public SQLiteHelper sqLiteHelper;


    android.app.AlertDialog.Builder dialog;
    LayoutInflater inflater;
    View dialogView;

    FloatingActionButton fab, fabGejala;
    FabSpeedDial fabSpeedDial;

    final String key = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_admin);
        setTitle("Halaman Admin");

        listView = (ListView) findViewById(R.id.listView);
        sqLiteHelper = sqLiteHelper.getInstance(this);



        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        TextView txtusername = (TextView)findViewById(R.id.adminUsername);
        TextView txtfullname = (TextView)findViewById(R.id.adminFullname);
        fabSpeedDial = (FabSpeedDial) findViewById(R.id.fabAdd);



//get login
        Bundle extras = getIntent().getExtras();
        String username = null;
        String fullname = null;
        if(extras != null){
            username = extras.getString("fullname");
            fullname = extras.getString("fullname");

            txtusername.setText("Welcome " + username);
            txtfullname.setText(" " + fullname);

        }





        pagerAdapter = new Pager(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        final TabLayout.Tab tbGejala = tabLayout.newTab();
        final TabLayout.Tab tbPenyakit = tabLayout.newTab();
        final TabLayout.Tab tbSolusi = tabLayout.newTab();
        final TabLayout.Tab tbKeputusan = tabLayout.newTab();
        final TabLayout.Tab tbUsers = tabLayout.newTab();


        tbGejala.setText("Gejala");
        tbPenyakit.setText("Penyakit");
        tbSolusi.setText("Solusi");
        tbKeputusan.setText("RuleBase");
        tbUsers.setText("Users");


        tabLayout.addTab(tbGejala, 0);
        tabLayout.addTab(tbPenyakit, 1);
        tabLayout.addTab(tbSolusi, 2);
        tabLayout.addTab(tbKeputusan, 3);
        tabLayout.addTab(tbUsers, 4);


        tabLayout.setTabTextColors(ContextCompat.getColorStateList(this, R.color.white));
        tabLayout.setSelectedTabIndicatorColor(ContextCompat.getColor(this, R.color.red));




        final List<String> listGejala = sqLiteHelper.getTableGejala();




//set long click
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final String selection = listGejala.get(arg2);
                final CharSequence[] dialogitem = {"Lihat", "Update", "Hapus"};

                android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(ScreenAdmin.this);
                builder.setTitle("Pilihan");
                builder.setItems(dialogitem, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int item) {
                        switch (item) {
                            case 0:
                                Intent i = new Intent(getApplicationContext(), ScreenAdminDetil.class);
                                i.putExtra("kode_gejala", selection);
                                startActivity(i);
                                break;
                            case 1:
                                Intent in_update = new Intent(getApplicationContext(), ScreenUpdate.class);
                                in_update.putExtra("nama_gejala", selection);
                                startActivity(in_update);
                                break;
                            case 2:
                                SQLiteDatabase db = sqLiteHelper.getWritableDatabase();
                                db.execSQL("delete from tbl_gejala WHERE nama_gejala = '" + selection + "'");
                                Toast.makeText(getApplicationContext(), "Berhasil dihapus", Toast.LENGTH_SHORT).show();

                                Log.d(TAG, "onClick: " + db);
                                break;

                        }
                    }
                });
                builder.create().show();
            }
        });




        fabSpeedDial.setMenuListener(new SimpleMenuListenerAdapter() {
            @Override
            public boolean onMenuItemSelected(MenuItem menuItem) {
                if (menuItem.getItemId() == R.id.fab_gejala){
                    Intent i = new Intent(ScreenAdmin.this, ScreenTambahData.class);
                    i.putExtra("fab_add_gejala", key);
                    startActivity(i);
                }
                else if (menuItem.getItemId() == R.id.fab_penyakit){
                    Intent i = new Intent(ScreenAdmin.this, ScreenTambahData.class);
                    i.putExtra("fab_add_penyakit", key);
                    startActivity(i);
                }
                else if (menuItem.getItemId() == R.id.fab_solusi){
                    Intent i = new Intent(ScreenAdmin.this, ScreenTambahData.class);
                    i.putExtra("fab_add_solusi", key);
                    startActivity(i);
                }
                else if (menuItem.getItemId() == R.id.fab_kode_penyakit_solusi){
                    Intent i = new Intent(ScreenAdmin.this, ScreenTambahData.class);
                    i.putExtra("fab_add_penyakit_solusi", key);
                    startActivity(i);
                }
                else if (menuItem.getItemId() == R.id.fab_rulebase){
                    Intent i = new Intent(ScreenAdmin.this, ScreenTambahData.class);
                    i.putExtra("fab_add_rulebase", key);
                    startActivity(i);
                }
                //what do I do here?
                return false;
            }
        });




        //event clik
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
            @Override
            public void onPageSelected(int position) {
                switch (position) {
                    case 0: {
                        tbGejala.setText("Gejala");
                        break;
                    }
                    case 1: {
                        tbPenyakit.setText("Penyakit");
                        break;
                    }
                    case 2: {
                        tbSolusi.setText("Solusi");
                        break;
                    }
                    case 3: {
                        tbKeputusan.setText("RuleBase");
                        break;
                    }
                    case 4: {
                        tbUsers.setText("Users");
                        break;
                    }
                }

            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }


    //OPTIONS MENU
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()){

            case R.id.item_logout:{
                AlertDialog.Builder builder = new AlertDialog.Builder(this)
                        .setMessage("Yakin Anda ingin keluar?")
                        .setCancelable(false)
                        .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent i = new Intent(getApplicationContext(), IndexUtama.class);
                                startActivity(i);
                            }
                        });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }



    public void onBackPressed() {
        //super.onBackPressed();
    }
}

