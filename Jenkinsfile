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
        stage('Prepare AWS CLI') {
            steps {
                sh '''
                    # Kiểm tra nếu chưa có aws cli thì cài nhanh
                    if ! command -v aws &> /dev/null; then
                        curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip"
                        unzip -q awscliv2.zip
                        ./aws/install --install-dir /home/jenkins/aws-cli --bin-dir /home/jenkins/bin
                        export PATH=$PATH:/home/jenkins/bin
                    fi
                '''
            }
        }
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