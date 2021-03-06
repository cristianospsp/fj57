package br.com.caelum.alunos.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

import br.com.caelum.alunos.model.Aluno;

/**
 * Created by android5243 on 12/09/15.
 */
public class AlunoDAO extends SQLiteOpenHelper {

    private static final int VERSAO = 9;
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
                .append(" telefone TEXT, endereco TEXT, site TEXT, nota REAL, caminhoFoto TEXT);");

        db.execSQL(ddl.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
       // StringBuilder ddl = new StringBuilder("DROP TABLE IF EXISTS ")
        StringBuilder ddl = new StringBuilder("ALTER TABLE " + TABLE + " ADD COLUMN caminhoFoto TEXT")
                //.append(TABLE)
                .append(";");
        db.execSQL(ddl.toString());
       // onCreate(db);
    }

    public void insere(Aluno aluno) {
        ContentValues values = new ContentValues();
        values.put("nome", aluno.getNome());
        values.put("nota", aluno.getNota());
        values.put("site", aluno.getSite());
        values.put("endereco", aluno.getEndereco());
        values.put("telefone", aluno.getTelefone());
        values.put("caminhoFoto", aluno.getCaminhoFoto());

        getWritableDatabase().insert(TABLE, null, values);
    }


    public List<Aluno> getList() {
        ArrayList<Aluno> alunos = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();

        Cursor c = db.rawQuery("SELECT * from " + TABLE + ";", null);

        while (c.moveToNext()) {
            Aluno aluno = new Aluno();

            aluno.setId(c.getLong(c.getColumnIndex("id")));
            aluno.setNome(c.getString(c.getColumnIndex("nome")));
            aluno.setSite(c.getString(c.getColumnIndex("site")));
            aluno.setEndereco(c.getString(c.getColumnIndex("endereco")));
            aluno.setTelefone(c.getString(c.getColumnIndex("telefone")));
            aluno.setNota(c.getDouble(c.getColumnIndex("nota")));
            aluno.setCaminhoFoto(c.getString(c.getColumnIndex("caminhoFoto")));

            alunos.add(aluno);
        }

        c.close();

        return alunos;
    }

    public void delete(Aluno aluno) {
        String []values = { aluno.getId().toString() };

        getWritableDatabase().delete(TABLE, "id=?", values);
    }

    public void update(Aluno aluno) {
        ContentValues campos = new ContentValues();
        campos.put("nome", aluno.getNome());
        campos.put("nota", aluno.getNota());
        campos.put("site", aluno.getSite());
        campos.put("endereco", aluno.getEndereco());
        campos.put("telefone", aluno.getTelefone());
        campos.put("caminhoFoto", aluno.getCaminhoFoto());

        String []values = { aluno.getId().toString() };

        getWritableDatabase().update(TABLE, campos, "id=?", values);
    }

    public void insertOrUpdate(Aluno aluno) {
        if (aluno.getId() == null) {
            insere(aluno);
        } else {
            update(aluno);
        }
    }

    public boolean isAluno(String telefone) {
        String[] parametros = {telefone};

        Cursor rawQuery = getReadableDatabase().rawQuery("SELECT telefone FROM " + TABLE + " WHERE telefone = ?", parametros);

        int total = rawQuery.getCount();

        rawQuery.close();

        return  total > 0;
    }

}
