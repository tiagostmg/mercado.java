package classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import sql.DatabaseConnection;
import sql.SqlFunctions;

public class Produto {

	private int id;
	private String nome;
	private double preco;
	private int quantidade;

	public Produto(int id) {
		String selectQuery = "SELECT * FROM PRODUTO WHERE id = " + id;

		try (Connection connection = DatabaseConnection.getConnection()) {

			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(selectQuery);
			this.id = id;
			if (rs.next()) {
				nome = rs.getString("nome");
				preco = rs.getDouble("preco");
				quantidade = rs.getInt("quantidade");
			}
		} catch (SQLException e) {
			System.out.println("Erro ao realizar a consulta no construtor");
			e.printStackTrace();
		}
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}

	public int getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(int quantidade) {
		SqlFunctions sql = new SqlFunctions();
		String updateQuery = "UPDATE PRODUTO SET quantidade = " + quantidade + " WHERE id = " + id;
		sql.update(updateQuery);
		this.quantidade = quantidade;
	}

	

}

