sudo docker run -d --net=host consul:latest
sudo docker build -t ehh-poc .

sudo docker build -t ehipolito/k8s-poc .
sudo docker push ehipolito/k8s-poc
sudo docker run --name=poc --net="host" -it  ehh-poc


--criar repositorio e subindo localmente a versao v1
sudo docker run -d -p 5000:5000 --restart=always --name registry registry:2
sudo docker build -t localhost:5000/ehh-poc:v1-local .
sudo docker ^Csh localhost:5000/ehh-poc:v1-local
http://127.0.0.1:5000/v2/_catalog
http://127.0.0.1:5000/v2/ehh-poc/tags/list