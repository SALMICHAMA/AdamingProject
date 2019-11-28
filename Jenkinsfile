pipeline {
  agent any
  stages {
    stage('Build') {
      steps {
        echo 'hello word'
      }
    }

    stage('Test_MVN') {
      steps {
        sh 'mvn clean'
      }
    }

  }
}