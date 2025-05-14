# Conversor de Moedas com API Externa

Um conversor de moedas que utiliza a API ExchangeRate-API para obter cotações em tempo real.

# 📋 Funcionalidades

 * Consulta de taxas de câmbio para diversas moedas
 * Conversão entre moedas com valores atualizados
 * Suporte para conversão personalizada (qualquer moeda para qualquer moeda)
 * Validação de moedas suportadas
 * Interface de linha de comando (CLI) intuitiva

# 🛠️ Tecnologias Utilizadas

   * Linguagem: Java 21
   * Bibliotecas:
      * Gson: Para manipulação de JSON
      * Java HTTP Client: Para requisições HTTP
   * API: ExchangeRate-API v6

# ⚙️ Configuração

## 1.Pré-requisitos:
   * JDK 17+ instalado
   * Maven instalado (para construção)
   * Conta no ExchangeRate-API com chave válida

## 2.Configuração da API:
   * Substitua a chave API em ConsumindoConversor.java:
    
     ```
     private static final String API_KEY = "sua_chave_aqui";
     ```

## 3.Construção:
   ```
   mvn clean package
   ```

## 4.Execução 
   ```
   java -jar target/conversor-moeda.jar
   ```

# 🖥️ Como Usar

## Ao iniciar a aplicação, você verá o menu principal:

   ```
    ++++++++++++++++++++++++++++++++++++
      1 - Mostrar lista de câmbios
      2 - Converter para USD(dolar)
      3 - Converter para EUR(euro)
      4 - Converter para JPY(iene)
      5 - Converter Moeda Personalizada
      6 - Sair
   ++++++++++++++++++++++++++++++++++++
   ```

# Exemplo de Uso

## 1.Consultar taxas de câmbio:
   ```
   Escolha uma opção: 1
   Informe a moeda base para ver as taxas (ex: EUR, USD, BRL...): BRL
   ```

## 2.Converter moeda:
   ```
   Escolha uma opção: 5
   Digite a moeda origem (BRL, USD): EUR
   Digite a moeda destino: BRL
   Valor a ser convertido: 100
   ```

# 🗂️ Estrutura do Projeto

   ```
            src/
                ├── main/
                │   ├── java/
                │   │   └── br/
                │   │       └── com/
                │   │           └── conversor/
                │   │               ├── Main.java
                │   │               ├── controller/
                │   │               ├── service/
                │   │               ├── util/
                │   │               ├── exception/
                │   │               ├── model/
                │   │               └── consumindoapi/
                │   └── resources/
                └── test/
   ```

# ✉️ Contato

## Se tiver dúvidas ou sugestões, entre em contato:
   * PEDRO HENRIQUE 

