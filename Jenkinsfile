pipeline {
    agent {
        kubernetes {
            yaml '''
apiVersion: v1
kind: Pod
spec:
  containers:
  - name: aws-cli
    image: amazon/aws-cli:latest
    command: ["sleep"]
    args: ["99d"]
  - name: maven
    image: maven:3.9.6-eclipse-temurin-17
    command: ["sleep"]
    args: ["99d"]
'''
        }
    }

    environment {
        AWS_REGION = "ap-southeast-1"
        AWS_ACCOUNT = "187104821419"
        DOMAIN = "phongnd-artifacts"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Get Auth Token') {
            steps {
                container('aws-cli') {
                    script {
                        // Lấy Token từ container aws-cli và lưu vào biến môi trường của Pipeline
                        env.CODEARTIFACT_AUTH_TOKEN = sh(
                            script: "aws codeartifact get-authorization-token --domain ${DOMAIN} --domain-owner ${AWS_ACCOUNT} --query authorizationToken --output text --region ${AWS_REGION}",
                            returnStdout: true
                        ).trim()
                    }
                }
            }
        }

        stage('Build & Deploy to CodeArtifact') {
            steps {
                container('maven') {
                    script {
                        // Tạo file settings.xml và chạy lệnh deploy trong container maven
                        sh """
                            echo '<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
                                            xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
                                            xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0 https://maven.apache.org/xsd/settings-1.0.0.xsd">
                                <servers>
                                    <server>
                                        <id>codeartifact</id>
                                        <username>aws</username>
                                        <password>${env.CODEARTIFACT_AUTH_TOKEN}</password>
                                    </server>
                                </servers>
                              </settings>' > settings.xml

                            mvn -s settings.xml clean deploy -DskipTests
                        """
                    }
                }
            }
        }
    }

    post {
        always {
            container('maven') {
                sh 'rm -f settings.xml'
            }
        }
    }
}