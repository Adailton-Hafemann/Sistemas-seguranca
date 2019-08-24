CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `login` varchar(225) NOT NULL,
  `senha` varchar(225) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`);

INSERT INTO usuario (id,login,senha)VALUES(1,'silvino','aaa');