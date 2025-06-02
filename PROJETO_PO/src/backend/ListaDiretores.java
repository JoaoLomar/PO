package backend;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ListaDiretores implements Serializable {

    List<Professor> listaDiretores;

    public ListaDiretores() {
        listaDiretores = new ArrayList<>();
    }

    public List<Professor> getListaDiretores() {
        return listaDiretores;
    }

    public void setListaDiretores(List<Professor> listaDiretores) {
        this.listaDiretores = listaDiretores;
    }

    public void adicionarDiretores(Professor d) {
        listaDiretores.add(d);
    }

    public boolean removerDiretores(Professor d) {
        boolean removido = listaDiretores.remove(d);
        return removido;
    }

    public boolean verificar(String s) {
        for (Professor d : listaDiretores) {
            if (d.getNumero().equals(s)) {
                return true;
            }
        }
        return false;
    }

    public boolean verificarDirecao(int cod) {
        for (Professor d : listaDiretores) {
            if (d.getCod_diretor() == cod) {
                System.out.println("\nOk.\n");
                return true;
            }
        }
        return false;
    }

    public Professor procurarDiretor(int cod) {
        for (Professor d : listaDiretores) {
            if (d.getCod_diretor() == cod) {
                return d;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Lista de Diretores de Cursos\n");
        for (Professor d : listaDiretores) {
            sb.append(d.toString()).append("\n");
        }
        return sb.toString();
    }

    public void gravarFicheiro() throws IOException {
        System.out.println("Tamanho da Lista de Diretores: " + listaDiretores.size());
        if (!listaDiretores.isEmpty()) {
            try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("ListaDiretores")))) {
                out.writeObject(listaDiretores);
            } catch (IOException ex) {

            }
        } else {
            System.out.println("Lista de Diretores está vazia.");
        }

    }

    public void lerFicheiro() throws IOException {
        listaDiretores.clear();
        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("ListaDiretores")))) {
            Object obj = in.readObject();
            if (obj instanceof List) {
                List<?> listaLida = (List<?>) obj;
                System.out.println("Tamanho da Lista dos Diretores: " + listaLida.size());

                for (Object obj2 : listaLida) {
                    if (obj2 instanceof Professor) {
                        Professor professor = (Professor) obj2;
                        listaDiretores.add(professor);
                       //System.out.println("Diretores: " + professor.toString());

                    } else {
                        System.out.println("Classe não correspondida " + obj2.getClass().getName());
                    }
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
        }
    }

}
