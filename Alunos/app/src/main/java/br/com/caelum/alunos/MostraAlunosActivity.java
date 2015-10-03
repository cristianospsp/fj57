package br.com.caelum.alunos;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import br.com.caelum.alunos.fragment.MapaFragment;

/**
 * Created by android5243 on 03/10/15.
 */
public class MostraAlunosActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mostra_alunos);

        MapaFragment mapaFragment = new MapaFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.mostra_alunos_mapa, mapaFragment).commit();

    }
}
