package com.example.springgraphqldemo.repository

import com.example.springgraphqldemo.types.Author
import com.example.springgraphqldemo.types.Book
import org.springframework.stereotype.Repository

@Repository
class AuthorRepository {
  val values: List<Author> =
    listOf(
      Author("author:1", "Joanne Rowling", 55),
      Author("author:2", "Herman Melville", 72),
      Author("author:3", "Anne Rice", 79),
    )
}
