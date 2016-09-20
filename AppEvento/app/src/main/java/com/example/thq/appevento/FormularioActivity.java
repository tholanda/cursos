package com.example.thq.appevento;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.thq.appevento.dao.ParticipanteDAO;
import com.example.thq.appevento.modelo.FormularioHelper;
import com.example.thq.appevento.modelo.Participante;

public class FormularioActivity extends AppCompatActivity {

    private FormularioHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);

        Button botao = (Button) findViewById(R.id.btn_inserir);

        helper = new FormularioHelper(this);

        final Participante participanteSelecionado = (Participante) getIntent().getSerializableExtra("participanteSelecionado");

        if(participanteSelecionado != null) {
            helper.carregaParticipanteNoFormulario(participanteSelecionado);
            botao.setText("Alterar");
        }

        botao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(FormularioActivity.this, "Clicou no inserir...", Toast.LENGTH_LONG).show();

                Participante participanteDoFormulario = helper.getParticipanteDoFormulario();

                ParticipanteDAO dao = new ParticipanteDAO(FormularioActivity.this);

                if(participanteSelecionado != null){
                    participanteDoFormulario.setId(participanteSelecionado.getId());
                    dao.alterar(participanteDoFormulario);
                } else {
                    dao.inserir(participanteDoFormulario);
                }

                dao.close();

                finish();
            }
        });
    }


}
