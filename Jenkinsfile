pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        sh 'echo Test starting....'
      }
    }

    stage('Test_MVN') {
      steps {
        sh '''mvn test
'''
        sh 'echo Test finished'
      }
    }

  }
}