package com.example.relativeleyout.ui.activites;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.relativeleyout.R;
import com.example.relativeleyout.dao.PersonagemDAO;
import com.example.relativeleyout.model.Personagem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

import static com.example.relativeleyout.ui.activites.ConstanteActivity.CHAVE_PERSONAGEM;

public class ListaPersonagemActivity extends AppCompatActivity {
    public static final String TITULO_APPBAR = "Lista de Personagens";


    private final PersonagemDAO dao = new PersonagemDAO();

    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagm);

        PersonagemDAO dao = new PersonagemDAO();

        setTitle(TITULO_APPBAR);
//Popula inicialmente minha lista
        //dao.salva(new Personagem("Ken", "1,80", "02041979"));
        //dao.salva(new Personagem("Ryu", "1,80", "02041979"));

        chamaMeuFab();


        //cria a lista
        //List<String> Personagens = new ArrayList<>(Arrays.asList("Alex", "Kem", "Mario", "Chunli"));

        //encontra a lista dos personagens na aplicação
        //ListView listaDepersonagens = findViewById(R.id.lista_personagens);
        //foi dentro de um arquivo do android para popular ocm a lista
        //listaDepersonagens.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dao.todos()));


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private void chamaMeuFab() {
        FloatingActionButton botaoNovoPersonagem = findViewById(R.id.fab);

        //chama a função de clicar o fab
        botaoNovoPersonagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //transiciona a tela
                AbreoFormulario();
            }
        });
    }

    private void AbreoFormulario() {
        startActivity(new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //persiste os dados pra manter depois q fecha a aplicação
    @Override
    protected void onResume() {
        super.onResume();

        ConfingLista();
    }

    private void ConfingLista() {
        ListView listaDePersonagem = findViewById(R.id.lista_personagens);
        final List<Personagem> personagems = dao.todos();
        listadePersonagens(listaDePersonagem);
        //função quando clica no nome da lista.
        configuraItemPorClick(listaDePersonagem);
    }

    private void configuraItemPorClick(ListView listaDePersonagem) {
        listaDePersonagem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int posicao, long id) {

                Personagem personagemEscolhido = (Personagem) adapterView.getItemAtPosition(posicao);
                abreFormularioModoEditar(personagemEscolhido);
            }
        });
    }

    private void abreFormularioModoEditar(Personagem personagem) {
        Intent vaiparaformulario = new Intent(this, FormularioPersonagemActivity.class);
        vaiparaformulario.putExtra(CHAVE_PERSONAGEM, personagem);
        startActivity(vaiparaformulario);
    }

    private void listadePersonagens(ListView listaDePersonagem) {
        listaDePersonagem.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dao.todos()));
    }
}