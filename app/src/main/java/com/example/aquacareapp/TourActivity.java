package com.example.aquacareapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.VideoView;

public class TourActivity extends AppCompatActivity {

    private Button btLogarTour;
    private Button btCadastrarTour;

    VideoView videoView2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tour);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getSupportActionBar().hide();

        videoView2 = findViewById(R.id.videoView2);

        Uri video = Uri.parse("android.resource://"  + getPackageName() + "/" +R.raw.backapp);

        videoView2.setVideoURI(video);

        btLogarTour = findViewById(R.id.btLogarTour);
        btCadastrarTour = findViewById(R.id.btCadastrarTour);

        videoView2.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.setLooping(true);
            }
        });

        videoView2.start();

        btLogarTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TelaLogin();
            }
        });

        btCadastrarTour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TelaCadastro();
            }
        });

    }

    private void TelaCadastro() {

        startActivity(new Intent(this, CadastroActivity.class));
    }

    private void TelaLogin() {

        startActivity(new Intent(this, LoginActivity.class));
    }



    //Fernanda Costa salvou esse TCC
    //Eu amo ela pra caralho e to gostando muito dela, apenas
    //Mlr pessoa que conheci esse ano e espero q ela continue na minha vida pra sempre
    //Minha motivação pra tudo, inclusive pra fazer esse caraio de projeto funcionar
    //Obrigado Deus por colocar ela na minha vida!!!!
}
