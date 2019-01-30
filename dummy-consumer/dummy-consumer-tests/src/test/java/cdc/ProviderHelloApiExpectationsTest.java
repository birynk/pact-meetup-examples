package cdc;

import au.com.dius.pact.consumer.Pact;
import au.com.dius.pact.consumer.PactProviderRuleMk2;
import au.com.dius.pact.consumer.PactVerification;
import au.com.dius.pact.consumer.dsl.PactDslWithProvider;
import au.com.dius.pact.model.RequestResponsePact;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;

import static io.pactfoundation.consumer.dsl.LambdaDsl.newJsonArray;

public class ProviderHelloApiExpectationsTest {

    @Rule
    public PactProviderRuleMk2 mockProvider = new PactProviderRuleMk2("dummy-provider",
            "localhost",
            8085,
            this);

    @Pact(provider = "dummy-provider", consumer = "dummy-consumer")
    public RequestResponsePact createPact(PactDslWithProvider builder) {
        return builder
                .uponReceiving("Say hello")
                .path("/provider/api/hello")
                .method("POST")
                .body("{\"name\": \"testUser\"}")
                .willRespondWith()
                .status(200)
                .body("{\"hello\": \"testUser\"}")
                .toPact();
    }

    @Test
    @PactVerification
    public void runTest() throws IOException {
        Request.Post("http://localhost:8085/provider/api/hello")
                .bodyString("{\"name\": \"testUser\"}", ContentType.APPLICATION_JSON)
                .execute()
                .returnContent()
                .asString();
    }
}
