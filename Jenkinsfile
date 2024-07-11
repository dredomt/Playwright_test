pipeline {
    agent any

    environment {
        ALLURE_RESULTS = 'target/allure-results'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build') {
            steps {
                sh './mvnw clean install'
            }
        }

        stage('Test') {
            steps {
                sh './mvnw test'
            }
        }

        stage('Allure Report') {
            steps {
                allure([
                    results: [[path: 'target/allure-results']]
                ])
            }
        }
    }

    post {
        always {
            // Publish Allure Report
            allure([
                results: [[path: "${ALLURE_RESULTS}"]]
            ])
        }
    }
}
