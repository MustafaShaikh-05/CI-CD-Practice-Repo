pipeline{
    agent any

    environment{
        NAME = "Mustafa"
        PRACTICE = "CI/CD Pipeline"
    }

    stages{
        stage('Test Printing'){
            steps{
                echo "Printing Environment Values"
                sh "echo ${PRACTICE}"
            }
        }
        stage('One More Printing'){
            steps{
                echo "Now Printing Name"
                sh "echo ${NAME}"
            }
        }
    }
}