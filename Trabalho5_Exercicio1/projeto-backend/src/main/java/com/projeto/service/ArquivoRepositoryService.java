package com.projeto.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class ArquivoRepositoryService {
	
	public void savaArquivo() {		
		conectarBanco("tt", 11);		
	}
	
	public void conectarBanco(String nome, Integer idade) {
		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "admin")) {
			             
            System.out.println("Connected to PostgreSQL database!");            
//            Statement statement = connection.createStatement();            
            System.out.printf("%-30.30s  %-30.30s%n", "Nome", "Idade");
            String sqlInject = "INSERT INTO public.arquivo VALUES (?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sqlInject);
            stmt.setObject(1, uuidGenerater());
            stmt.setString(2, "Caminho teste");
            int resultSet = stmt.executeUpdate();
//            while (resultSet.next()) {
//                System.out.printf("%-30.30s  %-30.30s%n", resultSet.getString("nome"), resultSet.getString("idade"));
//            }
//            System.out.println("Finalizado consulta, resultado acima");
 
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
	}
	
	private UUID uuidGenerater() {
		return  UUID.randomUUID(); // Generate a random UUID. 		
	}

}
