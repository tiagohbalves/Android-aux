package br.com.tiagohbalves.cursoandroid.chatapp.helper;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class Permissao {

    public static boolean validaPermissoes(int requestCode,Activity activity, String[] permissioes){

        if(Build.VERSION.SDK_INT>=23){
            List<String> listaPermissoes = new ArrayList<>();
            for(String permissao : permissioes){
                boolean valida =  ContextCompat.checkSelfPermission(activity,permissao)== PackageManager.PERMISSION_GRANTED;
                if(!valida) listaPermissoes.add(permissao);

            }
            if(listaPermissoes.isEmpty())return true;

            String[] novasPermissoes = new String[listaPermissoes.size()];
            listaPermissoes.toArray(novasPermissoes);
            ActivityCompat.requestPermissions(activity,novasPermissoes,requestCode);

        }

        return true;
    }
}
