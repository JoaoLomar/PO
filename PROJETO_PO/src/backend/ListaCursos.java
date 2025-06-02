package backend;

import java.io.*;

import java.util.ArrayList;
import java.util.List;

public class ListaCursos implements Serializable {

    List<Curso> lc;

    public ListaCursos() {
        this.lc = new ArrayList<>();
        lc.size();
    }

    public void adicionarCurso(Curso c) {
        lc.add(c);
    }

    public boolean removerCurso(Curso c) {
        boolean removido = lc.remove(c);
        return removido;
    }

    public List<Curso> getLc() {
        return lc;
    }

    public void setLc(ArrayList<Curso> lc) {
        this.lc = lc;
        lc.size();
    }

    public Curso getCurso() {
        for (Curso c : lc) {
            return c;
        }
        return null;
    }

    public boolean verificar(String codCurso) {
        for (Curso curso : lc) {
            if (curso.getCodCurso().equals(codCurso)) {
                return true;
            }
        }
        return false;
    }

    public Curso procurarCurso(String codCurso) {
        for (Curso c : lc) {
            if (c.getCodCurso().equals(codCurso)) {
                return c;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Lista de Cursos:\n");
        for (Curso curso : lc) {
            sb.append(curso.toString()).append("\n");
        }
        return sb.toString();
    }

    public void gravarFicheiro() throws IOException {
        System.out.println("Tamanho da Lista dos Cursos: " + lc.size());
        if (!lc.isEmpty()) {
            try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("ListaCursos")))) {
                out.writeObject(lc);
            } catch (IOException ex) {

            }
        } else {
            System.out.println("Lista de Cursos está vazia.");
        }

    }

    public void lerFicheiro() throws IOException {
        lc.clear();
        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("ListaCursos")))) {
            Object obj = in.readObject();
            if (obj instanceof List) {
                List<?> listaLida = (List<?>) obj;
                System.out.println("Tamanho da Lista dos Cursos: " + listaLida.size());

                for (Object obj2 : listaLida) {
                    if (obj2 instanceof Curso) {
                        Curso curso = (Curso) obj2;
                        lc.add(curso);
                       //System.out.println("Cursos: " + curso.toString());

                    } else {
                        System.out.println("Classe não correspondida: " + obj2.getClass().getName());
                    }
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
        }
    }

}
