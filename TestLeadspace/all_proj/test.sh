DOCKERFILE_FULL_PATH=$1
mkdir -p /tmp/yehuda/build
mkdir -p /tmp/yehuda/test
cd /tmp/yehuda/build
cp $DOCKERFILE_FULL_PATH .
docker build -t alex_test -f Dockerfile .
docker run -d -p 7894:8080 --name yehuda_test alex_test:latest
cd /tmp/yehuda/test 
git clone https://github.com/yudix/TestLeadSpace.git /tmp/yehuda/test
cd TestLeadspace
mvn clean test
