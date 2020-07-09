pipeline{
    agent any   
    stages{
        stage('Build Back-End'){
            steps{
                bat 'mvn clean package -DskipTests=true'
            }
        }
        stage('Meio'){
            steps{
                bat 'echo meio'
                bat 'echo meio 1'
                
            }
        }
        stage('Fim'){
            steps{
                bat 'echo fim'
            }
        }
        
    }
}