pipeline {
    agent any
    tools {
        maven 'MVN'
    }
    stages {
        stage ('Compile Stage') {
           
            steps {
                    sh 'mvn clean compile'
            }
        }
       
        stage ('Testing Stage') {
           
            steps {
                    sh 'mvn test'
            }
        }
                stage ('SonarCloud Analysis') {
           
            steps {
                    sh 'mvn verify sonar:sonar'
            }
  }
}