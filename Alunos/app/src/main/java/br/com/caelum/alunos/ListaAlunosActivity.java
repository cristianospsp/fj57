package br.com.caelum.alunos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import br.com.caelum.alunos.br.com.caelum.alunos.dao.AlunoDAO;
import br.com.caelum.alunos.br.com.caelum.alunos.model.Aluno;

/**
 * Created by android5243 on 05/09/15.
 */
public class ListaAlunosActivity extends Activity {

    private List<Aluno> alunos;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lista);

        //List<String> alunos = Arrays.asList("Maria Clara", "Juliana Alvez", "Tião Macalé");

        listView = (ListView) findViewById(R.id.lista);

        View botaoAdiciona = findViewById(R.id.lista_alunos_floating_button);

        //carregaLista();

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
                Aluno aluno = (Aluno) parent.getItemAtPosition(position);

                Toast.makeText(ListaAlunosActivity.this, "Aluno: " + aluno, Toast.LENGTH_LONG ).show();

            }
        });



        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Aluno aluno = (Aluno) parent.getItemAtPosition(position);

                Toast.makeText(ListaAlunosActivity.this, "Aluno: " + aluno + " pronto para ser editado !", Toast.LENGTH_LONG).show();
                return true;
            }
        });

    }

    @Override
    public void onResume() {
        super.onResume();
        carregaLista();
    }

    private void carregaLista() {
        AlunoDAO alunoDAO = new AlunoDAO(this);
        alunos = alunoDAO.getList();
        alunoDAO.close();

        ArrayAdapter<Aluno> alunosAdapter = new ArrayAdapter<Aluno>(this, android.R.layout.simple_list_item_1, alunos);

        this.listView.setAdapter(alunosAdapter);
    }

}
