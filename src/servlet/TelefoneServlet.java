package servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.BeanCurso;
import beans.Telefone;
import dao.DaoTelefone;
import dao.DaoUsuario;

/**
 * Servlet implementation class TelefoneServlet
 */
@WebServlet("/salvarTelefone")
public class TelefoneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();

	private DaoTelefone daoTelefone = new DaoTelefone();

	public TelefoneServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String acao = request.getParameter("acao");
			String idUsuario = request.getParameter("id");

			if (acao != null && acao.equals("delete")) {
				String idTelefone = request.getParameter("idTelefone");
				daoTelefone.delete(idTelefone);
			}
			
			BeanCurso usuario = idUsuario == null 
					? (BeanCurso)request.getSession().getAttribute("usuarioEscolhido") 
							: daoUsuario.consultar(idUsuario);

			request.getSession().setAttribute("usuarioEscolhido", usuario);

			RequestDispatcher view = request.getRequestDispatcher("/cadastroTelefone.jsp");
			request.setAttribute("telefones", daoTelefone.findTelefoneByIdUsuario(usuario.getIdBeanCurso()));
			view.forward(request, response);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			BeanCurso usuario = (BeanCurso) request.getSession().getAttribute("usuarioEscolhido");
			String numero = request.getParameter("numero");
			String tipo = request.getParameter("tipo");

			Telefone telefone = new Telefone();
			telefone.setIdUsuario(usuario.getIdBeanCurso());
			telefone.setNumero(numero);
			telefone.setTipo(tipo);

			daoTelefone.salvarTelefone(telefone);

			request.getSession().setAttribute("usuarioEscolhido", usuario);

			RequestDispatcher view = request.getRequestDispatcher("/cadastroTelefone.jsp");

			request.setAttribute("telefones", daoTelefone.findTelefoneByIdUsuario(usuario.getIdBeanCurso()));
			view.forward(request, response);

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
