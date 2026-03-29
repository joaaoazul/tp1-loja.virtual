# TP1 - Loja Virtual

Projeto desenvolvido no âmbito de uma unidade curricular de Tecnologias de Mercado. 
Simula uma loja virtual com gestão de produtos, inventário, clientes e proprietário.

## Estrutura do Projeto
```
src/
├── Client.java          # Representa um cliente da loja
├── Helpers.java         # Métodos utilitários (validação de input, etc.)
├── Inventory.java       # Gestão do inventário de produtos
├── Main.java            # Ponto de entrada da aplicação
├── Owner.java           # Representa o proprietário da loja
├── Product.java         # Modelo de um produto
├── Store.java           # Implementação da loja
├── StoreInterface.java  # Interface que define o contrato da loja
└── User.java            # Classe base de utilizador
```

## Funcionalidades

- Listagem de produtos em inventário
- Adição e edição de produtos (nome e preço)
- Validação de input do utilizador
- Gestão de clientes e proprietário

## Como Executar

1. Clona o repositório:
```bash
   git clone https://github.com/joaaoazul/tp1-loja.virtual.git
```
2. Abre o projeto numa IDE (IntelliJ IDEA recomendado)
3. Corre o ficheiro `Main.java`

## Requisitos

- Java 8+
- Nenhuma dependência externa
