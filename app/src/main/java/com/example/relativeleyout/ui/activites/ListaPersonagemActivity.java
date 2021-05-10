package com.example.relativeleyout.ui.activites;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.relativeleyout.R;
import com.example.relativeleyout.dao.PersonagemDAO;
import com.example.relativeleyout.model.Personagem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import static com.example.relativeleyout.ui.activites.ConstanteActivity.CHAVE_PERSONAGEM;

public class ListaPersonagemActivity extends AppCompatActivity {
    public static final String TITULO_APPBAR = "Lista de Personagens";
    private final PersonagemDAO dao = new PersonagemDAO();
    private ArrayAdapter<Personagem> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_personagm);
        PersonagemDAO dao = new PersonagemDAO();
        setTitle(TITULO_APPBAR);
        chamaMeuFab();
        //cria a lista
        ConfingLista();
        //Cria um Alerta




//Popula inicialmente minha lista
        //dao.salva(new Personagem("Ken", "1,80", "02041979"));
        //dao.salva(new Personagem("Ryu", "1,80", "02041979"));


        //List<String> Personagens = new ArrayList<>(Arrays.asList("Alex", "Kem", "Mario", "Chunli"));

        //encontra a lista dos personagens na aplicação
        //ListView listaDepersonagens = findViewById(R.id.lista_personagens);
        //foi dentro de um arquivo do android para popular ocm a lista
        //listaDepersonagens.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, dao.todos()));



    }

    private void chamaMeuFab() {
        FloatingActionButton botaoNovoPersonagem = findViewById(R.id.fab);

        //chama a função de clicar o fab
        botaoNovoPersonagem.setOnClickListener(v -> {
            //transiciona a tela
            AbreoFormulario();
        });
    }

    private void AbreoFormulario() {
        startActivity(new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class));
    }


    //persiste os dados pra manter depois q fecha a aplicação
    @Override
    protected void onResume() {

        super.onResume();
        atualizaPersonagem();
    }

    private void atualizaPersonagem() {
        //limpa a lista
        adapter.clear();
        //remonta a lista com os item Lista de Personagenm
        adapter.addAll(dao.todos());
    }

    //Limpa os contrutores
    private void remove (Personagem personagem){
        dao.remove(personagem);
        adapter.remove(personagem);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //menu.add("Remover");

        getMenuInflater().inflate(R.menu.activity_lista_personagem_menu, menu);
    }

    //Faz a ação de presionar um nome
    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        ConfiguraMenu(item);
        return super.onContextItemSelected(item);
    }

    private void ConfiguraMenu(@NonNull MenuItem item) {
        int itemId = item.getItemId();
        if(itemId == R.id.activity_lista_personagem_menu_remover) {

            new AlertDialog.Builder(this)
                    .setTitle("Removendo Personagem")
                    .setMessage("Tem certeza que deseja remover?")
                    .setPositiveButton("Sim", (dialog, which) -> {
                        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                        Personagem personagemEscolhido = adapter.getItem(menuInfo.position);
                        remove(personagemEscolhido);
                    })
                    .setNegativeButton("Não", null)
                    .show();



        }
    }

    private void ConfingLista() {


        ListView listaDePersonagem = findViewById(R.id.lista_personagens);
        //final List<Personagem> personagems = dao.todos();
        configuraAdapter(listaDePersonagem);
        //função quando clica no nome da lista.
        configuraItemPorClick(listaDePersonagem);
        //indentifica o que está sendo pressinado na lista
        registerForContextMenu(listaDePersonagem);
    }

    private void configuraItemPorClick(ListView listaDePersonagem) {
        listaDePersonagem.setOnItemClickListener((adapterView, view, posicao, id) -> {

            Personagem personagemEscolhido = (Personagem) adapterView.getItemAtPosition(posicao);
            abreFormularioModoEditar(personagemEscolhido);
        });
    }

    private void abreFormularioModoEditar(Personagem personagem) {
        Intent vaiparaformulario = new Intent(this, FormularioPersonagemActivity.class);
        vaiparaformulario.putExtra(CHAVE_PERSONAGEM, personagem);
        startActivity(vaiparaformulario);
    }

    private void configuraAdapter(ListView listaDePersonagem) {
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listaDePersonagem.setAdapter(adapter);
    }
}