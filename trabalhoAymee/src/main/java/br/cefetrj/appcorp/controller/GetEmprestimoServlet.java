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

@WebServlet("/emprestimo/edicao")
public class GetEmprestimoServlet extends HttpServlet {

    private DataSource getDataSource() {
        return (DataSource) getServletContext().getAttribute("dataSource");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        DataSource ds = getDataSource();
        Emprestimo emprestimo = new EmprestimoService(ds).getById(Long.valueOf(req.getParameter("id")));
        List<Pessoa> pessoas = new PessoaService(ds).getAll();
        List<Livro> livros = new LivroService(ds).getAll();

        req.setAttribute("emprestimo", emprestimo);
        req.setAttribute("pessoas", pessoas);
        req.setAttribute("livros", livros);
        req.getRequestDispatcher("/emprestimos/form.jsp").forward(req, resp);
    }
}
