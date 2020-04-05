pipeline {
    agent {dockerfile true}
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
             
          }
          
          post {
           failure {
               emailext body: 'The jenkins build was a failure', replyTo: 'markodonoghue230@gmail.com', subject: 'Build failed', to: 'markodonoghue230@gmail.com'
           }
           
           success {
          emailext body: 'the jenkins build was succcesful', replyTo: 'markodonoghue230@gmail.com', subject: 'sample', to: 'markodonoghue230@gmail.com'
         }
      }
          
          
     }
      
   