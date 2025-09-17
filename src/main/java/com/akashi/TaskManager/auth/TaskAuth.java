package com.akashi.TaskManager.auth;

import at.favre.lib.crypto.bcrypt.BCrypt;
import com.akashi.TaskManager.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Base64;

@Component
public class TaskAuth extends OncePerRequestFilter {

	private final UserRepository userRepository;

	public TaskAuth(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

		var servletPath = request.getServletPath();

		if (servletPath.startsWith("/tasks/")){
			var authorization = request.getHeader("Authorization");
			var tokenEncoded = authorization.substring("Basic".length()).trim();

			byte[] tokenDecoded = Base64.getDecoder().decode(tokenEncoded);
			var tokenString = new String(tokenDecoded);

			// username:password -> [username, password]
			String[] token = tokenString.split(":");

			var username = token[0];
			var password = token[1];

			var user = userRepository.findByUsername(username);

			if(user == null){
				response.sendError(404, "User not found!");
				return;
			}

			var validatePass = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());

			if(!validatePass.verified){
				response.sendError(401, "Username or Password is not valid!");
				return;
			}

			// Send userID for controller
			request.setAttribute("userID", user.getId());

			filterChain.doFilter(request, response);
			return;
		}
		filterChain.doFilter(request, response);

	}
}
