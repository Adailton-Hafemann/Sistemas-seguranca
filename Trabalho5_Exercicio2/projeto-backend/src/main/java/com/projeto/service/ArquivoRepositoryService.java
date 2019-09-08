package com.projeto.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

@Service
public class ArquivoRepositoryService {
	
	public void savaArquivo(String nomeArquivo) {		
		conectarBanco(nomeArquivo);		
	}
	
	public String buscaArquivo(String id) {
		String resposta = "";
		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "admin")) {
            
            System.out.println("Connected to PostgreSQL database!");            
//            Statement statement = connection.createStatement();            
            System.out.printf("%-30.30s  %-30.30s%n", "Nome", "Idade");
            String sqlInject = "SELECT * FROM public.arquivo where id = ?";
            PreparedStatement stmt = connection.prepareStatement(sqlInject);    
            stmt.setString(1, id);
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
            	resposta = resultSet.getString("caminho");            	               
            }
            System.out.println("Finalizado consulta, resultado acima");
 
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
		return resposta;
	}
	
	public List<CaminhoFile> listaArquivos() {
		List<CaminhoFile> lista = new ArrayList<>();
		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "admin")) {
            
            System.out.println("Connected to PostgreSQL database!");            
//            Statement statement = connection.createStatement();            
            System.out.printf("%-30.30s  %-30.30s%n", "Nome", "Idade");
            String sqlInject = "SELECT * FROM public.arquivo";
            PreparedStatement stmt = connection.prepareStatement(sqlInject);            
            ResultSet resultSet = stmt.executeQuery();
            while (resultSet.next()) {
            	lista.add(new CaminhoFile(resultSet.getString("id"), resultSet.getString("caminho")));                
            }
            System.out.println("Finalizado consulta, resultado acima");
 
        } catch (SQLException e) {
            System.out.println("Connection failure.");
            e.printStackTrace();
        }
		return lista;
	}
	
	public void conectarBanco(String nomeArquivo) {
		try (Connection connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "admin")) {
			             
            System.out.println("Connected to PostgreSQL database!");            
//            Statement statement = connection.createStatement();            
            System.out.printf("%-30.30s  %-30.30s%n", "Nome", "Idade");
            String sqlInject = "INSERT INTO public.arquivo VALUES (?, ?)";
            PreparedStatement stmt = connection.prepareStatement(sqlInject);
            stmt.setObject(1, uuidGenerater());
            stmt.setString(2, nomeArquivo);
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
