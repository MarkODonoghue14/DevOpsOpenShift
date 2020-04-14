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
        
                 stage("SonarQube Analysis Stage") {
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
           
           
        stage ('Push to Nexus Stage (Deploy)') {
            steps {
            sh 'mvn deploy'
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
         
         
         
          stage('Ansible Playbook Stage & Anaylsis Stage(Prothmeus, NodeExporter, Grafina)'){
            steps {
            sshPublisher(publishers: [sshPublisherDesc(configName: 'ansible', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: '/usr/local/bin/ansible-playbook -e BUILD_NUMBER=$BUILD_NUMBER -i /etc/ansible/hosts /etc/ansible/main.yml', execTimeout: 150000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '', remoteDirectorySDF: false, removePrefix: '', sourceFiles: '', usePty: true)], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])
                
            }
        }
        

		    }
          
          post {
          
          success{
          mail bcc: '', body: 'Jenkins build was successful', cc: '', from: 'markodonoghue230@gmail.com', replyTo: 'markodonoghue230@gmail.com', subject: 'Jenkins Build', to: 'markodonoghue230@gmail.com'
          }
          failure{
          mail bcc: '', body: 'Jenkins Build was a failure', cc: '', from: 'markodonoghue230@gmail.com', replyTo: 'markodonoghue230@gmail.com', subject: 'Jenkins Build', to: 'markodonoghue230@gmail.com'
          }
         always{
         slackSend channel: 'build', message: "${currentBuild.currentResult}", token: '6uez3MxXOBc2QXNgOIXAV0vv'
         }
      }
          
          
     }
      
   