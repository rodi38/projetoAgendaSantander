package ui;

import controller.Agenda;
import enums.TipoContato;
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
                adicionarNovoContato();
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
    /*
    public Endereco cadastraEndereco(){

    }*/

    private void adicionarNovoContato() {

        System.out.println("Qual o tipo de contato: ");
        for (TipoContato tipoContato : TipoContato.values()) {
            System.out.println(tipoContato);
        }

        int tipo = scanner.nextInt();
        TipoContato tipoContato = TipoContato.getByValor(tipo);

        System.out.println("Digite o nome do contato: ");
        String nome = scanner.nextLine();
        System.out.println("Digite o sobrenome do contato: ");
        String sobrenome = scanner.nextLine();

        Contato novoContato = new Contato(nome, sobrenome);
        novoContato.setTipo(tipoContato);

        System.out.println("Digite o ddd do telefone: ");
        String ddd = scanner.nextLine();
        System.out.println("Digite o telefone: ");
        String numero = scanner.nextLine();

        Telefone novoTelefone = new Telefone(ddd, numero);
        novoContato.addTelefone(novoTelefone);

        System.out.println("Digite o cep: ");
        String cep = scanner.nextLine();
        System.out.println("Digite o logradouro: ");
        String logradouro = scanner.nextLine();
        System.out.println("Digite o numero: ");
        String numeroLogradouro = scanner.nextLine();
        System.out.println("Digite o bairro: ");
        String bairro = scanner.nextLine();
        System.out.println("Digite a cidade: ");
        String cidade = scanner.nextLine();
        System.out.println("Digite o estado: ");
        String estado = scanner.nextLine();

        Endereco endereco = new Endereco();
        endereco.setCep(cep);
        endereco.setLogradouro(logradouro);
        endereco.setNumero(numeroLogradouro);
        endereco.setBairro(bairro);
        endereco.setCidade(cidade);
        endereco.setEstado(estado);

        novoContato.addEndereco(endereco);

        agenda.adicionar(novoContato);

        System.out.println("Contato adicionado");
    }

    public  void adicionar() {
        System.out.println("quer adicionar quantos telefones");

    }

}
