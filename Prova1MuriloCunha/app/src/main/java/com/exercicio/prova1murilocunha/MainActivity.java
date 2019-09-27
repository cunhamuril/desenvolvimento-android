package com.exercicio.prova1murilocunha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Vibrator;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.camera:
                Intent camera = new Intent(MainActivity.this, CameraActivity.class);
                startActivity(camera);
                return (true);
            case R.id.jokeypo:
                Intent exercicio2 = new Intent(MainActivity.this, JokeyPoActivity.class);
                startActivity(exercicio2);
                return (true);
        }

        return (super.onOptionsItemSelected(item));
    }

}
