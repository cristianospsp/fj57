package br.com.caelum.alunos.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import br.com.caelum.alunos.R;

/**
 * Created by android5243 on 03/10/15.
 */
public class DetalhesProvaFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View layoutDetalhes = inflater.inflate(R.layout.fragment_detalhes_prova, container, false);




        return layoutDetalhes;
    }
}
