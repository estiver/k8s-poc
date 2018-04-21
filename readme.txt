sudo docker run -d --net=host consul:latest
sudo docker build -t ehh-poc .

sudo docker build -t ehipolito/k8s-poc .
sudo docker push ehipolito/k8s-poc
sudo docker run --name=poc --net="host" -it  ehh-poc