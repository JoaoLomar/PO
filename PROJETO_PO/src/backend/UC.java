package backend;

import java.io.Serializable;

public class UC implements Serializable {

    // Atributos da classe UC
    private String designacao;
    private String cod_UC;
    private String cod_Curso;
    private final ListaAlunos la = new ListaAlunos();
    private final Lista_Sumarios lsu = new Lista_Sumarios();

    // Construtor da classe UC
    public UC(String des, String cod, String cod_curso) {
        designacao = des;
        cod_UC = cod;
        cod_Curso = cod_curso;
    }
    
    // Métodos Getters e Setters
    public String getCod_Curso() {
        return cod_Curso;
    }

    public void setCod_Curso(String cod_Curso) {
        this.cod_Curso = cod_Curso;
    }

    public String getCodigo() {
        return cod_UC;
    }

    public String getDesignacao() {
        return designacao;
    }

    public void setCodigo(String cod) {
        cod_UC = cod;
    }

    public void setDesignacao(String des) {
        designacao = des;
    }

    public ListaAlunos getListaAlunos() {
        return la;
    }

    public Lista_Sumarios getListaSumarios() {
        return lsu;
    }

    @Override
    public String toString() {
        return "Designação: " + designacao
                + "\nCódigo de UC: " + cod_UC + "\nCurso da UC: " + cod_Curso;
    }
}
