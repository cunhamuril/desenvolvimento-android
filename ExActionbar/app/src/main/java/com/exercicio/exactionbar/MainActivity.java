package com.exercicio.exactionbar;

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
            case R.id.adicionar:
                Toast.makeText(getApplicationContext(), "Adicionar", Toast.LENGTH_SHORT).show();
                return (true);
            case R.id.telaCalc:
                Intent tela = new Intent(MainActivity.this, CalculadoraActivity.class);
                startActivity(tela);
                Vibrator vib = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
                vib.vibrate(5000);
                return (true);
            case R.id.opa:
                Toast.makeText(getApplicationContext(), "0P4", Toast.LENGTH_LONG).show();
                return (true);
        }

        return (super.onOptionsItemSelected(item));
    }
}
