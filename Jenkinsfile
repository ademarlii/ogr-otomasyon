pipeline {
    agent any

    // GitHub push gelince tetiklensin (webhook ayarlıysa)
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
                // Linux agent'ta maven wrapper ile test
                sh './mvnw -B clean test'
            }
        }

        stage('Package') {
            steps {
                // Testleri tekrar koşmadan jar üret
                sh './mvnw -B -DskipTests package'
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
        // Workspace Cleanup plugin varsa kullan; yoksa bu bloğu silebirsin
        always {
            cleanWs()
        }
    }
}
