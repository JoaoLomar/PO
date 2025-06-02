package backend;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class ListaRegentes implements Serializable {

    // Lista que armazena os professores regentes.
    List<Professor> listaRegentes;

    // Construtor que inicializa a lista como um ArrayList.
    public ListaRegentes() {
        listaRegentes = new ArrayList<>();
    }

    // Método para obter a lista de regentes.
    public List<Professor> getListaRegentes() {
        return listaRegentes;
    }

    // Método para definir a lista de regentes.
    public void setListaRegentes(List<Professor> listaRegentes) {
        this.listaRegentes = listaRegentes;
    }

    // Adiciona um professor à lista de regentes.
    public void adicionarRegentes(Professor r) {
        listaRegentes.add(r);
    }

    // Verifica se um professor com o número mecanográfico existe na lista de regentes.
    public boolean verificar(String s) {
        for (Professor r : listaRegentes) {
            if (r.getNumero().equals(s)) {
                System.out.println("\nOk.\n");
                return true;
            }
        }
        return false;
    }

    // Verifica se existe algum regente com o código de regência.
    public boolean verificarRegencia(int cod) {
        for (Professor r : listaRegentes) {
            if (r.getCod_regencia() == cod) {
                return true;
            }
        }
        return false;
    }

    // Procura e retorna um professor com o código de regência.
    public Professor procurarRegente(int cod) {
        for (Professor r : listaRegentes) {
            if (r.getCod_regencia() == (cod)) {
                return r;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Lista de Regentes de UCs\n");
        for (Professor r : listaRegentes) {
            sb.append(r.toString()).append("\n");
        }
        return sb.toString();
    }

    // Grava a lista de regentes num ficheiro.
    public void gravarFicheiro() throws IOException {
        System.out.println("Tamanho da Lista de Regentes: " + listaRegentes.size());
        if (!listaRegentes.isEmpty()) {
            try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("ListaRegentes")))) {
                out.writeObject(listaRegentes);
            } catch (IOException ex) {

            }
        } else { // Caso a lista esteja vazia.
            System.out.println("Lista de Regentes está vazia.");
        }

    }

    // Lê a lista de regentes do ficheiro.
    public void lerFicheiro() throws IOException {
        listaRegentes.clear();
        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("ListaRegentes")))) {
            Object obj = in.readObject();
            if (obj instanceof List) {
                List<?> listaLida = (List<?>) obj; // A lista pode ler qualquer tipo de objetos.
                System.out.println("Tamanho da Lista dos Regentes: " + listaLida.size());

                for (Object obj2 : listaLida) {
                    if (obj2 instanceof Professor) {
                        Professor professor = (Professor) obj2;
                        listaRegentes.add(professor);
                        //System.out.println("Regentes: " + professor.toString());

                    } else {
                        System.out.println("Classe não correspondida: " + obj2.getClass().getName());
                    }
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
        }
    }

}
