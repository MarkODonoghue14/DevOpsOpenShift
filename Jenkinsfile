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
     }
      
   