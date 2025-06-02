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

public class ListaProfessores implements Serializable {

    private final List<Professor> lp;

    public ListaProfessores() {
        lp = new ArrayList<>();
        lp.size();
    }

    public void adicionarProf(Professor p) {
        lp.add(p);

    }

    public boolean removerProf(String nmec) {
        for (Professor p : lp) {
            if (p.getNumero().equals(nmec)) {
                lp.remove(p);
                return true;
            }
        }
        return false;
    }

    public boolean verificar(String numMecanog) {
        for (Professor p : lp) {
            if (p.getNumero().equals(numMecanog)) {
                return true;
            }
        }
        return false;
    }

    public boolean verificarUsername(String user2) {
        for (Professor p : lp) {
            if (p.getUsername().equals(user2)) {
                System.out.print("Existe username");
                return true;
            }
        }
        return false;
    }

    public boolean verificarPassword(String passw2) {
        for (Professor p : lp) {
            if (p.getPassword().equals(passw2)) {
                return true;
            }
        }
        return false;
    }

    public Professor procurarProfessor(String nmec) {
        for (Professor p : lp) {
            if (p.getNumero().equals(nmec)) {
                return p;
            }
        }
        return null;
    }

    public int contarProfessores() {
        int cont = 0;
        for (Professor p : lp) {
            cont++;
        }
        return cont;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Lista de Professores\n");
        for (Professor professor : lp) {
            sb.append(professor.toString()).append("\n");
        }
        return sb.toString();
    }

    public void gravarFicheiro() throws IOException {
        System.out.println("Tamanho da Lista de Professores: " + lp.size());
        if (!lp.isEmpty()) {
            try (ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream("ListaProfessores")))) {
                out.writeObject(lp);
            } catch (IOException ex) {

            }
        } else {
            System.out.println("Lista de Professores está vazia.");
        }

    }

    public void lerFicheiro() throws IOException {
        lp.clear();
        try (ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream("ListaProfessores")))) {
            Object obj = in.readObject();
            if (obj instanceof List) {
                List<?> listaLida = (List<?>) obj;
                System.out.println("Tamanho da lista dos professores: " + listaLida.size());

                for (Object obj2 : listaLida) {
                    if (obj2 instanceof Professor) {
                        Professor professor = (Professor) obj2;
                        lp.add(professor);
                        //System.out.println("Professores: " + professor.toString());

                    } else {
                        System.out.println("Classe não correspondida: " + obj2.getClass().getName());
                    }
                }
            }
        } catch (IOException | ClassNotFoundException ex) {
        }
    }

}
