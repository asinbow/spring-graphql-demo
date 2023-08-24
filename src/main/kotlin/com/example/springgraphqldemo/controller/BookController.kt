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
class BookController(
  private val bookRepository: BookRepository,
  private val authorRepository: AuthorRepository
) {
  @QueryMapping
  @GetMapping("/books/{id}")
  fun book(@Argument @PathVariable id: String): Book? = bookRepository.values.firstOrNull { it.id == id }

  @QueryMapping
  @GetMapping("/books")
  fun books(): List<Book> = bookRepository.values

  @SchemaMapping(typeName = "Book")
  fun author(book: Book): Author {
    return authorRepository.values.first { it.id == book.authorId }
  }
}
