package br.cefetrj.appcorp.controller;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import br.cefetrj.appcorp.model.Emprestimo;
import br.cefetrj.appcorp.model.Livro;
import br.cefetrj.appcorp.model.Pessoa;
import br.cefetrj.appcorp.service.EmprestimoService;
import br.cefetrj.appcorp.service.LivroService;
import br.cefetrj.appcorp.service.PessoaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/emprestimos")
public class EmprestimoServlet extends HttpServlet {

    private DataSource getDataSource() {
        return (DataSource) getServletContext().getAttribute("dataSource");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        DataSource ds = getDataSource();
        List<Emprestimo> emprestimos = new EmprestimoService(ds).getAll();
        req.setAttribute("emprestimos", emprestimos);
        req.getRequestDispatcher("/emprestimos/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        DataSource ds = getDataSource();
        EmprestimoService emprestimoService = new EmprestimoService(ds);
        PessoaService pessoaService = new PessoaService(ds);
        LivroService livroService = new LivroService(ds);

        String id = req.getParameter("id");
        boolean edicao = id != null && !id.isEmpty();

        Emprestimo emprestimo = edicao
            ? emprestimoService.getById(Long.valueOf(id))
            : new Emprestimo();

        Pessoa pessoa = pessoaService.getById(Long.valueOf(req.getParameter("pessoaId")));
        Livro livro = livroService.getById(Long.valueOf(req.getParameter("livroId")));

        emprestimo.setPessoa(pessoa);
        emprestimo.setLivro(livro);
        emprestimo.setDataEmprestimo(
            java.time.LocalDate.parse(req.getParameter("dataEmprestimo"))
        );

        String dataDevolucaoStr = req.getParameter("dataDevolucao");
        if (dataDevolucaoStr != null && !dataDevolucaoStr.isEmpty()) {
            emprestimo.setDataDevolucao(java.time.LocalDate.parse(dataDevolucaoStr));
        } else {
            emprestimo.setDataDevolucao(null);
        }

        if (edicao) {
            emprestimoService.update(emprestimo);
        } else {
            emprestimoService.create(emprestimo);
        }
        resp.sendRedirect(req.getContextPath() + "/emprestimos");
    }
}
