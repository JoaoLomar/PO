package backend;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ListaAlunos implements Serializable {

    List<Aluno> la;

    // Construtor que inicializa a lista
    public ListaAlunos() {
        la = new ArrayList<>();
        la.size();
    }

    // Método para adicionar alunos
    public void adicionarAluno(Aluno a) {
        la.add(a);
    }

    // Getter
    public List<Aluno> getLista() {
        return la;
    }

    // Método para adicionar Alunos provenientes de outra lista
    public void adicionarAlunosDeOutraLista(ListaAlunos l) {
        for (Aluno a : l.getLista()) {
            if (!la.contains(a)) {
                la.add(a);
            }
        }
    }

    // Método para remover alunos
    public boolean removerAluno(Aluno a) {
        boolean removido = la.remove(a);
        return removido;
    }

    // Método que verifica se um aluno existe com base num nº mecanográfico
    public boolean verificar(String num) {
        for (Aluno aluno : la) {
            if (aluno.getNumMecanog().equals(num)) {
                return true;
            }
        }
        return false;

    }

    // Método que procura o aluno com o nº mecanográfico correspondente
    public Aluno procurarAluno(String nmec) {
        for (Aluno a : la) {
            if (a.getNumMecanog().equals(nmec)) {
                return a;
            }
        }
        return null;
    }

    //Método para contar alunos
    public int contarAlunos() {
        int cont = 0;
        for (Aluno a : la) {
            cont++;
        }
        return cont;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("\nLista de alunos:\n");
        for (Aluno aluno : la) {
            sb.append(aluno.toString()).append("\n");
        }
        return sb.toString();
    }

    //Grava a lista de Alunos num ficheiro
    public void gravarFicheiro() throws IOException {
        System.out.println("Tamanho da Lista Alunos : " + la.size());
        if (!la.isEmpty()) {
            try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("ListaAlunos")))) {
                out.writeObject(la);
            } catch (IOException ex) {

            }
        } else { // caso esteja vazia
            System.out.println("Lista de Alunos está vazia.");
        }

    }

//Lê a Lista de Alunos do Ficheiro
    public void lerFicheiro() throws IOException {
        la.clear();
        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("ListaAlunos")))) {
            Object obj = in.readObject();
            if (obj instanceof List) {
                List<?> listaLida = (List<?>) obj; // A lista pode ler qualquer tipo de objetos.
                System.out.println("Tamanho da Lista de Alunos: " + listaLida.size());

                for (Object obj2 : listaLida) {
                    if (obj2 instanceof Aluno) {
                        Aluno aluno = (Aluno) obj2;
                        la.add(aluno);
                        //System.out.println("Alunos: " + aluno.toString());

                    } else {
                        System.out.println("Classe não Correspondida: " + obj2.getClass().getName());
                    }
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
        }
    }

}
