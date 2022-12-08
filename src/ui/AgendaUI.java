package ui;

import controller.Agenda;
import model.Endereco;
import model.Telefone;

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
                System.out.println("adiciona");
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


    /*public Telefone cadastraTelefones(){

    }
    public Endereco cadastraEndereco(){

    }*/

    public  void adicionar() {
        System.out.println("quer adicionar quantos telefones");
    }

}
