This is the base gRPC project w/ base authenticate to copy and use in future projects


Server:
There are two main servers ; envoy which acts as proxy to convery grpc-web request to grpc server, and public server, which hosts two grpc services ( login and sample call)

Will need to create the docker images for both. For grpc server, build gradle , run tests, then run 'gradle docker'; docker image will be created , run with port 8081 exposed.

For envoy, build dokcer images in docker file in server dir; will read envoy.yaml file for configurations.Expose port 9901(admin) and 8080(port exposed to forward to 8081 for grpc calls)

Client:
React, run with 'npm start'. Will save jwt as cookie on client, and use to make call to server 
