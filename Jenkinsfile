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
           
            
            stage ('Build Stage') {
            
            steps {
                    sh 'mvn package'
                    }               
             }
             
                stage ('Push to Nexus') {
            steps {
            sh 'mvn deploy'
            }
        }
        
        stage ('Docker Build'){
        agent any
        steps {
        sh ' docker build -t mark/book:latest .'
        }
        }
          }
          
                   post {
           failure {
               emailext body: 'The jenkins build failed', subject: 'Jenkins Build Failed', to: 'markodonoghue230@gmail.com'
           }
           
           success {
           emailext body: 'The jenkins build succeded', subject: 'Jenkins Build Success', to: 'markodonoghue230@gmail.com'
         }
      }
          
          
     }
      
   