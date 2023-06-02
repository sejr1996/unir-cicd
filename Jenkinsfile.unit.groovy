pipeline {
    agent {
        label 'docker'
    }
    stages {
        stage('Build') {
            steps {
                echo 'Building stage!'
                sh 'make build'
            }
        }
        stage('Unit tests') {
            steps {
                sh 'make test-unit'
                archiveArtifacts artifacts: 'results/*.xml'
            }
        }
        stage('Api tests') {
            steps {
                sh 'make test-api'
            }
        }
        stage('e2e tests') {
            steps {
                sh 'make test-e2e'
            }
        }
    }
    post {
        always {
            junit 'results/*_result.xml'
        }
    }
}