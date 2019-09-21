package com.exercicio.swipecamera;

import android.app.Activity;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

public class PermissionUtils {
    public static boolean validate (Activity activity, int requestCode, String ... permissions){ // OU AQUI
        List<String> list = new ArrayList<>();

        for(String permission : permissions) {
            // Valida Permissão
            // A instrução abaixo é responsável por fazer a pergunta da permissão passando a activity
            // (classe que chamou) assim como parâmetro da permissão
            boolean ok = ContextCompat.checkSelfPermission(activity, permission)
                    == PackageManager.PERMISSION_GRANTED; // 1 - ERRO PODE SER AQUI
            if (!ok) {
                // Se a resposta não é falsa então ... adiciona permissão
                list.add(permission);
            }
        }

        if (list.isEmpty()) {
            // Tudo ok, retorna true
            return true;
        }

        // Lista de permissões que faltam acesso.
        String[] newPermissions = new String[list.size()];
        list.toArray(newPermissions);

        // Solicita permissão, método provida pela classe app compat
        ActivityCompat.requestPermissions(activity, newPermissions, requestCode);

        return false;
    }
}
