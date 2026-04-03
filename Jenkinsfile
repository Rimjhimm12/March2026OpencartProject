pipeline {
    agent any
    stages {
        stage('Clone Repository') {
            steps {
                git branch: 'main', url: 'https://github.com/Rimjhimm12/March2026OpencartProject'
            }
        }
        stage('Run Tests') {
            steps {
                sh 'mvn clean test -Dsurefire.suiteXmlFiles=src/resources/testrunners/test_firefox.xml'
            }
        }
    }
}
