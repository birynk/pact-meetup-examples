package cdc;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import org.apache.http.client.fluent.Request;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import static io.pactfoundation.consumer.dsl.LambdaDsl.newJsonArray;

public class ProviderUsersApiExpectationsTest {

    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("dummy-provider", "localhost", 8085, this);

    @Pact(provider = "dummy-provider", consumer = "dummy-consumer")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        return builder
                .uponReceiving("Retrieve users")
                .path("/provider/api/users")
                .method("GET")
                .willRespondWith()
                .status(200)
                .body(newJsonArray((rootArray) -> {
                    rootArray.object((o) -> {
                        o.stringValue("firstName", "Max");
                        o.stringType("lastName");
                    }).build();
                    rootArray.object((o) -> {
                        o.stringValue("firstName", "TestFirstName");
                        o.stringType("lastName");
                    }).build();
                }).build())
                .toPact();
    }

    @Test
    @PactVerification
    public void runTest() throws IOException {
        String response = Request.Get("http://localhost:8085/provider/api/users").execute().returnContent().asString();
        System.out.println(response);
    }
}
