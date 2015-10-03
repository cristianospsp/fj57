package br.com.caelum.alunos.fragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import br.com.caelum.alunos.ProvasActivity;
import br.com.caelum.alunos.R;
import br.com.caelum.alunos.model.Prova;

/**
 * Created by android5243 on 03/10/15.
 */
public class ListaProvasFragment extends Fragment {

    private ListView listViewProvas;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View layoutProvas = inflater.inflate(R.layout.fragment_lista_provas, container, false);

        Prova matematica = new Prova("20/06/2015", "Matemárica");
        matematica.addTopico("Álgebra linear");
        matematica.addTopico("Cálculo");
        matematica.addTopico("Estatística");

        Prova portugues = new Prova("20/06/2015", "Português");
        portugues.addTopico("Complemento nominal");
        portugues.addTopico("Orações subordinadas");
        portugues.addTopico("Análise sintática");

        List<Prova> provas = Arrays.asList(matematica, portugues);

        this.listViewProvas = (ListView)layoutProvas.findViewById(R.id.lista_provas_listview);
        this.listViewProvas.setAdapter(new ArrayAdapter<Prova>(getActivity(), android.R.layout.simple_list_item_1, provas));

        this.listViewProvas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Prova provaSelecionada = (Prova) parent.getItemAtPosition(position);
                //Toast.makeText(getActivity(), "Prova selecionada: " + provaSelecionada, Toast.LENGTH_LONG).show();

                ProvasActivity calendarioProvas = (ProvasActivity) getActivity();

                calendarioProvas.selecionaProva(provaSelecionada);

            }
        });

        return layoutProvas;
    }
}
