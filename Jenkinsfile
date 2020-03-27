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
                stage ('SonarCloud Analysis Stage') {
           
            steps {
                    sh 'mvn verify sonar:sonar'
            }
          }
            
            stage ('Build Stage') {
            
            steps {
                    sh 'mvn package'
                    }               
             }
          }
      }