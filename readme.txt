sudo docker run -d --net=host consul:latest
sudo docker build -t ehh-poc .
sudo docker run --name=poc --net="host" -it  ehh-poc