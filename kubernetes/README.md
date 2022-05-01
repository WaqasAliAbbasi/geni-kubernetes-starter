## How to run geni kubernetes starter on kubernetes?

Let's build a uberjar from the project and run it on a kubernetes cluster.

## Setting up local cluster

1. Install [MiniKube](https://minikube.sigs.k8s.io/docs/start/)
1. Run MiniKube cluster using `minikube start`

## Docker Image for Spark Job

1. `/usr/local/Cellar/apache-spark/3.2.1/bin/docker-image-tool.sh -m -r spark-base -t latest build` (One-time) Use apache spark's script to create a base spark image 
1. `docker build -t geni-kubernetes-start:latest -f kubernetes/Dockerfile .` Run from root
1. `minikube image load geni-kubernetes-start:latest` Load your local docker image into minikube so it can be pulled inside the cluster 

## Submitting Spark Job to Cluster
1. Find cluster proxy `kubectl cluster-info` and replace master below with it
1. Submit spark job
```
/usr/local/Cellar/apache-spark/3.2.1/bin/spark-submit \
    --master k8s://https://127.0.0.1:52754 \
    --deploy-mode cluster \
    --name geni-kubernetes-starter \
    --class geni-kubernetes-start.core \
    --conf spark.executor.instances=5 \
    --conf spark.kubernetes.container.image=geni-kubernetes-start:latest \
    --conf spark.kubernetes.driverEnv.USE_CASE=job-a \
    local:///app/geni-kubernetes-starter.jar
``` 