pipeline {
  agent any
  stages {
    stage('Build') {
      parallel {
        stage('Build') {
          steps {
            echo 'Test initialisation....'
          }
        }

        stage('Sonar_Test') {
          steps {
            sh 'mvn clean install verify sonar:sonar'
            sh 'sonar:sonar'
          }
        }

      }
    }

    stage('Mvn_Test') {
      steps {
        sh 'mvn test'
      }
    }

    stage('Git_Test') {
      steps {
        git(url: 'https://github.com/SALMICHAMA/AdamingProject.git', branch: 'master')
      }
    }

    stage('End_Test') {
      steps {
        sh 'mvn clean'
        echo 'Test finished...'
        slackSend(channel: '#encyclopedia-collaborative', baseUrl: 'webanimals.slack.com', notifyCommitters: true, replyBroadcast: true, sendAsText: true, teamDomain: 'WebAnimals', token: 'webanimals.slack.com')
      }
    }

  }
}