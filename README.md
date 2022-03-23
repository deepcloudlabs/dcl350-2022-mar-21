# DCL-350: Implementing MicroService Architecture using Spring Cloud

These projects are created as part of the following training: **DCL-350**: *Implementing MicroService Architecture using Spring Cloud*

Please follow the link for the complete training catalog: https://www.deepcloudlabs.com/resources

Kurulum Bilgisi:
========================================
Eğitimde lab çalışmaları için gerekli olan çalışma ortamının kurulumu için öncelikle aşağıdaki bağlantıda yer alan sıkıştırılmış dosyayı makinanıza indirmeniz gerekiyor: https://courseware.deepcloudlabs.com/software/DEVEL-stage-2022a-java.se.and.spring.zip

Sıkıştırılmış dosyayı **C:\\** dizinine açtıktan sonra dizin yapısı aşağıda gösterildiği şekilde olacaktır:

![Installation folder](DEVEL-stage.png?raw=true "C: drive after decompress DEVEL-stage-2022a-java.se.and.spring.zip")

Diskinizdeki dizin yapısını yukarıdaki ile karşılaştırarak kontrol ediniz. **C:** sürücünüzün dolu dolması durumunda farklı bir sürücüye sıkıştırılmış dosyayı açabilirsiniz. Ancak bu durumda bir kaç konfigürasyon dosyasında değişiklik yapmanız gerekecektir. Lütfen, eğitim sırasında bu değişikliklerin neler olduğunu eğitmeninize sorunuz. 

NOTES: INTEGRATING MICROSERVICES 
========================================
1. REST over HTTP -> @RestController
REST Client:   
    i. Synchronous -> Blocking
       RestTemplate
   ii. Asynchronous -> Observer -> Callback
       AsyncRestTemplate (Deprecated) (since Spring 5)
       Reactive Client -> WebClient (Spring 5+)	
2. REST over WebSocket (Event) (RT Web Programming)
   Client -- connection --> Service/Server/Provider
3. Messaging (Event) -> AMQP (RabbitMQ), Kafka, ...
   Client --> MS <-- Service
4. RPC-style REST API 
   gRPC -> Protocol Buffer
5. GraphQL
   BFF (Proxy) + API Gateway -> 1+ MS
   
TOPICS IN SPRING CLOUD/CLOUD-NATIVE APPLICATION DEVELOPMENT
========================================
Spring Cloud -> Cloud-Native: 
  MSA + EDA + Serverless Architecture
  - Configuration Management
    Spring Cloud Config
      Config Server -> Git
      MSA -> Component -> RefreshScope
      POST 
    Spring Cloud Bus
     
  - Service Discvoery
  - Client-side Load Balancing
  - Resilience Patterns: 
      Retry, TimeLimiter, RateLimiter, BulkHead, CB
  - Routing
  - API Gateway
  - Monitoring
  - Distributed Logging
  - Security
  - Deployment
