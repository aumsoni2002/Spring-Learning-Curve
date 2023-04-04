package com.practice.springcoredemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringcoredemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringcoredemoApplication.class, args);
    }
}

/*
-- Bean Scopes - Overview
Scope refers to the lifecycle of a bean such as:  How long does the bean live? How many instances are created? How is the bean shared?

• Singleton Scope
Now the default scope is singleton.
Singleton means that by default, the Spring Container creates only one instance of the bean. It is catched in memory. And all dependency injections
for the bean will reference the same bean.(Single item that is created in memory and shared amongst different other items.)
Singleton Example:
@RestController
public class DemoController {
    // Here we have created two references and both will point at the same instance.
    private Coach myCoach;
    private Coach anotherCoach;

    @Autowired
    public DemoController(
                  @Qualifier("cricketCoach") Coach theCoach,			// first reference points at the 'CricketCoach' instance.
                  @Qualifier("cricketCoach") Coach theAnotherCoach) {		// Second reference also points at the same above 'CricketCoach' instance.
        myCoach = theCoach;
        anotherCoach = theAnotherCoach;
    }
}

• Explicitly Specify Bean Scope
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation. Scope;
import org.springframework.stereotype.Component;

@Component
@Scope (ConfigurableBeanFactory.SCOPE_SINGLETON)				// this is how we can explicitly specify bean scope
public class CricketCoach implements Coach {
    ...
}

• Additional Spring Bean Scopes
Scope		Description
singleton	Create a single shared instance of the bean. Default scope.
prototype	Creates a new bean instance for each container request.
request		Scoped to an HTTP web request. Only used for web apps.
session		Scoped to an HTTP web session. Only used for web apps.
global-session	Scoped to a global HTTP web session. Only used for web apps.


• Prototype Scope Example: a new object instance is created for each injection
// File: CricketCoach.java
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation. Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)		// we have to specify the scope here in 'CricketCoach' class.
public class CricketCoach implements Coach {
    ...
}

// File: DemoController.java
@RestController
public class DemoController {
    // Here we have created two references and both will point at the two different instances.
    private Coach myCoach;
    private Coach anotherCoach;

    @Autowired
    public DemoController(
		  // Here we have done two injections of same class 'CricketCoach', as we have specified 'Prototype' scope, a new object instance is created for each injection
                  @Qualifier("cricketCoach") Coach theCoach,			// first reference will point at one instance of 'CricketCoach'
                  @Qualifier("cricketCoach") Coach theAnotherCoach) {	        // second reference will point at second instance of 'CricketCoach'
        myCoach = theCoach;
        anotherCoach = theAnotherCoach;
    }
}
*/