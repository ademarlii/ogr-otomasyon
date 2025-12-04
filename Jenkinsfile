pipeline {
    agent any

    environment {
        // Replace these with real values in Jenkins job configuration or leave empty
        DEPLOY_HOST = ''               // e.g. 192.0.2.10 or host.example.com
        DEPLOY_USER = ''               // e.g. deployuser
        DEPLOY_PATH = ''               // e.g. /opt/apps/ogrenci
        SSH_CREDENTIALS_ID = 'deploy-ssh-cred' // Jenkins credential id for "SSH Username with private key"
        MAVEN_TOOL = 'M3'              // Jenkins Maven tool name
        JDK_TOOL = 'jdk-21'            // Jenkins JDK tool name
    }

    tools {
        maven "${MAVEN_TOOL}"
        jdk "${JDK_TOOL}"
    }

    triggers {
        // GitHub push webhook trigger (configure webhook in GitHub repo settings)
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
                // Run tests
                sh 'mvn -B clean test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn -B -DskipTests package'
            }
        }

        stage('Archive') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }

        stage('Deploy') {
            when {
                expression { return env.DEPLOY_HOST?.trim() }
            }
            steps {
                script {
                    // Use ssh-agent plugin with SSH Username with private key credential
                    sshagent (credentials: [env.SSH_CREDENTIALS_ID]) {
                        sh "scp -o StrictHostKeyChecking=no target/*.jar ${DEPLOY_USER}@${DEPLOY_HOST}:${DEPLOY_PATH}/"
                        // Optionally restart remote service (uncomment and adjust service name)
                        // sh "ssh -o StrictHostKeyChecking=no ${DEPLOY_USER}@${DEPLOY_HOST} 'sudo systemctl restart myapp.service || true'"
                    }
                }
            }
        }
    }

    post {
        success {
            echo "Pipeline completed successfully"
        }
        failure {
            echo "Pipeline failed"
        }
        always {
            cleanWs()
        }
    }
}

