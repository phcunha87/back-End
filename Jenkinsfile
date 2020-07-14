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
                deploy adapters: [tomcat8(credentialsId: 'Tomcat', path: '', url: 'http://localhost:8001/')], contextPath: 'tasks-backend', war: 'target\tasks-backend.war'
            }
        } 
        stage ('API Tests'){
            steps {
                dir('api-test'){
                    git 'https://github.com/phcunha87/APITestes.git'
                    bat 'mvn test'

                }
                
            }
        }
        stage('Deploy FrontEnd'){
            steps {
                dir('frontend'){
                    git 'https://github.com/phcunha87/tasks-frontend.git'
                    bat 'mvn clean package'
                    deploy adapters: [tomcat8(credentialsId: 'TomcatLogon', path: '', url: 'http://localhost:8001/')], contextPath: 'tasks', war: 'target/tasks.war'
                }
                
            }
        }
        stage ('Funcional Tests'){
            steps {
                dir('funcional-test'){
                    git 'https://github.com/phcunha87/FuncionalTeste.git'
                    echo 'mvn test'

                }
                
            }
        }
       
	}
}    
	
	