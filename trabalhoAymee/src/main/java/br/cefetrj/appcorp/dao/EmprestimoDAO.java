package br.cefetrj.appcorp.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import br.cefetrj.appcorp.model.Emprestimo;

public class EmprestimoDAO extends GenericDAO<Emprestimo> {

    private final PessoaDAO pessoaDAO;
    private final LivroDAO livroDAO;

    public EmprestimoDAO(DataSource dataSource) {
        super(dataSource);
        this.pessoaDAO = new PessoaDAO(dataSource);
        this.livroDAO = new LivroDAO(dataSource);
    }

    @Override
    protected String getTableName() {
        return "emprestimo";
    }

    @Override
    protected String getInsertSql() {
        return "INSERT INTO emprestimo (pessoaId, livroId, dataEmprestimo, dataDevolucao, " +
               "quemCadastrou, quemAlterouAUltimavez, dataCadastro, dataUltimaAlteracao) " +
               "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected void setInsertParameters(PreparedStatement statement, Emprestimo emprestimo) throws SQLException {
        statement.setLong(1, emprestimo.getPessoa().getId());
        statement.setLong(2, emprestimo.getLivro().getId());
        statement.setDate(3, Date.valueOf(emprestimo.getDataEmprestimo()));
        if (emprestimo.getDataDevolucao() != null) {
            statement.setDate(4, Date.valueOf(emprestimo.getDataDevolucao()));
        } else {
            statement.setNull(4, Types.DATE);
        }
        if (emprestimo.getQuemCadastrou() != null) {
            statement.setLong(5, emprestimo.getQuemCadastrou().getId());
        } else {
            statement.setNull(5, Types.BIGINT);
        }
        if (emprestimo.getQuemAlterouAUltimavez() != null) {
            statement.setLong(6, emprestimo.getQuemAlterouAUltimavez().getId());
        } else {
            statement.setNull(6, Types.BIGINT);
        }
        statement.setDate(7, Date.valueOf(emprestimo.getDataCadastro()));
        statement.setDate(8, Date.valueOf(emprestimo.getDataUltimaAlteracao()));
    }

    @Override
    protected String getUpdateSql() {
        return "UPDATE emprestimo SET pessoaId=?, livroId=?, dataEmprestimo=?, dataDevolucao=?, " +
               "quemAlterouAUltimavez=?, dataUltimaAlteracao=? WHERE id=?";
    }

    @Override
    protected void setUpdateParameters(PreparedStatement statement, Emprestimo emprestimo) throws SQLException {
        statement.setLong(1, emprestimo.getPessoa().getId());
        statement.setLong(2, emprestimo.getLivro().getId());
        statement.setDate(3, Date.valueOf(emprestimo.getDataEmprestimo()));
        if (emprestimo.getDataDevolucao() != null) {
            statement.setDate(4, Date.valueOf(emprestimo.getDataDevolucao()));
        } else {
            statement.setNull(4, Types.DATE);
        }
        if (emprestimo.getQuemAlterouAUltimavez() != null) {
            statement.setLong(5, emprestimo.getQuemAlterouAUltimavez().getId());
        } else {
            statement.setNull(5, Types.BIGINT);
        }
        statement.setDate(6, Date.valueOf(emprestimo.getDataUltimaAlteracao()));
        statement.setLong(7, emprestimo.getId());
    }

    @Override
    protected Emprestimo mapResultSetToEntity(ResultSet rs) throws SQLException {
        Emprestimo emprestimo = new Emprestimo();
        emprestimo.setId(rs.getLong("id"));

        long pessoaId = rs.getLong("pessoaId");
        if (!rs.wasNull()) {
            emprestimo.setPessoa(pessoaDAO.getById(pessoaId));
        }

        long livroId = rs.getLong("livroId");
        if (!rs.wasNull()) {
            emprestimo.setLivro(livroDAO.getById(livroId));
        }

        Date dataEmprestimo = rs.getDate("dataEmprestimo");
        if (dataEmprestimo != null) {
            emprestimo.setDataEmprestimo(dataEmprestimo.toLocalDate());
        }

        Date dataDevolucao = rs.getDate("dataDevolucao");
        if (dataDevolucao != null) {
            emprestimo.setDataDevolucao(dataDevolucao.toLocalDate());
        }

        Date dataCadastro = rs.getDate("dataCadastro");
        if (dataCadastro != null) {
            emprestimo.setDataCadastro(dataCadastro.toLocalDate());
        }

        Date dataUltimaAlteracao = rs.getDate("dataUltimaAlteracao");
        if (dataUltimaAlteracao != null) {
            emprestimo.setDataUltimaAlteracao(dataUltimaAlteracao.toLocalDate());
        }

        long quemCadastrouId = rs.getLong("quemCadastrou");
        if (!rs.wasNull()) {
            emprestimo.setQuemCadastrou(pessoaDAO.getById(quemCadastrouId));
        }

        long quemAlterouId = rs.getLong("quemAlterouAUltimavez");
        if (!rs.wasNull()) {
            emprestimo.setQuemAlterouAUltimavez(pessoaDAO.getById(quemAlterouId));
        }

        return emprestimo;
    }
}
