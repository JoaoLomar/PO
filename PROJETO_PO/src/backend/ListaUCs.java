package backend;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;

public class ListaUCs implements Serializable {

    List<UC> l;

    // Construtor - inicializa a lista de UCs como um ArrayList vazio.
    public ListaUCs() {
        l = new ArrayList<>();
        l.size();
    }

    // Adiciona uma UC à lista
    public void adicionarUC(UC u) {
        l.add(u);
    }

    // Remove uma UC da lista de UCs e retorna o resultado da remoção (verdadeiro ou falso).
    public boolean removerUC(UC u) {
        boolean removido = l.remove(u);
        return removido;
    }

    // Verifica se existe uma UC na lista com o código igual ao fornecido. 
    public boolean verificar(String s) {
        for (UC u : l) {
            if (u.getCodigo().equals(s)) {
                return true;
            }
        }
        return false;
    }

    // Procura e retorna uma UC com base no código fornecido.
    public UC procurarUC(String cod) {
        for (UC u : l) {
            if (u.getCodigo().equals(cod)) {
                return u;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Lista de UCs\n");
        for (UC uc : l) {
            sb.append(uc.toString()).append("\n");
        }
        return sb.toString();
    }

    // Grava a lista de UCs num ficheiro.
    public void gravarFicheiro() throws IOException {
        System.out.println("Tamanho da Lista de UCs: " + l.size());
        if (!l.isEmpty()) {
            try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("ListaUCs")))) {
                out.writeObject(l);
            } catch (IOException ex) {
            }
        } else { // Caso esteja vazia.
            System.out.println("Lista de UCs está vazia.");
        }

    }

    // Lê a lista de UCs do ficheiro.
    public void lerFicheiro() throws IOException {
        l.clear();
        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("ListaUCs")))) {
            Object obj = in.readObject();
            if (obj instanceof List) {
                List<?> listaLida = (List<?>) obj; // A lista pode ler qualquer tipo de objetos.
                System.out.println("Tamanho da Lista das UCs: " + listaLida.size());
                for (Object obj2 : listaLida) {
                    if (obj2 instanceof UC) {
                        UC uc = (UC) obj2;
                        l.add(uc);
                        //System.out.println("UCs: " + uc.toString());

                    } else {
                        System.out.println("Classe não correspondida: " + obj2.getClass().getName());
                    }
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
        }
    }
}
