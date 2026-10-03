package br.cefetrj.appcorp.controller;

import java.io.IOException;

import javax.sql.DataSource;

import br.cefetrj.appcorp.model.Livro;
import br.cefetrj.appcorp.service.LivroService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/livro/deletar")
public class DeleteLivroServlet extends HttpServlet {

    private LivroService getLivroService() {
        DataSource dataSource =
            (DataSource) getServletContext()
                .getAttribute("dataSource");
        return new LivroService(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/livros");
            return;
        }
        LivroService livroService = getLivroService();
        Livro livro = livroService.getById(Long.valueOf(idParam));
        if (livro != null) {
            livroService.delete(livro);
        }
        resp.sendRedirect(req.getContextPath() + "/livros");
    }
}
