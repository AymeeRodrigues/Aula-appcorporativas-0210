package br.cefetrj.appcorp.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class Livro extends GenericEntity {

    private String titulo;

    private String autor;

    private String isbn;

    private Integer anoPublicacao;
}
