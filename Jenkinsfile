pipeline {
    agent any

    environment {
        SONARQUBE_TOKEN = credentials('sonarqube-token') // Jenkins credential ID for SonarQube token
    }

    tools {
        maven 'sonarmaven' // Refers to the Maven auto-installation configured in Jenkins
    }

    stages {
        stage('Checkout Code') {
            steps {
                echo 'Checking out code from GitHub...'
                checkout scm
            }
        }

        stage('Build') {
            steps {
                echo 'Building the Maven project...'
                bat 'mvn clean install'
            }
        }

        stage('Run Tests') {
            steps {
                echo 'Running automation tests...'
                bat 'mvn test'
            }
        }

        stage('Code Coverage') {
            steps {
                echo 'Generating JaCoCo Code Coverage Report...'
                bat 'mvn verify'
            }
        }

        stage('SonarQube Analysis') {
            steps {
                withSonarQubeEnv('sonarqube-server') {
                    bat '''
                    mvn sonar:sonar ^
                        -Dsonar.projectKey=Assignment2-Task2 ^
                        -Dsonar.projectName="Assignment2-Task2" ^
                        -Dsonar.host.url=http://localhost:9000 ^
                        -Dsonar.login=%SONARQUBE_TOKEN% ^
                        -Dsonar.sources=src/main/java ^
                        -Dsonar.tests=src/test/java ^
                        -Dsonar.java.binaries=target/classes ^
                        -Dsonar.java.test.binaries=target/test-classes ^
                        -Dsonar.junit.reportPaths=target/surefire-reports ^
                        -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
                    '''
                }
            }
        }
    }

    post {
        success {
            echo 'Pipeline executed successfully!'
        }
        failure {
            echo 'Pipeline failed. Please check the logs for details.'
        }
    }
}
