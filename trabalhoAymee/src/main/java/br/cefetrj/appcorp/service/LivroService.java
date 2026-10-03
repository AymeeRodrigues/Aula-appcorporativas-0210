package br.cefetrj.appcorp.service;

import javax.sql.DataSource;

import br.cefetrj.appcorp.dao.LivroDAO;
import br.cefetrj.appcorp.model.Livro;

public class LivroService extends GenericService<Livro> {

    public LivroService(DataSource dataSource) {
        super(new LivroDAO(dataSource));
    }
}
