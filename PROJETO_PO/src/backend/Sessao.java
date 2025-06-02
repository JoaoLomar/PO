package backend;

import java.io.Serializable;

public class Sessao implements Serializable {

    // Atributos da classe Sessao
    private String username;
    private String password;

    // Construtor da classe Sessao
    public Sessao(String usn, String pass) {
        username = usn;
        password = pass;
    }

    // Métodos Getters e Setters 
    public void setUsername(String usn) {
        username = usn;
    }

    public void setPassword(String pass) {
        password = pass;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return "Sessão" + "username=" + username + ", password=" + password;
    }

    // Método de autenticação para verificar se a sessão corresponde a de um admin (tem de começar por "adm")
    public boolean autenticar(Sessao sessãoligada) {
        if (sessãoligada.getUsername().startsWith("adm")) {
            return true;
        }
        return false;
    }

}
