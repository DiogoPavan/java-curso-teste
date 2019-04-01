package servlet;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Objects;

import javax.imageio.ImageIO;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.xml.bind.DatatypeConverter;

import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.codec.binary.Base64;

import beans.BeanCurso;
import dao.DaoUsuario;

/**
 * Servlet implementation class Usuario
 */
@SuppressWarnings("ALL")
@WebServlet("/salvarUsuario")
@MultipartConfig
public class UsuarioServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private DaoUsuario daoUsuario = new DaoUsuario();

	public UsuarioServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			String acao = request.getParameter("acao");
			String user = request.getParameter("user");

			if (acao.equals("delete")) {

				daoUsuario.delete(user);
				forwardCadastroUsuario(request, response, "usuarios");

			} else if (acao.equals("editar")) {

				BeanCurso beanCurso = daoUsuario.consultar(user);

				RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
				request.setAttribute("user", beanCurso);
				request.setAttribute("usuarios", daoUsuario.findAll());
				view.forward(request, response);

			} else if (acao.equals("findAll")) {

				forwardCadastroUsuario(request, response, "usuarios");

			} else if (acao.equals("download")) {
				
				BeanCurso beanCurso = daoUsuario.consultar(user);

				if (beanCurso != null) {
					String contentType = "";
					byte[] fileBytes = null;
					String tipo = request.getParameter("tipo");
					
					if (tipo.equals("imagem")) {
						contentType = beanCurso.getContentType();
						/*converte a base64 da imagem do banco para byte[] array*/
						fileBytes = Base64.decodeBase64(beanCurso.getFotoBase64());
					} else if (tipo.equals("pdf")) {
						contentType = beanCurso.getContentTypeCurriculo();
						/*converte a base64 da imagem do banco para byte[] array*/
						fileBytes = Base64.decodeBase64(beanCurso.getCurriculoBase64());
					}
					response.setHeader("Content-Disposition",
							"attachment;filename=arquivo." + contentType.split("\\/")[1]);				
					
					/* coloca os bytes no objeto de entrada para processar*/
					InputStream is = new ByteArrayInputStream(fileBytes);
					
					/*in�cio da resposta para o navegador*/
					int read = 0;
					byte[] bytes = new byte[1024];
					
					OutputStream os = response.getOutputStream();
					
					while ((read = is.read(bytes)) != -1) {
						os.write(bytes, 0, read);
					}
					
					os.flush();
					os.close();
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String acao = request.getParameter("acao");

		if (acao != null && acao.equals("reset")) {

			try {
				forwardCadastroUsuario(request, response, "usuarios");
			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {

			String id = request.getParameter("id");
			String login = request.getParameter("login");
			String senha = request.getParameter("senha");
			String fgAtivo = request.getParameter("ativo");
			String mensagem = null;
			Boolean actionIsValid = false;

			BeanCurso usuario = new BeanCurso();
			usuario.setIdBeanCurso(id != null && !id.isEmpty() ? Long.parseLong(id) : null);
			usuario.setLogin(login);
			usuario.setSenha(senha);
			usuario.setNome(request.getParameter("nome"));
			usuario.setFone(request.getParameter("fone"));
			usuario.setCep(request.getParameter("cep"));
			usuario.setBairro(request.getParameter("bairro"));
			usuario.setCidade(request.getParameter("cidade"));
			usuario.setEstado(request.getParameter("estado"));
			usuario.setIbge(request.getParameter("ibge"));
			usuario.setRua(request.getParameter("rua"));
			usuario.setFgAtivo(fgAtivo != null && fgAtivo.equalsIgnoreCase("on"));
			
			try {
				
				/* upload imagem e pdf */
				
				if (ServletFileUpload.isMultipartContent(request)) {
					
					Part imagemFoto = request.getPart("foto");
					
					if (Objects.nonNull(imagemFoto) && imagemFoto.getInputStream().available() > 0) {
					
						String fotoBase64 = Base64.encodeBase64String(getBytes(imagemFoto.getInputStream()));
						
						usuario.setFotoBase64(fotoBase64);
						usuario.setContentType(imagemFoto.getContentType());	
						
						/* In�cio miniatura da imagem */
						
						//Transformar em um bufferedimage
						//necess�rio decodificar a imagem base64
						byte[] imageByteDecode = new Base64().decodeBase64(fotoBase64);
						
						BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageByteDecode));
						
						//Pega o tipo da imagem
						int type = bufferedImage.getType() == 0 ? BufferedImage.TYPE_INT_ARGB : bufferedImage.getType();
						
						//Cria imagem em miniatura
						BufferedImage resizedImage = new BufferedImage(100, 100, type);
						Graphics2D g = resizedImage.createGraphics();
						g.drawImage(bufferedImage, 0, 0, 100, 100, null);
						g.dispose();
						
						//Escrever imagem novamente
						ByteArrayOutputStream baos = new ByteArrayOutputStream();
						ImageIO.write(resizedImage, "png", baos);
						
						String miniaturaBase64 = getCabecalhoImage(baos.toByteArray());
						
						usuario.setFotoBase64Miniatura(miniaturaBase64);
						/* Fim miniatura da imagem */
						
					} else {
						usuario.setFotoBase64(request.getParameter("fotoTemp"));
						usuario.setContentType(request.getParameter("contentTypeTemp"));
					}
					
					/* processa pdf */
					Part curriculo = request.getPart("curriculo");
					
					if (Objects.nonNull(curriculo) && curriculo.getInputStream().available() > 0) {
						String curriculoBase64 = Base64.encodeBase64String(getBytes(curriculo.getInputStream()));
						
						usuario.setCurriculoBase64(curriculoBase64);
						usuario.setContentTypeCurriculo(curriculo.getContentType());
					} else {
						usuario.setCurriculoBase64(request.getParameter("curriculoTemp"));
						usuario.setContentTypeCurriculo(request.getParameter("contentTypeCurriculoTemp"));
					}
					
				}
				/* fim upload imagem e pdf */
				
				if (isFormInvalid(request)) {
					mensagem = "Formul�rio inv�lido. Preencha todos os campos dispon�ves";
				} else if (id == null || id.isEmpty()) {
					// valida login e sennha ao inserir
					if (!daoUsuario.isLoginValido(login)) {
						mensagem = "Usu�rio duplicado com o mesmo login";
					} else if (!daoUsuario.isSenhaValida(senha)) {
						mensagem = "Usu�rio duplicado com a mesma senha";
					} else {
						daoUsuario.salvarUsuario(usuario);
						actionIsValid = true;
						mensagem = "Salvou com sucesso";
					}

				} else if (id != null || !id.isEmpty()) {
					// valida login e sennha ao atualizar
					if (!daoUsuario.isLoginValidoUpdate(login, id)) {
						mensagem = "Usu�rio j� existente";
					} else if (!daoUsuario.isSenhaValidaUpdate(senha, id)) {
						mensagem = "Senha j� existente";
					} else {
						daoUsuario.atualizaUsuario(usuario);
						actionIsValid = true;
						mensagem = "Atualizou com sucesso";
					}
				}

				if (!actionIsValid) {
					request.setAttribute("user", usuario);
				}

				request.setAttribute("msg", mensagem);
				forwardCadastroUsuario(request, response, "usuarios");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	private boolean isFormInvalid(HttpServletRequest request) {
		String login = request.getParameter("login");
		String senha = request.getParameter("senha");
		String nome = request.getParameter("nome");
		String fone = request.getParameter("fone");
		
		boolean isLoginInvalid = login == null || login.isEmpty();
		boolean isSenhaInvalid = senha == null || senha.isEmpty();
		boolean isNomeInvalid = nome == null || nome.isEmpty();
		boolean isFoneInvalid = fone == null || fone.isEmpty();
		
		return isLoginInvalid || isSenhaInvalid  || isNomeInvalid || isFoneInvalid;
	}

	private void forwardCadastroUsuario(HttpServletRequest request, HttpServletResponse response, String object)
			throws Exception, ServletException, IOException {

		RequestDispatcher view = request.getRequestDispatcher("/cadastroUsuario.jsp");
		request.setAttribute(object, daoUsuario.findAll());
		view.forward(request, response);

	}
	
	/* Converte imagem para bytes */
	private byte[] getBytes(InputStream imagem) throws Exception {
		
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		int reads = imagem.read();
		
		while (reads != -1) {
			baos.write(reads);
			reads = imagem.read();
		}
		
		return baos.toByteArray();
	}
	
	public String getCabecalhoImage(byte[] bs) {
		return "data:image/png;base64," + DatatypeConverter.printBase64Binary(bs);
	}
}
