package dao;

import java.sql.Connection;
import java.sql.SQLException;

import classes.Cliente;
import classes.Produto;
import sql.DatabaseConnection;

public class MercadoDAO {
	
	public void compra(Cliente cliente, Produto produto, int qtd) {

		try (Connection connection = DatabaseConnection.getConnection()) {

			double saldoCliente = cliente.getSaldo();
			int qtdProduto = produto.getQuantidade();
			double precoProduto = produto.getPreco();
			
			if (qtdProduto >= qtd) {
				
				double valorTotal = qtd * precoProduto;
				if(saldoCliente >= valorTotal) {
					
					int novaQuantidade = qtdProduto - qtd;
					produto.setQuantidade(novaQuantidade);

					double novoSaldo = saldoCliente - valorTotal;
					cliente.setSaldo(novoSaldo);

					System.out.println("Compra realizada com sucesso!");
					if(novaQuantidade == 0) {
						System.out.println("Estoque do produto zerado");
					}
					
				} else {
					System.out.println("Saldo do cliente insuficiente");
				}
			} else {
				System.out.println("Quantidade insuficiente em estoque.");
			}

		}
		catch (SQLException e) {
			System.out.println("Erro ao realizar a consulta");
			e.printStackTrace();
		}
	}
}
