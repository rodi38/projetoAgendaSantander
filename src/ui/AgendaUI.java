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
        String nome = ConsoleUIHelper.askNoEmptyInput("Informe o nome do contato para remover.", 5);
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

    public void adicionarTelefoneEmContatoExistente(String nome) {
        List<Contato> contatoEncontrado = agenda.pesquisarNome(nome);
        List<Telefone> telefones = new ArrayList<>();
        Contato contato = null;
        if (contatoEncontrado.size() == 0){
            System.out.println("Contato não encontrado.");
            ConsoleUIHelper.drawLine(width);
            ConsoleUIHelper.askSimpleInput("Digite qualquer coisa para retornar ao menu");
            return;
        }
        contato = InputHelper.getContatoPesquisado(contatoEncontrado);
        for (int i = 0; i < agenda.getContatos().size(); i++) {
            if (agenda.getContatos().get(i).equals(contato)) {
                telefones = cadastraTelefones();
                agenda.getContatos().get(i).setTelefones(telefones);
            }
        }
    }

    public void removerTelefoneEmContatoExistente(String nome) {
        List<Contato> contatoEncontrado = agenda.pesquisarNome(nome);
        if (contatoEncontrado.size() == 0){
            System.out.println("Contato não encontrado.");
            ConsoleUIHelper.drawLine(width);
            ConsoleUIHelper.askSimpleInput("Digite qualquer coisa para retornar ao menu");
            return;
        }
        Contato contato = InputHelper.getContatoPesquisado(contatoEncontrado);
        Telefone telefone = InputHelper.getTelefoneContato(contato);
        for (int i = 0; i < agenda.getContatos().size(); i++) {
            if (agenda.getContatos().get(i).getTelefones().get(i).equals(telefone)){
                agenda.getContatos().get(i).getTelefones().remove(telefone);
            }
        }
    }
    public void removerEnderecoEmContatoExistente(String nome) {
        List<Contato> contatoEncontrado = agenda.pesquisarNome(nome);
        if (contatoEncontrado.size() == 0){
            System.out.println("Contato não encontrado.");
            ConsoleUIHelper.drawLine(width);
            ConsoleUIHelper.askSimpleInput("Digite qualquer coisa para retornar ao menu");
            return;
        }
        Contato contato = InputHelper.getContatoPesquisado(contatoEncontrado);
        Endereco endereco = InputHelper.getEnderecoContato(contato);
        for (int i = 0; i < agenda.getContatos().size(); i++) {
            if (agenda.getContatos().get(i).getEnderecos().get(i).equals(endereco)){
                agenda.getContatos().get(i).getEnderecos().remove(endereco);
            }
        }
    }

    public void listarTelefones(String nomeContato) {
        ConsoleUIHelper.drawHeader("TELEFONES", width);
        System.out.println(agenda.printTelefoneBasico(nomeContato));
    }

    public void listarEnderecos(String nomeContato) {
        ConsoleUIHelper.drawHeader("ENDEREÇOS", width);
        System.out.println(agenda.printEnderecosBasico(nomeContato));
    }
    public void exibirInformacoesContato(String nome){
        List<Contato> contatoEncontrado = agenda.pesquisarNome(nome);
        boolean continua = true;
        while (continua){
            System.out.println("Essas são todas as informações do seu contato: ");
            System.out.println(contatoEncontrado);
            System.out.println("O que deseja fazer?");
            System.out.println("1- Excluir telefone do contato");
            System.out.println("2- Excluir endereço do contato");
            System.out.println("3- Voltar ao menu de pesquisa");
            String opcao = ConsoleUIHelper.askSimpleInput("Opção: ").substring(0,1);
            switch (opcao){
                case "1" ->{
                    removerTelefoneEmContatoExistente(nome);
                }
                case "2" ->{
                    removerEnderecoEmContatoExistente(nome);
                }
                case "3" ->{
                    continua = false;

                }
                default -> {
                    System.out.println("Opção errada");
                }
            }
        }
    }


    public void adicionaEnderecosEmContatoExistente(String nome) {
        List<Contato> contatoEncontrado = agenda.pesquisarNome(nome);

        List<Endereco> enderecos = new ArrayList<>();
        Contato contato = null;
        if (contatoEncontrado.size() == 0){
            int linha = ConsoleUIHelper.drawWithRightPadding("Contato não encontrado.", width, pad);
            ConsoleUIHelper.drawLine(linha);
        }
        List<String> ids = new ArrayList<>();
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
        for (int i = 0; i < agenda.getContatos().size(); i++) {
            if (agenda.getContatos().get(i).equals(contato)) {
                enderecos = cadastraEnderecos();
                agenda.getContatos().get(i).setEnderecos(enderecos);
            }
        }
    }

    public List<Telefone> cadastraTelefones() {
        List<Telefone> telefones = new ArrayList<>();
        int telefoneQuantidade;
        String ddd = "", numero = "";
        while (true) {
            try {
                System.out.println("Digite a quantidade de telefones");
                telefoneQuantidade = Math.abs(scanner.nextInt());
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Tipo errado, digite apenas um numero positivo");
            }
        }
        for (int i = 0; i < telefoneQuantidade; i++) {
            TipoTelefone[] tipos = TipoTelefone.values();
            System.out.println("Digite o tipo de telefone: ");
            for (int j = 0; j < tipos.length; j++) {
                System.out.println(j + 1 + " - " + tipos[j]);
            }
            int tipoTelefoneOpcao = scanner.nextInt() - 1;
            scanner.nextLine();
            if (tipoTelefoneOpcao < 0 || tipoTelefoneOpcao >= tipos.length) {
                System.out.println("OPÇÃO INVALIDA");
                i--;
                continue;
            }
            TipoTelefone tipoTelefone = tipos[tipoTelefoneOpcao];
            ddd = ConsoleUIHelper.askSimpleInput("Digite o DDD: ");
            numero = ConsoleUIHelper.askSimpleInput("Digite o numero: ");
            telefones.add(new Telefone(tipoTelefone, ddd, numero));
        }
        RNHelper.trataTelefone(agenda, telefones);
        return telefones;
    }

    public List<Endereco> cadastraEnderecos() {
        List<Endereco> enderecos = new ArrayList<>();
        int quantidadeEnderecos;
        String cep = "", logradouro = "", numero = "", cidade = "", estado = "";
        while (true) {
            try {
                System.out.println("Digite a quantidade de Enderecos do contato");
                quantidadeEnderecos = Math.abs(scanner.nextInt());
                scanner.nextLine();
                break;
            } catch (InputMismatchException e) {
                System.out.println("Tipo errado, digite apenas um numero positivo");
            }
        }
        for (int i = 0; i < quantidadeEnderecos; i++) {
            TipoEndereco[] tipoEnderecos = TipoEndereco.values();
            System.out.println("Digite o tipo de Endereco de acordo com o indice: ");
            for (int j = 0; j < tipoEnderecos.length; j++) {
                System.out.println(j + 1 + " - " + tipoEnderecos[j]);
            }
            int tipoEnderecoOpcao = scanner.nextInt() - 1;
            scanner.nextLine();

            if (tipoEnderecoOpcao < 0 || tipoEnderecoOpcao >= tipoEnderecos.length) {
                System.out.println("OPÇÃO INVALIDA");
                i--;
                continue;
            }
            TipoEndereco tipoEndereco = tipoEnderecos[tipoEnderecoOpcao];
            cep = ConsoleUIHelper.askSimpleInput("Digite o cep: ");
            logradouro = ConsoleUIHelper.askSimpleInput("Digite o logradouro: ");
            numero = ConsoleUIHelper.askSimpleInput("Digite o numero da casa: ");
            cidade = ConsoleUIHelper.askSimpleInput("Digite o nome da cidade: ");
            estado = ConsoleUIHelper.askSimpleInput("Digite o nome do estado: ");
            enderecos.add(new Endereco(tipoEndereco, cep, logradouro, numero, cidade, estado));
            System.out.println("Endereço cadastrado com sucesso.");
            ConsoleUIHelper.drawLine(width);
        }
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
        int telefoneOption = ConsoleUIHelper.askChooseOption("Quer adicionar um ou mais Telefones?", "Sim", "Não");
        if (telefoneOption == 0) {
            telefones = cadastraTelefones();
        }
        //agenda.getContatos().get(i).getTelefones().size()
        int enderecoOption = ConsoleUIHelper.askChooseOption("Quer adicionar um ou mais Endereços?", "Sim", "Não");
        if (enderecoOption == 0) {
            enderecos = cadastraEnderecos();
            RNHelper.trataEndereco(agenda, enderecos);
        }
        Contato contato = new Contato(tipoContato, nome, sobreNome, enderecos, telefones);
        int confirma = RNHelper.trataContato(agenda, contato);
        if (confirma == 1){
            return;
        }
        agenda.adicionar(contato);
        System.out.println("Contato adicionado com sucesso!");
        ConsoleUIHelper.drawLine(width);
        ConsoleUIHelper.askSimpleInput("Digite qualquer coisa para retornar ao menu");
    }

    public void listarContatos() {
        if (agenda.getContatos().size() == 0){
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
        List<Contato> contatoEncontrado = agenda.pesquisarNome(nome);
        if (contatoEncontrado.size() == 0){
            System.out.println("Contato não encontrado.");
            ConsoleUIHelper.drawLine(width);
            ConsoleUIHelper.askSimpleInput("Digite qualquer coisa para retornar ao menu");
            return;
        }

        boolean continua = true;
        while (continua){
            for (Contato contato : contatoEncontrado) {
                System.out.println(contato.getNomeCompleto());
            }
            System.out.println("Contato encontrado, o que deseja fazer com ele?");
            System.out.println("1- Ver todas informações do contato");
            System.out.println("2- Adicionar telefones ao contato");
            System.out.println("3- Adicionar endereços ao contato");
            System.out.println("4- Listar todos os telefones do contato");
            System.out.println("5- Listar todos os endereços do contato");

            System.out.println("6- Voltar ao menu principal");
            String opcao = ConsoleUIHelper.askSimpleInput("Opção: ").substring(0,1);
            switch (opcao) {
                case "1" ->{
                    exibirInformacoesContato(nome);
                    continua = ConsoleUIHelper.askConfirm("Deseja continuar no menu de pesquisa?", "Sim", "Não");

                }
                case "2" ->{
                    adicionarTelefoneEmContatoExistente(nome);
                    continua = ConsoleUIHelper.askConfirm("Deseja continuar no menu de pesquisa?", "Sim", "Não");
                }
                case "3" ->{
                    adicionaEnderecosEmContatoExistente(nome);
                    continua = ConsoleUIHelper.askConfirm("Deseja continuar no menu de pesquisa?", "Sim", "Não");
                }
                case "4" ->{
                    listarTelefones(nome);
                    continua = ConsoleUIHelper.askConfirm("Deseja continuar no menu de pesquisa?", "Sim", "Não");
                }
                case "5" ->{
                    listarEnderecos(nome);
                }
                case "6" ->{
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