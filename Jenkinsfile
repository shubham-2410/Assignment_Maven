// pipeline {
//     agent any

//     environment {
//         SONARQUBE_TOKEN = credentials('sonarqube-token') // Jenkins credential ID for SonarQube token
//     }

//     tools {
//         maven 'sonarmaven' // Refers to the Maven auto-installation configured in Jenkins
//     }

//     stages {
//         stage('Checkout Code') {
//             steps {
//                 echo 'Checking out code from GitHub...'
//                 checkout scm
//             }
//         }

//         stage('Build') {
//             steps {
//                 echo 'Building the Maven project...'
//                 bat 'mvn clean install'
//             }
//         }

//         stage('Run Tests') {
//             steps {
//                 echo 'Running automation tests...'
//                 bat 'mvn test'
//             }
//         }

//         stage('SonarQube Analysis') {
//             steps {
//                 echo 'Starting SonarQube analysis...'
//                 withSonarQubeEnv('sonarqube-server') {
//                     sh '''
//                     mvn clean verify sonar:sonar ^
//                         -Dsonar.projectKey=Assignment2-Task2 ^
//                         -Dsonar.projectName="Assignment2-Task2" ^
//                         -Dsonar.host.url=http://localhost:9000 ^
//                         -Dsonar.login=%SONARQUBE_TOKEN% ^
//                         -Dsonar.sources=src/main/java ^
//                         -Dsonar.tests=src/test/java 
//                     '''
//                 }
//                         // -Dsonar.java.binaries=target/classes \
//                         // -Dsonar.java.test.binaries=target/test-classes \
//                         // -Dsonar.junit.reportPaths=target/surefire-reports \
//                         // -Dsonar.coverage.jacoco.xmlReportPaths=target/site/jacoco/jacoco.xml
//             }
//         }
//     }

//     post {
//         success {
//             echo 'Pipeline completed successfully!'
//         }
//         failure {
//             echo 'Pipeline failed. Please check the logs for details.'
//         }
//     }
// }


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

        stage('SonarQube Analysis') {
            steps {
                echo 'Starting SonarQube analysis...'
                withSonarQubeEnv('sonarqube-server') {
                    bat '''
                    mvn clean verify sonar:sonar ^
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
            echo 'Pipeline completed successfully!'
        }
        failure {
            echo 'Pipeline failed. Please check the logs for details.'
        }
        always {
            archiveArtifacts artifacts: '**/target/surefire-reports/*.xml', allowEmptyArchive: true
            archiveArtifacts artifacts: '**/target/*.log', allowEmptyArchive: true
        }
    }
}
