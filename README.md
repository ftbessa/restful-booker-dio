# Automação de API com Rest-Assured

Explorando rest-assured para automação de teste de API.

## 🚀 Começando

Essas instruções permitirão que você obtenha uma cópia do projeto em operação na sua máquina local para fins de desenvolvimento e teste.

### 📋 Pré-requisitos

De que coisas você precisa para instalar o software e como instalá-lo?

```
1. IntelliJ
2. Java
3. Maven
4. Allure Framework
```
### 🔧 Instalação

1 - Download do [IntelliJ Community](https://www.jetbrains.com/idea/download/#section=windows)<br>
2 - Download do [Java JDK](https://www.oracle.com/java/technologies/downloads/)<br>
3 - Download do [Maven](https://maven.apache.org/download.cgi)<br>
4 - Download do [Allure Framework](https://github.com/allure-framework/allure2/releases)

## ⚙️ Executando os testes e verificando relatório

Para executar os testes, basta executar o comando abaixo no diretorio do projeto

- **Diretorio:**
```
~/restful-booker-dio
```
- **Comando para executar todos os testes:**
```
mvn clean test
```
- **Comando para gerar o relatório com allure framework:**
```
allure serve allure-results/
```

## 🛠️ Construído com

Nesse projeto foi utilizado as seguintes ferramentas:

* [Junit5](https://github.com/junit-team/junit5) - Responsável por realizar algumas validações e definição de testes com anotation
* [Maven](https://mvnrepository.com/) - Gerente de Dependência
* [Gson](https://github.com/google/gson) - Biblioteca do google responsável em converter objeto java para JSON
* [Rest-Assured](https://rest-assured.io/) - Framework responsável em realizar e validar requisições de uma API Rest

## 📄 Postman

Na pasta **postman** está a collection com as requests e environment, caso queira realizar as requisições manualmente via ferramenta [Postman](https://www.postman.com/downloads/).

## ✒️ Autores

* **QA** - *Trabalho Inicial* - [Flávia Bessa](https://github.com/ftbessa)
## 🎁 Expressões de gratidão

* Um agradecimento publicamente a *Carolina Satana Louzada*