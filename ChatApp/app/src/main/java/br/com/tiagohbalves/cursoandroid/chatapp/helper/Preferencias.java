package br.com.tiagohbalves.cursoandroid.chatapp.helper;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

public class Preferencias {

    Context ctx;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private final String NOME_ARQUIVO = "chatapp.pref";
    private final int MODE = 0;
    private final String CHAVE_NOME = "nome";
    private final String CHAVE_TELEFONE= "telefone";
    private final String CHAVE_TOKEN = "token";


    public Preferencias (Context ctxPreference){

        this.ctx = ctxPreference;
        this.preferences = ctx.getSharedPreferences(NOME_ARQUIVO,MODE);
        editor = preferences.edit();

    }

    public void savePreferenceFiles(String nome, String telefone, String token){
        editor.putString(CHAVE_NOME,nome);
        editor.putString(CHAVE_TELEFONE,telefone);
        editor.putString(CHAVE_TOKEN,token);

    }

    public HashMap<String, String> getDadosUsuario(){
        HashMap<String , String> dadosUsuario = new HashMap<>();
        dadosUsuario.put(CHAVE_NOME,preferences.getString(CHAVE_NOME,null));
        dadosUsuario.put(CHAVE_TELEFONE,preferences.getString(CHAVE_TELEFONE,null));
        dadosUsuario.put(CHAVE_TELEFONE,preferences.getString(CHAVE_TOKEN,null));
        return dadosUsuario;
    }

}
