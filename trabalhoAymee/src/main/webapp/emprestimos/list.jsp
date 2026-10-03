<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="br.cefetrj.appcorp.model.Emprestimo" %>
<%
    List<Emprestimo> emprestimos = (List<Emprestimo>) request.getAttribute("emprestimos");
    if (emprestimos == null) {
        response.sendRedirect(request.getContextPath() + "/emprestimos");
        return;
    }
%>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Empréstimos</title>
</head>
<body>
    <h1>Empréstimos</h1>
    <p><a href="<%= request.getContextPath() %>/emprestimo/novo">Registrar novo empréstimo</a></p>
    <p><a href="<%= request.getContextPath() %>/pessoas">Ir para Pessoas</a> |
       <a href="<%= request.getContextPath() %>/livros">Ir para Livros</a></p>
    <% if (emprestimos.isEmpty()) { %>
        <p>Nenhum empréstimo registrado.</p>
    <% } else { %>
        <table border="1">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Pessoa</th>
                    <th>Livro</th>
                    <th>Data do Empréstimo</th>
                    <th>Data de Devolução</th>
                    <th>Data de Cadastro</th>
                    <th>Última Alteração</th>
                    <th>Ações</th>
                </tr>
            </thead>
            <tbody>
            <% for (Emprestimo emp : emprestimos) { %>
                <tr>
                    <td><%= emp.getId() %></td>
                    <td><%= emp.getPessoa() != null ? emp.getPessoa().getNome() : "" %></td>
                    <td><%= emp.getLivro() != null ? emp.getLivro().getTitulo() : "" %></td>
                    <td><%= emp.getDataEmprestimo() %></td>
                    <td><%= emp.getDataDevolucao() != null ? emp.getDataDevolucao() : "Em aberto" %></td>
                    <td><%= emp.getDataCadastro() %></td>
                    <td><%= emp.getDataUltimaAlteracao() %></td>
                    <td>
                        <a href="<%= request.getContextPath() %>/emprestimo/edicao?id=<%= emp.getId() %>">Editar</a>
                        |
                        <a href="<%= request.getContextPath() %>/emprestimo/deletar?id=<%= emp.getId() %>"
                           onclick="return confirm('Deseja remover este empréstimo?')">Remover</a>
                    </td>
                </tr>
            <% } %>
            </tbody>
        </table>
    <% } %>
</body>
</html>
