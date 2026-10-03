package br.cefetrj.appcorp.dao;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import javax.sql.DataSource;

import br.cefetrj.appcorp.model.Livro;

public class LivroDAO extends GenericDAO<Livro> {

    private final PessoaDAO pessoaDAO;

    public LivroDAO(DataSource dataSource) {
        super(dataSource);
        this.pessoaDAO = new PessoaDAO(dataSource);
    }

    @Override
    protected String getTableName() {
        return "livro";
    }

    @Override
    protected String getInsertSql() {
        return "INSERT INTO livro (titulo, autor, isbn, anoPublicacao, quemCadastrou, quemAlterouAUltimavez, dataCadastro, dataUltimaAlteracao) " +
               "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    }

    @Override
    protected void setInsertParameters(PreparedStatement statement, Livro livro) throws SQLException {
        statement.setString(1, livro.getTitulo());
        statement.setString(2, livro.getAutor());
        statement.setString(3, livro.getIsbn());
        if (livro.getAnoPublicacao() != null) {
            statement.setInt(4, livro.getAnoPublicacao());
        } else {
            statement.setNull(4, Types.INTEGER);
        }
        if (livro.getQuemCadastrou() != null) {
            statement.setLong(5, livro.getQuemCadastrou().getId());
        } else {
            statement.setNull(5, Types.BIGINT);
        }
        if (livro.getQuemAlterouAUltimavez() != null) {
            statement.setLong(6, livro.getQuemAlterouAUltimavez().getId());
        } else {
            statement.setNull(6, Types.BIGINT);
        }
        statement.setDate(7, Date.valueOf(livro.getDataCadastro()));
        statement.setDate(8, Date.valueOf(livro.getDataUltimaAlteracao()));
    }

    @Override
    protected String getUpdateSql() {
        return "UPDATE livro SET titulo=?, autor=?, isbn=?, anoPublicacao=?, quemAlterouAUltimavez=?, dataUltimaAlteracao=? WHERE id=?";
    }

    @Override
    protected void setUpdateParameters(PreparedStatement statement, Livro livro) throws SQLException {
        statement.setString(1, livro.getTitulo());
        statement.setString(2, livro.getAutor());
        statement.setString(3, livro.getIsbn());
        if (livro.getAnoPublicacao() != null) {
            statement.setInt(4, livro.getAnoPublicacao());
        } else {
            statement.setNull(4, Types.INTEGER);
        }
        if (livro.getQuemAlterouAUltimavez() != null) {
            statement.setLong(5, livro.getQuemAlterouAUltimavez().getId());
        } else {
            statement.setNull(5, Types.BIGINT);
        }
        statement.setDate(6, Date.valueOf(livro.getDataUltimaAlteracao()));
        statement.setLong(7, livro.getId());
    }

    @Override
    protected Livro mapResultSetToEntity(ResultSet rs) throws SQLException {
        Livro livro = new Livro();
        livro.setId(rs.getLong("id"));
        livro.setTitulo(rs.getString("titulo"));
        livro.setAutor(rs.getString("autor"));
        livro.setIsbn(rs.getString("isbn"));
        int ano = rs.getInt("anoPublicacao");
        if (!rs.wasNull()) {
            livro.setAnoPublicacao(ano);
        }
        Date dataCadastro = rs.getDate("dataCadastro");
        if (dataCadastro != null) {
            livro.setDataCadastro(dataCadastro.toLocalDate());
        }
        Date dataUltimaAlteracao = rs.getDate("dataUltimaAlteracao");
        if (dataUltimaAlteracao != null) {
            livro.setDataUltimaAlteracao(dataUltimaAlteracao.toLocalDate());
        }
        long quemCadastrouId = rs.getLong("quemCadastrou");
        if (!rs.wasNull()) {
            livro.setQuemCadastrou(pessoaDAO.getById(quemCadastrouId));
        }
        long quemAlterouId = rs.getLong("quemAlterouAUltimavez");
        if (!rs.wasNull()) {
            livro.setQuemAlterouAUltimavez(pessoaDAO.getById(quemAlterouId));
        }
        return livro;
    }
}
