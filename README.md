# Parquimetro API

Sistema responsável por gerenciar um sistema de parquimetro - permitindo o cadastro de condutores e veiculos, envio de alertas, além de calcular o preço a ser pago.

## Documentação técnica

A documentação técnica está disponível via swagger utilizando o padrão da OpenAPI. Acessar:

- http://localhost:8080/swagger-ui/index.html#/

Ou então, olhar o arquivo disponível [aqui](link).

## Desafios encontrados

Mexer com datas é sempre complicado 😅, mas com essa exceção foi possível executar a codificação do projeto sem tantos impedimentos.

## Soluções implementadas

- spring-boot 3.x.x
- gradle
- h2 (banco de dados em memória)
- cache (utilizando o cache do próprio spring para evitar muitas requisições iguais ao banco de dados)
- mapstruct (melhor performance ao fazer o mapper)
