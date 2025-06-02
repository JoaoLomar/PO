package Frontend;

import backend.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.io.*;

import java.util.Scanner;

public class MenuPrincipal {

    private final Consola consola = new Consola();
    private final Sistema sistema = new Sistema();
    private Sessao sessao;

    public static void main(String[] args) throws IOException {
        MenuPrincipal menu = new MenuPrincipal(); // Cria um novo Menu
        menu.sistema.abrirFicheiro(); // Abre o ficheiro com base no método implementado na classe Sistema
        menu.sistema.adicionar(); // Adiciona os dados previamente criados ás listas respectivas.
        menu.mostrar(); // Chama o método mostrar
    }

    //Método para mostrar o Menu Principal do Programa, onde o utilizador pode escolher o que pretende fazer
    public void mostrar() throws IOException {
        Scanner scanner = new Scanner(System.in);
        int escolha;

        do {
            consola.escrever("\n -----Menu Principal-----");
            consola.escrever("1. Login");
            consola.escrever("2. Registar");
            consola.escrever("0. Sair");

            escolha = consola.lerInteiro("Escolha a opção desejada: ");

            switch (escolha) {
                case 1:
                    loginMenu();
                    break;
                case 2:
                    registarMenu();
                    break;
                case 0:
                    sistema.guardarFicheiro();
                    consola.escrever("A sair do sistema.");
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    consola.escreverErro("Opção inválida. Escolha novamente.");
            }
        } while (escolha != 0);
    }

    // Solicita os dados para efetuar o menu, e de acordo com a validação, abre o menu correspondente.
    private void loginMenu() {
        String user = consola.lerString("Introduza o nome de utilizador: ");
        String passw = consola.lerString("Introduza a password: ");
        this.sessao = new Sessao(user, passw);

        consola.escrever("1. Administrador");
        consola.escrever("2. Professor");
        consola.escrever("0. Sair");
        int funcao = consola.lerInteiro("Escolha a sua Função: ");

        switch (funcao) {
            case 1:
                if (validarAdmin(user, passw)) {
                    mostrarAdminMenu();
                } else {
                    consola.escreverErro("Início de Sessão inválido.");
                }
                break;
            case 2:
                if (validarProfessor(user, passw)) {
                    mostrarProfessorMenu();
                } else {
                    consola.escreverErro("Início de Sessão inválido.");
                }
                break;
            case 0:
                consola.escrever("A sair do sistema.");
                break;
            default:
                consola.escreverErro("Função não reconhecida.");
        }
    }

    // Solicita os dados necessários para o registo da Sessão e, com base na escolha, valida-os
    private void registarMenu() {
        String user = consola.lerString("Introduza o nome de utilizador (caso seja administrador, deve começar por 'adm'): ");
        String passw = consola.lerString("Introduza a password: ");
        this.sessao = new Sessao(user, passw);

        consola.escrever("1. Administrador.");
        consola.escrever("2. Professor.");
        consola.escrever("0. Sair");
        int funcao = consola.lerInteiro("Escolha a sua Função: ");

        switch (funcao) {
            case 1:
                if (validarNovoAdmin(sessao)) {
                    consola.escrever("Novo Registo efetuado com sucesso.");
                } else {
                    consola.escreverErro("Registo inválido.");
                }
                break;
            case 2:
                if (validarNovoProfessor(sessao)) {
                    consola.escrever("Novo Registo efetuado com sucesso.");
                } else {
                    consola.escreverErro("Registo inválido.");
                }
                break;
            case 0:
                consola.escrever("A sair.");
                break;
            default:
                consola.escreverErro("Função não reconhecida.");
        }
    }

    private boolean validarNovoAdmin(Sessao s) {
        if (!sistema.getListaSess().verificar(s.getUsername())) { //Verifica se já existe sessão com esse nome de utilizador
            if (s.autenticar(s)) { // caso se comprove que é administrador
                Admin a = new Admin(s.getUsername(), s.getPassword()); // cria um novo administrador
                sistema.getListaSess().adicionarSessao(s); // adiciona á lista de sessões
                return true;
            } else {
                consola.escreverErro("Não pode iniciar sessao como administrador.");
            }
        } else {
            consola.escreverErro("Já existe esse nome de utilizador.");
        }
        return false;
    }

    private boolean validarAdmin(String user, String passw) {
        if (sistema.getListaSess().verificar(user)) { //Verifica se o nome de utilizador e a palavra passe corresponde,.
            Sessao s = sistema.getListaSess().procurarSessao(user);
            if (s.getPassword().equals(passw)) {
                consola.escrever("Password Correta.");
                if (s.autenticar(s)) {
                    consola.escrever("É administrador.");
                    return true;
                } else {
                    consola.escreverErro("Não é administrador.");
                }
            } else {
                consola.escreverErro("Password Incorreta.");
            }
        } else {
            consola.escreverErro("Nome de Utilizador não encontrado.");
        }
        return false;
    }

    //Menu do Administrador.
    public void mostrarAdminMenu() {
        int escolha2;
        do {
            System.out.println("\n -----Menu Administrador-----");
            System.out.println("1.Adicionar Professores");
            System.out.println("2.Apagar Professor");
            System.out.println("3.Alterar informaçoes sobre o professor");
            System.out.println("4.Registar Curso");
            System.out.println("5.Alterar informaçao sobre o curso");
            System.out.println("6.Registar UC");
            System.out.println("7.Alterar informaçao sobre a UC");
            System.out.println("8.Listar Cursos");
            System.out.println("9.Listar Unidades Curriculares");
            System.out.println("10.Listar Alunos");
            System.out.println("11.Listar Professor");
            System.out.println("12.Atribuir direçao de curso a professor");
            System.out.println("13.Atribuir regencia da Unidade Curricular a professor");
            System.out.println("0.Sair");

            escolha2 = consola.lerInteiro("Escolha a opçao desejada: ");
            switch (escolha2) {
                case 1:
                    adicionarProfessorAdmin();
                    break;
                case 2:
                    apagarProfessor();
                    break;
                case 3:
                    alterarInfoProf();
                    break;
                case 4:
                    registarInfoCurso();
                    break;
                case 5:
                    alterarInfoCurso();
                    break;
                case 6:
                    registarInfoUC();
                    break;
                case 7:
                    alterarInfoUC();
                    break;
                case 8:
                    listarCurso();
                    break;
                case 9:
                    listarUC();
                    break;
                case 10:
                    listarAlunos();
                    break;
                case 11:
                    listarProf();
                    break;
                case 12:
                    atribuirDirecaoProf();
                    break;
                case 13:
                    atribuirRegenciaUCProf();
                    break;
                case 0:
                    consola.escrever("A sair.");
                    break;
                default:
                    consola.escrever("Opçao invalida. Escolha novamente.");
            }
        } while (escolha2 != 0);
    }

//FUNÇÕES ADMIN ----------------------------------------------------------------

    private void adicionarProfessorAdmin() {
        String nome_prof = consola.lerString("Introduza o nome: ");
        String num_mecanog = consola.lerString("Intoduza o numero mecanográfico: ");

        if (sistema.getListaProf().verificar(num_mecanog)) { //Verifica se já é um Professor existente
            consola.escreverErro("Já existe professor com esse número mecanográfico.");
            return;
        }

        LocalDate data_inicio = consola.lerData("Introduza a data de inicio: ");

        int cod_Regencia = 0;
        int cod_Direcao = 0;

        consola.escrever("\nUsar as Opções de Atribuir Direção de Curso e Regências de UCs para o Propósito.\n");

        String username = consola.lerString("Introduza o nome de utilizador :");

        if (sistema.getListaSess().verificar(username)) { //Verifica se já é um nome de utilizador existente
            consola.escreverErro("Já existe professor com esse username.");
            return;
        }

        String password = consola.lerString("Introduza a password:");

        ListaUCs servico = new ListaUCs();
        ListaUCs regencia = new ListaUCs();
        ListaCursos cursos = new ListaCursos();
        ListaCursos direcao = new ListaCursos();

        //Colocar UCs no servico docente.
        int adicionarMaisUCs = 0;
        while (adicionarMaisUCs == 0) {
            String cod_UC = consola.lerString("Introduza o codigo da Unidade Curricular: ");

            if (sistema.getListaUCs().verificar(cod_UC)) {
                UC u = sistema.getListaUCs().procurarUC(cod_UC);
                if (!servico.verificar(cod_UC)) {
                    servico.adicionarUC(u);
                }
            } else {
                consola.escreverErro("Unidade Curricular não encontrada");
            }
            adicionarMaisUCs = consola.lerInteiro("Deseja adicionar outra Unidade Curricular? (0-Sim, 1- Não): ");
        }

        //Colocar Cursos nos Cursos em que o professor dá aulas.
        int adicionarMaisCursos = 0;
        while (adicionarMaisCursos == 0) {
            String cod_curso = consola.lerString("Introduza o codigo dos Cursos que o professor leciona: ");

            if (sistema.getListaCurs().verificar(cod_curso)) {
                Curso c = sistema.getListaCurs().procurarCurso(cod_curso);
                if (!cursos.verificar(cod_curso)) {
                    cursos.adicionarCurso(c);
                }
            } else {
                consola.escreverErro("Curso não encontrado");
            }
            adicionarMaisCursos = consola.lerInteiro("Deseja adicionar outro Curso? (0-Sim, 1-Não):");
        }

        //Adicionar o professor ás listas de Professores e de sessões
        if (!sistema.getListaProf().verificar(num_mecanog)) {
            Professor p = new Professor(nome_prof, num_mecanog, data_inicio, servico, regencia, direcao, cursos, cod_Regencia, cod_Direcao, username, password);
            sistema.getListaProf().adicionarProf(p);

            if (!sistema.getListaSess().verificar(username)) {
                Sessao s = new Sessao(username, password);
                sistema.getListaSess().adicionarSessao(s);

                for (Curso c : cursos.getLc()) {
                    c.getListaP().adicionarProf(p);
                }
            } else {
                consola.escreverErro("Sessão já existe.");
            }
        } else {
            consola.escreverErro("Professor já existe.");
        }
    }

    //Remover Professor
    private void apagarProfessor() {
        String nmec = consola.lerString("Escreva o nº mecanográfico do Professor que quer eliminar:");
        if (sistema.getListaProf().verificar(nmec)) {
            Professor p = sistema.getListaProf().procurarProfessor(nmec);
            sistema.getListaProf().removerProf(nmec);
            Sessao s = sistema.getListaSess().procurarSessao(p.getUsername());
            sistema.getListaSess().removerSessao(s);
            consola.escrever("Apagado");
        } else {
            consola.escreverErro("Professor não existe.");
        }
    }

    
    private void alterarInfoProf() {
        String nmec = consola.lerString("Introduza o nº mecanográfico do Professor que quer alterar:");
        String novo_nome_prof = consola.lerString("Introduza o nome: ");
        String novo_num_mecanog = consola.lerString("Intoduza o numero mecanográfico: ");

        if (sistema.getListaProf().verificar(novo_num_mecanog) && !nmec.equals(novo_num_mecanog)) {
            consola.escrever("Já existe professor com esse número mecanográfico.");
            return;
        }

        LocalDate nova_data_inicio = consola.lerData("introduza data de inicio: ");

        consola.escrever("Usar as Opções de Atribuir Direção de Curso e Regências de UCs para o Propósito.");

        if (sistema.getListaProf().verificar(nmec)) {
            Professor p = sistema.getListaProf().procurarProfessor(nmec);
            ListaUCs servico = p.getLista_UCs();
            ListaCursos cursos = p.getListaCursos();
            p.setNome(novo_nome_prof);
            p.setNumero(novo_num_mecanog);
            p.setDataInicio(nova_data_inicio);

            int adicionarMaisUCs = 0;
            while (adicionarMaisUCs == 0) {
                String cod_UC = consola.lerString("Introduza o codigo da Unidade Curricular: ");

                if (sistema.getListaUCs().verificar(cod_UC)) {
                    UC u = sistema.getListaUCs().procurarUC(cod_UC);
                    if (!servico.verificar(u.getCodigo())) {
                        servico.adicionarUC(u);
                    }
                } else {
                    consola.escreverErro("Unidade Curricular não encontrada");
                }
                adicionarMaisUCs = consola.lerInteiro("Deseja adicionar outra Unidade Curricular? (0-Sim, 1- Não): ");
            }

            int adicionarMaisCursos = 0;
            while (adicionarMaisCursos == 0) {
                String cod_curso = consola.lerString("Introduza o codigo dos Cursos que o professor leciona: ");

                if (sistema.getListaCurs().verificar(cod_curso)) {
                    Curso c = sistema.getListaCurs().procurarCurso(cod_curso);
                    if (!cursos.verificar(cod_curso)) {
                        cursos.adicionarCurso(c);
                    }
                } else {
                    consola.escreverErro("Curso não encontrado");
                }
                adicionarMaisCursos = consola.lerInteiro("Deseja adicionar outro Curso? (0-Sim, 1-Não):");
            }

            p.setLista_UCs(servico);
            p.setListaCursos(cursos);

            for (Curso c : cursos.getLc()) {
                if (!c.getListaP().verificar(novo_num_mecanog)) {
                    c.getListaP().adicionarProf(p);
                } else {
                    consola.escreverErro("Numero mecanográfico já existe no curso " + c.getNomeCurso());
                }
            }

        } else {
            consola.escreverErro("Professor não existe.");
        }
    }

    private void alterarInfoCurso() {
        String n_curso = consola.lerString("Introduza o código do curso que quer alterar:");

        if (sistema.getListaCurs().verificar(n_curso)) {
            Curso c = sistema.getListaCurs().procurarCurso(n_curso);

            String novo_nome_curso = consola.lerString("Introduza o novo nome do curso: ");
            String novo_cod_curso = consola.lerString("Intoduza o novo código do curso: ");
            if (!sistema.getListaCurs().verificar(novo_cod_curso)) {
                c.setCodCurso(novo_cod_curso);
                c.setNomeCurso(novo_nome_curso);

            } else {
                consola.escreverErro("Curso com esse código já existe.");
            }

        } else {
            consola.escreverErro("Curso não existe");
        }
    }

    private void registarInfoCurso() {
        String nCurso = consola.lerString("Escreva o código do novo curso que quer registar:");

        if (!sistema.getListaCurs().verificar(nCurso)) {
            String novoNome = consola.lerString("Escreva o nome do curso");
            Curso c = new Curso(nCurso, novoNome);
            sistema.getListaCurs().adicionarCurso(c);

        } else {
            consola.escreverErro("Curso já existe");
        }
    }

    private void registarInfoUC() {
        String codCurso = consola.lerString("Escreva o código do curso a que a UC pertence:");

        if (sistema.getListaCurs().verificar(codCurso)) {
            Curso c = sistema.getListaCurs().procurarCurso(codCurso);
            String codUC = consola.lerString("Escreva o código da nova UC que quer registar:");

            if (!sistema.getListaUCs().verificar(codUC) || !c.getListaUCs().verificar(codUC)) {

                String novoNome = consola.lerString("Escreva o nome da UC:");
                UC u = new UC(codUC, novoNome, codCurso);
                sistema.getListaUCs().adicionarUC(u);
                c.getListaUCs().adicionarUC(u);
                c.getListaAlunos().adicionarAlunosDeOutraLista(u.getListaAlunos());

            } else {
                consola.escreverErro("Já existe UC.");
            }
        } else {
            consola.escreverErro("Curso não existe.");
        }
    }

    private void alterarInfoUC() {
        String cod_UC = consola.lerString("Introduza o código da UC que quer alterar:");

        if (sistema.getListaUCs().verificar(cod_UC)) {
            UC uc = sistema.getListaUCs().procurarUC(cod_UC);

            String novo_nome_uc = consola.lerString("Introduza o novo nome da UC: ");
            String novo_cod_curso = consola.lerString("Intoduza o novo código do curso a que a UC pertence: ");

            if (sistema.getListaCurs().verificar(novo_cod_curso)) {
                Curso cursoAntigo = sistema.getListaCurs().procurarCurso(uc.getCod_Curso());
                cursoAntigo.getListaUCs().removerUC(uc);

                Curso cursoNovo = sistema.getListaCurs().procurarCurso(novo_cod_curso);
                cursoNovo.getListaUCs().adicionarUC(uc);

                uc.setCod_Curso(novo_cod_curso);
                uc.setCodigo(cod_UC);
                uc.setDesignacao(novo_nome_uc);

            } else {
                consola.escreverErro("Curso com esse código não existe.");
            }

        } else {
            consola.escreverErro("UC não existe");
        }
    }

    private void listarCurso() {
        consola.escrever("Cursos:\n");
        consola.escrever(sistema.getListaCurs().toString());
    }

    private void listarAlunos() {
        consola.escrever("Alunos:\n");
        consola.escrever(sistema.getListaAlun().toString());
    }

    private void listarUC() {
        consola.escrever("UCs:\n");
        consola.escrever(sistema.getListaUCs().toString());
    }

    private void listarProf() {
        consola.escrever("Professores:\n");
        consola.escrever(sistema.getListaProf().toString());
    }

    private void atribuirRegenciaUCProf() {
        String nmec = consola.lerString("Escreva o número mecanográfico do Professor que quer elevar a Regente.");

        if (sistema.getListaProf().verificar(nmec)) {
            Professor p = sistema.getListaProf().procurarProfessor(nmec);
            String cod_UC = consola.lerString("Escreva o nome da UC que vai ter o Professor como Regente.");

            if (sistema.getListaUCs().verificar(cod_UC)) {
                UC uc = sistema.getListaUCs().procurarUC(cod_UC);

                if (!sistema.getListaRegentes().verificar(nmec)) {
                    int cod_Regencia = consola.lerInteiro("Escreva o novo código de Regência (diferente de 0):");

                    if (cod_Regencia == 0 || sistema.getListaRegentes().verificarRegencia(cod_Regencia) || p.getRegencias().verificar(cod_UC)) {
                        consola.escrever("Impossível de Atribuir.");
                    } else {
                        p.setCod_regencia(cod_Regencia);
                        p.getRegencias().adicionarUC(uc);
                        sistema.getListaRegentes().adicionarRegentes(p);
                        consola.escrever("Regência atribuída.");
                    }
                } else {
                    consola.escreverErro("Já é Regente.");
                }
            } else {
                consola.escreverErro("UC não encontrada.");
            }
        } else {
            consola.escreverErro("Professor não encontrado.");
        }

    }

    private void atribuirDirecaoProf() {
        String nmec = consola.lerString("Escreva o número mecanográfico do Professor que quer promover a Diretor de Curso:");

        if (sistema.getListaProf().verificar(nmec)) {
            Professor p = sistema.getListaProf().procurarProfessor(nmec);
            String cod_Curso = consola.lerString("Escreva o código do Curso que vai ter o Professor como Diretor.");

            if (sistema.getListaCurs().verificar(cod_Curso)) {
                Curso c = sistema.getListaCurs().procurarCurso(cod_Curso);

                if (!sistema.getListaDiretores().verificar(nmec)) {
                    int cod_Direcao = consola.lerInteiro("Escreva o novo código de Direção (diferente de 0):");

                    if (cod_Direcao == 0 || sistema.getListaDiretores().verificarDirecao(cod_Direcao) || p.getDirecoes().verificar(cod_Curso)) {
                        consola.escrever("Impossível de Atribuir.");
                    } else {
                        p.setCod_diretor(cod_Direcao);
                        p.getDirecoes().adicionarCurso(c);
                        sistema.getListaDiretores().adicionarDiretores(p);
                        consola.escrever("Direção atribuída.");
                    }
                } else {
                    consola.escreverErro("Já é Diretor de Curso");
                }
            } else {
                consola.escreverErro("Curso não existe.");
            }
        } else {
            consola.escreverErro("Professor não encontrado.");
        }
    }
//-----------------------------------------------------------------FUNÇÕES ADMIN

    private boolean validarNovoProfessor(Sessao s) {
        if (!sistema.getListaSess().verificar(s.getUsername())) {
            if (!sistema.getListaProf().verificarUsername(s.getUsername())) {
                adicionarProfessor(s);
                return true;
            }
        } else {
            consola.escreverErro("Nome de Utilizador já existe.");
        }
        return false;
    }

    private boolean validarProfessor(String user, String passw) {
        if (sistema.getListaSess().verificar(user)) {
            if (sistema.getListaProf().verificarUsername(user)) {
                if (sistema.getListaProf().verificarPassword(passw)) {
                    return true;
                } else {
                    consola.escrever("Password incorreta.");
                }
            } else {
                consola.escreverErro("Nome de Utilizador não corresponde.");
            }
        } else {
            consola.escreverErro("Nome de Utilizador não corresponde.");
        }
        return false;
    }

    private void adicionarProfessor(Sessao s) {

        String nome_prof = consola.lerString("Introduza o nome: ");
        String num_mecanog = consola.lerString("Intoduza o numero mecanográfico: ");

        // Verificar se já existe um professor com o mesmo número mecanográfico
        if (sistema.getListaProf().verificar(num_mecanog)) {
            consola.escreverErro("Já existe professor com esse número mecanográfico.");
            return;
        }

        LocalDate data_inicio = consola.lerData("Introduza a data de inicio:");

        // Inicializar variáveis para os códigos de direção e regência
        int cod_Regencia = 0;
        int cod_Direcao = 0;

        consola.escrever("\nUsar as Opções de Atribuir Direção de Curso e Regências de UCs para o Propósito.\n");

        String username = s.getUsername();

        // Verificar se já existe um professor com o mesmo nome de utilizador
        if (sistema.getListaSess().verificar(username)) {
            consola.escreverErro("Já existe professor com esse nome de utilizador.");
            return;
        }

        String password = s.getPassword();

        // Inicializar listas
        ListaUCs servico = new ListaUCs();
        ListaUCs regencia = new ListaUCs();
        ListaCursos cursos = new ListaCursos();
        ListaCursos direcao = new ListaCursos();

        int adicionarMaisUCs = 0;
        while (adicionarMaisUCs == 0) {
            String cod_UC = consola.lerString("Introduza o código da Unidade Curricular: ");

            // Verificar se a uc existe
            if (sistema.getListaUCs().verificar(cod_UC)) {
                UC u = sistema.getListaUCs().procurarUC(cod_UC);
                if (!servico.verificar(cod_UC)) {
                    servico.adicionarUC(u);
                }
            } else {
                consola.escreverErro("Unidade Curricular não encontrada");
            }
            adicionarMaisUCs = consola.lerInteiro("Deseja adicionar outra Unidade Curricular? (0-Sim, 1-Não): ");
        }

        int adicionarMaisCursos = 0;
        while (adicionarMaisCursos == 0) {
            String cod_curso = consola.lerString("Introduza o código dos Cursos que o professor leciona: ");

            // Verificar se o curso existe
            if (sistema.getListaCurs().verificar(cod_curso)) {
                Curso c = sistema.getListaCurs().procurarCurso(cod_curso);
                if (!cursos.verificar(cod_curso)) {
                    cursos.adicionarCurso(c);
                }
            } else {
                consola.escrever("Curso não encontrado");
            }
            adicionarMaisCursos = consola.lerInteiro("Deseja adicionar outro Curso? (0-Sim, 1-Não):");
        }

        // Verificar se o professor com o mesmo número mecanográfico existe novamente (por segurança)
        if (!sistema.getListaProf().verificar(num_mecanog)) {
            // Criar um novo professor
            Professor p = new Professor(nome_prof, num_mecanog, data_inicio, servico, regencia, direcao, cursos, cod_Regencia, cod_Direcao, username, password);

            // Adicionar o professor à lista de professores
            sistema.getListaProf().adicionarProf(p);

            // Verificar se o nome de utilizador existe na lista de sessões
            if (!sistema.getListaSess().verificar(username)) {
                // Adicionar o professor aos cursos
                for (Curso c : cursos.getLc()) {
                    c.getListaP().adicionarProf(p);
                }

                // Adicionar a sessão apenas se o professor for adicionado com sucesso
                sistema.getListaSess().adicionarSessao(s);

            } else {
                consola.escreverErro("Sessão já existe.");
            }
        } else {
            consola.escreverErro("Professor já existe.");
        }
    }

    private void mostrarProfessorMenu() {
        int escolha1;
        do {
            consola.escrever("\n -----Menu Professor-----");
            consola.escrever("1.Criar Sumário");
            consola.escrever("2.Consultar lista de Sumários por UC");
            consola.escrever("3.Consultar lista de Sumários por tipo de Aula");
            consola.escrever("4.Consultar Serviço de Docente");
            consola.escrever("\n----Outras Opções:----");
            consola.escrever("5.Regente da UC");
            consola.escrever("6.Diretor de Curso");
            consola.escrever("0. Sair");

            escolha1 = consola.lerInteiro("Escolha a opçao desejada: ");

            switch (escolha1) {
                case 1:
                    criarSumario();
                    break;
                case 2:
                    listarSumarioUC();
                    break;
                case 3:
                    listarSumarioTipo();
                    break;
                case 4:
                    consultarServicoDocente();
                    break;
                case 5:
                    String user3 = consola.lerString("Introduza o nome de utilizador: ");
                    String passw3 = consola.lerString("Introduza a password: ");
                    if (validarRegenteUC(user3, passw3)) {
                        mostrarRegenteMenu();
                    } else {
                        consola.escreverErro("Login como Regente inválido.");
                    }
                    break;
                case 6:
                    String user4 = consola.lerString("Introduza o nome de utilizador: ");
                    String passw4 = consola.lerString("Introduza a password: ");
                    if (validarDiretorCurso(user4, passw4)) {
                        mostrarDiretorMenu();
                    } else {
                        consola.escreverErro("Login como Diretor de Curso inválido.");
                    }
                    break;
                case 0:
                    consola.escrever("A sair do sistema.");
                    break;
                default:
                    consola.escreverErro("Opçao invalida. Escolha novamente.");
            }
        } while (escolha1 != 0);
    }

//FUNÇÕES PROFESSOR-------------------------------------------------------------
    private void criarSumario() {
        String titulo = consola.lerString("Titulo do Sumario: ");
        int licao = consola.lerInteiro("Nº de Lição: ");
        String tipo = consola.lerString("Tipo de Aula: ");
        String texto = consola.lerString("Sumário da Aula: ");
        LocalDateTime data = consola.lerDataHora("Qual a data?");
        ListaAlunos presencas = new ListaAlunos();

        String cod_UC = consola.lerString("Introduza o codigo da Unidade Curricular da aula: ");
        UC u = sistema.getListaUCs().procurarUC(cod_UC);

        int adicionarPresencas = 0;
        while (adicionarPresencas == 0) {
            String nmec = consola.lerString("Introduza o nº mecanográfico dos alunos presentes na aula: ");
            if (sistema.getListaAlun().verificar(nmec) && u.getListaAlunos().verificar(nmec)) {
                Aluno a = u.getListaAlunos().procurarAluno(nmec);
                presencas.adicionarAluno(a);
            } else {
                consola.escreverErro("Aluno não encontrado.");
            }
            adicionarPresencas = consola.lerInteiro("Deseja adicionar outro Aluno? (0-Sim, 1- Não): ");
        }

        if (!u.getListaSumarios().verificar(licao)) {
            Sumario s = new Sumario(titulo, licao, tipo, texto, u.getCodigo(), data, presencas);
            sistema.getListaSU().adicionarSumario(s);
            u.getListaSumarios().adicionarSumario(s);
        } else {
            consola.escreverErro("Sumario já existe.");
        }
    }

    private void listarSumarioUC() {
        String codigo = consola.lerString("Escreva o codigo de UC:");
        if (sistema.getListaUCs().verificar(codigo)) {
            UC u = sistema.getListaUCs().procurarUC(codigo);
            Lista_Sumarios l = u.getListaSumarios();
            consola.escrever(l.toString());
        }
    }

    private void listarSumarioTipo() {
        String tipo = consola.lerString("Escreva o tipo de Aula:");
        if (sistema.getListaSU().verificarTipo(tipo)) {
            Sumario s = sistema.getListaSU().procurarSumarioTipo(tipo);
            Lista_Sumarios l = new Lista_Sumarios();
            l.adicionarSumario(s);
            consola.escrever(l.toString());
        }
    }

    private void consultarServicoDocente() {
        String nmec = consola.lerString("Escreva o numero mecanográfico do professor que quer consultar:");
        if (sistema.getListaProf().verificar(nmec)) {
            Professor p = sistema.getListaProf().procurarProfessor(nmec);
            consola.escrever(p.getLista_UCs().toString());
        } else {
            consola.escreverErro("Não foi possível encontrar o professor.");
        }
    }

//-------------------------------------------------------------FUNÇÕES PROFESSOR
//MENU REGENTE------------------------------------------------------------------
    private boolean validarRegenteUC(String user, String passw) {
        if (sistema.getListaSess().verificar(user)) {
            if (sistema.getListaProf().verificarUsername(user)) {
                if (sistema.getListaProf().verificarPassword(passw)) {
                    int cod = consola.lerInteiro("\nQual o código de Regência:");
                    if (cod != 0) {
                        if (sistema.getListaRegentes().verificarRegencia(cod)) {
                            return true;
                        } else {
                            consola.escreverErro("Código não corresponde.");
                        }
                    } else {
                        consola.escreverErro("Código não pode ser 0.");
                    }
                } else {
                    consola.escreverErro("Password incorreta.");
                }
            } else {
                consola.escreverErro("Nome de Utilizador não corresponde.");
            }
        } else {
            consola.escreverErro("Nome de Utilizador não corresponde.");
        }
        return false;
    }

    private void mostrarRegenteMenu() {
        int escolha3;
        do {
            consola.escrever("-----Menu Regente-----");
            consola.escrever("1.Adicionar Alunos ao Curso");
            consola.escrever("2.Remover Alunos do Curso");
            consola.escrever("3.Consultar Assiduidade de Aluno");
            consola.escrever("0. Sair");

            escolha3 = consola.lerInteiro("Escolha a opçao desejada: ");

            switch (escolha3) {
                case 1:
                    adicionarAluno();
                    break;
                case 2:
                    removerAluno();
                    break;
                case 3:
                    consultarAssiduidade();
                    break;
                case 0:
                    consola.escrever("A sair do sistema.");
                    break;
                default:
                    consola.escreverErro("Opçao invalida. Escolha novamente.");
            }
        } while (escolha3 != 0);
    }

//------------------------------------------------------------------MENU REGENTE
//FUNÇÕES REGENTE---------------------------------------------------------------
    private void adicionarAluno() {
        String cod_curso = consola.lerString("Código do curso:");

        if (sistema.getListaCurs().verificar(cod_curso)) { //se o curso existir,
            Curso c = sistema.getListaCurs().procurarCurso(cod_curso); //curso c = esse curso.
            String num_mecanog = consola.lerString("Numero mecanografico do aluno:");

            if (!c.getListaAlunos().verificar(num_mecanog)) { //se o aluno não estiver nesse curso,
                Aluno a = sistema.getListaAlun().procurarAluno(num_mecanog);//aluno a = esse aluno.
                c.getListaAlunos().adicionarAluno(a);
                a.setCodCurso(cod_curso);
                consola.escrever("Aluno:\n" + a.toString() + "adicionado.");
            } else {
                consola.escreverErro("Já está inscrito no curso.");
            }
        } else {
            consola.escreverErro("Curso não existe.");
        }
    }

    private void removerAluno() {
        String cod_curso = consola.lerString("Codigo do curso:");
        String numero = consola.lerString("Numero mecanografico:");

        if (sistema.getListaCurs().verificar(cod_curso)) { //se o curso existir, 
            Curso c = sistema.getListaCurs().procurarCurso(cod_curso); // curso c =  esse curso .
            if (c.getListaAlunos().verificar(numero)) { //se o aluno estiver nesse curso, 
                Aluno a = c.getListaAlunos().procurarAluno(numero); // aluno a  = esse aluno.
                c.getListaAlunos().removerAluno(a);
                consola.escrever("Aluno removido");
            } else {
                consola.escreverErro("Aluno não inscrito no curso");
            }
        } else {
            consola.escreverErro("Curso não existe.");
        }
    }

    private void consultarAssiduidade() {
        String curso = consola.lerString("Qual o Curso do aluno cuja assiduidade quer consultar?");

        if (sistema.getListaCurs().verificar(curso)) { //se o curso existir,
            Curso c = sistema.getListaCurs().procurarCurso(curso); //curso c = esse curso;
            String cod_uc = consola.lerString("Qual o código da unidade curricular?");

            if (c.getListaUCs().verificar(cod_uc)) { // se o curso tiver a uc,
                UC uc = sistema.getListaUCs().procurarUC(cod_uc); //UC uc = esse curso;
                Lista_Sumarios listaSu = uc.getListaSumarios(); //Lista de Sumarios dessa UC
                ListaAlunos listaPresencas = listaSu.procurarSumarioUC(cod_uc).getListaPresencas(); //Lista de Presencas desses sumarios

                String num_mecanog = consola.lerString("Escreva o numero mecanográfico do aluno cuja assiduidade quer consultar:");
                if (listaPresencas.verificar(num_mecanog)) { //se o aluno estiver na lista de presenças.
                    consola.escrever("Aula nº" + listaSu.procurarSumarioUC(cod_uc).getNum_licao());
                    consola.escrever("Presente."); //retorna presente.
                    consola.escrever(listaPresencas.toString()); // retorna a lista de presencas.
                }
            } else {
                consola.escreverErro("UC não pertence a esse curso.");
            }
        } else {
            consola.escreverErro("Curso não existe.");
        }
    }

//---------------------------------------------------------------FUNÇÕES REGENTE
//MENU DIRETOR------------------------------------------------------------------
    private boolean validarDiretorCurso(String user, String passw) {
        if (sistema.getListaSess().verificar(user)) {
            if (sistema.getListaProf().verificarUsername(user)) {
                if (sistema.getListaProf().verificarPassword(passw)) {
                    int cod = consola.lerInteiro("\nQual o código de Direção:");
                    if (cod != 0) {
                        if (sistema.getListaDiretores().verificarDirecao(cod)) {
                            return true;
                        } else {
                            consola.escreverErro("Código não corresponde.");
                        }
                    } else {
                        consola.escreverErro("Código não pode ser 0.");
                    }
                } else {
                    consola.escreverErro("Password incorreta.");
                }
            } else {
                consola.escreverErro("Nome de Utilizador não corresponde.");
            }
        } else {
            consola.escreverErro("Nome de Utilizador não corresponde.");
        }
        return false;
    }

    private void mostrarDiretorMenu() {
        int escolha4;
        do {
            consola.escrever("-----Menu Diretor da UC-----");
            consola.escrever("1.Alterar Designação do Curso");
            consola.escrever("2.Listar Número de Professores no Curso");
            consola.escrever("3.Listar Número de Alunos no Curso");
            consola.escrever("0.Sair");

            escolha4 = consola.lerInteiro("Escolha a opçao desejada: ");

            switch (escolha4) {
                case 1:
                    alterarDesignacao();
                    break;
                case 2:
                    listarProfessorCurso();
                    break;
                case 3:
                    listarAlunoCurso();
                    break;
                case 0:
                    consola.escrever("A sair do sistema.");
                    break;
                default:
                    consola.escrever("Opçao invalida. Escolha novamente.");

            }
        } while (escolha4 != 0);
    }
//------------------------------------------------------------------MENU DIRETOR
//FUNÇÕES DIRETOR---------------------------------------------------------------

    private void alterarDesignacao() {
        String nome_curso = consola.lerString("Introduza o nome do curso: ");
        if (sistema.getListaCurs().verificar(nome_curso)) {
            Curso c = sistema.getListaCurs().procurarCurso(nome_curso);
            String novo_nome_curso = consola.lerString("Introduza o novo nome: ");
            c.setNomeCurso(novo_nome_curso);
        }
    }

    private void listarProfessorCurso() {
        String curso = consola.lerString("Introduza o nome do curso: ");
        if (sistema.getListaCurs().verificar(curso)) {
            Curso c = sistema.getListaCurs().procurarCurso(curso);
            consola.escrever("Numero de Professores: " + c.getListaP().contarProfessores());
        }
    }

    private void listarAlunoCurso() {
        String curso = consola.lerString("Introduza o nome do curso: ");
        if (sistema.getListaCurs().verificar(curso)) {
            Curso c = sistema.getListaCurs().procurarCurso(curso);
            consola.escrever("Numero de Alunos: " + c.getListaAlunos().contarAlunos());
        }
    }
//---------------------------------------------------------------FUNÇÕES DIRETOR

}
