package benicio.soluces.listadetarefas.utils.model;

public class TarefaModel {

    public TarefaModel(String tarefaString) {
        this.tarefaString = tarefaString;
    }

    String tarefaString;

    public String getTarefaString() {
        return tarefaString;
    }

    public void setTarefaString(String tarefaString) {
        this.tarefaString = tarefaString;
    }
}
