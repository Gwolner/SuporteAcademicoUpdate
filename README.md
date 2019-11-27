# Refatoração da entrega 2 de Descorp

Entre as principais alterações destacam-se:

* Criação de classes de consulta (SELECT) JPQL para cada entidade: Antes só havia uma única classe com vários tipos de consultas (MIN, MAX, BETWEEN, IN, COUNT, LIKE, ORDER BY e outras);

* Criação de classes de atualização e exclusão (UPDATE e DELETE) JPQL para cada entidade: Antes só havia uma única classe com um UPDATE e um DELETE;

* Adição de NamedQuery nas entidades: Havia apenas CreateQuery realizando a maioria dos SELECTs do primeiro item;

* Adição de dois JOINs, um AVG e um SUM: Itens solicitados durante a refatoração do projeto;
