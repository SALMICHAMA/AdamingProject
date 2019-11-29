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
      }
    }

    stage('Test Git') {
      steps {
        git(url: 'https://github.com/SALMICHAMA/AdamingProject.git', branch: 'develop')
      }
    }

  }
}