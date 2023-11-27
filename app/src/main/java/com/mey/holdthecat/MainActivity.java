package com.mey.holdthecat;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView scoreTxt,timeTxt;
    private int score;
    private ImageView imageView,imageView2,imageView3,imageView4,
            imageView5,imageView6,imageView7,imageView8,imageView9;
    private ImageView[] imageArray;
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreTxt=findViewById(R.id.scoreTxt);
        timeTxt=findViewById(R.id.timeTxt);
        imageView=findViewById(R.id.imageView);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        imageView4=findViewById(R.id.imageView4);
        imageView5=findViewById(R.id.imageView5);
        imageView6=findViewById(R.id.imageView6);
        imageView7=findViewById(R.id.imageView7);
        imageView8=findViewById(R.id.imageView8);
        imageView9=findViewById(R.id.imageView9);

        imageArray=new ImageView[]{imageView,imageView2,imageView3,imageView4,imageView5,imageView6,imageView7,imageView8,imageView9};


        score=0;

        hideImage();

        new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeTxt.setText("Time:"+millisUntilFinished/1000);
            }

            @Override
            public void onFinish() {
                timeTxt.setText("Time is over!!");
                handler.removeCallbacks(runnable);
                for (ImageView image:imageArray
                ) {
                    image.setVisibility(View.INVISIBLE);
                }

                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity.this);

                alert.setTitle("Restart?");
                alert.setMessage("are u sure to restart game?");
                alert.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent=getIntent();
                        finish();
                        startActivity(intent);
                    }
                });

                alert.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"oyun bitti",Toast.LENGTH_LONG).show();
                    }
                });
                alert.show();

            }
        }.start();
    }
    public void increaseScore (View view){
        score++;
        scoreTxt.setText("Score:"+score);

    }

    public void hideImage(){

        handler=new Handler();
        runnable=new Runnable() {
            @Override
            public void run() {
                for (ImageView image:imageArray
            ) {
                image.setVisibility(View.INVISIBLE);
            }

                Random random=new Random();
                int i=random.nextInt(9);
                imageArray[i].setVisibility(View.VISIBLE);

                handler.postDelayed(runnable,800);

            }
        };

        handler.post(runnable);


    }
}