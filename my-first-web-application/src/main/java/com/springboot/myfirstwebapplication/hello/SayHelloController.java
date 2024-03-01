package com.springboot.myfirstwebapplication.hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {
    // "say-hello" => "Hello! What are you learning today?"

    @RequestMapping("/say-hello")
    @ResponseBody
    public String sayHello() {
        return "Hello! What are you learning today?";
    }

    @RequestMapping("/demo")
    public String sayHelloJsp() {
        return "demo";
    }

    @RequestMapping("/say-hello-html")
    @ResponseBody
    public String sayHelloHtml() {
        return """
                <!DOCTYPE html>
                <html lang="en">
                <head>
                    <meta charset="UTF-8">
                    <meta name="viewport" content="width=device-width, initial-scale=1.0">
                    <title>Simple HTML Page</title>
                </head>
                <body>
                                
                <div>
                    <h1>Welcome to My Simple HTML Page</h1>
                    <p>This is a basic HTML page. It can be easily modified and expanded to create more complex web pages.</p>
                   
                </div>
          
                </body>
                </html>
                                
                """;
    }
}
