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
        stage ( 'Quality Gate' ) {
            steps {
                def scannerHome = tool 'SONAR_SCANNER';
                 withSonarQubeEnv ('SONAR_LOCAL') {
                       $"{scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=DeployBack -Dsonar.host.url=http://localhost:9000 -Dsonar.login=f48ddab2b796310f637114a09fe78b13e3efbead -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/.mvn/**,**/src/test/**,**/model/**,**Application.java"
                    }
                }
                sleep(10)
                timeout (time: 1, unit: 'HOURS') {
                    waitForQualityGate abortPipeline: true
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
                    bat 'mvn test'

                }
                
            }
        }
       
	}
}    
	
	