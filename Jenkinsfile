pipeline {
    agent any

    tools {
        maven 'Maven'
        jdk 'JDK'
        allure 'Allure'
    }

    stages {
        stage('Checkout') {
            steps {
 
                git 'https://github.com/dredomt/Playwright_test.git'
            }
        }
        stage('Build') {
            steps {
 
                sh 'mvn clean test'
            }
        }
        stage('Allure Report') {
            steps {
 
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
 
            archiveArtifacts artifacts: '**/target/allure-results/**', allowEmptyArchive: true
 
            junit 'target/surefire-reports/*.xml'
        }
    }
}
