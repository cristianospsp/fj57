package br.com.caelum.alunos;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;
import java.util.concurrent.ExecutionException;

import br.com.caelum.alunos.adapter.ListaAlunosAdapter;
import br.com.caelum.alunos.converter.AlunoConverter;
import br.com.caelum.alunos.dao.AlunoDAO;
import br.com.caelum.alunos.model.Aluno;
import br.com.caelum.alunos.support.WebClient;
import br.com.caelum.alunos.task.EnviaAlunosTask;

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

        listView = (ListView) findViewById(R.id.lista);

        View botaoAdiciona = findViewById(R.id.lista_alunos_floating_button);


        botaoAdiciona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(intent);
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

        ListaAlunosAdapter listaAlunosAdapter = new ListaAlunosAdapter(alunos, this.getLayoutInflater());

        this.listView.setAdapter(listaAlunosAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista_alunos, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menu_enviar_notas:
                new EnviaAlunosTask(this).execute();
                return true;
            case R.id.menu_receber_provas:
                Intent provas = new Intent(this, ProvasActivity.class);
                startActivity(provas);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
