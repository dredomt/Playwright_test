pipeline {
    agent any

    tools {
        maven 'Maven' // Ім'я інсталяції Maven
    }

    stages {
        stage('Checkout') {
            steps {
                // Отримуємо код з репозиторію
                git 'https://github.com/dredomt/Playwright_test.git'
            }
        }
        stage('Build') {
            steps {
                // Запускаємо Maven для збірки проекту і тестування
                sh 'mvn clean test'
            }
        }
        stage('Allure Report') {
            steps {
                // Генеруємо Allure звіт
                allure([
                    includeProperties: false,
                    jdk: '',
                    properties: [],
                    reportBuildPolicy: 'ALWAYS',
                    results: [[path: 'target/allure-results']]
                ])
            }
        }
    }
    
    post {
        always {
            // Архівуємо результати тестів
            archiveArtifacts artifacts: '**/target/allure-results/**', allowEmptyArchive: true
            // Публікуємо результати тестів
            junit 'target/surefire-reports/*.xml'
        }
    }
}
