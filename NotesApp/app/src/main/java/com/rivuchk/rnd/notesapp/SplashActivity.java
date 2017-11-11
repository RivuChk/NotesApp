package com.rivuchk.rnd.notesapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.rivuchk.rnd.notesapp.utils.Utils;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Observable.timer(3, TimeUnit.SECONDS)
                .compose(new Utils.BackgroundLoader<>())
                .subscribe((time)->{
                    Intent intent = new Intent(SplashActivity.this,NotesActivity.class);
                    startActivity(intent);
                    finish();
                });
    }
}
