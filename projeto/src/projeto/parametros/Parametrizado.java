package projeto.parametros;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Parametrizado {

	public static void main(String[] args) {
		System.out.println("Teste de Conexçao");
		//Fazendo consulta de forma certa.
		conectarBanco("Adailton", 26);
		//Fazendo consulta com SQL Inject
		conectarBanco("Adailton' or 1 = 1 --", 26);

	}
	
	
	public static void conectarBanco(String nome, Integer idade) {
		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "admin")) {
			             
            System.out.println("Connected to PostgreSQL database!");            
//            Statement statement = connection.createStatement();            
            System.out.printf("%-30.30s  %-30.30s%n", "Nome", "Idade");
            String sqlInject = "SELECT * FROM public.Teste where nome = ? and idade = ?";
            PreparedStatement stmt = connection.prepareStatement(sqlInject);
            stmt.setString(1, nome);
            stmt.setInt(2, idade);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
                System.out.printf("%-30.30s  %-30.30s%n", resultSet.getString("nome"), resultSet.getString("idade"));
            }
            System.out.println("Finalizado consulta, resultado acima");
 
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
	}
}
