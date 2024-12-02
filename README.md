# Cobertura de Código com JaCoCo

Este projeto utiliza o [JaCoCo](https://www.jacoco.org/jacoco/) para medir a cobertura de código durante a execução dos testes automatizados. A integração com o **GitHub Actions** permite a geração e validação dos relatórios de cobertura diretamente na pipeline de CI/CD.

## 📋 Configuração

### 1. Configuração no `build.gradle`

Certifique-se de que o arquivo `build.gradle` inclua as configurações do JaCoCo:

```groovy
plugins {
    id 'jacoco'
}

jacoco {
    toolVersion = "0.8.10"
}

tasks.jacocoTestReport {
    reports {
        xml.required.set(true)
        html.required.set(true)
    }
}
```

### 2. Configuração do GitHub Actions

O workflow de CI no arquivo .github/workflows/ci.yaml foi configurado para:

#### Rodar os testes automatizados.
#### Gerar relatórios de cobertura de código nos formatos HTML e XML.
#### Validar a cobertura mínima (80%).
#### Fazer upload do relatório HTML como artefato nos Actions.

#### Exemplo de Configuração no ci.yaml:

```groovy

name: CI

on:
  push:
    branches:
      - main
  pull_request:
    branches:
      - main

jobs:
  test:
    runs-on: ubuntu-latest

    steps:
      - name: Check out code
        uses: actions/checkout@v3

      - name: Set up JDK 21
        uses: actions/setup-java@v3
        with:
          java-version: '21'
          distribution: 'temurin'

      - name: Grant execute permission for Gradlew
        run: chmod +x ./gradlew

      - name: Install xmllint
        run: |
          sudo apt-get update
          sudo apt-get install -y libxml2-utils

      - name: Run Spotless Apply
        run: ./gradlew spotlessApply

      - name: Run Spotless Check
        run: ./gradlew spotlessCheck

      - name: Run tests and generate JaCoCo report
        run: |
          ./gradlew clean test jacocoTestReport
          echo "Generated JaCoCo reports:"
          ls -R build/reports/jacoco || echo "No JaCoCo reports found."

      - name: Upload JaCoCo Coverage Report
        uses: actions/upload-artifact@v3
        with:
          name: jacoco-coverage-report
          path: build/reports/jacoco/test/html
      
      - name: Extract and display coverage percentage
        run: |
          # Caminho fixo do relatório XML
          REPORT_PATH=build/reports/jacoco/test/jacocoTestReport.xml
      
          if [ ! -f "$REPORT_PATH" ]; then
            echo "JaCoCo XML coverage report not found!"
            exit 1
          fi
      
          echo "Found JaCoCo XML report at: $REPORT_PATH"
      
          # Extração dos dados de cobertura
          COVERED=$(xmllint --xpath 'string(sum(//counter[@type="LINE"]/@covered))' "$REPORT_PATH")
          MISSED=$(xmllint --xpath 'string(sum(//counter[@type="LINE"]/@missed))' "$REPORT_PATH")
      
          if [ -z "$COVERED" ] || [ -z "$MISSED" ]; then
            echo "Error: Unable to extract coverage data from the XML report."
            exit 1
          fi
      
          # Converte valores para inteiros para evitar problemas de cálculo
          COVERED=$(printf "%.0f" "$COVERED")
          MISSED=$(printf "%.0f" "$MISSED")
          TOTAL=$((COVERED + MISSED))
      
          if [ "$TOTAL" -eq 0 ]; then
            echo "Error: Total lines is zero, coverage cannot be calculated."
            exit 1
          fi
      
          # Cálculo do percentual de cobertura
          PERCENTAGE=$(echo "scale=2; ($COVERED / $TOTAL) * 100" | bc)
      
          echo "Lines Covered: $COVERED"
          echo "Lines Missed: $MISSED"
          echo "Total Lines: $TOTAL"
          echo "Coverage Percentage: $PERCENTAGE%"
      
          # Validação da cobertura mínima (80%)
          MINIMUM=80.0
          if (( $(echo "$PERCENTAGE < $MINIMUM" | bc -l) )); then
            echo "Coverage is below the minimum required: $MINIMUM%!"
            exit 1
          fi
          echo "Coverage validation passed with $PERCENTAGE%."
```


🚀 
### Como Utilizar
Executar o Workflow de CI:

Faça um push no branch principal ou abra um pull request. <br>
O workflow será automaticamente acionado.

Acessar o Relatório de Cobertura:

1. Navegue até a aba Actions no GitHub.<br>
2. Selecione a execução do workflow correspondente.<br>
3. Baixe o artefato chamado jacoco-coverage-report.<br>
4. Visualizar o Relatório:

Extraia o artefato baixado.<br>
Abra o arquivo index.html no navegador para verificar a cobertura de código.


📊 #### Validação de Cobertura
O workflow valida que a cobertura de código seja, no mínimo, 80%.<br>
Caso a cobertura seja inferior, o processo de CI falhará.

Ajustando o Percentual Mínimo
Para alterar o percentual mínimo exigido, edite o workflow e modifique a variável MINIMUM:

```yaml
MINIMUM=80.0
```

💡 #### Dicas
Certifique-se de rodar os testes localmente antes de fazer push para evitar falhas no workflow.<br>
Utilize ferramentas de análise estática, como PMD e Spotless, para manter o código limpo e consistente.<br>
Caso precise de ajuda, consulte a documentação oficial do JaCoCo.
