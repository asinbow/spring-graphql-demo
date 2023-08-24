package com.example.springgraphqldemo.repository

import com.example.springgraphqldemo.types.Author
import com.example.springgraphqldemo.types.Book
import org.springframework.stereotype.Repository

@Repository
class BookRepository {
  val values: List<Book> =
    listOf(
      Book("book:1", "Harry Potter and the Philosopher's Stone", 223, "author:1"),
      Book("book:2", "Harry Potter and the Chamber of Secrets", 251, "author:1"),
      Book("book:3", "Harry Potter and the Prisoner of Azkaban", 251, "author:1"),
      Book("book:4", "Harry Potter and the Goblet of Fire", 636, "author:1"),
      Book("book:5", "Two Cities", 766, "author:2"),
      Book("book:5", "Peace and War", 324, "author:2"),
    )
}
