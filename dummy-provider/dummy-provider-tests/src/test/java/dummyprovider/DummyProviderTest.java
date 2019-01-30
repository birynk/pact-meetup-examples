package dummyprovider;

import au.com.dius.pact.provider.junit.PactRunner;
import au.com.dius.pact.provider.junit.Provider;
import au.com.dius.pact.provider.junit.State;
import au.com.dius.pact.provider.junit.loader.PactFolder;
import au.com.dius.pact.provider.junit.target.HttpTarget;
import au.com.dius.pact.provider.junit.target.Target;
import au.com.dius.pact.provider.junit.target.TestTarget;
import cdct.dummyprovider.DummyProviderApplication;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

@RunWith(PactRunner.class)
@Provider("dummy-provider")
@PactFolder("pacts")
public class DummyProviderTest {

    @TestTarget
    public final Target target = new HttpTarget(8085);

    public static ConfigurableApplicationContext application;

    @BeforeClass
    public static void setUpService() {
        application = SpringApplication.run(DummyProviderApplication.class);
    }

    @BeforeClass
    public static void afterAll() {
        application.stop();
    }

    @State("default") // Method will be run before testing interactions that require "default" or "no-data" state
    public void toDefaultState() {
        // Prepare service before interaction that require "default" state
        // ...
        System.out.println("Now service in default state");
    }
}
