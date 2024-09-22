package classes;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import sql.DatabaseConnection;
import sql.SqlFunctions;

public class Cliente {
	
	private int id;
	private String nome;
	private double saldo;

	public Cliente(int id) {
		String selectQuery = "SELECT * FROM CLIENTE WHERE id = " + id;

		try (Connection connection = DatabaseConnection.getConnection()) {

			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(selectQuery);
			this.id = id;
			if (rs.next()) {
				nome = rs.getString("nome");
				saldo = rs.getDouble("saldo");
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


	public double getSaldo() {
		return saldo;
	}


	public void setSaldo(double saldo) {
		SqlFunctions sql = new SqlFunctions();
		String updateSaldoCliente = "UPDATE CLIENTE SET saldo = " + saldo + " WHERE id = " + id;
		sql.update(updateSaldoCliente);
		this.saldo = saldo;
	}
}
