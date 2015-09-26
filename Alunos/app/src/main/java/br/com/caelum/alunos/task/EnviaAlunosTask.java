package br.com.caelum.alunos.task;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import br.com.caelum.alunos.converter.AlunoConverter;
import br.com.caelum.alunos.dao.AlunoDAO;
import br.com.caelum.alunos.model.Aluno;
import br.com.caelum.alunos.support.WebClient;

/**
 * Created by android5243 on 26/09/15.
 */
public class EnviaAlunosTask extends AsyncTask<Object, Object, String> {

    private final Context context;
    private ProgressDialog progress;

    public EnviaAlunosTask(Context context) {
        this.context = context;
    }

    @Override
    protected String doInBackground(Object... params) {
        String resposta = null;
        AlunoDAO dao = new AlunoDAO(context);
        List<Aluno> alunos = dao.getList();
        dao.close();

        String json = new AlunoConverter(alunos).toJson();
        WebClient client = new WebClient();

        return client.post(json);

    }

    @Override
    protected void onPostExecute(String resposta) {
        super.onPostExecute(resposta);
        Toast.makeText(context, resposta, Toast.LENGTH_LONG).show();
        progress.cancel();
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progress = ProgressDialog.show(context, "Aguarde...", "Envio de dados para a web", true, true);
    }
}
