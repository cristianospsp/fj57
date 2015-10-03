package br.com.caelum.alunos.fragment;

import android.util.Log;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import br.com.caelum.alunos.dao.AlunoDAO;
import br.com.caelum.alunos.model.Aluno;
import br.com.caelum.alunos.util.Localizador;

/**
 * Created by android5243 on 03/10/15.
 */
public class MapaFragment extends SupportMapFragment {

    @Override
    public void onResume() {
        super.onResume();

        Localizador localizador = new Localizador(getActivity());

        LatLng local = localizador.getCoordenada("Rua Vergueiro 3185 Vila Mariana");

        Log.i("MAPA", "Coordenadas da Caelum: " + local);


        List<Aluno> alunos = new AlunoDAO(getActivity()).getList();

        for (Aluno a : alunos) {
            MarkerOptions m = new MarkerOptions();
            m.title(a.getNome());
            m.position(localizador.getCoordenada(a.getEndereco()));
            getMap().addMarker(m);
        }

        if (!alunos.isEmpty()) {
            Aluno aluno = alunos.get(0);
            this.centralizaNo(localizador.getCoordenada(aluno.getEndereco()));
        }

    }

    private void centralizaNo(LatLng local) {
        CameraUpdate novaCamera = CameraUpdateFactory.newLatLngZoom(local, 11);
        getMap().moveCamera(novaCamera);
    }
}
