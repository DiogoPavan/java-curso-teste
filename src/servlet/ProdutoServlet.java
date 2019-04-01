package servlet;

import java.io.IOException;
import java.math.BigDecimal;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Produto;
import dao.DaoProduto;

/**
 * Servlet implementation class ProdutoServlet
 */
@WebServlet("/salvarProduto")
public class ProdutoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private DaoProduto daoProduto = new DaoProduto();

	public ProdutoServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			
			String acao = request.getParameter("acao");
			
			if (acao.equals("findAll")) {
				forwardCadastroProduto(request, response, "produtos");
			} else if (acao.equals("editar")) {

				Produto produto = daoProduto.consultar(request.getParameter("id"));

				RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
				request.setAttribute("produto", produto);
				request.setAttribute("produtos", daoProduto.findAll());
				view.forward(request, response);
			} else if (acao.equals("delete")) {

				daoProduto.deleteProduto(request.getParameter("id"));
				forwardCadastroProduto(request, response, "produtos");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if (acao != null && acao.equals("reset")) {

			try {
				forwardCadastroProduto(request, response, "produtos");
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			
			Produto produto = createProduto(request);
			String mensagem = null;
			Boolean actionIsValid = false;
			
			try {
				if (isFormInvalid(request)) {
					mensagem = "Formulário inválido. Preencha todos os campos disponíves";
				} else if (produto.getIdProduto() == null) {
					if (!daoProduto.isDescricaoValida(produto.getDescricao())) {
						mensagem = "Descrição de produto duplicada";
					} else {
						daoProduto.salvarProduto(produto);
						actionIsValid = true;
					}
			
				} else if (produto.getIdProduto() != null) {
					
					if (!daoProduto.isDescricaoValidaUpdate(produto.getDescricao(), produto.getIdProduto().toString())) {
						mensagem = "Já existe essa Descrição de produto";
					} else {
						daoProduto.atualizarProduto(produto);
						actionIsValid = true;
					}
					
				}
				
				if (!actionIsValid) {
					request.setAttribute("produto", produto);
				}
				
				request.setAttribute("msg", mensagem);
				forwardCadastroProduto(request, response, "produtos");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private Produto createProduto(HttpServletRequest request) {

		String idProduto = request.getParameter("id");
		String descricao = request.getParameter("descricao");
		String quantidade = request.getParameter("quantidade");
		String valor = request.getParameter("valor");
		valor = valor.replaceAll("\\.", "");
		valor = valor.replaceAll("\\,", ".");

		Produto produto = new Produto();
		produto.setIdProduto(!idProduto.isEmpty() ? Long.parseLong(idProduto) : null);
		produto.setDescricao(descricao);
		produto.setQuantidade(quantidade == null || quantidade.isEmpty() ? null : Integer.parseInt(quantidade));
		produto.setValor(valor == null || valor.isEmpty() ? null : new BigDecimal(valor));

		return produto;
	}

	private boolean isFormInvalid(HttpServletRequest request) {
		
		String descricao = request.getParameter("descricao");
		String quantidade = request.getParameter("quantidade");
		String valor = request.getParameter("valor");
		
		boolean isDescricaoInvalid = descricao == null || descricao.isEmpty();
		boolean isQuantidadeInvalid = quantidade == null || quantidade.isEmpty();
		boolean isValorInvalid = valor == null || valor.isEmpty();
		
		return isDescricaoInvalid || isQuantidadeInvalid  || isValorInvalid;
	}
	
	private void forwardCadastroProduto(HttpServletRequest request, HttpServletResponse response, String object)
			throws Exception, ServletException, IOException {

		RequestDispatcher view = request.getRequestDispatcher("/cadastroProduto.jsp");
		request.setAttribute(object, daoProduto.findAll());
		view.forward(request, response);

	}
}
