package com.hcl.ecommerce.config;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.EntityType;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;

import com.hcl.ecommerce.entity.Country;
import com.hcl.ecommerce.entity.Order;
import com.hcl.ecommerce.entity.Product;
import com.hcl.ecommerce.entity.ProductCategory;
import com.hcl.ecommerce.entity.State;

@Configuration
public class MyDataRestConfig implements RepositoryRestConfigurer {
	
	@Value("${allowed.origins}")
	private String[] theAllowedOrigins;
	
	private EntityManager entityManager;
	
	@Autowired
	public MyDataRestConfig(EntityManager theEntityManager) {
		
		entityManager = theEntityManager;
	}
	
	static Logger log = Logger.getLogger(MyDataRestConfig.class);
	
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config, CorsRegistry cors) {
		
		HttpMethod[] theUnsupportedActions = {HttpMethod.PUT, HttpMethod.POST,
											  HttpMethod.DELETE, HttpMethod.PATCH};
		log.info("Disabling Put, Post, Delete and Patch");
		// disable HTTP methods for Product: PUT, POST and DELETE
		disableHttpMethods(Product.class, config, theUnsupportedActions);
		
		// disable HTTP methods for Product: PUT, POST and DELETE
		disableHttpMethods(ProductCategory.class, config, theUnsupportedActions);
		
		// use above functionality to make State and Country READ ONLY
		disableHttpMethods(Country.class, config, theUnsupportedActions);
		disableHttpMethods(State.class, config, theUnsupportedActions);
		
		disableHttpMethods(Order.class, config, theUnsupportedActions);
		
		
		// call an internal helper method
		exposeIds(config);
		
		// configure cors mapping
		cors.addMapping(config.getBasePath() + "/**").allowedOrigins(theAllowedOrigins);
	}

	private void disableHttpMethods(Class theClass, RepositoryRestConfiguration config, HttpMethod[] theUnsupportedActions) {
		log.debug("The helper method disableHttpMethods is called for the " + theClass + " class");
		config.getExposureConfiguration()
			  .forDomainType(theClass)
			  .withItemExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions))
			  .withCollectionExposure((metdata, httpMethods) -> httpMethods.disable(theUnsupportedActions));
	}
	
	// call an internal helper method
	private void exposeIds(RepositoryRestConfiguration config) {
		log.info("The helper method exposeIds is called");
		
		//expose entity ids
		
		// - get a list of all entity classes from the entity manager
		Set<EntityType<?>> entities = entityManager.getMetamodel().getEntities();
		
		// - create an array of the entity types
		List<Class> entityClasses = new ArrayList<>();
		
		// - get the entity types for the entities
		for (EntityType tempEntityType : entities) {
			entityClasses.add(tempEntityType.getJavaType());
		}
		
		// - expose the entity ids for the array of entity/domain types
		Class[] domainTypes = entityClasses.toArray(new Class[0]);
		config.exposeIdsFor(domainTypes);
	}
}
	
