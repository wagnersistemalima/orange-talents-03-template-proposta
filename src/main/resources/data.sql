INSERT INTO endereco (logradouro, bairro, complemento, uf) VALUES ('Rua das acacias, 150', 'Pinheiro', 'Proximo ao mercado', 'PB');
INSERT INTO endereco (logradouro, bairro, complemento, uf) VALUES ('Rua das avencas, 155', 'Jeremias', 'Proximo a farmacia', 'PE');
INSERT INTO endereco (logradouro, bairro, complemento, uf) VALUES ('Rua das barracas, 200', 'Pedregal', 'ap 300, Bloco B', 'RN');
INSERT INTO endereco (logradouro, bairro, complemento, uf) VALUES ('Rua dos oficiais, 12', 'Bodocongo', 'Proximo ao bar', 'BA');
INSERT INTO endereco (logradouro, bairro, complemento, uf) VALUES ('Rua das meninas, 15', 'Jose pinheiro', 'Proximo a loteria', 'CE');


INSERT INTO proposta (nome, documento, email, salario, endereco_id, status_proposta, data_registro, update_data_registro) VALUES ('cliente1 da Silva', '46580194006', 'cliente1@gmail.com', '2000', 5, 'ELEGIVEL', TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:07.12345Z', TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:08.12345Z');
INSERT INTO proposta (nome, documento, email, salario, endereco_id, status_proposta, data_registro, update_data_registro) VALUES ('cliente2 de Souza', '69508139021', 'cliente2@gmail.com', '2500', 4, 'ELEGIVEL', TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:07.12345Z', TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:08.12345Z');
INSERT INTO proposta (nome, documento, email, salario, endereco_id, status_proposta, data_registro, update_data_registro) VALUES ('cliente3 da Costa', '47071831076', 'cliente3@gmail.com', '3000', 3, 'ELEGIVEL', TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:07.12345Z', TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:08.12345Z');
INSERT INTO proposta (nome, documento, email, salario, endereco_id, status_proposta, data_registro, update_data_registro) VALUES ('cliente4 de Lima', '87181258000110', 'cliente4@gmail.com', '3500', 2, 'NAO_ELEGIVEL', TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:07.12345Z', TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:08.12345Z');
INSERT INTO proposta (nome, documento, email, salario, endereco_id, status_proposta, data_registro, update_data_registro) VALUES ('cliente5 da Mata', '71405202000161', 'cliente5@gmail.com', '4000', 1, 'NAO_ELEGIVEL', TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:07.12345Z', TIMESTAMP WITH TIME ZONE '2020-07-13T20:50:08.12345Z');
