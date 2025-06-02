package backend;

import java.io.Serializable;


public class Aluno implements Serializable{

    // Atributos dos Alunos
    private String nome_aluno;
    private String num_mecanog;
    private String cod_curso;

    // Construtor 
    public Aluno(String nome, String num, String curso) {
        nome_aluno = nome;
        num_mecanog = num;
        cod_curso = curso;
    }

    // Getters e Setters
    public void setNome(String nome) {
        nome_aluno = nome;
    }

    public void setNumMecanog(String num) {
        num_mecanog = num;
    }

    public void setCodCurso(String nCurso) {
        cod_curso = nCurso;
    }

    public String getNumMecanog() {
        return num_mecanog;
    }
    
    public String getNome(){
        return nome_aluno;
    }
    
    public String getNomeCurso(){
        return cod_curso;
    }
    
    @Override
    public String toString() {
        return "Nome: " + nome_aluno +
               "\nNúmero Mecanográfico: " + num_mecanog +
               "\nCurso: " + cod_curso;
    }
}
