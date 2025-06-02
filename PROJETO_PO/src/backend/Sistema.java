package backend;

import java.io.*;

public class Sistema implements Serializable {

    // Instâncias das listas e objetos relacionados ao sistema
    private final ListaCursos listaC = new ListaCursos();
    private final ListaProfessores listaP = new ListaProfessores();
    private final ListaSessoes listaS = new ListaSessoes();
    private final ListaAlunos listaA = new ListaAlunos();
    private final ListaUCs listaU = new ListaUCs();
    private final Lista_Sumarios listaSU = new Lista_Sumarios();
    private final ListaRegentes listaR = new ListaRegentes();
    private final ListaDiretores listaD = new ListaDiretores();

    // Métodos Getters para obter as instâncias das listas
    public ListaCursos getListaCurs() {
        return listaC;
    }

    public ListaProfessores getListaProf() {
        return listaP;
    }

    public ListaSessoes getListaSess() {
        return listaS;
    }

    public ListaAlunos getListaAlun() {
        return listaA;
    }

    public ListaUCs getListaUCs() {
        return listaU;
    }

    public Lista_Sumarios getListaSU() {
        return listaSU;
    }

    public ListaRegentes getListaRegentes() {
        return listaR;
    }

    public ListaDiretores getListaDiretores() {
        return listaD;
    }

    // Adiciona elementos ás listas, para que já tenham algo antes da abertura do programa.
    public void adicionar() {
        //CRIAR ALUNOS----------------------------------------------------------
        Aluno a = new Aluno("Pedro", "a123", "LEGSI");
        Aluno b = new Aluno("João", "a124", "LEGSI");
        Aluno c = new Aluno("Miguel", "a11", "MIEGSI");

        //ADICIONAR ALUNOS ÁS LISTAS--------------------------------------------
        if (!getListaAlun().verificar(a.getNumMecanog())) {
            getListaAlun().adicionarAluno(a);
        }

        if (!getListaAlun().verificar(b.getNumMecanog())) {
            getListaAlun().adicionarAluno(b);
        }

        if (!getListaAlun().verificar(c.getNumMecanog())) {
            getListaAlun().adicionarAluno(c);
        }

        //CRIAR CURSOS----------------------------------------------------------
        Curso legsi = new Curso("LEGSI2324", "LEGSI");
        Curso miegsi = new Curso("MIEGSI2324", "MIEGSI");

        //CRIAR UCs-------------------------------------------------------------
        UC po = new UC("PO", "PO", "LEGSI");
        UC pms = new UC("PMS", "PMS", "LEGSI");
        UC mestrado = new UC("mestrado", "mestrado", "MIEGSI");

        //ADICIONAR UCs ÁS LISTAS-----------------------------------------------
        if (!getListaUCs().verificar(po.getCodigo())) {
            getListaUCs().adicionarUC(po);
        }

        if (!getListaUCs().verificar(pms.getCodigo())) {
            getListaUCs().adicionarUC(pms);
        }

        if (!getListaUCs().verificar(mestrado.getCodigo())) {
            getListaUCs().adicionarUC(mestrado);
        }

        //ADICIONAR ALUNOS ÁS UCs-----------------------------------------------
        if (!po.getListaAlunos().verificar(a.getNumMecanog())) {
            po.getListaAlunos().adicionarAluno(a);
        }

        if (!po.getListaAlunos().verificar(b.getNumMecanog())) {
            po.getListaAlunos().adicionarAluno(b);
        }

        if (!pms.getListaAlunos().verificar(a.getNumMecanog())) {
            pms.getListaAlunos().adicionarAluno(a);
        }

        if (!mestrado.getListaAlunos().verificar(c.getNumMecanog())) {
            mestrado.getListaAlunos().adicionarAluno(c);
        }

        //ADICIONAR UCs AOS CURSOS----------------------------------------------
        if (!legsi.getListaUCs().verificar(po.getCodigo())) {
            legsi.getListaUCs().adicionarUC(po);
        }

        if (!legsi.getListaUCs().verificar(pms.getCodigo())) {
            legsi.getListaUCs().adicionarUC(pms);
        }

        if (!miegsi.getListaUCs().verificar(mestrado.getCodigo())) {
            miegsi.getListaUCs().adicionarUC(mestrado);
        }

        //ADICIONAR ALUNOS DAS UCs AOS CURSOS-----------------------------------
        ListaAlunos listaAlunosLEGSI = legsi.getListaAlunos();

        listaAlunosLEGSI.adicionarAlunosDeOutraLista(po.getListaAlunos());
        listaAlunosLEGSI.adicionarAlunosDeOutraLista(pms.getListaAlunos());

        ListaAlunos listaAlunosMIEGSI = miegsi.getListaAlunos();
        listaAlunosMIEGSI.adicionarAlunosDeOutraLista(mestrado.getListaAlunos());

        //ADICIONAR CURSOS ÁS LISTAS--------------------------------------------
        if (!getListaCurs().verificar(legsi.getCodCurso())) {
            getListaCurs().adicionarCurso(legsi);
        }

        if (!getListaCurs().verificar(miegsi.getCodCurso())) {
            getListaCurs().adicionarCurso(miegsi);
        }
    }
    
    // Método para gravar informações das listas em ficheiros
    public void guardarFicheiro() throws IOException {
        this.listaA.gravarFicheiro();
        this.listaC.gravarFicheiro();
        this.listaD.gravarFicheiro();
        this.listaP.gravarFicheiro();
        this.listaR.gravarFicheiro();
        this.listaS.gravarFicheiro();
        this.listaSU.gravarFicheiro();
        this.listaU.gravarFicheiro();
    }

    // Método para ler informações das listas a partir de ficheiros
    public void abrirFicheiro() throws IOException {
        this.listaA.lerFicheiro();
        this.listaC.lerFicheiro();
        this.listaD.lerFicheiro();
        this.listaP.lerFicheiro();
        this.listaR.lerFicheiro();
        this.listaS.lerFicheiro();
        this.listaSU.lerFicheiro();
        this.listaU.lerFicheiro();
    }

}
