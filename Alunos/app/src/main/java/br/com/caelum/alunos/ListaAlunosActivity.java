package br.com.caelum.alunos;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.MenuItem;
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
public class ListaAlunosActivity extends ActionBarActivity {

    private List<Aluno> alunos;
    private ListView listView;

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuItem ligar = menu.add("Ligar");
        MenuItem enviarSms = menu.add("Enviar SMS");
        MenuItem acharNoMapa = menu.add("Achar no Mapa");
        MenuItem navegarNoSite = menu.add("Navegar no Site");
        MenuItem deletar = menu.add("Deletar");

        Aluno aluno = (Aluno)listView.getItemAtPosition(((AdapterView.AdapterContextMenuInfo) menuInfo).position);

        Intent intentLigar = new Intent(Intent.ACTION_CALL);
        intentLigar.setData(Uri.parse("tel:"+aluno.getTelefone()));
        ligar.setIntent(intentLigar);

        Intent intentSms = new Intent(Intent.ACTION_VIEW);
        intentSms.setData(Uri.parse("sms:"+aluno.getTelefone()));
        enviarSms.setIntent(intentSms);

        Intent intentCharNoMapa = new Intent(Intent.ACTION_VIEW);
        intentCharNoMapa.setData(Uri.parse("geo:0,0:?q="+aluno.getEndereco()));
        acharNoMapa.setIntent(intentCharNoMapa);

        String site = aluno.getSite();
        if(!site.startsWith("http://")){
            site = "http://" + site;
        }

        Intent intentNavegarNoSite = new Intent(Intent.ACTION_VIEW);
        intentNavegarNoSite.setData(Uri.parse(site));
        navegarNoSite.setIntent(intentNavegarNoSite);


        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                new AlertDialog.Builder(ListaAlunosActivity.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Deletar")
                        .setMessage("Deseja mesmo deletar ?")
                        .setPositiveButton("Quero", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                AlunoDAO alunoDAO = new AlunoDAO(ListaAlunosActivity.this);
                                AdapterView.AdapterContextMenuInfo adapterContextMenuInfo = (AdapterView.AdapterContextMenuInfo)menuInfo;
                                Aluno aluno = (Aluno)listView.getItemAtPosition(((AdapterView.AdapterContextMenuInfo) menuInfo).position);
                                alunoDAO.delete(aluno);
                                alunoDAO.close();
                                carregaLista();
                            }
                        }).setNegativeButton("Não", null).show();


                return false;
            }
        });

    }

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
                Intent intent = new Intent(ListaAlunosActivity.this, FormularioActivity.class);

                intent.putExtra("alunoSelecionado", (Aluno) parent.getItemAtPosition(position));

                startActivity(intent);
            }
        });



        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
              //  Aluno aluno = (Aluno) parent.getItemAtPosition(position);

                //Toast.makeText(ListaAlunosActivity.this, "Aluno: " + aluno + " pronto para ser editado !", Toast.LENGTH_LONG).show();
                return false;
            }
        });

        registerForContextMenu(listView);
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
