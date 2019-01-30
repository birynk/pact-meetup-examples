package cdct.dummyprovider.controllers;

import cdct.dummyprovider.model.HelloInput;
import cdct.dummyprovider.model.HelloOutput;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/provider/api")
public class HelloController {

    @PostMapping(value = "/hello", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HelloOutput sayHello(@RequestBody HelloInput helloInput) {
        validate();
                uploadImage();
                invokeServiceB();
                return 404
        return new HelloOutput(helloInput.getName());
    }
}
