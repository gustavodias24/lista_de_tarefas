package benicio.soluces.listadetarefas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import benicio.soluces.listadetarefas.adapter.AdapterTarefa;
import benicio.soluces.listadetarefas.databinding.ActivityMainBinding;
import benicio.soluces.listadetarefas.databinding.AddTarefaLayoutBinding;
import benicio.soluces.listadetarefas.utils.TarefaSaveIstance;
import benicio.soluces.listadetarefas.utils.model.TarefaModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private RecyclerView r;
    private List<TarefaModel> lista = new ArrayList<>();
    private AdapterTarefa a;

    private Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        r = binding.recyclerTarefas;
        r.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        r.addItemDecoration(new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL));
        r.setHasFixedSize(true);
        a = new AdapterTarefa(lista);
        r.setAdapter(a);

        loadTarefas();
        criarDialog();

        binding.floatingActionButton.setOnClickListener( view -> {
            dialog.show();
        });
    }


    public void criarDialog(){
        AlertDialog.Builder b = new AlertDialog.Builder(MainActivity.this);
        b.setTitle("Adicione sua tarefa!");
        AddTarefaLayoutBinding tarefaBinding = AddTarefaLayoutBinding.inflate(getLayoutInflater());

        tarefaBinding.button.setOnClickListener( view -> {
            String tarefaString = tarefaBinding.editTextText.getText().toString();

            if ( tarefaString.isEmpty() ){
                Toast.makeText(this, "Tarefa vazia!", Toast.LENGTH_SHORT).show();
            }else{
                tarefaBinding.editTextText.setText("");
                Toast.makeText(this, "Tarefa Adicionada com sucesso", Toast.LENGTH_SHORT).show();
                lista.add(new TarefaModel(tarefaString));
                TarefaSaveIstance.saveTarefas(getApplicationContext(), lista);
                loadTarefas();
                dialog.dismiss();
            }

        });

        b.setView(tarefaBinding.getRoot());
        dialog = b.create();
    }
    public void loadTarefas(){
        lista.clear();
        if ( TarefaSaveIstance.loadTarefas(getApplicationContext()) != null){
            lista.addAll(TarefaSaveIstance.loadTarefas(getApplicationContext()));
        }

        a.notifyDataSetChanged();
        if ( lista.isEmpty() ){
            Toast.makeText(this, "Nenhuma tarefa cadastrada.", Toast.LENGTH_SHORT).show();
        }
    }

}