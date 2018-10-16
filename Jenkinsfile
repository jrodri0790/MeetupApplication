#!groovy

pipeline {
    agent any
    stages {
        stage('Running unit tests') {
            steps {
                sh 'fastlane unit'
            }
        }
        stage('Running static code analysis') {
            steps {
                sh 'fastlane lint'
            }
        }
        stage('Running functional tests') {
            steps {
                sh 'fastlane functional'
            }
        }
        stage('Building staging application') {
            steps {
                sh 'fastlane build_staging'
            }
        }
        stage('Building prod application') {
            steps {
                sh 'fastlane build_prod'
            }
        }
    }
}