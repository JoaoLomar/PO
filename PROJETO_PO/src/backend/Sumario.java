package backend;

import java.io.Serializable;
import java.time.LocalDateTime;

public class Sumario implements Serializable {

    // Atributos da classe Sumario
    private String titulo;
    private int num_licao;
    private String tipo;
    private String sumario;
    private String uc;
    private LocalDateTime data;
    private ListaAlunos listaPresencas;
    
    // Construtor da classe Sumario
    public Sumario(String titulo, int num_licao, String tipo, String sumario, String uc, LocalDateTime data, ListaAlunos listaPresencas) {
        this.titulo = titulo;
        this.num_licao = num_licao;
        this.tipo = tipo;
        this.sumario = sumario;
        this.uc = uc;
        this.data = data;
        this.listaPresencas = listaPresencas;
    }
 
    // Métodos Getters e Setters
    public int getNum_licao() {
        return num_licao;
    }

    public void setNum_licao(int num_licao) {
        this.num_licao = num_licao;
    }

    public void setTitulo(String titl) {
        titulo = titl;
    }

    public void setTip(String tip) {
        tipo = tip;
    }

    public void setSumario(String sum) {
        sumario = sum;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getTipo() {
        return tipo;
    }

    public String getSumario() {
        return sumario;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime d) {
        data = d;
    }

    public ListaAlunos getListaPresencas() {
        return listaPresencas;
    }

    public void setListaPresencas(ListaAlunos listaPresencas) {
        this.listaPresencas = listaPresencas;
    }

    public String getUc() {
        return uc;
    }

    public void setUc(String uc) {
        this.uc = uc;
    }

    @Override
    public String toString() {
        return "\nTítulo: " + titulo
                + "\nTipo: " + tipo
                + "\nSumário: " + sumario
                + "\nData: " + data
                + "\nLista de Presenças: " + listaPresencas.toString();
    }

}
