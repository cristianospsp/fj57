package br.com.caelum.alunos;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import java.io.File;

import br.com.caelum.alunos.dao.AlunoDAO;
import br.com.caelum.alunos.model.Aluno;


public class FormularioActivity extends ActionBarActivity {

    public static final int TIRA_FOTO = 123;
    private FormularioActivityHelper helper;
    private String localArquivoFoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        this.helper = new FormularioActivityHelper(this);

        Intent intent = getIntent();
        Aluno alunoSelecionado = (Aluno)intent.getSerializableExtra("alunoSelecionado");

        if (alunoSelecionado != null) {
            this.helper.colocaNoFormulario(alunoSelecionado);
        }

        Button foto = helper.getFotoButtonn();
        foto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                localArquivoFoto = getExternalFilesDir(null) + "/" + System.currentTimeMillis() + ".jpg";
                Intent irParaCamera = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

                Uri localFoto = Uri.fromFile(new File(localArquivoFoto));

                irParaCamera.putExtra(MediaStore.EXTRA_OUTPUT, localFoto);

                startActivityForResult(irParaCamera, TIRA_FOTO);
            }
        });

        //View salvar = findViewById(R.id.formulario_botao);
       // final EditText nome = (EditText)findViewById(R.id.formulario_nome);

        /*salvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FormularioActivity.this, "Salvar Estudante: " + nome.getText(), Toast.LENGTH_LONG ).show();
            }
        });*/
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == TIRA_FOTO) {
            if (resultCode == Activity.RESULT_OK) {
                helper.carregaImagem(this.localArquivoFoto);

            } else {
                this.localArquivoFoto = null;
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_formulario, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();

//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        switch (item.getItemId()) {
            case R.id.menu_formulario_ok:
                Aluno aluno = helper.pegaAluno();
                if (helper.temNome()) {
                    AlunoDAO alunoDAO = new AlunoDAO(this);
                    alunoDAO.insertOrUpdate(aluno);
                    alunoDAO.close();
                    finish();
                    return true;
                } else {
                    helper.mostraErro();
                    return false;
                }

            default:
                return super.onOptionsItemSelected(item);
        }


    }
}
