package br.com.caelum.alunos;


import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

import br.com.caelum.alunos.fragment.ListaProvasFragment;

/**
 * Created by android5243 on 03/10/15.
 */
public class ProvasActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provas);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        transaction.replace(R.id.provas_view, new ListaProvasFragment());

        transaction.commit();
    }
}
