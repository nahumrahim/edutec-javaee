
insert into ROL values (1, 'Administrador del Sistema', 'ADMIN')
insert into ROL values (2, 'Profesor', 'PROFE')
insert into ROL values (3, 'Estudiante regular', 'ESTUDIANTE')

insert into usuario values(1, 'admin', 'admin@admon.com', '1981-07-23', null, 'Administrador del Sistema', null, 'holamundo1.', null, '1234124', 1)
insert into usuario values(2, 'yesteban', 'yesteban09@gmail.com', '1907-05-25', null, 'Yoli Esteban', null, 'holamundo1.', null, '3423423', 2)
insert into usuario values(3, 'jlpazos', 'leokirap014@gmail.com', '1994-01-07', null, 'Jorge Leonel Lam Pazos', null, 'holamundo1.', null, '534563', 3)
insert into usuario values(4, 'mmutzus', 'elmutzus@gmail.com', '1987-01-02', null, 'Manuel Mutzus', null, 'helloworld1.', null, '3456456', 3)
insert into usuario values(5, 'arigalt', 'arigalt@gmail.com', '1978-02-08', null, 'Alejandro Rigalt', null, 'holamundo1.', null, '57456567', 2)

insert into PARAMETRO_SISTEMA VALUES (1, 'MODO_DEBUG', '0')
insert into PARAMETRO_SISTEMA VALUES (2, 'NUMERO_DECIMALES', '2')
insert into PARAMETRO_SISTEMA VALUES (3, 'BANGUAT_TIPOCAMBIO_WSDL', 'https://www.banguat.gob.gt/variables/ws/TipoCambio.asmx?WSDL')
insert into PARAMETRO_SISTEMA VALUES (4, 'TARJETA_VALIDAR_URL', 'http://localhost:8081/tarjeta/api/consultas/validate/')
insert into PARAMETRO_SISTEMA VALUES (5, 'TARJETA_PAGAR_URL', 'http://localhost:8081/tarjeta/api/consultas/pagar')

insert into DEPARTAMENTO values (1, 'GUA', 'Guatemala')
insert into DEPARTAMENTO values (2, 'SAC', 'Sacatepéquez')

insert into MUNICIPIO values (1, 'GUATE', 'Guatemala', 1)
insert into MUNICIPIO values (2, 'MXC', 'Mixco', 1)
insert into MUNICIPIO values (3, 'VLLN', 'Villa Nueva', 1)

insert into SEDE values (1, 'EDUTEC', 'Edutec', 'Zona 4 Guatemala', 1)
insert into SEDE values (2, 'USAC', 'Universidad de San Carlos de Guatema', 'Zona 12 Guatemala', 1)

insert into SALON values (1, '201', 1)
insert into SALON values (2, '202', 1)
insert into SALON values (3, '203', 1)

insert into CICLO values (1, '2017')
insert into CICLO values (2, '2018')

insert into CURSO values (1, 'MATE1', 'Matemática 1', 1, 2)
insert into CURSO values (2, 'JAVAEE', 'Java Empresarial', 1, 2)
insert into CURSO values (3, '.NET', '.NET Web API empresarial', 1, 2)
insert into CURSO values (4, 'NG5', 'Angular 5', 1, 2)

insert into estudiante values(1, '1111', 'Yoli Esteban', 'Guatemala')
insert into estudiante values(2, '2222', 'Manuel Mutzua', 'Guatemala')
insert into estudiante values(3, '3333', 'Jorge Lam', 'Guatemala')
insert into estudiante values(4, '4444', 'Berny Torres', 'Guatemala')
insert into estudiante values(5, '5555', 'Alejandro Rigalt', 'Guatemala')

insert into ASIGNACION_ESTUDIANTE (id_estudiante, id_curso, zona, examen_final, nota_final) values( 1, 2, 0, 0, 0)
insert into ASIGNACION_ESTUDIANTE (id_estudiante, id_curso, zona, examen_final, nota_final) values( 2, 2, 0, 0, 0)
insert into ASIGNACION_ESTUDIANTE (id_estudiante, id_curso, zona, examen_final, nota_final) values( 3, 2, 0, 0, 0)
insert into ASIGNACION_ESTUDIANTE (id_estudiante, id_curso, zona, examen_final, nota_final) values( 4, 2, 0, 0, 0)
insert into ASIGNACION_ESTUDIANTE (id_estudiante, id_curso, zona, examen_final, nota_final) values( 5, 2, 0, 0, 0)

insert into PROFESOR values(1, '1111', 'Guatemala', 'Nahum Alarcón')

insert into ASIGNACION_PROFESOR (id_profesor, id_curso) values (1, 2)
