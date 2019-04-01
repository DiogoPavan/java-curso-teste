package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.DaoLogin;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoLogin daoLogin = new DaoLogin();

	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		try {
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String page = null;

			if (isLoginValido(login) && isSenhaValida(senha)) {
				page = daoLogin.validarLogin(login, senha) ? "acessoMenu.jsp"	: "acessoNegado.jsp";
			} else {
				page = "index.jsp";
			}
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(page);
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private boolean isSenhaValida(String senha) {
		return senha != null && !senha.isEmpty();
	}

	private boolean isLoginValido(String login) {
		return login != null && !login.isEmpty();
	}

}
