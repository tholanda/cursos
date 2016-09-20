package com.example.thq.appevento.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.thq.appevento.modelo.Participante;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by droid03 on 26/05/16.
 */
public class ParticipanteDAO extends SQLiteOpenHelper {

    private static final String DATABASE = "appEventos";
    private static final int VERSAO = 2;
    private static final String TABELA = "participante" ;


    public ParticipanteDAO(Context context) {

        super(context, DATABASE, null,VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String sql = "CREATE TABLE " + TABELA + " ("
                + "id INTEGER PRIMARY KEY, "
                + "nome TEXT UNIQUE NOT NULL, "
                + "email TEXT, "
                + "telefone TEXT, "
                + "endereco TEXT"
                + ");";

        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        String sql = "ALTER TABLE " + TABELA + " ADD COLUMN endereco TEXT";
        db.execSQL(sql);
        //onCreate(db);

    }

    public void inserir(Participante participante){
        ContentValues values = new ContentValues();
        values.put("nome", participante.getNome());
        values.put("email", participante.getEmail());
        values.put("telefone", participante.getTelefone());
        values.put("endereco", participante.getEndereco());

        getWritableDatabase().insert(TABELA, null, values);
    }

    public List<Participante> getLista(){
        List<Participante> lista = new ArrayList<>();
        String sql = "SELECT * FROM " + TABELA + ";";
        Cursor c = getReadableDatabase().rawQuery(sql, null);

        Participante participante = null;

        while(c.moveToNext()){
            participante = new Participante();

            participante.setId(c.getLong(c.getColumnIndex("id")));
            participante.setNome(c.getString(c.getColumnIndex("nome")));
            participante.setEmail(c.getString(c.getColumnIndex("email")));
            participante.setTelefone(c.getString(c.getColumnIndex("telefone")));
            participante.setEndereco(c.getString(c.getColumnIndex("endereco")));

            lista.add(participante);
        }

        return lista;
    }

    public void deletar(Participante participante){
        String[] argumentos = {participante.getId().toString()};
        getWritableDatabase().delete(TABELA, "id=?", argumentos);
    }

    public void alterar(Participante participante){
        ContentValues values = new ContentValues();
        values.put("nome", participante.getNome());
        values.put("email", participante.getEmail());
        values.put("telefone", participante.getTelefone());
        values.put("endereco", participante.getEndereco());

        String[] argumentos = {participante.getId().toString()};
        getWritableDatabase().update(TABELA, values, "id=?", argumentos);
    }


}
