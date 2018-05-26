#!/bin/bash

minikube start
kubectl create -f ./kubernetes/dynamo.yml

while [[ `kubectl get pods` != *"Running"* ]];
do
	echo "Waiting for DynamoDB pod Running"
	sleep 15
done


kubectl expose deployment/dynamodb --type=NodePort --port 8000
export DYN_NODE_PORT=$(kubectl get services/dynamodb -o go-template='{{(index .spec.ports 0).nodePort}}')

export DYNAMO_URL=http://$(minikube ip):$DYN_NODE_PORT
echo "Dynamo URL: $DYNAMO_URL"

aws dynamodb create-table --table-name UserAccounts --attribute-definitions AttributeName=userId,AttributeType=S  --key-schema AttributeName=userId,KeyType=HASH --provisioned-throughput ReadCapacityUnits=5,WriteCapacityUnits=5 --endpoint-url $DYNAMO_URL

aws dynamodb list-tables --endpoint-url $DYNAMO_URL --output json


kubectl port-forward deployment/dynamodb 8000:8000 