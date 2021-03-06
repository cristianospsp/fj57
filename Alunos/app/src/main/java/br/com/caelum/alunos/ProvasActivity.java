package br.com.caelum.alunos;


import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;

import br.com.caelum.alunos.fragment.DetalhesProvaFragment;
import br.com.caelum.alunos.fragment.ListaProvasFragment;
import br.com.caelum.alunos.model.Prova;

/**
 * Created by android5243 on 03/10/15.
 */
public class ProvasActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_provas);

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();

        if (isTablet()) {
            transaction.replace(R.id.provas_lista, new ListaProvasFragment());
            transaction.replace(R.id.provas_detalhes, new DetalhesProvaFragment());
        } else {
            transaction.replace(R.id.provas_view, new ListaProvasFragment());
        }


        transaction.commit();
    }

    private boolean isTablet() {
        return getResources().getBoolean(R.bool.isTablet);
    }

    public void selecionaProva(Prova provaSelecionada) {
        FragmentManager manager = getSupportFragmentManager();

        if (isTablet()) {
            DetalhesProvaFragment detalhesProva = (DetalhesProvaFragment) manager.findFragmentById(R.id.provas_detalhes);
            detalhesProva.populaCamposComDados(provaSelecionada);
        } else {
            Bundle argumentos = new Bundle();
            argumentos.putSerializable("prova", provaSelecionada);

            DetalhesProvaFragment detalhesProvaFragment = new DetalhesProvaFragment();
            detalhesProvaFragment.setArguments(argumentos);

            FragmentTransaction fragmentTransaction = manager.beginTransaction();
            fragmentTransaction.replace(R.id.provas_view, detalhesProvaFragment);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        }
    }
}
