package com.example.caelum.olamundo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by android5243 on 05/09/15.
 */
public class DesafioBotoesActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_desafio_botoes);

        Button botao = (Button) findViewById(R.id.botao);
        final EditText textoDigitado = (EditText) findViewById(R.id.input);
        final TextView textoParaExibir = (TextView) findViewById(R.id.texto_digitado);


        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                textoParaExibir.setText(textoDigitado.getText());

            }
        });

    }
}
