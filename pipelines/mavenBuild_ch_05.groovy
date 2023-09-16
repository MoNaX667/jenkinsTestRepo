pipeline {
    agent any
    
    tools {
        maven 'maven-3.9.4' 
    }

    triggers {
        cron '@midnight'
    }
    
    stages {
        stage('Getting source from repo') {
            steps {
                git branch: 'main',
                    changelog: false,
                    poll: false,
                    url: 'https://github.com/LinkedInLearning/essential-jenkins-2468076.git'
            }
        }
        stage('Clean workspace') {
            steps {
                dir("${env.WORKSPACE}/Ch05/05_04-challenge-create-artifacts-and-reports"){
                    echo "Cleaning the workspace..."
                    sh 'mvn clean'
                }
            }
        }
        stage('Test') {
            steps {
                dir("${env.WORKSPACE}/Ch05/05_04-challenge-create-artifacts-and-reports"){
                    echo "Running tests..."
                    sh 'mvn test'
                }
            }
        }
        stage('Build') {
            steps {
                dir("${env.WORKSPACE}/Ch05/05_04-challenge-create-artifacts-and-reports"){
                    echo "Creating the JAR file..."
                    sh 'mvn package -DskipTests'
                }
            }
        }
    }
    post {
        always {
            echo "Collecting jUnit test results..."
            junit allowEmptyResults: true, 
                testResults: '**/TEST-com.learningjenkins.AppTest.xml'
            
            echo "Collecting Code Coverage results ..."
            publishCoverage adapters: [cobertura('**/coverage.xml')],
                sourceFileResolver: sourceFiles('STORE_LAST_BUILD')

            echo "Archiving the JAR file..."
            archiveArtifacts allowEmptyArchive: true,
                artifacts: '**/hello-1.0-SNAPSHOT.jar',
                fingerprint: true,
                followSymlinks: false,
                onlyIfSuccessful: true
        }
    }
}