pipeline {
    agent any
    stages {
        stage ('Build Backend') {
            steps {
                echo 'mvn clean package -DskipTests=true'
            }
        }
        stage ('Unit Tests') {
            steps {
                echo 'mvn test'
            }
        }
        stage ('Sonar Analysis') {
            environment {
                scannerHome = tool 'SONAR_SCANNER'
            }
            steps {
                withSonarQubeEnv('SONAR_LOCAL') {
                    echo "${scannerHome}/bin/sonar-scanner -e -Dsonar.projectKey=DeployBack -Dsonar.host.url=http://localhost:9000 -Dsonar.login=f48ddab2b796310f637114a09fe78b13e3efbead -Dsonar.java.binaries=target -Dsonar.coverage.exclusions=**/.mvn/**,**/src/test/**,**/model/**,**Application.java"
                }
            }
        }
		stage("Quality Gate") {
            steps {
                sleep(30)
                  timeout(time: 1, unit: 'HOURS') {
                  waitForQualityGate abortPipeline: true
              }
            }
          }
		}
	
    }
	
	