package backend;

import java.io.*;
import java.util.*;

public class ListaSessoes implements Serializable {

    private ListaProfessores lp = new ListaProfessores();
    private final List<Sessao> ls;

    // Construtor que inicializa a lista de sessões como um ArrayList.
    public ListaSessoes() {
        ls = new ArrayList<>();
    }

    // Método para obter a lista de sessões.
    public List<Sessao> getListaSessao() {
        return ls;
    }

    // Método para definir a lista de professores associados a sessões.
    public void setLp(ListaProfessores ls) {
        this.lp = ls;
    }

    // Método para obter a lista de professores associados a sessões.
    public ListaProfessores getLp() {
        return lp;
    }

    // Adiciona uma sessão à lista de sessões.
    public void adicionarSessao(Sessao s) {
        ls.add(s);
    }

    // Remove uma sessão da lista de sessões e retorna o resultado da remoção (verdadeiro ou falso).
    public boolean removerSessao(Sessao s) {
        boolean removido = ls.remove(s);
        return removido;
    }

    // Verifica se uma sessão com o nome de utilizador existe na lista de sessões.
    public boolean verificar(String u) {
        for (Object obj : ls) {
            if (obj instanceof Sessao) {
                Sessao s = (Sessao) obj;
                if (s.getUsername().equals(u)) {
                    return true;
                }
            }
        }
        return false;
    }

    // Procura e retorna uma sessão com o nome de utilizador.
    public Sessao procurarSessao(String use) {
        for (Sessao s : ls) {
            if (s.getUsername().equals(use)) {
                return s;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Lista de Sessões\n");
        for (Sessao s : ls) {
            sb.append(s.toString()).append("\n");
        }
        return sb.toString();
    }

    // Grava a lista de sessoes num ficheiro.
    public void gravarFicheiro() throws IOException {
        System.out.println("Tamanho da Lista de Sessões: " + ls.size());
        if (!ls.isEmpty()) {
            try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("ListaSessoes")))) {
                out.writeObject(ls);
            } catch (IOException ex) {

            }
        } else { // Caso a lista esteja vazia.
            System.out.println("Lista de Sessões está vazia.");
        }

    }

    // Lê a lista de Sessoes do ficheiro.
    public void lerFicheiro() throws IOException {
        ls.clear();
        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("ListaSessoes")))) {
            Object obj = in.readObject();
            if (obj instanceof List) {
                List<?> listaLida = (List<?>) obj; // A lista pode ler qualquer tipo de objetos.
                System.out.println("Tamanho da Lista das Sessões: " + listaLida.size());
                for (Object obj2 : listaLida) {
                    if (obj2 instanceof Sessao) {
                        Sessao sessao = (Sessao) obj2;
                        ls.add(sessao);
                        //System.out.println("Sessões: " + sessao.toString());

                    } else {
                        System.out.println("Classe não correspondida: " + obj2.getClass().getName());
                    }
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
        }
    }

}
