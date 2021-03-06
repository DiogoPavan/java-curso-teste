package beans;

/**
 * Classes que ficam em mem�ria
 * 
 * @author Diogo Reis Pavan
 * 
 */
public class BeanCurso {

	private Long idBeanCurso;
	private String login;
	private String nome;
	private String senha;	
	private String fone;
	private String cep;
	private String rua;
	private String bairro;
	private String cidade;
	private String estado;
	private String ibge;
	private String fotoBase64;
	private String fotoBase64Miniatura;
	private String contentType;
	private String curriculoBase64;
	private String contentTypeCurriculo;
	private String tempFotoUser;
	private Boolean fgAtivo;

	public Boolean getFgAtivo() {
		return fgAtivo;
	}

	public void setFgAtivo(Boolean fgAtivo) {
		this.fgAtivo = fgAtivo;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Long getIdBeanCurso() {
		return idBeanCurso;
	}

	public void setIdBeanCurso(Long idBeanCurso) {
		this.idBeanCurso = idBeanCurso;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getFone() {
		return fone;
	}

	public void setFone(String fone) {
		this.fone = fone;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getIbge() {
		return ibge;
	}

	public void setIbge(String ibge) {
		this.ibge = ibge;
	}

	public String getFotoBase64() {
		return fotoBase64;
	}

	public void setFotoBase64(String fotoBase64) {
		this.fotoBase64 = fotoBase64;
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}
	
	public String getTempFotoUser() {
		
		tempFotoUser = "data:"+ this.contentType + ";base64," + this.fotoBase64;
		
		return tempFotoUser;
	}

	public String getCurriculoBase64() {
		return curriculoBase64;
	}

	public void setCurriculoBase64(String curriculoBase64) {
		this.curriculoBase64 = curriculoBase64;
	}

	public String getContentTypeCurriculo() {
		return contentTypeCurriculo;
	}

	public void setContentTypeCurriculo(String contentTypeCurriculo) {
		this.contentTypeCurriculo = contentTypeCurriculo;
	}

	public String getFotoBase64Miniatura() {
		return fotoBase64Miniatura;
	}

	public void setFotoBase64Miniatura(String fotoBase64Miniatura) {
		this.fotoBase64Miniatura = fotoBase64Miniatura;
	}
	
}
