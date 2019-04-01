<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Insert title here</title>
    <link rel="stylesheet" href="resources/css/cadastro.css">
    <!-- Adicionando JQuery -->
    <script src="https://code.jquery.com/jquery-3.2.1.min.js"
            integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
            crossorigin="anonymous"></script>
</head>
<body>
<a href="acessoMenu.jsp">Início</a>
<center>
    <h1>Cadastro Usuário</h1>
</center>

<center>
    <h3 style="color: red;">${msg}</h3>
</center>

<center>
    <form action="salvarUsuario" method="post" id="form"
          onsubmit="return validarCampos()" enctype="multipart/form-data">
        <ul class="form-style-1">
            <li>
                <table>
                    <tr>
                        <td>Código:</td>
                        <td><input type="text" id="id" readonly="readonly" name="id"
                                   value="${user.idBeanCurso}" class="field-long"></td>

                        <td>Cidade:</td>
                        <td><input type="text" id="cidade" name="cidade"
                                   value="${user.cidade}"></td>
                    </tr>
                    <tr>
                        <td>Login:</td>
                        <td><input type="text" id="login" name="login"
                                   value="${user.login}" class="field-long"></td>

                        <td>Rua:</td>
                        <td><input type="text" id="rua" name="rua"
                                   value="${user.rua}"></td>
                    </tr>
                    <tr>
                        <td>Senha:</td>
                        <td><input type="password" id="senha" name="senha"
                                   value="${user.senha}" class="field-long"></td>

                        <td>Bairro:</td>
                        <td><input type="text" id="bairro" name="bairro"
                                   value="${user.bairro}"></td>
                    </tr>
                    <tr>
                        <td>Nome:</td>
                        <td><input type="text" id="nome" name="nome"
                                   value="${user.nome}" class="field-long"></td>

                        <td>Estado:</td>
                        <td><input type="text" id="estado" name="estado"
                                   value="${user.estado}"></td>
                    </tr>
                    <tr>
                        <td>Fone:</td>
                        <td><input type="text" id="fone" name="fone"
                                   value="${user.fone}" class="field-long"></td>

                        <td>Ibge:</td>
                        <td><input type="text" id="ibge" name="ibge"
                                   value="${user.ibge}" class="field-long"></td>

                    </tr>

                    <tr>
                        <td>CEP:</td>
                        <td><input type="text" id="cep" name="cep"
                                   onblur="consultaCep();" value="${user.cep}" class="field-long"></td>
                        <td>Ativo:</td>
                        <td><input type="checkbox" id="ativo" name="ativo" value="${user.fgAtivo}">

                    </tr>

                    <tr>
                        <td>Foto:</td>
                        <td>
                            <input type="file" name="foto" value="Foto" accept=".jpeg, .jpg, .png">
                            <input type="text" name="fotoTemp" value="${user.fotoBase64}" readonly="readonly"
                                   style="display:none;">
                            <input type="text" name="contentTypeTemp" value="${user.contentType}" readonly="readonly"
                                   style="display:none;">
                        </td>
                    </tr>

                    <tr>
                        <td>Currículo:</td>
                        <td>
                            <input type="file" name="curriculo" value="curriculo" accept=".pdf">
                            <input type="text" name="curriculoTemp" value="${user.curriculoBase64}" readonly="readonly"
                                   style="display:none;">
                            <input type="text" name="contentTypeCurriculoTemp" value="${user.contentTypeCurriculo}"
                                   readonly="readonly" style="display:none;">
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value="Salvar"> <input
                                type="submit" value="Cancelar"
                                onclick="document.getElementById('form').action='salvarUsuario?acao=reset'"></td>
                    </tr>
                </table>
            </li>
        </ul>
    </form>
</center>
<div class="container">
    <table class="responsive-table">
        <thead>
        <tr>
            <th scope="col">Id</th>
            <th scope="col">Usuário</th>
            <th scope="col">Foto</th>
            <th scope="col">Curriculo</th>
            <th scope="col">Nome</th>
            <th scope="col">Fone</th>
            <th scope="col">Deletar</th>
            <th scope="col">Editar</th>
            <th scope="col">Fones</th>
        </tr>
        </thead>
        <c:forEach items="${usuarios}" var="user">
            <tr>
                <td><c:out value="${user.idBeanCurso}"></c:out></td>
                <td><c:out value="${user.login}"></c:out></td>

                <c:if test="${user.fotoBase64Miniatura.isEmpty() == false}">
                    <td>
                        <a href="salvarUsuario?acao=download&tipo=imagem&user=${user.idBeanCurso}">
                            <img src='<c:out value="${user.fotoBase64Miniatura}"></c:out>'
                                 width="32px" height="32px" title="Imagem"/>
                        </a>
                    </td>
                </c:if>
                <c:if test="${user.fotoBase64Miniatura.isEmpty() == true}">
                    <td><img src="resources/img/userpadrao.png" width="32px" height="32px" title="Imagem Padrão"/></td>
                </c:if>

                <c:if test="${user.curriculoBase64.isEmpty() == false}">
                    <td>
                        <a href="salvarUsuario?acao=download&tipo=pdf&user=${user.idBeanCurso}">
                            <img src="resources/img/pdf.png" width="32px" height="32px" title="Imagem"/>
                        </a>
                    </td>
                </c:if>
                <c:if test="${user.curriculoBase64 == null || user.curriculoBase64.isEmpty() == true}">
                    <td><img src="resources/img/nopdf.png" width="32px" height="32px" title="Sem currículo"/></td>
                </c:if>

                <td><c:out value="${user.nome}"></c:out></td>
                <td><c:out value="${user.fone}"></c:out></td>
                <td><a
                        href="salvarUsuario?acao=delete&user=${user.idBeanCurso}"
                        onclick="return confirm('Confirmar a exclusão?');"> <img
                        src="resources/img/excluir.png" width="20px" height="20px"
                        title="Excluir"/>
                </a></td>
                <td><a
                        href="salvarUsuario?acao=editar&user=${user.idBeanCurso}"> <img
                        src="resources/img/editar.png" width="20px" height="20px"
                        title="Editar"/>
                </a></td>
                <td><a href="salvarTelefone?id=${user.idBeanCurso}"> <img
                        src="resources/img/phone.png" width="20px" height="20px"
                        title="Telefones"/>
                </a></td>
            </tr>
        </c:forEach>
    </table>
</div>
<script type="text/javascript">
    function validarCampos() {
        var isValid = true;
        var action = document.getElementById('form').action;

        if (!action.endsWith('salvarUsuario?acao=reset')) {
            if (document.getElementById("login").value == '') {
                alert('Informe o login');
                isValid = false;
            } else if (document.getElementById("senha").value == '') {
                alert('Informe o senha');
                isValid = false;
            } else if (document.getElementById("nome").value == '') {
                alert('Informe o nome');
                isValid = false;
            } else if (document.getElementById("fone").value == '') {
                alert('Informe o fone');
                isValid = false;
            }
        }

        return isValid;
    }

    function consultaCep() {
        var cep = $("#cep").val();

        $.getJSON("https://viacep.com.br/ws/" + cep + "/json/?callback=?",
            function (dados) {

                if (!("erro" in dados)) {

                    $("#rua").val(dados.logradouro);
                    $("#bairro").val(dados.bairro);
                    $("#cidade").val(dados.localidade);
                    $("#estado").val(dados.uf);
                    $("#ibge").val(dados.ibge);

                } else {
                    //CEP pesquisado não foi encontrado.
                    limpa_formulário_cep();
                    alert("CEP não encontrado.");
                }
            });
    }

    function limpa_formulário_cep() {
        // Limpa valores do formulário de cep.
        $("#cep").val("");
        $("#rua").val("");
        $("#bairro").val("");
        $("#cidade").val("");
        $("#estado").val("");
        $("#ibge").val("");
    }
</script>
</body>
</html>