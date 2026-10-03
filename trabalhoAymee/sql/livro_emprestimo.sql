-- Tabela livro
CREATE TABLE `livro` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `titulo` varchar(255) NOT NULL,
  `autor` varchar(255) NOT NULL,
  `isbn` varchar(20) DEFAULT NULL,
  `anoPublicacao` int DEFAULT NULL,
  `quemCadastrou` bigint DEFAULT NULL,
  `quemAlterouAUltimavez` bigint DEFAULT NULL,
  `dataCadastro` date DEFAULT NULL,
  `dataUltimaAlteracao` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_livro_quemCadastrou` (`quemCadastrou`),
  KEY `fk_livro_quemAlterouAUltimavez` (`quemAlterouAUltimavez`),
  CONSTRAINT `fk_livro_quemCadastrou` FOREIGN KEY (`quemCadastrou`) REFERENCES `pessoa` (`id`),
  CONSTRAINT `fk_livro_quemAlterouAUltimavez` FOREIGN KEY (`quemAlterouAUltimavez`) REFERENCES `pessoa` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Tabela emprestimo
CREATE TABLE `emprestimo` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `pessoaId` bigint NOT NULL,
  `livroId` bigint NOT NULL,
  `dataEmprestimo` date NOT NULL,
  `dataDevolucao` date DEFAULT NULL,
  `quemCadastrou` bigint DEFAULT NULL,
  `quemAlterouAUltimavez` bigint DEFAULT NULL,
  `dataCadastro` date DEFAULT NULL,
  `dataUltimaAlteracao` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_emprestimo_pessoa` (`pessoaId`),
  KEY `fk_emprestimo_livro` (`livroId`),
  KEY `fk_emprestimo_quemCadastrou` (`quemCadastrou`),
  KEY `fk_emprestimo_quemAlterouAUltimavez` (`quemAlterouAUltimavez`),
  CONSTRAINT `fk_emprestimo_pessoa` FOREIGN KEY (`pessoaId`) REFERENCES `pessoa` (`id`),
  CONSTRAINT `fk_emprestimo_livro` FOREIGN KEY (`livroId`) REFERENCES `livro` (`id`),
  CONSTRAINT `fk_emprestimo_quemCadastrou` FOREIGN KEY (`quemCadastrou`) REFERENCES `pessoa` (`id`),
  CONSTRAINT `fk_emprestimo_quemAlterouAUltimavez` FOREIGN KEY (`quemAlterouAUltimavez`) REFERENCES `pessoa` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
