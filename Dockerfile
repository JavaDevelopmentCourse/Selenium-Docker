FROM bellsoft/liberica-openjdk-alpine:21

RUN apk add curl jq
#working dir
WORKDIR /home/selenium-docker

#ADD
ADD target/docker-resources /home/selenium-docker
ADD runner.sh runner.sh
RUN dos2unix runner.sh

# broswer
#hub host
#test suite
# thread count

# why i am adding \ is so that linux wilil understand command is split and entered in new line
#ENTRYPOINT java -cp "libs/*" \
#           -Dselenium.grid.enabled=true \
#           -Dselenium.grid.hubHost=${HUBHOST} \
#           -Dbrowser=${BROWSER} \
#           org.testng.TestNG \
#           -threadcount ${THREADCOUNT} \
#           test-suites/${TESTSUITE}

ENTRYPOINT sh runner.sh