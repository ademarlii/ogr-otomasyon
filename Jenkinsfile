pipeline {
    agent any

    triggers {
        githubPush()
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                sh '''
                    chmod +x mvnw
                    ./mvnw -B clean test
                '''
            }
        }

        stage('Package') {
            steps {
                sh '''
                    chmod +x mvnw
                    ./mvnw -B -DskipTests package
                '''
            }
        }

        stage('Fake Deploy') {
            when {
                branch 'main'
            }
            steps {
                echo "SAHTE DEPLOY: target/*.jar dosyalarını fake-deploy klasörüne kopyalıyorum..."

                sh '''
                    rm -rf fake-deploy
                    mkdir -p fake-deploy
                    cp target/*.jar fake-deploy/
                '''
            }
        }
    }

    post {
        success {
            echo "✅ Pipeline başarıyla tamamlandı (testler geçti, fake deploy yapıldı)."
        }
        failure {
            echo "❌ Pipeline FAILED (test ya da build patladı)."
        }
        always {
            cleanWs()
        }
    }
}
