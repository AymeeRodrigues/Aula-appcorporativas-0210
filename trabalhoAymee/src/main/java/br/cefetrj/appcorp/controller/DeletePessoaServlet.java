package br.cefetrj.appcorp.controller;

import java.io.IOException;

import javax.sql.DataSource;

import br.cefetrj.appcorp.model.Pessoa;
import br.cefetrj.appcorp.service.PessoaService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/pessoa/deletar")
public class DeletePessoaServlet extends HttpServlet {

    private PessoaService getPessoaService() {
        DataSource dataSource =
            (DataSource) getServletContext()
                .getAttribute("dataSource");
        return new PessoaService(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/pessoas");
            return;
        }
        PessoaService pessoaService = getPessoaService();
        Pessoa pessoa = pessoaService.getById(Long.valueOf(idParam));
        if (pessoa != null) {
            pessoaService.delete(pessoa);
        }
        resp.sendRedirect(req.getContextPath() + "/pessoas");
    }
}
