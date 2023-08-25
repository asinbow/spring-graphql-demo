package com.example.springgraphqldemo.configuration

import com.apollographql.federation.graphqljava.Federation
import com.apollographql.federation.graphqljava._Entity
import com.example.springgraphqldemo.repository.AuthorRepository
import com.example.springgraphqldemo.repository.BookRepository
import graphql.schema.DataFetcher
import org.springframework.boot.autoconfigure.graphql.GraphQlSourceBuilderCustomizer
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.graphql.execution.ClassNameTypeResolver

@Configuration
class GraphqlConfiguration {

  @Bean
  fun federationTransform(
    authorRepository: AuthorRepository,
    bookRepository: BookRepository,
  ) = GraphQlSourceBuilderCustomizer { builder ->
    val entityDataFetcher: DataFetcher<*> = DataFetcher<Any> { dataFetchingEnvironment ->
      val representations: List<Map<String, Any>> = dataFetchingEnvironment.getArgument(_Entity.argumentName)
      representations.map { representation ->
        val typename = representation["__typename"] as String
        // spring-graphql doesn't support federation builtin, so we have to do it manually and naively
        // just wondering if an @EntityMapping or @BatchEntityMapping annotation could be implemented
        when(typename) {
          "Author" -> authorRepository.values.firstOrNull { it.id == representation["id"] }
          "Book" -> bookRepository.values.firstOrNull { it.id == representation["id"] }
          else -> null
        }
      }
    }

    builder.schemaFactory { registry, wiring ->
      Federation.transform(registry, wiring)
        .fetchEntities(entityDataFetcher)
        .resolveEntityType(ClassNameTypeResolver())
        .build()
    }
  }
}
