package com.exercicio.lista01;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.conversor:
                Intent converte = new Intent(MainActivity.this, ConverteActivity.class);
                startActivity(converte);
                return true;
            case R.id.question:
                Intent question = new Intent(MainActivity.this, QuestionActivity.class);
                startActivity(question);
                return true;
            case R.id.media:
                Intent media = new Intent(MainActivity.this, MediaActivity.class);
                startActivity(media);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
