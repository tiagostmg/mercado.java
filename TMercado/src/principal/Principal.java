package principal;

import java.util.Scanner;

import classes.Cliente;
import classes.Produto;

public class Principal {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);

		Produto sabao = new Produto(1);
		Produto frutas = new Produto(2);
		Produto arroz = new Produto(3);


		System.out.println("Digite seu ID: ");
		int usuarioId = sc.nextInt();
		switch(usuarioId) {
		case 1:
			Cliente tiago = new Cliente(1);
			break;
		case 2:
			Cliente rafael = new Cliente(2);
			break;
		case 3:
			Cliente igor = new Cliente(3);
			break;
		}


	}

}
