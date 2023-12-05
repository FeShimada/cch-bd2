CREATE USER zeninguem01 PASSWORD 'senha';
CREATE USER zeninguem02 PASSWORD 'senha';
CREATE GROUP zeninguem_group;
GRANT SELECT, UPDATE, DELETE, INSERT ON usuario to zeninguem_group;
ALTER GROUP zeninguem_group ADD USER zeninguem01;
ALTER GROUP zeninguem_group ADD USER zeninguem02;

CREATE USER fornecedor01 PASSWORD 'senha';
CREATE USER fornecedor02 PASSWORD 'senha';
CREATE GROUP fornecedor_group;
GRANT SELECT, UPDATE, DELETE, INSERT ON fornecedor to fornecedor_group;
GRANT SELECT, UPDATE, DELETE, INSERT ON produto to fornecedor_group;
GRANT SELECT, UPDATE, DELETE, INSERT ON usuario to fornecedor_group;
ALTER GROUP fornecedor_group ADD USER fornecedor01;
ALTER GROUP fornecedor_group ADD USER fornecedor02;

CREATE USER funcionario01 PASSWORD 'senha';
CREATE USER funcionario02 PASSWORD 'senha';
CREATE GROUP funcionario_group;
GRANT SELECT, UPDATE, DELETE, INSERT ON funcionario to funcionario_group;
GRANT SELECT, UPDATE, DELETE, INSERT ON venda to funcionario_group;
GRANT SELECT, UPDATE, DELETE, INSERT ON item to funcionario_group;
GRANT SELECT, UPDATE, DELETE, INSERT ON usuario to funcionario_group;
GRANT SELECT,UPDATE ON produto to funcionario_group;
GRANT SELECT ON fornecedor to funcionario_group;
ALTER GROUP funcionario_group ADD USER funcionario01;
ALTER GROUP funcionario_group ADD USER funcionario02;