package benicio.soluces.listadetarefas.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import benicio.soluces.listadetarefas.R;
import benicio.soluces.listadetarefas.utils.model.TarefaModel;

public class AdapterTarefa extends RecyclerView.Adapter<AdapterTarefa.MyViewHolder> {

    List<TarefaModel> lista;

    public AdapterTarefa(List<TarefaModel> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(
                LayoutInflater.from(parent.getContext()).inflate(R.layout.tarefa_layout,parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TarefaModel tarefa = lista.get(position);
        holder.tarefaString.setText(tarefa.getTarefaString());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public  class MyViewHolder extends  RecyclerView.ViewHolder {

        TextView tarefaString;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tarefaString = itemView.findViewById(R.id.textView2);
        }
    }
}
