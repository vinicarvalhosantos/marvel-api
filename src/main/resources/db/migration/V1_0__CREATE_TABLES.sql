-- SCRIPT PARA CRIACAO DA TABELA DE HEROIS
CREATE TABLE marvel_characters (
                                                id BIGINT auto_increment NOT NULL,
                                                name VARCHAR(100) NOT NULL,
                                                description VARCHAR(100) NOT NULL,
                                                modified DATETIME NULL,
                                                CONSTRAINT marvel_characters_PK PRIMARY KEY (id)
)
    ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci
AUTO_INCREMENT=1548;


-- SCRIPT PARA CRIACAO DA TABELA DE QUADRINHOS
CREATE TABLE `marvel_comics` (
                                 `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                 `description` varchar(255) DEFAULT NULL,
                                 `digital_id` bigint(20) DEFAULT NULL,
                                 `issue_number` int(11) DEFAULT NULL,
                                 `modified` datetime DEFAULT NULL,
                                 `title` varchar(255) DEFAULT NULL,
                                 `variant_description` varchar(255) DEFAULT NULL,
                                 PRIMARY KEY (`id`)
)
    ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci
AUTO_INCREMENT=10547;

-- SCRIPT PARA CRIAÇAO DA TABELA DE LIGACAO ENTRE HEROI E QUADRINHO
CREATE TABLE `marvel_characters_comics` (
                                            `marvel_characters_id` bigint(20) NOT NULL,
                                            `comics_id` bigint(20) NOT NULL
)
    ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;

-- SCRIPT PARA CRIAÇAO DA TABELA DE EVENTOS
CREATE TABLE `marvel_events` (
                                 `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                 `description` varchar(255) DEFAULT NULL,
                                 `title` varchar(255) DEFAULT NULL,
                                 PRIMARY KEY (`id`)
)
    ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci
AUTO_INCREMENT=253;

-- SCRIPT PARA CRIAÇAO DA TABELA DE LIGACAO ENTRE HEROI E EVENTO
CREATE TABLE `marvel_characters_events` (
                                            `marvel_characters_id` bigint(20) NOT NULL,
                                            `events_id` bigint(20) NOT NULL
)
    ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;

-- SCRIPT PARA CRIAR A TABELA DE COLECAO
CREATE TABLE `marvel_series` (
                                 `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                 `description` varchar(255) DEFAULT NULL,
                                 `title` varchar(255) DEFAULT NULL,
                                 PRIMARY KEY (`id`)
)
    ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci
AUTO_INCREMENT=154;

-- SCRIPT PARA CRIAR A TABELA DE HISTORIAS
CREATE TABLE `marvel_stories` (
                                  `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                  `description` varchar(255) DEFAULT NULL,
                                  `modified` datetime DEFAULT NULL,
                                  `title` varchar(255) DEFAULT NULL,
                                  `type` varchar(255) DEFAULT NULL,
                                  PRIMARY KEY (`id`)
)
    ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci
AUTO_INCREMENT=173;

-- SCRIPT PARA CRIAR A TABELA DE LIGAÇAO ENTRE HEROIS E COLECAO
CREATE TABLE `marvel_characters_series` (
                                            `marvel_characters_id` bigint(20) NOT NULL,
                                            `series_id` bigint(20) NOT NULL
)
    ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;


-- SCRIPT PARA CRIAR A TABELA DE LIGAÇAO ENTRE HEROIS E HISTORIA
CREATE TABLE `marvel_characters_stories` (
                                             `marvel_characters_id` bigint(20) NOT NULL,
                                             `stories_id` bigint(20) NOT NULL
)
    ENGINE=InnoDB
DEFAULT CHARSET=utf8
COLLATE=utf8_general_ci;