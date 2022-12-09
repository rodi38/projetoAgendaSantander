package ui;

import controller.Agenda;
import enums.TipoContato;
import enums.TipoEndereco;
import enums.TipoTelefone;
import model.Contato;
import model.Endereco;
import model.Telefone;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class AgendaUI {
    Agenda agenda = new Agenda();
    Scanner scanner = new Scanner(System.in);


    public void menuInicial(){
        System.out.println("********** AGENDA **********");
        System.out.println("Digite uma das opções abaixo");
        System.out.println("1-Adicionar um contato");
        System.out.println("2-Pesquisar por contato");
        System.out.println("3-Excluir um contato");
        System.out.println("4-Listar contatos");
        String opcao = scanner.nextLine();
        switch (opcao) {
            case "1" -> {
                adicionar();
                System.out.println(agenda.listar(0,1));
            }
            case "2" -> {
                System.out.println("Pesquisar");
            }
            case "3" -> {
                System.out.println("Excluir");
            }
            case "4" -> {
                System.out.println("Lista");
            }
            default -> {
                System.out.println("Opcao invalida");
            }
        }
    }


    public List<Telefone> cadastraTelefones(){
        List<Telefone> telefones = new ArrayList<>();
        int telefoneQuantidade;
        String ddd = "", numero = "";
        while (true){
            try {
                System.out.println("Digite a quantidade de telefones");
                telefoneQuantidade = Math.abs(scanner.nextInt());
                break;
            } catch (InputMismatchException e) {
                System.out.println("Tipo errado, digite apenas um numero positivo");
            }
        }

        for (int i = 0; i < telefoneQuantidade; i++) {
            TipoTelefone[] tipos = TipoTelefone.values();
            System.out.println("Digite o tipo de telefone: ");
            for (int j = 0; j < tipos.length; j++) {
                System.out.println(j+1 + " - " + tipos[i]);
            }
            int tipoTelefoneOpcao = scanner.nextInt()-1;
            if (tipoTelefoneOpcao < 0 || tipoTelefoneOpcao >= tipos.length){
                System.out.println("OPÇÃO INVALIDA");
                i--;
                continue;
            }
            TipoTelefone tipoTelefone = tipos[tipoTelefoneOpcao];

            System.out.print("Digite o DDD: ");
            ddd = scanner.nextLine();
            System.out.print("Digite o numero: ");
            numero = scanner.nextLine();
            telefones.add(new Telefone(tipoTelefone, ddd, numero));
            System.out.println(" Telefone Cadastrado");
            System.out.println();
            System.out.println("-----------------------------");
            System.out.println();
        }

        return telefones;
    }

    public List<Endereco> cadastraEnderecos(){
        List<Endereco> enderecos = new ArrayList<>();
        int quantidadeEnderecos;
        String cep = "", logradouro = "", numero="", cidade = "", estado = "";
        while (true){
            try {
                System.out.println("Digite a quantidade de Enderecos do contato");
                quantidadeEnderecos = Math.abs(scanner.nextInt());
                break;
            } catch (InputMismatchException e) {
                System.out.println("Tipo errado, digite apenas um numero positivo");
            }
        }

        for (int i = 0; i < quantidadeEnderecos; i++) {
            TipoEndereco[] tipoEnderecos = TipoEndereco.values();
            System.out.println("Digite o tipo de Endereco de acordo com o indice: ");
            for (int j = 0; j < tipoEnderecos.length; j++) {
                System.out.println(j+1 + " - " + tipoEnderecos[i]);
            }
            int tipoEnderecoOpcao = scanner.nextInt()-1;
            if (tipoEnderecoOpcao < 0 || tipoEnderecoOpcao >= tipoEnderecos.length){
                System.out.println("OPÇÃO INVALIDA");
                i--;
                continue;
            }
            TipoEndereco tipoEndereco = tipoEnderecos[tipoEnderecoOpcao];
            System.out.print("Digite o cep: ");
            cep = scanner.nextLine();
            System.out.print("Digite o logradouro: ");
            logradouro = scanner.nextLine();
            System.out.print("Digite o numero da casa: ");
            numero = scanner.nextLine();
            System.out.print("Digite o nome da cidade: ");
            cidade = scanner.nextLine();
            System.out.print("Digite o nome do estado: ");
            estado = scanner.nextLine();
            enderecos.add(new Endereco(tipoEndereco, cep, logradouro, numero, cidade,estado));
            System.out.println(" Endereco Cadastrado");
            System.out.println();
            System.out.println("-----------------------------");
            System.out.println();
        }

        return enderecos;
    }

    public  void adicionar() {
        System.out.println("Digite o nome do contato");
        String nome = scanner.nextLine();
        System.out.println("Digite o sobrenome do contato");
        String sobrenome = scanner.nextLine();

        Contato contato = new Contato(TipoContato.Profissional, nome, sobrenome);
        agenda.adicionar(contato);

    }

}
