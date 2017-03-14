# RestBuce

RestBuce is component for easy consumer rest in environment microservice.
You can create interface for consumer Rest without create implementation.

## Example

```java
// You can create interface for consumer Rest without create implementation
public interface MyRepoRest extends RestRepository {
    @Rest(endPoint="/endpoint1", method=HttpMethod.GET)
	String endpoint1();
	
	@Rest(endPoint="/endpoint2", method=HttpMethod.POST)
	String endpoint2(ArgEntity<Nome> nome);
	
	@Rest(endPoint="/endpoint3/{0}/", method=HttpMethod.POST)
	String endpoint3(ArgEntity<Nome> nome, ArgPathParam<String> params);
	
	@Rest(endPoint="/endpoint3/{0}/{1}", method=HttpMethod.POST)
	String endpoint3(ArgEntity<Nome> nome, ArgPathParam<String> params);
}

@Service
@DependsOn(value="MyRepoRest")
public class Service {

    @Autowired
    private MyRepoRest myRepoRest;
    
    public void exec(){
         Strign value = myRepoRest.endpoint1();
    }
}

// started config the RestBuce
@Configuration
@ImportAutoConfiguration(value={RestBuceConfig.class})
public class Config {
}
```
## Version

1.2017.1.1

#### 1) Binaries

Add this dependency in your software.

```xml
<dependency>
    <groupId>br.com.restbuce</groupId>
    <artifactId>restbuce</artifactId>
    <version>1.2017.1.1</version>
</dependency>
```

#### 2) Configuration
In the application.properties you have add key restbuce.pathscan and add package where have the your repositorys.

```
restbuce.pathscan=br.com.xx

```


## Communication

- [GitHub](https://github.com/marcelosv/eye)
- [Linkedin](https://www.linkedin.com/in/marcelo-souza-vieira-112174a9)
- [Twitter](https://twitter.com/uaicelo)
