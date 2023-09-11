pipeline{
    agent any

    triggers {
        cron '@midnight'
    }

    stages{
        stage('TEST'){
            steps{
                echo "Testing data and changes"
            }
        }
        stage('DEPLOY'){
            steps{
                input message: "Do you want to deploy changes for production env?", ok: 'Deploy to Production'
                echo "Deploying the changes since it is Production trigger"
            }
        }
        stage('REPORT'){
            steps{
                sh "echo 'Getting changelog data into artifact' > my-result.txt"
                archiveArtifacts allowEmptyArchive: true, artifacts: '*.txt', fingerprint: true, followSymlinks: false, onlyIfSuccessful: true
            }
        }
    }
}