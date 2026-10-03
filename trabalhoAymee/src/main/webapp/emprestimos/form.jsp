<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.List" %>
<%@ page import="br.cefetrj.appcorp.model.Emprestimo" %>
<%@ page import="br.cefetrj.appcorp.model.Pessoa" %>
<%@ page import="br.cefetrj.appcorp.model.Livro" %>
<%
    Emprestimo emprestimo = (Emprestimo) request.getAttribute("emprestimo");
    List<Pessoa> pessoas = (List<Pessoa>) request.getAttribute("pessoas");
    List<Livro> livros = (List<Livro>) request.getAttribute("livros");
    boolean edicao = emprestimo != null;
%>
<!DOCTYPE html>
<html lang="pt">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title><%= edicao ? "Editar Empréstimo" : "Registrar Empréstimo" %></title>
</head>
<body>
    <h1><%= edicao ? "Editar Empréstimo" : "Registrar Empréstimo" %></h1>
    <form action="<%= request.getContextPath() %>/emprestimos" method="post">
        <% if (edicao) { %>
            <input type="hidden" name="id" value="<%= emprestimo.getId() %>">
        <% } %>

        <label for="pessoaId">Pessoa:</label>
        <select id="pessoaId" name="pessoaId" required>
            <option value="">-- Selecione --</option>
            <% if (pessoas != null) { for (Pessoa p : pessoas) { %>
                <option value="<%= p.getId() %>"
                    <%= (edicao && emprestimo.getPessoa() != null && emprestimo.getPessoa().getId().equals(p.getId())) ? "selected" : "" %>>
                    <%= p.getNome() %>
                </option>
            <% } } %>
        </select>
        <br>

        <label for="livroId">Livro:</label>
        <select id="livroId" name="livroId" required>
            <option value="">-- Selecione --</option>
            <% if (livros != null) { for (Livro l : livros) { %>
                <option value="<%= l.getId() %>"
                    <%= (edicao && emprestimo.getLivro() != null && emprestimo.getLivro().getId().equals(l.getId())) ? "selected" : "" %>>
                    <%= l.getTitulo() %> — <%= l.getAutor() %>
                </option>
            <% } } %>
        </select>
        <br>

        <label for="dataEmprestimo">Data do Empréstimo:</label>
        <input type="date" id="dataEmprestimo" name="dataEmprestimo" required
               value="<%= (edicao && emprestimo.getDataEmprestimo() != null) ? emprestimo.getDataEmprestimo() : "" %>">
        <br>

        <label for="dataDevolucao">Data de Devolução:</label>
        <input type="date" id="dataDevolucao" name="dataDevolucao"
               value="<%= (edicao && emprestimo.getDataDevolucao() != null) ? emprestimo.getDataDevolucao() : "" %>">
        <br>

        <input type="submit" value="<%= edicao ? "Salvar Alterações" : "Registrar Empréstimo" %>">
    </form>
    <p><a href="<%= request.getContextPath() %>/emprestimos">Voltar para a lista</a></p>
</body>
</html>
