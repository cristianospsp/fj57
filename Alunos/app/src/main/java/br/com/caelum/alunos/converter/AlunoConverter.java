package br.com.caelum.alunos.converter;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

import br.com.caelum.alunos.model.Aluno;

/**
 * Created by android5243 on 26/09/15.
 */
public class AlunoConverter {


    private final List<Aluno> alunos;

    public AlunoConverter(List<Aluno> alunos) {
        this.alunos = alunos;
    }

    public String toJson() {
        JSONStringer json = new JSONStringer();
        String stringJson = null;

        try {

            json.object().key("list").array().object().key("aluno").array();

            for (Aluno aluno : alunos) {
                json.object()
                        .key("id").value(aluno.getId())
                        .key("nome").value(aluno.getNome())
                        .key("nota").value(aluno.getNota())
                        .key("site").value(aluno.getSite())
                        .key("endereco").value(aluno.getEndereco())
                        .key("telefone").value(aluno.getTelefone()).endObject();
            }

             stringJson = json.endArray().endObject().endArray().endObject().toString();

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return stringJson;
    }

}
