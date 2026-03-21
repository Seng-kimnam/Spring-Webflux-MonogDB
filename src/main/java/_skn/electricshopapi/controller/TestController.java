package _skn.electricshopapi.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/tests")
public class TestController {

    @GetMapping("/first")
    public Mono<String> showMessage(){
        return Mono.just("Hello From Webflux");
    }
}
