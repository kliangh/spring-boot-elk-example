package code.kliangh.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    private static final Logger LOG = LoggerFactory.getLogger(TestController.class);

    @GetMapping("/welcome/{name}")
    String welcome(@PathVariable String name) {
        StringBuilder welcomeMessage = new StringBuilder();
        welcomeMessage.append("Greeting! ");
        welcomeMessage.append(name);

        LOG.info(welcomeMessage.toString());
        return welcomeMessage.toString();
    }

}
