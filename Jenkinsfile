pipeline{
    agent any

    stages{
        stage('Demo Building From Jenkins File'){
            steps{
                sh 'mvn clean package'
            }
    }
    }

     post {
        success {
            echo 'Build Successful!'
        }

        failure {
            echo 'Build Failed!'
        }
    }
}

