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
     }
      
   