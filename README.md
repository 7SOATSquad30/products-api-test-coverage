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
