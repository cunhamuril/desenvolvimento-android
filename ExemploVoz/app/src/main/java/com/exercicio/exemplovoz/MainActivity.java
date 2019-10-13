package com.exercicio.exemplovoz;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    private EditText tMsg;
    private TextToSpeech textFala;
    private Button btFalar, btOuvirVoce;
    private ListView lstV;
    private Locale locale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textFala = new TextToSpeech(MainActivity.this, MainActivity.this);
        // no objeto acima que será tratado a voz

        tMsg = findViewById(R.id.edtText);
        botoes();
    }

    @Override
    public void onInit(int i) {
        locale = new Locale("pt", "BR");
        textFala.setLanguage(locale);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int i = item.getItemId();

        if (i == R.id.actionPortuguese) {
            locale = new Locale("pt", "BR");
            textFala.setLanguage(locale);
            btFalar.setText("falar em português");
        }
        if (i == R.id.actionEnglish) {
            textFala.setLanguage(Locale.ENGLISH);
            btFalar.setText("falar em inglês");
        }
        if (i == R.id.actionSpanish) {
            locale = new Locale("spa");
            textFala.setLanguage(locale);
            btFalar.setText("falar em espanhol");
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (resultCode == RESULT_OK) {
            ArrayList<String> palavras = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            lstV.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, palavras));
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    protected Intent getRecognizerIntent() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Fale aqui !");
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "pt-BR");
        intent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 10);

        return intent;
    }

    private void botoes() {
        btFalar = findViewById(R.id.btnFalar);
        btFalar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String texto = tMsg.getText().toString();
                textFala.speak(texto, TextToSpeech.QUEUE_FLUSH, Bundle.EMPTY, "1");
            }
        });

        btOuvirVoce = findViewById(R.id.btnOuvir);
        btOuvirVoce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                lstV = findViewById(R.id.listView);
                // verifica se há suporte para reconhecimento de voz
                PackageManager pm = getPackageManager();

                List<ResolveInfo> activities = pm.queryIntentActivities(
                        new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0
                );

                if (activities.size() != 0) {
                    Intent intent = getRecognizerIntent();
                    startActivityForResult(intent, 0);
                } else {
                    Toast.makeText(getApplicationContext(), "Sem reconhecimento de voz",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
