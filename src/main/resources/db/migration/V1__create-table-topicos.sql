create table topicos(
    id bigint not null auto_increment,
    titulo varchar(100) not null,
    mensagem varchar(2000) not null,
    data_de_criacao datetime not null,
    estado_do_topico varchar(100) not null,
    autor varchar(100) not null,
    curso varchar(100) not null,

    primary key(id)
);