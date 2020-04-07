pipeline {

  environment {
    registry = "markslaw/devops"
    registryCredential = "dockerhubcreds"
    image = ''
  }     
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
             
          
          		stage ('Build Docker Image Stage') {
		 steps {
		    script {
		    image = docker.build registry + ":$BUILD_NUMBER"
		    }
		   }
		  } 
		  
		  stage ('Push Image to DockerHub Registery Stage') {
		   steps {
		    script {
		    docker.withRegistry('https://index.docker.io/v1/','dockerhubcreds') {
		    image.push()
		    }
		    }
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
      
   