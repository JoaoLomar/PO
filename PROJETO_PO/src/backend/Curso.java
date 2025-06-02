package backend;

import java.io.Serializable;

public class Curso implements Serializable{

    // Atributos
    private String nomeCurso;
    private String codCurso;
    private final ListaAlunos lista = new ListaAlunos();
    private final ListaUCs listaUC = new ListaUCs();
    private final ListaProfessores listaP = new ListaProfessores();

    // Construtor
    public Curso(String nCurso, String codC) {
        nomeCurso = nCurso;
        codCurso = codC;
    }

    // Getters e Setters
    public String getNomeCurso() {
        return nomeCurso;
    }

    public String getCodCurso() {
        return codCurso;
    }

    public void setNomeCurso(String nCurso) {
        nomeCurso = nCurso;
    }

    public void setCodCurso(String codC) {
        codCurso = codC;
    }

    public ListaAlunos getListaAlunos() {
        return lista;
    }

    public ListaUCs getListaUCs(){
        return listaUC;
    }

    public ListaProfessores getListaP() {
        return listaP;
    }
    
    @Override
    public String toString() {
        return "Nome: " + nomeCurso
                + "\nCódigo de Curso: " + codCurso +"\nLista de Alunos do Curso: " + lista.toString();
    }

}
