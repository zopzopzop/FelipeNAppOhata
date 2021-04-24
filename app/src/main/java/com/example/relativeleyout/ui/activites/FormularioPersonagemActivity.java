package com.example.relativeleyout.ui.activites;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.relativeleyout.R;
import com.example.relativeleyout.dao.PersonagemDAO;
import com.example.relativeleyout.model.Personagem;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import static com.example.relativeleyout.ui.activites.ConstanteActivity.CHAVE_PERSONAGEM;

public class FormularioPersonagemActivity extends AppCompatActivity {


    public static final String TITULO_APPBAR_NOVO_PERSONAGEM = "Novo Personagens";
    private static final String TITULO_APPBAR_EDITA_PERSONAGEM = "Editar Personagem";
    private EditText camponome;
    private EditText campoaltura;
    private EditText camponascimento;
    private Personagem personagem;

    private final PersonagemDAO dao = new PersonagemDAO();

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_personagem_menu_salvar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        int itenID = item.getItemId();
        if (itenID == R.id.activity_formulario_personagem_menu_salvar) {
        FinalizaFormulario();
        }
        return super.onContextItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario_personagem);


        //pega meus campos
        pegaMeusCampos();

        //pegua o botão salvar no meu activity formulario
        Button botaoSalvar = findViewById(R.id.salvar_button);

        //comando do android para identificar o botão
        botaoSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FinalizaFormulario();
                //Personagem personagemSalvo = new Personagem(nome, altura, nascimento);
                //Salva meu contrutor no meu personagem dao


                //dao.edita(personagem);

                //debuga meu contrutor
                //Toast.makeText(FormularioPersonagemActivity.this, personagemSalvo.getNome() + "-" + personagemSalvo.getAltura() + "-" +personagemSalvo.getNascimento(), Toast.LENGTH_SHORT).show();
                //popula meu contrutor
                //new Personagem(nome, altura, nascimento);
                //debuga o botão
                //Toast.makeText(FormularioPersonagemActivity.this, "Estou funcionando", Toast.LENGTH_SHORT).show();
            }
        });

        CarregaPersonagem();


    }

    private void FinalizaFormulario() {
        pegaMeusCampos();
        if (personagem.IdValido()) {
            dao.edita(personagem);
        } else {
            dao.salva(personagem);
        }
        //trasiciona de uma janela para outra
        //startActivity(new Intent(FormularioPersonagemActivity.this, ListaPersonagemActivity.class));
        finish();

    }

    private void PreenchePersonagem() {
        //guarda meus campos do personagem
        String nome = camponome.getText().toString();
        String altura = campoaltura.getText().toString();
        String nascimento = camponascimento.getText().toString();

        //salva os atributos em personagem e manda ara o dao
        personagem.setNome(nome);
        personagem.setAltura(altura);
        personagem.setNascimento(nascimento);
        dao.edita(personagem);
    }

    private void CarregaPersonagem() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_PERSONAGEM)) {
            setTitle(TITULO_APPBAR_EDITA_PERSONAGEM);
            personagem = (Personagem) dados.getSerializableExtra(CHAVE_PERSONAGEM);
            PreencheCampos();
        } else {
            setTitle(TITULO_APPBAR_NOVO_PERSONAGEM);
            personagem = new Personagem();
        }
    }

    //configurações utilizada no formulário
    private void PreencheCampos() {
        //campos do Xml
        camponome.setText(personagem.toString());
        campoaltura.setText(personagem.getAltura());
        camponascimento.setText(personagem.getNascimento());

        //formatação de entrada de dados no campo de Altura
        SimpleMaskFormatter smfAltura = new SimpleMaskFormatter("N,NN");
        MaskTextWatcher mtwAltura = new MaskTextWatcher(campoaltura, smfAltura);
        campoaltura.addTextChangedListener(mtwAltura);

        SimpleMaskFormatter smfNascimento = new SimpleMaskFormatter("N/NN/NNNN");
        MaskTextWatcher mtwNascimento = new MaskTextWatcher(campoaltura, smfNascimento);
        campoaltura.addTextChangedListener(mtwNascimento);
    }

    private void pegaMeusCampos() {
        camponome = findViewById(R.id.edittext_nomedopersonagem);
        campoaltura = findViewById(R.id.edittext_altura);
        camponascimento = findViewById(R.id.editext_nascimento);
    }
}