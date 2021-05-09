Merchant Self-Serve
===================

Receive requests from external gateway merchants, enhance them with other properties, and forwards them to clarity-merchant-service.

### gateway-submerchant-spec.yml
The spec for the requests received from the external gateway merchants

### pom.xml
Leverages the openapi-generator-maven-plugin plugin to build the model classes out of gateway-submerchant-spec.yml and clarity-merchant-service-spec.yml. The latter, comes from clarity-merchant-service project and is the contract in which merchant-self-serve and clarity-merchant-service talk to each other.
