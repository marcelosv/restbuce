package com.br.restbuce.test.intercept;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.br.restbuce.config.RestBuceConfig;
import com.br.restbuce.test.TestApplication;
import com.br.restbuce.test.intercept.resources.RepositoryLocal;

@RunWith(SpringRunner.class)
@SpringBootTest(classes={TestApplication.class, RestBuceConfig.class})
public class IntercepRepositoryTest_antigp {

	@Autowired
	private ApplicationContext applicationContext;
	private Class xx;
	
	@Autowired
	private RepositoryLocal repositoryLocal2;
	
/*	
	public static interface MultiBeanFactory<T> {  // N.B. should not implement FactoryBean
		  T getObject(String name) throws Exception;
		  Class<?> getObjectType();
		  Collection<String> getNames();
		}

	public static class MultiBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
	  public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
	    BeanDefinitionRegistry registry = (BeanDefinitionRegistry) beanFactory;
	    Map<String, MultiBeanFactory> factories = beanFactory.getBeansOfType(MultiBeanFactory.class);

	    for (Map.Entry<String, MultiBeanFactory> entry : factories.entrySet()) {
	      MultiBeanFactory factoryBean = entry.getValue();
	      
	      for (Object name : factoryBean.getNames()) {
	    	  
	        BeanDefinition definition = BeanDefinitionBuilder
	            .genericBeanDefinition(factoryBean.getObjectType())
	            .setScope(BeanDefinition.SCOPE_SINGLETON)
	            .setFactoryMethod("getObject")
	            .addConstructorArgValue(name)
	            .getBeanDefinition();
	        
	        definition.setFactoryBeanName(entry.getKey());
	        registry.registerBeanDefinition(entry.getKey() + "_" + name, definition);
	        
	      }
	    }
	  }
	}
		*/
/*	private static class InterfaceTypeFilter extends AssignableTypeFilter {

		public InterfaceTypeFilter(Class<?> targetType) {
			super(targetType);
		}

		
		@Override
		public boolean match(MetadataReader metadataReader, MetadataReaderFactory metadataReaderFactory) throws IOException {

			return metadataReader.getClassMetadata().isInterface() && super.match(metadataReader, metadataReaderFactory);
		}
	}
	*/
	/*@Configuration
	static class Config {
		
		@Bean
		  public MultiBeanFactoryPostProcessor msdultiBeanFactoryPostProcessor() {
		    return new MultiBeanFactoryPostProcessor();
		  }

		  @Bean
		  public MultiBeanFactory<RestRepository> personFactory() {
			
		    return new MultiBeanFactory<RestRepository>() {
		    	
		      public RestRepository getObject(String name) throws Exception {
		    	  Class ccc= Class.forName(name);
		    	  return (RepositoryLocal) xxx(ccc);
		      }
		      
		      public Class<?> getObjectType() {
		        return RepositoryLocal.class;
		      }
		      
		      public Collection<String> getNames() {
		    	  
		    	  Reflections reflections = new Reflections("com.br.restbuce.intercept");    
		    	  Set<Class<? extends RestRepository>> classes = reflections.getSubTypesOf(RestRepository.class);
		    	  
		    	  Collection<String> itens = new ArrayList<String>(classes.size());
		    	  for( Class item : classes){
		    		  itens.add(item.getCanonicalName());
		    	  }
		    	  
		        return itens;//Arrays.asList("com.br.restbuce.intercept.RepositoryLocal");
		      }
		      
		    };
		  }
		  */
		  
//		@Bean
//		RepositoryLocal crete(){
//			
//			return xxx();
//		}

		/*private Object xxx(Class interfaceClass) {
			Object robot = (RepositoryLocal) java.lang.reflect.Proxy.newProxyInstance(
					RepositoryLocal.class.getClassLoader(),
	                new java.lang.Class[] { interfaceClass },
	                new java.lang.reflect.InvocationHandler(){

		            @Override
		            public Object invoke(Object proxy, java.lang.reflect.Method method, Object[] args) throws java.lang.Throwable {
		                String method_name = method.getName();
		                Class<?>[] classes = method.getParameterTypes();

		                if (method_name.equals("test")) {
		                    if (args == null) {
		                    	System.out.println("test");
		                        return "Mr IRobot";
		                    } else {
		                        return args[0] + " IRobot";
		                    }
		                } else if (method_name.equals("Talk")) {
		                    switch (classes.length) {
		                        case 0:
		                            System.out.println("Hello");
		                            break;
		                        case 1:
		                            if (classes[0] == int.class) {
		                                System.out.println("Hi. Int: " + args[0]);
		                            } else {
		                                System.out.println("Hi. String: " + args[0]);
		                            }
		                            break;
		                        case 2:
		                            if (classes[0] == String.class) {
		                                System.out.println("Hi. String: " + args[0] + ". Int: " + args[1]);
		                            } else {
		                                if (classes[1] == String.class) {
		                                    System.out.println("Hi. int: " + args[0] + ". String: " + args[1]);
		                                } else {
		                                    System.out.println("Hi. int: " + args[0] + ". Int: " + args[1]);
		                                }
		                            }
		                            break;
		                    }
		                }
		                return null;
		            }
		        });
			
			return robot;
		}*/
//	}
	
	@Before
	public void init(){
		
		
		
                
//		 robot.test();
	        
		/*xx = new RepositoryLocal() {
			{
				System.out.println("arg = ");
			}
			
			@Override
			public void test() {
				System.out.println("sgg");				
			}
			
		}.getClass();

		AnnotatedGenericBeanDefinition bd = new AnnotatedGenericBeanDefinition(xx);
		((GenericApplicationContext)applicationContext).registerBeanDefinition("repositoryLocalXX", bd);
		
		ConfigurableListableBeanFactory beanFactory = ((ConfigurableApplicationContext) applicationContext).getBeanFactory();
		beanFactory.createBean(xx, beanFactory.AUTOWIRE_AUTODETECT, true);*/
		
//		AssociationOperation obj1 = (AssociationOperation)applicationContext.getBeanFactory().createBean(AssociationOperation.class, applicationContext.getBeanFactory().AUTOWIRE_AUTODETECT, true);
//		AssociationOperation obj = (AssociationOperation)applicationContext.getBean("associationOperation");


//		BeanDefinitionBuilder builder = BeanDefinitionBuilder.rootBeanDefinition(robot.getClass());
//		builder.addPropertyReference("propertyName", "sfsd");
//		builder.addPropertyValue("propertyName", robot);
//		
//		GenericApplicationContext context = new GenericApplicationContext();
//        context.setParent(applicationContext);
//        context.registerBeanDefinition("someBean",builder.getBeanDefinition());
//		
//        context.refresh();
        
//		GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
//		beanDefinition.setBeanClass(xx);
//		beanDefinition.setLazyInit(false);
//		beanDefinition.setAbstract(false);
//		beanDefinition.setAutowireCandidate(true);
//		beanDefinition.setScope("session");
//
//		GenericApplicationContext context = new GenericApplicationContext();
//        context.setParent(applicationContext);
//        context.registerBeanDefinition("dynamicBean",beanDefinition);
	}
	
//	@Autowired
//	private RepositoryLocal repositoryLocal;
	
	@Test
	public void testIntercept(){
		
		RepositoryLocal repositoryLocal = (RepositoryLocal) applicationContext.getBean(RepositoryLocal.class);
		Assert.assertNotNull(repositoryLocal);
		Assert.assertNotNull(repositoryLocal2);
	}
	
}
