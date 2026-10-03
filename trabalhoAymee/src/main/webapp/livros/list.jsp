<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="br.cefetrj.appcorp.model.Livro" %>
<%
    List<Livro> livros = (List<Livro>) request.getAttribute("livros");
    if (livros == null) {
        response.sendRedirect(request.getContextPath() + "/livros");
        return;
    }
%>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Livros</title>
</head>
<body>
    <h1>Livros</h1>
    <p><a href="<%= request.getContextPath() %>/livros/form.jsp">Cadastrar novo livro</a></p>
    <p><a href="<%= request.getContextPath() %>/pessoas">Ir para Pessoas</a> |
       <a href="<%= request.getContextPath() %>/emprestimos">Ir para Empréstimos</a></p>
    <% if (livros.isEmpty()) { %>
        <p>Nenhum livro cadastrado.</p>
    <% } else { %>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Título</th>
                    <th>Autor</th>
                    <th>ISBN</th>
                    <th>Ano de Publicação</th>
                    <th>Data de Cadastro</th>
                    <th>Última Alteração</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
            <% for (Livro livro : livros) { %>
                <tr>
                    <td><%= livro.getId() %></td>
                    <td><%= livro.getTitulo() %></td>
                    <td><%= livro.getAutor() %></td>
                    <td><%= livro.getIsbn() != null ? livro.getIsbn() : "" %></td>
                    <td><%= livro.getAnoPublicacao() != null ? livro.getAnoPublicacao() : "" %></td>
                    <td><%= livro.getDataCadastro() %></td>
                    <td><%= livro.getDataUltimaAlteracao() %></td>
                    <td>
                        <a href="<%= request.getContextPath() %>/livro/edicao?id=<%= livro.getId() %>">Editar</a>
                        |
                        <a href="<%= request.getContextPath() %>/livro/deletar?id=<%= livro.getId() %>"
                           onclick="return confirm('Deseja remover este livro?')">Remover</a>
                    </td>
                </tr>
            <% } %>
            </tbody>
        </table>
    <% } %>
</body>
</html>
