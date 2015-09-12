package br.com.caelum.alunos.br.com.caelum.alunos.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import br.com.caelum.alunos.br.com.caelum.alunos.model.Aluno;

/**
 * Created by android5243 on 12/09/15.
 */
public class AlunoDAO extends SQLiteOpenHelper {

    private static final int VERSAO = 2;
    private static final String DATABASE = "CadastroCaelum";
    private static final String TABLE = "ALUNOS";

    public AlunoDAO(Context context) {
        super(context, DATABASE, null, VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder ddl = new StringBuilder("CREATE TABLE ")
                .append(TABLE)
                .append(" (id INTEGER PRIMARY KEY, nome TEXT NOT NULL,")
                .append(" telefone TEXT, endereco TEXT, site TEXT, nota REAL);");

        db.execSQL(ddl.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        StringBuilder ddl = new StringBuilder("DROP TABLE IF EXISTS ")
                .append(TABLE)
                .append(";");
        db.execSQL(ddl.toString());
        onCreate(db);
    }

    public void insere(Aluno aluno) {
        ContentValues values = new ContentValues();
        values.put("nome", aluno.getNome());
        values.put("nota", aluno.getNota());
        values.put("site", aluno.getSite());
        values.put("endereco", aluno.getEndereco());
        values.put("telefone", aluno.getTelefone());

        getWritableDatabase().insert(TABLE, null, values);
    }

}
