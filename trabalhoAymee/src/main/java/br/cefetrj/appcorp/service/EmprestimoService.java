package br.cefetrj.appcorp.service;

import javax.sql.DataSource;

import br.cefetrj.appcorp.dao.EmprestimoDAO;
import br.cefetrj.appcorp.model.Emprestimo;

public class EmprestimoService extends GenericService<Emprestimo> {

    public EmprestimoService(DataSource dataSource) {
        super(new EmprestimoDAO(dataSource));
    }
}
