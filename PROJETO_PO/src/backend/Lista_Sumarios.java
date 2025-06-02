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

public class Lista_Sumarios implements Serializable {

    List<Sumario> lsu;

    // Construtor - inicializa a lista de Sumários como um ArrayList vazio.
    public Lista_Sumarios() {
        lsu = new ArrayList<>();

    }

    // Retorna a lista de Sumários.
    public List<Sumario> getLsu() {
        return lsu;
    }

    // Adiciona um Sumário à lista.
    public void adicionarSumario(Sumario su) {
        lsu.add(su);
    }

    // Remove um Sumario da lista de Sumarios e retorna o resultado da remoção (verdadeiro ou falso).
    public boolean removerSumario(Sumario su) {
        boolean removido = lsu.remove(su);
        return removido;
    }

    // Verifica se existe um Sumário com o número de lição fornecido na lista.
    public boolean verificar(int licao) {
        for (Sumario s : lsu) {
            if (s.getNum_licao() == licao) {
                return true;
            }
        }
        return false;
    }

    // Verifica se existe um Sumário com o tipo fornecido na lista.
    public boolean verificarTipo(String tipo) {
        for (Sumario s : lsu) {
            if (s.getTipo().equals(tipo)) {
                return true;
            }
        }
        return false;
    }

    // Procura e retorna um Sumário com base no número da lição.
    public Sumario procurarSumario(int licao) {
        for (Sumario s : lsu) {
            if (s.getNum_licao() == licao) {
                return s;
            }
        }
        return null;
    }

    // Procura e retorna um Sumário com base no código da UC
    public Sumario procurarSumarioUC(String cod) {
        for (Sumario s : lsu) {
            if (s.getUc().equals(cod)) {
                return s;
            }
        }
        return null;
    }

    // Procura e retorna um Sumário com base no tipo
    public Sumario procurarSumarioTipo(String tipo) {
        for (Sumario s : lsu) {
            if (s.getTipo().equals(tipo)) {
                return s;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Lista de Sumarios\n");
        for (Sumario su : lsu) {
            sb.append(su.toString()).append("\n");
        }
        return sb.toString();
    }

    // Grava a lista de Sumarios num ficheiro.
    public void gravarFicheiro() throws IOException {
        System.out.println("Tamanho da Lista de Sumários: " + lsu.size());
        if (!lsu.isEmpty()) {
            try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("ListaSumarios")))) {
                out.writeObject(lsu);
            } catch (IOException ex) {

            }
        } else { // Caso esteja vazia.
            System.out.println("A Lista de Sumários está vazia.");
        }

    }

    // Lê a lista de Sumários do Ficheiro.
    public void lerFicheiro() throws IOException {
        lsu.clear();
        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("ListaSumarios")))) {
            Object obj = in.readObject();
            if (obj instanceof List) {
                List<?> listaLida = (List<?>) obj; //A lista pode ler qualquer tipo de objetos.
                System.out.println("Tamanho da Lista dos Sumários: " + listaLida.size());
                for (Object obj2 : listaLida) {
                    if (obj2 instanceof Sumario) {
                        Sumario sumario = (Sumario) obj2;
                        lsu.add(sumario);
                        //System.out.println("Sumarios: " + sumario.toString());

                    } else {
                        System.out.println("Classe não correspondida: " + obj2.getClass().getName());
                    }
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
        }
    }
}
