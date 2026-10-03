package br.cefetrj.appcorp.controller;

import java.io.IOException;

import javax.sql.DataSource;

import br.cefetrj.appcorp.model.Emprestimo;
import br.cefetrj.appcorp.service.EmprestimoService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/emprestimo/deletar")
public class DeleteEmprestimoServlet extends HttpServlet {

    private EmprestimoService getEmprestimoService() {
        DataSource dataSource =
            (DataSource) getServletContext()
                .getAttribute("dataSource");
        return new EmprestimoService(dataSource);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            resp.sendRedirect(req.getContextPath() + "/emprestimos");
            return;
        }
        EmprestimoService emprestimoService = getEmprestimoService();
        Emprestimo emprestimo = emprestimoService.getById(Long.valueOf(idParam));
        if (emprestimo != null) {
            emprestimoService.delete(emprestimo);
        }
        resp.sendRedirect(req.getContextPath() + "/emprestimos");
    }
}
