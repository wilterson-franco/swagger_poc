Merchant Clarity Service
========================

Receives requests from merchant-self-serve and saves them in a DB which, in turn, has a different model.

### clarity-merchant-service-spec.yml
The spec for the requests received from merchant-self-serve

### eliminator-domain-spec.yml
The spec for the model used in the DB

### pom.xml
Leverages the openapi-generator-maven-plugin plugin to build the model classes out of clarity-merchant-service-spec.yml and eliminator-domain-spec.yml
