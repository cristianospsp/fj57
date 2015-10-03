package br.com.caelum.alunos.util;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;

import com.google.android.gms.maps.model.LatLng;

import java.io.IOException;
import java.util.List;

/**
 * Created by android5243 on 03/10/15.
 */
public class Localizador {

   private Geocoder geocoder;

    public Localizador(Context ctx) {
        geocoder = new Geocoder(ctx);
    }

    public LatLng getCoordenada(String endereco) {

        try {
            Address address = geocoder.getFromLocationName(endereco, 1).get(0);
            return new LatLng(address.getLatitude(), address.getLongitude());
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
