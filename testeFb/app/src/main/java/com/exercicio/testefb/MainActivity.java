package com.exercicio.testefb;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import Modelo.Cliente;

public class MainActivity extends AppCompatActivity {
    private Button btnAdicionar, btnConsultar;
    private EditText edtNome, edtId, edtCidade;
    private ListView lstView;

    // Objeto que receberá a lista de clientes
    private List<Cliente> clienteList = new ArrayList();

    // Objeto responsável por mapear os clientes recebidos no Firebase
    private ArrayAdapter<Cliente> clienteArrayAdapter;

    // Objeto responsável por pegar a instãncia do Firebase
    final private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();

    // Objeto que irá fazer a referência ao da base de dados
    private DatabaseReference databaseReference = firebaseDatabase.getReference("server");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iniciarFirebase();
        carregaWidgets();
        botoes();
    }

    private void pesquisa(String nome) {
        Query query; // Objeto que receberá a consulta
        clienteList.clear(); // limpa o listview

        // Pesquisa através da chave cliente ordenando pela nome da cliente
        query = databaseReference.child("cliente").orderByChild("nome");

        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot objdataSnapshot:dataSnapshot.getChildren()) {
                    Cliente cliente = objdataSnapshot.getValue(Cliente.class);
                    clienteList.add(cliente);

                    clienteArrayAdapter = new ArrayAdapter<>(MainActivity.this,
                            android.R.layout.simple_list_item_1, clienteList);

                    lstView.setAdapter(clienteArrayAdapter);
//                    if (clienteArrayAdapter.getCount() > 0) {
//                        Toast.makeText(getApplicationContext(), clienteList.get(1).getNome().toString(),
//                                Toast.LENGTH_LONG).show();
//                    } else {
//                        Toast.makeText(getApplicationContext(), "Sem dados", Toast.LENGTH_LONG).show();
//                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void botoes() {
        btnAdicionar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cliente cliente = new Cliente();

                // Seta o conteúdo digitado nos objetos
                cliente.setId(edtId.getText().toString());
                cliente.setNome(edtNome.getText().toString());
                cliente.setCidade(edtCidade.getText().toString());

                // Faz a referencia criando uma linha com a chave cliente
                DatabaseReference clienteRef = databaseReference.child("cliente")
                        .child(cliente.getNome().toString());

                // Mapeia o objeto cliente para o objeto que será gravado no Firebase
                Map<String, Cliente> mClientes = new HashMap<>();

                // Grava a linha no banco de dados e atualiza no servidor
                if (edtId.getText().length() != 0 || edtNome.getText().length() != 0
                        || edtCidade.getText().length() != 0) {
                    try {
                        clienteRef.setValue(cliente);
                    } catch (SecurityException e) {
                        Toast.makeText(getApplicationContext(),
                                "Error: " + e, Toast.LENGTH_SHORT).show();
                    } finally {
                        Toast.makeText(getApplicationContext(),
                                "Inserido com sucesso! ", Toast.LENGTH_SHORT).show();

                        edtId.setText(null);
                        edtNome.setText(null);
                        edtCidade.setText(null);
                    }
                } else {
                    Toast.makeText(getApplicationContext(),
                            "Preencha todos os campos !", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnConsultar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pesquisa("");
            }
        });
    }

    private void carregaWidgets() {
        edtId = findViewById(R.id.edtId);
        edtNome = findViewById(R.id.edtNome);
        edtCidade = findViewById(R.id.edtCidade);
        btnAdicionar = findViewById(R.id.btnInserir);
        btnConsultar = findViewById(R.id.btnConsultar);
        lstView = findViewById(R.id.lstView);
    }

    private void iniciarFirebase() {
        FirebaseApp.initializeApp(MainActivity.this);
    }
}
