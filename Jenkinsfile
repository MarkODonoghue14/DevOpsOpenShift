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
          stage('Ansible Stage'){
            steps {
            sshPublisher(publishers: [sshPublisherDesc(configName: 'ansible', transfers: [sshTransfer(cleanRemote: false, excludes: '', execCommand: '/usr/local/bin/ansible-playbook BUILD_NUMBER=$BUILD_NUMBER -i /etc/ansible/hosts /etc/ansible/main.yml', execTimeout: 120000, flatten: false, makeEmptyDirs: false, noDefaultExcludes: false, patternSeparator: '[, ]+', remoteDirectory: '', remoteDirectorySDF: false, removePrefix: '', sourceFiles: '')], usePromotionTimestamp: false, useWorkspaceInPromotion: false, verbose: false)])
                
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
      
   