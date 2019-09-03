package com.aykj.loglink.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created on 19-9-3.
 *
 * @author heyangya
 */
@RestController
public class FooController {

    @Autowired
    private FooService fooService;

    @GetMapping("/echo")
    public String echo(@RequestParam String s) {
        return fooService.echo(s);
    }
}
