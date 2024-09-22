package sql;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlFunctions {

	public void query(String comando) {
		try (Connection connection = DatabaseConnection.getConnection()) {
			Statement stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(comando);

			ResultSetMetaData rsmd = rs.getMetaData();
			int columnCount = rsmd.getColumnCount();

			while (rs.next()) {
				for (int i = 1; i <= columnCount; i++) {
					String columnValue = rs.getString(i);
					String columnName = rsmd.getColumnName(i);
					System.out.print(columnName + ": " + columnValue + "  ");
				}
				System.out.println();
			}
		} catch (SQLException e) {
			System.out.println("Erro ao realizar a consulta");
			e.printStackTrace();
		}
	}

	public void update(String comando) {
		try (Connection connection = DatabaseConnection.getConnection()) {

			PreparedStatement pstmt = connection.prepareStatement(comando);
			
			int linhasAfetadas = pstmt.executeUpdate();

			System.out.println("Número de linhas afetadas: " + linhasAfetadas);
		}
		catch(SQLException e) {
			System.out.println("Erro ao realizar o update");
			e.printStackTrace();
		}
	}

	public void mostra(String table) {
		if (table == null || table.trim().isEmpty()) {
	        throw new IllegalArgumentException("O nome da tabela não pode ser nulo ou vazio.");
	    }

	    String query = "SELECT * FROM " + table;

	    try (Connection connection = DatabaseConnection.getConnection()) {
	        
	        PreparedStatement pstmt = connection.prepareStatement(query);
	        ResultSet rs = pstmt.executeQuery();

	        ResultSetMetaData rsmd = rs.getMetaData();
	        int columnCount = rsmd.getColumnCount();

	        while (rs.next()) {
	            System.out.println();
	            for (int i = 1; i <= columnCount; i++) {
	                String columnValue = rs.getString(i);
	                String columnName = rsmd.getColumnName(i);
	                System.out.print(columnName + ": " + columnValue + "  ");
	            }
	        }
	    } catch (SQLException e) {
	        System.out.println("Erro ao realizar a consulta");
	        e.printStackTrace();
	    }
	}

	
}
