#!/bin/bash

kubectl delete service/dynamodb
kubectl delete -f ./kubernetes/dynamo.yml
minikube stop
# minikube delete
