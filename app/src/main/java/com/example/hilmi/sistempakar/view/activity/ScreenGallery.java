package com.example.hilmi.sistempakar.view.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hilmi.sistempakar.R;

public class ScreenGallery extends AppCompatActivity  implements View.OnClickListener {

    private static final int time = 3200;
    int [] imgGallery ={
            R.drawable.gallery_1_ganguan_pernafasan,
            R.drawable.gallery_2_penyakit_berak_kapur,
            R.drawable.gallery_3_penyakit_eggbinding,
            R.drawable.gallery_4_penyakit_kutu,
            R.drawable.gallery_5_penyakit_nyilet,
            R.drawable.gallery_6_penyakit_paruhgajah,
            R.drawable.gallery_7_snot,
    };

    String [] strPenyakit = {
            "Penyakit Gangguan Pernafasan",
            "Penyakit Berak Kapur",
            "Penyakit EggBinding",
            "Penyakit Kutu",
            "Penyakit Nyilet",
            "Penyakit Paruh Gajah",
            "Penyakit Snot"
    };

    ImageView ivImagePenyakit;
    TextView txtShowPenyakit;
    ImageView btnImageGallery, btnImageGallery2, btnImageGallery3, btnImageGallery4,
            btnImageGallery5, btnImageGallery6, btnImageGallery7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_screen_gallery);

        setTitle("Gallery LoveBird");


        ivImagePenyakit = (ImageView)findViewById(R.id.iv_gallery);
        txtShowPenyakit = (TextView)findViewById(R.id.txtGalery);
        btnImageGallery = (ImageView) findViewById(R.id.iv_galery_btn);
        btnImageGallery2 = (ImageView) findViewById(R.id.iv_galery_btn2);
        btnImageGallery3 = (ImageView) findViewById(R.id.iv_galery_btn3);
        btnImageGallery4 = (ImageView) findViewById(R.id.iv_galery_btn4);
        btnImageGallery5 = (ImageView) findViewById(R.id.iv_galery_btn5);
        btnImageGallery6 = (ImageView) findViewById(R.id.iv_galery_btn6);
        btnImageGallery7 = (ImageView) findViewById(R.id.iv_galery_btn7);


        ivImagePenyakit.setImageResource(imgGallery[0]);
        btnImageGallery.setImageResource(imgGallery[0]);
        btnImageGallery2.setImageResource(imgGallery[1]);
        btnImageGallery3.setImageResource(imgGallery[2]);
        btnImageGallery4.setImageResource(imgGallery[3]);
        btnImageGallery5.setImageResource(imgGallery[4]);
        btnImageGallery6.setImageResource(imgGallery[5]);
        btnImageGallery7.setImageResource(imgGallery[6]);







        btnImageGallery .setOnClickListener(this);
        btnImageGallery2 .setOnClickListener(this);
        btnImageGallery3 .setOnClickListener(this);
        btnImageGallery4 .setOnClickListener(this);
        btnImageGallery5 .setOnClickListener(this);
        btnImageGallery6 .setOnClickListener(this);
        btnImageGallery7 .setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.iv_galery_btn: {
                final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.anim_img);
                final Animation myAnim2= AnimationUtils.loadAnimation(this, R.anim.anim_img);
                myAnim.setDuration(2000);
                myAnim2.setDuration(2000);

                txtShowPenyakit.startAnimation(myAnim2);
                ivImagePenyakit.startAnimation(myAnim);
                ivImagePenyakit.setImageResource(imgGallery[0]);
                txtShowPenyakit.setText(strPenyakit[0]);
                break;
            }
            case R.id.iv_galery_btn2: {
                final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.anim_img);
                final Animation myAnim2= AnimationUtils.loadAnimation(this, R.anim.anim_img);
                myAnim.setDuration(2000);
                myAnim2.setDuration(2000);
                txtShowPenyakit.startAnimation(myAnim2);
                ivImagePenyakit.startAnimation(myAnim);

                ivImagePenyakit.setImageResource(imgGallery[1]);
                txtShowPenyakit.setText(strPenyakit[1]);
                break;
            }
            case R.id.iv_galery_btn3:{
                final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.anim_img);
                final Animation myAnim2= AnimationUtils.loadAnimation(this, R.anim.anim_img);
                myAnim.setDuration(2000);
                myAnim2.setDuration(2000);
                txtShowPenyakit.startAnimation(myAnim2);
                ivImagePenyakit.startAnimation(myAnim);

                ivImagePenyakit.setImageResource(imgGallery[2]);
                txtShowPenyakit.setText(strPenyakit[2]);
                break;
            }
            case R.id.iv_galery_btn4:{
                final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.anim_img);
                final Animation myAnim2= AnimationUtils.loadAnimation(this, R.anim.anim_img);
                myAnim.setDuration(2000);
                myAnim2.setDuration(2000);


                txtShowPenyakit.startAnimation(myAnim2);
                ivImagePenyakit.startAnimation(myAnim);

                ivImagePenyakit.setImageResource(imgGallery[3]);
                txtShowPenyakit.setText(strPenyakit[3]);
                break;
            }
            case R.id.iv_galery_btn5:{
                final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.anim_img);
                final Animation myAnim2= AnimationUtils.loadAnimation(this, R.anim.anim_img);
                myAnim.setDuration(2000);
                myAnim2.setDuration(2000);
                txtShowPenyakit.startAnimation(myAnim2);
                ivImagePenyakit.startAnimation(myAnim);

                ivImagePenyakit.setImageResource(imgGallery[4]);
                txtShowPenyakit.setText(strPenyakit[4]);
                break;
            }
            case R.id.iv_galery_btn6:{
                final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.anim_img);
                final Animation myAnim2= AnimationUtils.loadAnimation(this, R.anim.anim_img);
                myAnim.setDuration(2000);
                myAnim2.setDuration(2000);
                txtShowPenyakit.startAnimation(myAnim2);
                ivImagePenyakit.startAnimation(myAnim);

                ivImagePenyakit.setImageResource(imgGallery[5]);
                txtShowPenyakit.setText(strPenyakit[5]);
                break;
            }
            case R.id.iv_galery_btn7 :{
                final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.anim_img);
                final Animation myAnim2= AnimationUtils.loadAnimation(this, R.anim.anim_img);
                myAnim.setDuration(2000);
                myAnim2.setDuration(2000);
                txtShowPenyakit.startAnimation(myAnim2);
                ivImagePenyakit.startAnimation(myAnim);

                ivImagePenyakit.setImageResource(imgGallery[6]);
                txtShowPenyakit.setText(strPenyakit[6]);
                break;
            }
        }
    }
}
