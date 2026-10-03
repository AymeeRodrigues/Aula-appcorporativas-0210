package br.cefetrj.appcorp.model;

import java.time.LocalDate;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Emprestimo extends GenericEntity {

    private Pessoa pessoa;

    private Livro livro;

    private LocalDate dataEmprestimo;

    private LocalDate dataDevolucao;
}
