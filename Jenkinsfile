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
        stage('Sonar Analysis'){
            environment{
                scannerHome = tool 'SONAR_SCANNER'
            }
			steps{
			    withSonarQubeEnv('SONAR_LOCAL'){
				bat "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=DeployBack -Dsonar.host.url=http://localhost:9000 -Dsonar.login=499cc3fb74b5f38044fc6d32bd8a81c2f261c748 -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/.mvn/**,**/src/test/**,**/model/**,**Application.java"
				 
				}
			   
			}
        }
        
    }
}










