pipeline {
    agent any

    // GitHub'dan push gelince otomatik tetiklensin
    triggers {
        githubPush()
    }

    stages {
        stage('Checkout') {
            steps {
                // Jenkins job'ındaki SCM ayarındaki repo/branch'i çeker
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                // Windows'ta maven wrapper ile test çalıştır
                bat 'mvnw.cmd -B clean test'
            }
        }

        stage('Package') {
            steps {
                // Testleri tekrar koşmadan jar üret
                bat 'mvnw.cmd -B -DskipTests package'
            }
        }

        stage('Fake Deploy') {
            when {
                // Sadece main branch'te fake deploy çalışsın
                branch 'main'
            }
            steps {
                // SAHTE DEPLOY: jar'ı workspace içindeki fake-deploy klasörüne kopyala
                bat '''
                if exist fake-deploy rmdir /S /Q fake-deploy
                mkdir fake-deploy
                copy /Y target\\*.jar fake-deploy\\
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
        // Workspace Cleanup plugin varsa kalsın, yoksa bu bloğu silebilirsin
        always {
            cleanWs()
        }
    }
}
