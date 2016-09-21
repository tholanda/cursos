package com.example.thq.appevento;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.thq.appevento.dao.ParticipanteDAO;
import com.example.thq.appevento.modelo.Participante;

import java.util.List;

public class ListaParticipantesActivity extends AppCompatActivity {

    private ListView listaParticipantes;
    private ArrayAdapter<Participante> adapter;
    private Participante participante;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_participantes);

        this.listaParticipantes = (ListView) findViewById(R.id.lista_participantes);

        registerForContextMenu(listaParticipantes);

        this.listaParticipantes.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Participante participanteSelecionado = (Participante) parent.getItemAtPosition(position);

                Intent irParaOFormulario = new Intent(ListaParticipantesActivity.this, FormularioActivity.class);

                irParaOFormulario.putExtra("participanteSelecionado", participanteSelecionado);

                startActivity(irParaOFormulario);
            }
        });

        this.listaParticipantes.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                //Toast.makeText(ListaParticipantesActivity.this, "Clique longo", Toast.LENGTH_SHORT).show();
                participante = (Participante) parent.getItemAtPosition(position);

                return false;
            }
        });

        Button botaoAdiciona = (Button) findViewById(R.id.bt_flutuante_lista_participantes);
        botaoAdiciona.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intencao = new Intent(ListaParticipantesActivity.this, FormularioActivity.class);
                startActivity(intencao);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_inflate, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case R.id.novo:
                //Toast.makeText(this, "Novo Participante", Toast.LENGTH_LONG).show();
                //return true;
                Intent intencao = new Intent(ListaParticipantesActivity.this, FormularioActivity.class);
                startActivity(intencao);
                break;
            case R.id.preferencias:
                Toast.makeText(this, "Preferências", Toast.LENGTH_LONG).show();
                return true;
            default:
                //return super.onOptionsItemSelected(item);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        carregarListaParticipantes();

    }

    private void carregarListaParticipantes() {
        ParticipanteDAO dao = new ParticipanteDAO(ListaParticipantesActivity.this);

        List<Participante> participantes = dao.getLista();

        adapter = new ArrayAdapter<Participante>(this, android.R.layout.simple_list_item_1, participantes);

        this.listaParticipantes.setAdapter(adapter);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        //menu.add("Ver no mapa");
        //menu.add("Tirar foto");

        MenuItem deletar = menu.add("Deletar");

        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                ParticipanteDAO dao = new ParticipanteDAO(ListaParticipantesActivity.this);

                dao.deletar(participante);
                dao.close();

                carregarListaParticipantes();

                Toast.makeText(ListaParticipantesActivity.this, participante.getNome() + " deletado com sucesso!", Toast.LENGTH_LONG).show();

                return false;
            }
        });

        MenuItem menuSMS = menu.add("Enviar SMS");
        Intent itemSMS = new Intent(Intent.ACTION_VIEW);
        itemSMS.setData(Uri.parse("sms:" + participante.getTelefone()));
        menuSMS.setIntent(itemSMS);


        MenuItem menuMapa = menu.add("Ver no mapa");
        Intent intentMapa = new Intent(Intent.ACTION_VIEW);
        intentMapa.setData(Uri.parse("geo:0,0?q=" + Uri.encode(participante.getEndereco())));
        menuMapa.setIntent(intentMapa);

        MenuItem menuLigar = menu.add("Ligar...");
        menuLigar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                if (ActivityCompat.checkSelfPermission(ListaParticipantesActivity.this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {

                    ActivityCompat.requestPermissions(ListaParticipantesActivity.this,
                            new String[]{Manifest.permission.CALL_PHONE}, 1);
                }else {
                    Intent intentLigar = new Intent(Intent.ACTION_CALL);
                    intentLigar.setData(Uri.parse("tel:" + participante.getTelefone()));

                    startActivity(intentLigar);
                }


                return false;
            }
        });

        super.onCreateContextMenu(menu, v, menuInfo);

    }



}
