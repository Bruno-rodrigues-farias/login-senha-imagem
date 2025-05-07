# login-senha-imagem

#  Projeto de Conta Bancária com Upload de Foto e Autenticação

Este é um sistema de conta bancária desenvolvido em Java com Spring Boot. O projeto permite o cadastro de usuários com upload de foto, autenticação segura com Spring Security e gerenciamento de transferências.

##  Tecnologias Utilizadas

- Java 17
- Spring Boot 3
- Spring Security
- Spring Data JPA
- MySQL
- BCrypt
- MultipartFile (upload de imagem)
- Lombok

##  Funcionalidades

- Cadastro de usuários com:
  - Nome
  - Senha (criptografada)
  - Foto de perfil
  - Role (ADMIN / USER)
- Login com autenticação JWT
- Upload de imagem e exibição via endpoint
- Listagem de usuários


## Configuração

No `application.properties`, defina o diretório para salvar as imagens:
**upload.dir=uploads**

## Como Rodar o Projeto

Pré-requisitos
Antes de começar, certifique-se de ter instalado:

# Java 17+

# Maven

# MySQL


## Clonar o Projeto
git clone https://github.com/Bruno-rodrigues-farias/login-senha-imagem.git
cd login-senha-imagem


## Rodar o Projeto
abra o projeto na sua IDE e execute a classe principal (geralmente Application.java)
