package main;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Random;

@RestController
public class DefaulController
{
    @RequestMapping("/")
    public String index()
    {
        Random random = new Random();
        int rand_int = random.nextInt();
        return (new Date()).toString() + rand_int;
    }
}
