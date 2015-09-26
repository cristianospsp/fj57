package br.com.caelum.alunos.adapter;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import br.com.caelum.alunos.R;
import br.com.caelum.alunos.model.Aluno;

/**
 * Created by android5243 on 26/09/15.
 */
public class ListaAlunosAdapter extends BaseAdapter {

    private final List<Aluno> alunos;
    private final LayoutInflater inflater;

    public ListaAlunosAdapter(List<Aluno> alunos, LayoutInflater inflater) {
        this.alunos = alunos;
        this.inflater = inflater;
    }

    @Override
    public int getCount() {
        return alunos.size();
    }

    @Override
    public Object getItem(int position) {
        return alunos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return alunos.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View inflate = inflater.inflate(R.layout.item, parent, false);

        Aluno aluno = alunos.get(position);

        TextView nomeView = (TextView)inflate.findViewById(R.id.item_nome);
        TextView telefoneView = (TextView)inflate.findViewById(R.id.item_telefone);
        TextView siteView = (TextView)inflate.findViewById(R.id.item_site);
        ImageView fotoView = (ImageView)inflate.findViewById(R.id.item_foto);

        nomeView.setText(aluno.getNome());
        if (telefoneView != null) {
            telefoneView.setText(aluno.getTelefone());
        }

        if (siteView != null) {
            siteView.setText(aluno.getSite());
        }

        Bitmap imagem;
        if (aluno.getCaminhoFoto() != null) {
            imagem = BitmapFactory.decodeFile(aluno.getCaminhoFoto());
            imagem = Bitmap.createScaledBitmap(imagem, 150, 250, true);
        } else {
            imagem = BitmapFactory.decodeResource(inflater.getContext().getResources(), R.drawable.ic_no_image);
            imagem = Bitmap.createScaledBitmap(imagem, 100, 100, true);
        }


        fotoView.setImageBitmap(imagem);

        inflate.setBackgroundColor(inflater.getContext().getResources().getColor( position % 2 == 0 ? R.color.linha_par : R.color.linha_impar));

        return inflate;
    }
}
