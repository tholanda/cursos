package com.example.thq.appevento.modelo;

import android.provider.Telephony;
import android.widget.EditText;

import com.example.thq.appevento.FormularioActivity;
import com.example.thq.appevento.R;

/**
 * Created by droid03 on 26/05/16.
 */
public class FormularioHelper {

    private EditText campoNome;
    private EditText campoEmail;
    private EditText campoTelefone;
    private EditText campoEndereco;

    private Participante participante;

    public FormularioHelper(FormularioActivity activity) {

        participante = new Participante();

        campoNome = (EditText) activity.findViewById(R.id.edt_nome);
        campoEmail = (EditText) activity.findViewById(R.id.edt_email);
        campoTelefone = (EditText) activity.findViewById(R.id.edt_telefone);
        campoEndereco = (EditText) activity.findViewById(R.id.edt_endereco);
    }

    public Participante getParticipanteDoFormulario(){
        String nome = campoNome.getText().toString();
        String email = campoEmail.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String endereco = campoEndereco.getText().toString();

        participante.setNome(nome);
        participante.setEmail(email);
        participante.setTelefone(telefone);
        participante.setEndereco(endereco);

        return participante;
    }

    public void carregaParticipanteNoFormulario(Participante participante){
        this.campoNome.setText(participante.getNome());
        this.campoEmail.setText(participante.getEmail());
        this.campoTelefone.setText(participante.getTelefone());
        this.campoEndereco.setText(participante.getEndereco());
    }



}
