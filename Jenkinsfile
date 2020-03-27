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
        
          stage("build & SonarQube analysis Stage") {
            steps {
              withSonarQubeEnv('Sonar') {
                sh 'mvn clean package sonar:sonar'
              }
            }
           }
                 stage("Quality Gate Stage") {
            steps {
              timeout(time: 1, unit: 'HOURS') {
                waitForQualityGate abortPipeline: true
              }
            }
           }
            
            stage ('Build Stage') {
            
            steps {
                    sh 'mvn package'
                    }               
             }
          }
          
      post {
           failure {
               emailext body: 'The Jenkins build was unsuccessful', subject: 'Jenkins Build Failure', to: 'markodonoghue230@gmail.com'
           }
           
           success {
           emailext body: "The jenkins build was successful' , subject: 'Jenkins Build successful', to: 'markodonoghue230@gmail.com'
         }
      }
      
   