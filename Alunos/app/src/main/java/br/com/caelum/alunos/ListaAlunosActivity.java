package br.com.caelum.alunos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

/**
 * Created by android5243 on 05/09/15.
 */
public class ListaAlunosActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista);

        List<String> alunos = Arrays.asList("Maria Clara", "Juliana Alvez", "Tião Macalé");
        ArrayAdapter alunosAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, alunos);

        ListView listView = (ListView) findViewById(R.id.lista);

        View botaoAdiciona = findViewById(R.id.lista_alunos_floating_button);

        listView.setAdapter(alunosAdapter);

        botaoAdiciona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(intent);
                //Toast.makeText(ListaAlunosActivity.this, "Ir para formulario...", Toast.LENGTH_LONG ).show();
            }
        });


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String aluno = (String) parent.getItemAtPosition(position);
                Log.i("Selecionado: ", aluno);

                Toast.makeText(ListaAlunosActivity.this, "Aluno: " + aluno, Toast.LENGTH_LONG ).show();

            }
        });



        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String aluno = (String) parent.getItemAtPosition(position);
                Log.i("Selecionado: ", aluno);

                Toast.makeText(ListaAlunosActivity.this, "Aluno: " + aluno + " pronto para ser editado !", Toast.LENGTH_LONG).show();
                return true;
            }
        });


    }
}
