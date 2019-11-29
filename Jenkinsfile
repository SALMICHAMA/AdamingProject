pipeline {
  agent any
  stages {
    stage('Build') {
      parallel {
        stage('Build') {
          steps {
            sh 'triggers { cron(\'H 8-16/2 * * 1-5\') }'
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

  }
}