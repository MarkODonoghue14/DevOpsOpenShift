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
      
   