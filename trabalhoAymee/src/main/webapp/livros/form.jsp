<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="br.cefetrj.appcorp.model.Livro" %>
<%
    Livro livro = (Livro) request.getAttribute("livro");
    boolean edicao = livro != null;
%>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= edicao ? "Editar Livro" : "Cadastrar Livro" %></title>
</head>
<body>
    <h1><%= edicao ? "Editar Livro" : "Cadastrar Livro" %></h1>
    <form action="<%= request.getContextPath() %>/livros" method="post">
        <% if (edicao) { %>
            <input type="hidden" name="id" value="<%= livro.getId() %>">
        <% } %>

        <label for="titulo">Título:</label>
        <input type="text" id="titulo" name="titulo" required
               value="<%= edicao ? livro.getTitulo() : "" %>">
        <br>

        <label for="autor">Autor:</label>
        <input type="text" id="autor" name="autor" required
               value="<%= edicao ? livro.getAutor() : "" %>">
        <br>

        <label for="isbn">ISBN:</label>
        <input type="text" id="isbn" name="isbn"
               value="<%= (edicao && livro.getIsbn() != null) ? livro.getIsbn() : "" %>">
        <br>

        <label for="anoPublicacao">Ano de Publicação:</label>
        <input type="number" id="anoPublicacao" name="anoPublicacao" min="1" max="9999"
               value="<%= (edicao && livro.getAnoPublicacao() != null) ? livro.getAnoPublicacao() : "" %>">
        <br>

        <input type="submit" value="<%= edicao ? "Salvar Alterações" : "Cadastrar Livro" %>">
    </form>
    <p><a href="<%= request.getContextPath() %>/livros">Voltar para a lista</a></p>
</body>
</html>
