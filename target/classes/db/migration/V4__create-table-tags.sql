create table tags(

    id bigint not null auto_increment,
    chave varchar(100) not null,
    valor varchar(100),
    descricao varchar(200),
    ativo tinyint,

    primary key(id)
);