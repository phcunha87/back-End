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
        stage ('Sonar Analisys') {
            environment {
                scannerHome = tool 'SONAR_SCANNER'
            }
            steps {
                bat "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=DeployBack -Dsonar.host.url=http://localhost:9000 -Dsonar.login=35a2b2f20035b6cc05fa0ed56ae2a76b79e868ce -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/.mvn/**,**/src/test/**,**/model/**,**application.java"
            }
        }
        stage ('Quality Gate') {
            steps {
                timeout(time: 1, unit: 'MINUTES')
                waitForQualityGate abortPipeline: true
            }
        }

        stage ('Deploy BackEnd'){
            steps{
                deploy adapters: [tomcat8(credentialsId: 'Tomcat', path: '', url: 'http://localhost:8001/')], contextPath: 'tasks-backend', war: 'target/tasks-backend.war'
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
                    deploy adapters: [tomcat8(credentialsId: 'Tomcat', path: '', url: 'http://localhost:8001/')], contextPath: 'tasks', war: 'target/tasks.war'
                }
                
            }
        }
        stage ('Funcional Tests'){
            steps {
                dir('funcional-test'){
                    git 'https://github.com/phcunha87/FuncionalTeste.git'
                    bat 'mvn test'

                }
                
            }
        }
       
	}
}    
	
