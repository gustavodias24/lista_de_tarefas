package benicio.soluces.listadetarefas.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import benicio.soluces.listadetarefas.utils.model.TarefaModel;

public class TarefaSaveIstance {
    private static final String PREF_NAME = "tarefa_prefs";
    private static final String KEY_TAREFAS = "tarefas";

    // Método para salvar a lista de transações
    public static void saveTarefas(Context context, List<TarefaModel> transacoes) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        Gson gson = new Gson();
        String json = gson.toJson(transacoes);
        editor.putString(KEY_TAREFAS, json);
        editor.apply();
    }

    // Método para carregar a lista de transações
    public static List<TarefaModel> loadTarefas(Context context) {
        try {
            SharedPreferences sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
            Gson gson = new Gson();
            String json = sharedPreferences.getString(KEY_TAREFAS, "");
            Type type = new TypeToken<List<TarefaModel>>() {
            }.getType();
            return gson.fromJson(json, type);
        }catch (Exception e){
            return new ArrayList<>();
        }
    }
}
