pipeline{
    agent any

    triggers {
        cron '@midnight'
    }

    parameters {
        choice(
            name: "NUMBER",
            choices: [10,20,30,40,50,60],
            description: 'Select the value for F(n) for the fibonacci sequence.'
        )
    }

    stages{
        stage('Makes script executable'){
            steps{
                sh('chmod +x ./scripts/fibonacci.sh')
            }
        }

        stage('Generate finonacci sequence by relative path'){
            steps{
                sh("./scripts/fibonnacci.sh ${env.NUMBER}")
            }
        }

        stage('Generate finonacci sequence by full path'){
            steps{
                sh("${env.WORKSPACE}/scripts/fibonnacci.sh ${env.NUMBER}")
            }
        }

        stage('Generate finonacci from script folder'){
            steps{
                dir("${env.WORKSPACE}/scripts"){
                    sh("./fibonnacci.sh ${env.NUMBER}")
                }
            }
        }
    }
}