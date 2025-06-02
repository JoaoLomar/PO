package backend;

import java.io.Serializable;

public class Admin extends Sessao implements Serializable{

    //Construtor
    public Admin(String usn, String pass) {
        super(usn, pass);
    }

    //Getters e Setters
    @Override
    public void setUsername(String user) {
        super.setUsername(user);
    }

    @Override
    public void setPassword(String passe) {
        super.setPassword(passe);
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    //Verifica se a Password corresponde á password do administrador
    public int verificarPassword(String pssw1, Admin a) {
        if (a.getPassword().equals(pssw1)) {
            return 0;
        }
        return 1;
    }
}
