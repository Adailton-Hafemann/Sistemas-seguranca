package controller;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.servlet.ServletException;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dto.LoginDTO;
import dto.TokenDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@RestController
@CrossOrigin
@RequestMapping(value = "public/logar")
public class LoginController {

	@RequestMapping(method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public TokenDTO login(@RequestBody LoginDTO login) throws Exception {
		String jwtToken = "";

		if (login.getUsuario() == null || login.getSenha() == null) {
			throw new ServletException("Informe a senha e usuário");
		}

		String email = login.getUsuario();
		String password = login.getSenha();		
		
		if (UsuariosPermissao.usuarioValido(login)) {
			String pwd = login.getSenha();

			if (!password.equals(pwd)) {
				throw new ServletException("Invalid login. Please check your name and password.");
			}
			
			jwtToken = Jwts.builder().setSubject(email).claim("roles", UsuariosPermissao.getPapel(email)).setIssuedAt(new Date())
					.signWith(SignatureAlgorithm.HS256, "secretkey").compact();

			return new TokenDTO(jwtToken);
		}
		throw new ServletException("Usuario Inválido");
	}
}
