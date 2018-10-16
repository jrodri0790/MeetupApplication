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
        stage('Delivering application') {
            steps {
                script {
                    if(DELIVER_TO == 'nowhere') {
                        echo 'Do not deliver anywhere'
                    }
                    if(DELIVER_TO == 'staging') {
                        sh 'fastlane build_staging'
                        sh 'fastlane deliver_staging'
                    }
                    if(DELIVER_TO == 'prod') {
                        sh 'fastlane build_prod'
                        sh 'fastlane deliver_prod'
                    }
                }
            }
        }
    }
}