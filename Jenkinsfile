pipeline{

    agent any

    stages{

        stage('BUILD JAR')
        {
            steps{
                bat "mvn clean package -DskipTests"
            }
        }
        stage('BUILD DOCKER IMAGE')
        {
        steps{

                bat "docker build -t=javadevelopmentcourse/selenium-web-automation-docker:latest ."
            }
        }

        stage('PUSH THE DOCKER IMAGE TO DOCKER HUB')
        {

        environment{
                Docker_Hub=credentials('DockerHub-cred')
        }
        steps{
                 // docker login -u %Docker_Hub_USR% -p %Docker_Hub_PSW%

                bat 'docker login -u %Docker_Hub_USR% -p %Docker_Hub_PSW%'
                // bat 'echo %Docker_Hub_PSW% | docker login -u %Docker_Hub_USR% --password-stdin'
                bat "docker push javadevelopmentcourse/selenium-web-automation-docker:latest"
                bat "docker tag javadevelopmentcourse/selenium-web-automation-docker:latest javadevelopmentcourse/selenium-web-automation-docker:${env.BUILD_NUMBER}"
                bat "docker push javadevelopmentcourse/selenium-web-automation-docker:${env.BUILD_NUMBER}"
            }
        }
    }
    post{
        always{
            bat "docker logout"
        }
    }

}