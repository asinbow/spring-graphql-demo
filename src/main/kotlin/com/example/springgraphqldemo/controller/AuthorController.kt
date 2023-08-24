package com.example.springgraphqldemo.controller

import com.example.springgraphqldemo.repository.AuthorRepository
import com.example.springgraphqldemo.repository.BookRepository
import com.example.springgraphqldemo.types.Author
import com.example.springgraphqldemo.types.Book
import org.springframework.graphql.data.method.annotation.Argument
import org.springframework.graphql.data.method.annotation.QueryMapping
import org.springframework.graphql.data.method.annotation.SchemaMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

// @Controller
@RestController
class AuthorController(
  private val authorRepository: AuthorRepository,
  private val bookRepository: BookRepository,
) {
  @QueryMapping
  @GetMapping("/authors/{id}")
  fun author(@Argument @PathVariable id: String): Author? = authorRepository.values.firstOrNull { it.id == id }

  @QueryMapping
  @GetMapping("/authors")
  fun authors(): List<Author> = authorRepository.values

  @SchemaMapping(typeName = "Author")
  fun books(author: Author): List<Book> {
    return bookRepository.values.filter { it.authorId == author.id }
  }
}
