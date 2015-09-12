package br.com.caelum.alunos;

import android.widget.EditText;
import android.widget.RatingBar;

import br.com.caelum.alunos.br.com.caelum.alunos.model.Aluno;

/**
 * Created by android5243 on 12/09/15.
 */
public class FormularioActivityHelper {

    private final EditText nome;
    private final EditText telefone;
    private final EditText endereco;
    private final EditText site;
    private final RatingBar nota;
    private final Aluno aluno;

    public FormularioActivityHelper(FormularioActivity activity) {
        this.nome = (EditText)activity.findViewById(R.id.formulario_nome);
        this.telefone = (EditText)activity.findViewById(R.id.formulario_telefone);
        this.endereco = (EditText)activity.findViewById(R.id.formulario_endereco);
        this.site = (EditText)activity.findViewById(R.id.formulario_site);
        this.nota = (RatingBar)activity.findViewById(R.id.formulario_nota);

        this.aluno = new Aluno();
    }

    public Aluno pegaAluno() {

        this.aluno.setNome(this.nome.getText().toString());
        this.aluno.setTelefone(this.telefone.getText().toString());
        this.aluno.setEndereco(this.endereco.getText().toString());
        this.aluno.setSite(this.site.getText().toString());
        this.aluno.setNota(Double.valueOf(this.nota.getProgress()));

        return this.aluno;
    }

    public boolean temNome() {
        return !this.nome.getText().toString().isEmpty();
    }

    public void mostraErro() {
        this.nome.setError("Campo nome não pode ser vazio");
    }
}
