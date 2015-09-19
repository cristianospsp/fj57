package br.com.caelum.alunos;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import br.com.caelum.alunos.br.com.caelum.alunos.model.Aluno;

/**
 * Created by android5243 on 12/09/15.
 */
public class FormularioActivityHelper {

    private EditText nome;
    private EditText telefone;
    private EditText endereco;
    private EditText site;
    private RatingBar nota;
    private Aluno aluno;

    private ImageView foto;
    private Button fotoButtonn;


    public FormularioActivityHelper(FormularioActivity activity) {
        this.nome = (EditText)activity.findViewById(R.id.formulario_nome);
        this.telefone = (EditText)activity.findViewById(R.id.formulario_telefone);
        this.endereco = (EditText)activity.findViewById(R.id.formulario_endereco);
        this.site = (EditText)activity.findViewById(R.id.formulario_site);
        this.nota = (RatingBar)activity.findViewById(R.id.formulario_nota);

        this.foto = (ImageView) activity.findViewById(R.id.formulario_foto);
        this.fotoButtonn = (Button) activity.findViewById(R.id.formulario_foto_button);

        this.aluno = new Aluno();
    }

    public Aluno pegaAluno() {

        this.aluno.setNome(this.nome.getText().toString());
        this.aluno.setTelefone(this.telefone.getText().toString());
        this.aluno.setEndereco(this.endereco.getText().toString());
        this.aluno.setSite(this.site.getText().toString());
        this.aluno.setNota(Double.valueOf(this.nota.getRating()));
        this.aluno.setCaminhoFoto((String)foto.getTag());
        return this.aluno;
    }

    public boolean temNome() {
        return !this.nome.getText().toString().isEmpty();
    }

    public void mostraErro() {
        this.nome.setError("Campo nome não pode ser vazio");
    }

    public void colocaNoFormulario(Aluno alunoSelecionado) {
        this.nome.setText(alunoSelecionado.getNome());
        this.telefone.setText(alunoSelecionado.getTelefone());
        this.endereco.setText(alunoSelecionado.getEndereco());
        this.site.setText(alunoSelecionado.getSite());
        this.nota.setRating(alunoSelecionado.getNota().floatValue());

        if (alunoSelecionado.getCaminhoFoto() != null) {
            carregaImagem(alunoSelecionado.getCaminhoFoto());
        }

        this.aluno = alunoSelecionado;
    }

    public Button getFotoButtonn() {
        return fotoButtonn;
    }

    public void carregaImagem(String localArquivoFoto) {
        Bitmap imagemFoto = BitmapFactory.decodeFile(localArquivoFoto);
        Bitmap imagemFotoReduzida = Bitmap.createScaledBitmap(imagemFoto, imagemFoto.getWidth(), 300, true);
        foto.setImageBitmap(imagemFotoReduzida);
        foto.setTag(localArquivoFoto);
        foto.setScaleType(ImageView.ScaleType.FIT_XY);
    }
}
