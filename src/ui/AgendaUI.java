package ui;

import controller.Agenda;
import enums.TipoContato;
import enums.TipoEndereco;
import enums.TipoTelefone;
import model.Contato;
import model.Endereco;
import model.Telefone;
import util.ConsoleUIHelper;
import util.InputHelper;
import util.RNHelper;

import java.util.*;

public class AgendaUI {
    Agenda agenda = new Agenda();
    Scanner scanner = new Scanner(System.in);
    char pad = '#';
    int width = 120;


    public void menuInicial() {
        boolean continua = true;

        while (continua) {
            ConsoleUIHelper.drawHeader("AGENDA", width);
            System.out.println("Digite uma das opções abaixo");
            System.out.println("1-Adicionar um contato");
            System.out.println("2-Listar todos contatos");
            System.out.println("3-Pesquisar contato");
            System.out.println("4-Excluir contato");
            System.out.println("5-Excluir todos contatos");
            System.out.println("6-Limpar a tela");
            System.out.println("7-Sair");
            ConsoleUIHelper.drawLine(width);
            System.out.print("Opção escolhida: ");
            String opcao = scanner.nextLine();
            switch (opcao) {
                case "1" -> {
                    adicionarContato();
                    System.out.println();
                }
                case "2" -> {
                    listarContatos();
                    System.out.println();
                }
                case "3" -> {
                    pesquisarContatos();
                    ConsoleUIHelper.drawLine(width);
                    System.out.println();
                }
                case "4" -> {
                    excluirContato();
                    System.out.println();
                }
                case "5" -> {
                    excluirTodosOsContatos();
                    ConsoleUIHelper.drawLine(width);
                    System.out.println();
                }
                case "6" -> {
                    ConsoleUIHelper.clearScreen();
                }

                case "7" -> {
                    System.out.println("Saindo...");
                    continua = false;
                }
                default -> {
                    System.out.println("Opção invalida");
                    ConsoleUIHelper.drawLine(width);
                    System.out.println();

                }
            }
        }
    }

    public void excluirContato() {
        Contato contato = null;
        String nome = ConsoleUIHelper.askSimpleInput("Informe o nome do contato para remover.").toUpperCase();
        if (nome.isEmpty() || nome.isBlank()) {
            System.out.println("Contato não encontrado.");
            ConsoleUIHelper.drawLine(width);
            ConsoleUIHelper.askSimpleInput("Digite qualquer coisa para retornar ao menu");
            return;
        }
        List<Contato> contatosAchados = agenda.pesquisarNome(nome);
        if (contatosAchados.size() == 0) {
            System.out.println("Contato não encontrado.");
            ConsoleUIHelper.drawLine(width);
            ConsoleUIHelper.askSimpleInput("Digite qualquer coisa para retornar ao menu");
            return;
        }
        List<String> ids = new ArrayList<>();
        for (int i = 0; i <= contatosAchados.size(); i++) {
            if (i < contatosAchados.size()) {
                System.out.println("ID: " + (i + 1) + " " + contatosAchados.get(i) + "\n");
                ids.add("" + (i));
                continue;
            }
            int id;
            try {
                id = ConsoleUIHelper.askNumberInt("Digite o ID do contato que deseja remover:") - 1;
            } catch (InputMismatchException e) {
                System.out.println("Indice incorreto, informe apenas um número.");
                i--;
                continue;
            }
            if (ids.contains("" + id)) {
                boolean confirm = ConsoleUIHelper.askConfirm("Tem certeza que deseja remover este contato? \n" + contatosAchados.get(id), "Sim", "Não");
                if (confirm) {
                    System.out.println("Confirmando...");
                    System.out.printf("Contato %s \n Excluido com sucesso!", contatosAchados.get(id));
                    contato = contatosAchados.remove(id);
                } else {
                    System.out.println("Exclusão cancelada. \nRetornando...");
                }
            } else {
                System.out.println("Indice do contato inexistente.");
            }
        }
        agenda.excluir(contato);
    }

    public void excluirTodosOsContatos() {
        if (agenda.getContatos().size() == 0) {
            System.out.println("Não há contatos para exclusão.");
            ConsoleUIHelper.drawLine(width);
            ConsoleUIHelper.askSimpleInput("Digite qualquer coisa para retornar ao menu: ");
            return;
        }
        boolean confirmaExclusao = ConsoleUIHelper.askConfirm("Tem certeza que deseja remover todos os contatos?", "Sim", "Não");
        if (confirmaExclusao) {
            agenda.removerTodosOsContatos();
            System.out.println("Todos os contatos foram excluídos.");
            ConsoleUIHelper.drawLine(width);
            ConsoleUIHelper.askSimpleInput("Digite qualquer coisa para retornar ao menu: ");
        } else {
            System.out.println("Exclusão cancelada.");
            ConsoleUIHelper.drawLine(width);
            ConsoleUIHelper.askSimpleInput("Digite qualquer coisa para retornar ao menu: ");
        }
    }

    public void adicionarTelefoneEmContatoExistente(Contato contato) {
        List<Telefone> telefones = new ArrayList<>();
        for (int i = 0; i < agenda.getContatos().size(); i++) {
            if (agenda.getContatos().get(i).equals(contato)) {
                telefones = cadastraTelefones();
                agenda.getContatos().get(i).setTelefones(telefones);
            }
        }
    }

    public void removerTelefoneEmContatoExistente(Contato contato) {
        Telefone telefone = InputHelper.getTelefoneContato(contato);
        for (int i = 0; i < agenda.getContatos().size(); i++) {
            if (agenda.getContatos().get(i).getTelefones().get(i).equals(telefone)) {
                agenda.getContatos().get(i).getTelefones().remove(telefone);
            }
        }
    }

    public void removerEnderecoEmContatoExistente(Contato contato) {
        Endereco endereco = InputHelper.getEnderecoContato(contato);
        for (int i = 0; i < agenda.getContatos().size(); i++) {
            if (agenda.getContatos().get(i).getEnderecos().get(i).equals(endereco)) {
                agenda.getContatos().get(i).getEnderecos().remove(endereco);
            }
        }
    }

    public void listarTodosTelefonesContato(Contato contato) {
        ConsoleUIHelper.drawHeader("TELEFONES", width);
        System.out.println(agenda.listarTelefoneBasico(contato));
        ConsoleUIHelper.drawLine(width);
        ConsoleUIHelper.askSimpleInput("Digite alguma coisa para retornar ao menu de pesquisa: ");
        ConsoleUIHelper.drawLine(width);
    }

    public void listarTodosEnderecosContato(Contato contato) {
        ConsoleUIHelper.drawHeader("ENDEREÇOS", width);
        System.out.println(agenda.listarEnderecosBasico(contato));
        ConsoleUIHelper.drawLine(width);
        ConsoleUIHelper.askSimpleInput("Digite alguma coisa para retornar ao menu de pesquisa: ");
        ConsoleUIHelper.drawLine(width);
    }

    public void exibirInformacoesContato(Contato contato) {
        boolean continua = true;
        while (continua) {
            System.out.println("Essas são todas as informações do seu contato: ");
            System.out.println(contato);
            System.out.println("O que deseja fazer?");
            System.out.println("1- Excluir telefone do contato");
            System.out.println("2- Excluir endereço do contato");
            System.out.println("3- Voltar ao menu de pesquisa");
            String opcao = ConsoleUIHelper.askSimpleInput("Opção: ").substring(0, 1);
            switch (opcao) {
                case "1" -> {
                    removerTelefoneEmContatoExistente(contato);
                }
                case "2" -> {
                    removerEnderecoEmContatoExistente(contato);
                }
                case "3" -> {
                    continua = false;

                }
                default -> {
                    System.out.println("Opção errada");
                }
            }
        }
    }


    public void adicionaEnderecosEmContatoExistente(Contato contato) {
        List<Endereco> enderecos = new ArrayList<>();
        for (int i = 0; i < agenda.getContatos().size(); i++) {
            if (agenda.getContatos().get(i).equals(contato)) {
                enderecos = cadastraEnderecos();
                agenda.getContatos().get(i).setEnderecos(enderecos);
            }
        }
    }

    public List<Telefone> cadastraTelefones() {
        List<Telefone> telefones = new ArrayList<>();
        String ddd = "", numero = "";
        TipoTelefone[] tipos = TipoTelefone.values();
        System.out.println("Digite o tipo de telefone: ");
        for (int j = 0; j < tipos.length; j++) {
            System.out.println(j + 1 + " - " + tipos[j]);
        }
        int tipoTelefoneOpcao = ConsoleUIHelper.askChooseOption("Opção: ", tipos[0].toString(), tipos[1].toString(), tipos[2].toString());
        TipoTelefone tipoTelefone = tipos[tipoTelefoneOpcao];

        ddd = ConsoleUIHelper.askSimpleInput("Digite o DDD: ").trim().replaceAll(" ", "");;
        numero = ConsoleUIHelper.askSimpleInput("Digite o numero: ").trim().replaceAll(" ", "");;
        telefones.add(new Telefone(tipoTelefone, ddd, numero));
        RNHelper.trataTelefone(agenda, telefones);
        return telefones;
    }

    public List<Endereco> cadastraEnderecos() {
        List<Endereco> enderecos = new ArrayList<>();
        String cep = "", logradouro = "", numero = "", cidade = "", estado = "";
        TipoEndereco[] tipoEnderecos = TipoEndereco.values();
        System.out.println("Digite o tipo de Endereco de acordo com o indice: ");
        for (int j = 0; j < tipoEnderecos.length; j++) {
            System.out.println(j + 1 + " - " + tipoEnderecos[j]);
        }
        int tipoEnderecoOpcao = ConsoleUIHelper.askChooseOption("Opção: ", tipoEnderecos[0].toString(),
                tipoEnderecos[1].toString(), tipoEnderecos[2].toString());
        TipoEndereco tipoEndereco = tipoEnderecos[tipoEnderecoOpcao];

        cep = ConsoleUIHelper.askSimpleInput("Digite o cep: ").trim().replaceAll(" ", "");
        logradouro = ConsoleUIHelper.askSimpleInput("Digite o logradouro: ").trim().replaceAll(" ", "");
        numero = ConsoleUIHelper.askSimpleInput("Digite o numero da casa: ").trim().replaceAll(" ", "");
        cidade = ConsoleUIHelper.askSimpleInput("Digite o nome da cidade: ").trim().replaceAll(" ", "");
        estado = ConsoleUIHelper.askSimpleInput("Digite o nome do estado: ").trim().replaceAll(" ", "");
        enderecos.add(new Endereco(tipoEndereco, cep, logradouro, numero, cidade, estado));
        System.out.println("Endereço cadastrado com sucesso.");
        ConsoleUIHelper.drawLine(width);
        RNHelper.trataEndereco(agenda, enderecos);
        return enderecos;
    }

    public void adicionarContato() {
        List<Telefone> telefones = new ArrayList<>();
        List<Endereco> enderecos = new ArrayList<>();
        int tipoContatoOption = ConsoleUIHelper.askChooseOption("Qual tipo de contato deseja cadastrar",
                "Pessoal", "Comercial");
        TipoContato tipoContato = TipoContato.values()[tipoContatoOption];
        String nome = ConsoleUIHelper.askSimpleInput("Digite o nome do contato").toUpperCase();
        String sobreNome = ConsoleUIHelper.askSimpleInput("Digite o sobrenome do contato").toUpperCase();
        int telefoneOption = ConsoleUIHelper.askChooseOption("Quer adicionar um Telefone?", "Sim", "Não");
        if (telefoneOption == 0) {
            telefones = cadastraTelefones();
        }
        //agenda.getContatos().get(i).getTelefones().size()
        int enderecoOption = ConsoleUIHelper.askChooseOption("Quer adicionar um Endereço?", "Sim", "Não");
        if (enderecoOption == 0) {
            enderecos = cadastraEnderecos();
        }
        Contato contato = new Contato(tipoContato, nome, sobreNome, enderecos, telefones);
        int confirma = RNHelper.trataContato(agenda, contato);
        if (confirma == 1) {
            return;
        }
        agenda.adicionar(contato);
        System.out.println("Contato adicionado com sucesso!");
        ConsoleUIHelper.drawLine(width);
        ConsoleUIHelper.askSimpleInput("Digite qualquer coisa para retornar ao menu");
    }

    public void listarContatos() {
        if (agenda.getContatos().size() == 0) {
            ConsoleUIHelper.drawLine(width);
            System.out.println("Nenhum contato salvo na agenda!");
            ConsoleUIHelper.drawLine(width);
            ConsoleUIHelper.askSimpleInput("Digite qualquer coisa para retornar ao menu");
            return;
        }
        ConsoleUIHelper.drawHeader("AGENDA", width);
        System.out.println(agenda.listarTodosContatos());
        ConsoleUIHelper.drawLine(width);
        ConsoleUIHelper.askSimpleInput("Digite qualquer coisa para retornar ao menu");
        System.out.println();
    }

    public void pesquisarContatos() {
        String nome = ConsoleUIHelper.askSimpleInput("Digite o nome do contato que deseja pesquisar Ex: Rodrigo Rocha").toUpperCase();
        if (nome.isBlank() || nome.isEmpty()) {
            System.out.println("Contato não encontrado.");
            ConsoleUIHelper.drawLine(width);
            ConsoleUIHelper.askSimpleInput("Digite qualquer coisa para retornar ao menu");
            return;
        }
        List<Contato> contatoEncontrado = agenda.pesquisarNome(nome);
        if (contatoEncontrado.size() == 0) {
            System.out.println("Contato não encontrado.");
            ConsoleUIHelper.drawLine(width);
            ConsoleUIHelper.askSimpleInput("Digite qualquer coisa para retornar ao menu");
            return;
        }
        Contato contato = null;
        if (contatoEncontrado.size() > 1) {
            List<String> ids = new ArrayList<>();
            System.out.printf("Foi encontrado mais de um contato em sua pesquisa por '%s', qual deles você estava buscando?", nome);
            for (int i = 0; i <= contatoEncontrado.size(); i++) {
                if (i < contatoEncontrado.size()) {
                    System.out.println("ID: " + (i + 1) + " " + contatoEncontrado.get(i) + "\n");
                    ids.add("" + i);
                    continue;
                }
                int id = ConsoleUIHelper.askNumberInt("Digite o ID do contato") - 1;
                if (ids.contains("" + id)) {
                    contato = contatoEncontrado.remove(id);
                } else {
                    System.out.println("ID inexistente.");
                }
            }
        }
        if (contato == null) {
            contato = contatoEncontrado.get(0);
        }

        boolean continua = true;
        while (continua) {
            System.out.println(contato.getNomeCompleto());
            System.out.println("Contato encontrado, o que deseja fazer com ele?");
            System.out.println("1- Ver todas informações do contato");
            System.out.println("2- Adicionar telefones ao contato");
            System.out.println("3- Adicionar endereços ao contato");
            System.out.println("4- Listar todos os telefones do contato");
            System.out.println("5- Listar todos os endereços do contato");

            System.out.println("6- Voltar ao menu principal");
            String opcao = ConsoleUIHelper.askSimpleInput("Opção: ").substring(0, 1);
            switch (opcao) {
                case "1" -> {
                    exibirInformacoesContato(contato);
                    continua = ConsoleUIHelper.askConfirm("Deseja continuar no menu de pesquisa?", "Sim", "Não");

                }
                case "2" -> {
                    adicionarTelefoneEmContatoExistente(contato);
                    continua = ConsoleUIHelper.askConfirm("Deseja continuar no menu de pesquisa?", "Sim", "Não");
                }
                case "3" -> {
                    adicionaEnderecosEmContatoExistente(contato);
                }
                case "4" -> {
                    listarTodosTelefonesContato(contato);
                }
                case "5" -> {
                    listarTodosEnderecosContato(contato);
                }
                case "6" -> {
                    continua = false;
                }
                default -> {
                    System.out.println("Opção inválida.");
                    continua = ConsoleUIHelper.askConfirm("Deseja continuar no menu de pesquisa?", "Sim", "Não");
                }
            }
        }

        ConsoleUIHelper.drawLine(width);
    }

}