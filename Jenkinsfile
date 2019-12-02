pipeline {
  agent any
  stages {
    stage('Build') {
      parallel {
        stage('Build') {
          steps {
            sh 'echo Test starting....'
          }
        }

        stage('SonarTest') {
          steps {
            sh 'mvn clean verify sonar:sonar'
            sh 'mvn sonar:sonar'
          }
        }

      }
    }

    stage('Test_MVN') {
      steps {
        sh '''mvn test
'''
      }
    }

    stage('Test Git') {
      steps {
        git(url: 'https://github.com/SALMICHAMA/AdamingProject.git', branch: 'develop')
      }
    }

    stage('End_Test') {
      steps {
        sh 'mvn clean'
        echo 'Test finished...'
        slackSend(channel: '#encyclopedia-collaborative', message: 'Build Sucess')
      }
    }

  }
}