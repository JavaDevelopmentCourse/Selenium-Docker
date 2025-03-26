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

                bat "docker build -t=javadevelopmentcourse/selenium-web-automation-docker ."
            }
        }

        stage('PUSH THE DOCKER IMAGE TO DOCKER HUB')
        {
        steps{

                bat "docker push javadevelopmentcourse/selenium-web-automation-docker"
            }
        }
    }

}