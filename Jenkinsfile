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
        stage ('Sonar Aalisys'){
            steps {
                withSonarQubeEnv('SONAR_LOCAL') {
                    sh "../../../sonar-scanner-2.9.0.670/bin/sonar-scanner"   
                }
                def qualityGate = waitForQualityGate()
                if (qualityGate.status != 'OK') {
                    error "Pipeline aborted due to quality gate coverage failure: ${qualitygate.status}"
                }
            }

        }
	}
}    
	
	