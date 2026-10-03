package br.cefetrj.appcorp.controller;

import java.io.IOException;
import java.util.List;

import javax.sql.DataSource;

import br.cefetrj.appcorp.model.Livro;
import br.cefetrj.appcorp.service.LivroService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/livros")
public class LivroServlet extends HttpServlet {

    private LivroService getLivroService() {
        DataSource dataSource =
            (DataSource) getServletContext()
                .getAttribute("dataSource");
        return new LivroService(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Livro> livros = getLivroService().getAll();
        req.setAttribute("livros", livros);
        req.getRequestDispatcher("/livros/list.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        LivroService livroService = getLivroService();
        String id = req.getParameter("id");
        boolean edicao = id != null && !id.isEmpty();

        Livro livro = edicao
            ? livroService.getById(Long.valueOf(id))
            : new Livro();

        livro.setTitulo(req.getParameter("titulo"));
        livro.setAutor(req.getParameter("autor"));
        livro.setIsbn(req.getParameter("isbn"));
        String anoStr = req.getParameter("anoPublicacao");
        if (anoStr != null && !anoStr.isEmpty()) {
            livro.setAnoPublicacao(Integer.valueOf(anoStr));
        } else {
            livro.setAnoPublicacao(null);
        }

        if (edicao) {
            livroService.update(livro);
        } else {
            livroService.create(livro);
        }
        resp.sendRedirect(req.getContextPath() + "/livros");
    }
}
