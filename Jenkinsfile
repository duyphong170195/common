pipeline {
    agent any

    environment {
        // Thay đổi các thông số này theo AWS của bạn
        AWS_REGION = "ap-southeast-1"
        AWS_ACCOUNT = "187104821419"
        DOMAIN = "phongnd-artifacts"
        REPO = "eight_seneca_common"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Auth CodeArtifact') {
            steps {
                script {
                    // Lấy Token từ AWS và tạo file settings.xml tạm thời
                    sh '''
                        export CODEARTIFACT_AUTH_TOKEN=$(aws codeartifact get-authorization-token --domain ${DOMAIN} --domain-owner ${AWS_ACCOUNT} --query authorizationToken --output text)

                        echo "<settings>
                                <servers>
                                    <server>
                                        <id>codeartifact</id>
                                        <username>aws</username>
                                        <password>${CODEARTIFACT_AUTH_TOKEN}</password>
                                    </server>
                                </servers>
                              </settings>" > settings.xml
                    '''
                }
            }
        }

        stage('Deploy to CodeArtifact') {
            steps {
                // Chạy lệnh deploy sử dụng file settings.xml vừa tạo
                sh 'mvn -s settings.xml clean deploy -DskipTests'
            }
        }
    }

    post {
        always {
            // Xóa file chứa token sau khi xong để bảo mật
            sh 'rm -f settings.xml'
        }
    }
}