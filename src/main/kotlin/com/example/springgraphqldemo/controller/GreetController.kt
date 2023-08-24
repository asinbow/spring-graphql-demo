package com.example.springgraphqldemo.controller

import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

// @Controller
@RestController
class GreetController {
  @QueryMapping
  @GetMapping("/greet")
  fun greet(): String = "Hello, GraphQL!"
}
