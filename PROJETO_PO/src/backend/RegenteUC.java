package backend;

import java.io.Serializable;
import java.time.LocalDate;

public class RegenteUC extends Professor implements Serializable {

    // Construtor da classe RegenteUC que é subclasse de Professor.
    public RegenteUC(String nome_prof, String num_mecanog, LocalDate data_inicio, ListaUCs lista_cod_UC, ListaUCs regencias, ListaCursos Direcoes, ListaCursos listaCursos, int cod_regencia, int cod_diretor, String usn, String pass) {
        super(nome_prof, num_mecanog, data_inicio, lista_cod_UC, regencias, Direcoes, listaCursos, cod_regencia, cod_diretor, usn, pass);
    }

    @Override
    public String getUsername() {
        return super.getUsername();
    }

    @Override
    public String getPassword() {
        return super.getPassword();
    }

    @Override
    public void setPassword(String passe) {
        super.setPassword(passe);
    }

    @Override
    public void setUsername(String user) {
        super.setUsername(user);
    }

}
