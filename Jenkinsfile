pipeline{

    agent any

    stages{

        stage('BUILD JAR')
        {
            steps{
                bat "mvn clean package --DskipTests"

            }
        }
        stage('BUILD DOCKER IMAGE')
        {
        steps{

                echo "docker build -t=javadevelopmentcourse/selenium-web-automation-docker ."
            }
        }

        stage('PUSH THE DOCKER IMAGE TO DOCKER HUB')
        {
        steps{

                echo "docker push javadevelopmentcourse/selenium-web-automation-docker"
            }
        }
    }

}