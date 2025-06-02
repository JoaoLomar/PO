package backend;

import java.io.Serializable;
import java.time.LocalDate;

public class Professor extends Sessao implements Serializable {
    // Atributos do Professor.
    private String nome_prof;
    private String num_mecanog;
    private LocalDate data_inicio;
    private ListaUCs lista_UCs;
    private ListaUCs regencias;
    private ListaCursos direcoes;
    private ListaCursos listaCursos;
    private int cod_regencia;
    private int cod_diretor;
    
    // Construtor do Professor.
    public Professor(String nome_prof, String num_mecanog, LocalDate data_inicio, ListaUCs lista_UC, ListaUCs regencias, ListaCursos direcoes, ListaCursos listaCursos, int cod_regencia, int cod_diretor, String usn, String pass) {
        super(usn, pass);
        this.nome_prof = nome_prof;
        this.num_mecanog = num_mecanog;
        this.data_inicio = data_inicio;
        this.lista_UCs = lista_UC;
        this.regencias = regencias;
        this.direcoes = direcoes;
        this.listaCursos = listaCursos;
        this.cod_regencia = cod_regencia;
        this.cod_diretor = cod_diretor;
    }

    // Métodos Getters e Setters
    public ListaCursos getDirecoes() {
        return direcoes;
    }

    public void setDirecoes(ListaCursos direcoes) {
        this.direcoes = direcoes;
    }

    public ListaUCs getRegencias() {
        return regencias;
    }

    public void setRegencias(ListaUCs regencias) {
        this.regencias = regencias;
    }

    public int getCod_regencia() {
        return cod_regencia;
    }

    public void setCod_regencia(int cod_regencia) {
        this.cod_regencia = cod_regencia;
    }

    public int getCod_diretor() {
        return cod_diretor;
    }

    public void setCod_diretor(int cod_diretor) {
        this.cod_diretor = cod_diretor;
    }
    
    public void setNome(String nome) {
        nome_prof = nome;
    }

    public void setNumero(String num) {
        num_mecanog = num;
    }

    public void setDataInicio(LocalDate data) {
        data_inicio = data;
    }

    public ListaUCs getLista_UCs() {
        return lista_UCs;
    }

    public void setLista_UCs(ListaUCs lista_UCs) {
        this.lista_UCs = lista_UCs;
    }

    public ListaCursos getListaCursos() {
        return listaCursos;
    }

    public void setListaCursos(ListaCursos listaCursos) {
        this.listaCursos = listaCursos;
    }

    public String getNome() {
        return nome_prof;
    }

    public String getNumero() {
        return num_mecanog;
    }

    public LocalDate getDataInicio() {
        return data_inicio;
    }

    @Override
    public void setUsername(String user) {
        super.setUsername(user);
    }

    @Override
    public void setPassword(String passe) {
        super.setPassword(passe);
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }
    
    @Override
    public String toString() {
        return "Nome: " + nome_prof +
               "\nNúmero Mecanográfico: " + num_mecanog +
               "\nData de Ínicio de Funções: " + data_inicio + "\nServiço Docente: " 
                +lista_UCs.toString() + "\nUCs em que é Regente: " +regencias.toString() + "\nCódigo de Regência: " + cod_regencia + "\nCursos em que dá aulas: " +listaCursos.toString()
                + "\nCursos de que é Diretor: " + direcoes.toString() + "\nCódigo de Diretor: " +cod_diretor;
    }

    

}
