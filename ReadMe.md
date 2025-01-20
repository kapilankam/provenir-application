# Robobob application
### Overview

1. Created a spring boot application with a single endpoint GET /api/answer as per the requirement.
2. Added the handler code for handling arithmetic expressions and basic questions which can be extended in the future if we want to have a specific type of handling without changing the existing code.
   1. Refer to classes ArithenmicQuestionHandler.java (BODMAS rule in evaluation) and MatchingQuestionHandler.java
3. Loaded Predefined Questions from a json file from the resources folder.
4. To compare the question with matching sentence from predefined questions, explored the following libraries.
   1. python spacy library.
   2. edu.stanford.nlp java library with cosine similarity.
   3. There are couple of challenges to determine the similarity score to determine the exact match, for the scope of this task we have gone with .85 value. 
   4. But, we can explore more the similarity matching score
5. Inscope of this task, used basic stack based expression evaluation. We can also use existing expression evaluator libraries to support complex math operations like power function. (^ operator)

### Extensibility:
1. Currently, i have added json file from resources. We can externalise the json file to a repository in github and introduce a spring cloud config server to point to github resource.
2. When we want to add more question in future, we can update the json file in github repository and trigger the refersh (by /actuator/refresh or @RefreshScope annotation), This way we don't need to deploy the application again.
3. We can think of storing questions to a database and read from the database. the framework to support this code is already added in repository package which we can leverage and added support for mysql driver with datasource configuration.


### How to build docker image:
```docker build -t provenir-robobob-app .```
### How to run the docker application
```docker run -p 8080:8081 -it provenir-robobob-app```

### More details about code.
1. Added logging in required places.
2. Added exception handling.
3. Used Gradle as build tool.

### CI/CD Pipeline
1. We can user Jenkins to build and deploy the application.

## Deployment Strategy
1. We can use AWS Kubernates cluster to deploy the application.
2. Added the corresponding code in kube folder of the application code.
3. the code also added to support the scaling of the pods to support the increase in the traffic.
4. Useful kubectl commands.
   ```
   kubectl apply -f deployment.yaml
   kubectl apply -f service.yaml
   kubectl apply -f https://github.com/kubernetes-sigs/metrics-server/releases/latest/download/components.yaml
   kubectl apply -f hpa.yaml
   kubectl top pods to monitor resource usage.
    kubectl logs <pod-name> to view logs.```
We can integrate with prometheus and grafana for indepth monitoring.
We can use splunk, datadog for exporting the logs to search. Inorder to do that we need to run splunk, datadog agent as a side car pattern along with the main application.
