INSERT INTO `rol` (`nombre`) VALUES ('ADMIN');
INSERT INTO `usuario` (`nombre`, `pass`, `rol_nombre`) VALUES ('senate', '$2y$12$Wb7TBFTVpSmoymMvJU/7rep2/eG0Okd1Es8mNlybF5H5723rVjpz2', 'ADMIN');
INSERT INTO `plataformas` (`id`,`anio` ,`descripcion`,`img`,`nombre`) VALUES('GBA', 2001,'La Game boy esta bien', LOAD_FILE(''), 'Game Boy Advance');
INSERT INTO `desarrolladores` (`id`,`anio` ,`descripcion`,`img`,`nombre`, `pais`) VALUES('Gamefreak', 2001,'esta guapo', LOAD_FILE(''), 'Game Freak', 'Japón');
INSERT INTO `distribuidores` (`id`,`anio` ,`descripcion`,`img`,`nombre`, `pais`) VALUES('Nintendo', 2001,'esta guapo', LOAD_FILE(''), 'Nintendo', 'Japón');

