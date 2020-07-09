pipeline{
    agent any   
    stages{
        stage('Build Back-End'){
            steps{
                bat 'mvn clean package -DskipTests=true'
            }
        }
        stage('Units Tests'){
            steps{
                bat 'mvn test'
            }
        }
        stage('Api Tests'){
            steps{
                bat 'mvn test'
            }
        }
        
    }
}