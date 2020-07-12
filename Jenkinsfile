pipeline {
    agent any
    stages {
        stage ('Build Backend') {
            steps {
                bat 'mvn clean package -DskipTests=true'
            }
        }
        stage ('Unit Tests') {
            steps {
                bat 'mvn test'
            }
        }
        stage ('Deploy BackEnd'){
            steps{
                deploy adapters: [tomcat8(credentialsId: 'TomcatLogon', path: '', url: 'http://localhost:8001/')], contextPath: 'tasks-backend', war: 'target/tasks-backend.war'
            }
        }
        stage ('API Tests'){
            steps{
                git 'https://github.com/phcunha87/APITestes.git'
                bat 'mvn test'
            }
        }
	}
}    
	
	