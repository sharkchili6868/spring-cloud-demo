package demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class RibbonConsumerController {

    // inject restTemplate, this bean has been created in our application without load balance
    @Autowired
    private RestTemplate noBalanceRestTemplate;

    // inject restTemplate, but this been is created with load balance function
    // by default, we inject by class, but if we want to inject by name, we need the qualifier annotation
    @Autowired
    @Qualifier("balanceRestTemplate")
    private RestTemplate balanceRestTemplate;

    /**
     * no ribbon implementation
     */
    @GetMapping("/ribbon/old/get/{id}")
    public String old(@PathVariable int id) {
        ResponseEntity<String> result = noBalanceRestTemplate.getForEntity("http://localhost:9906/api/produce/" + id, String.class);
        return result.getBody();
    }

    /**
     * This will give you an error, because you cannot directly call a producer which has load balance feature
     */
    @GetMapping("/ribbon/old/test/{id}")
    public String error(@PathVariable int id) {
        ResponseEntity<String> result = balanceRestTemplate.getForEntity("http://localhost:9906/api/produce/" + id, String.class);
        return result.getBody();
    }

    /**
     * Use ribbon for load balance
     */
    @GetMapping("/ribbon/new/get/{id}")
    public String ribbon(@PathVariable int id) {
        ResponseEntity<String> result = balanceRestTemplate.getForEntity("http://spring-cloud-producer/api/produce/" + id, String.class);
        return result.getBody();
    }

}
