package com.aarya.bms.bms.merging.repository;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.GetResponse;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.Hit;
import com.aarya.bms.bms.merging.indexes.MovieIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MovieElasticRepository {

    private final ElasticsearchClient elasticsearchClient;

    @Autowired
    public MovieElasticRepository(ElasticsearchClient elasticsearchClient) {
        this.elasticsearchClient = elasticsearchClient;
    }

    public MovieIndex save(MovieIndex movie) {
        try {
            elasticsearchClient.index(i -> i
                    .index("movies")
                    .id(movie.getId())
                    .document(movie)
            );
            return movie;
        } catch (IOException e) {
            throw new RuntimeException("Failed to save movie to Elasticsearch", e);
        }
    }

    public Optional<MovieIndex> findById(String id) {
        try {
            GetResponse<MovieIndex> response = elasticsearchClient.get(g -> g
                            .index("movies")
                            .id(id),
                    MovieIndex.class
            );

            if (response.found()) {
                return Optional.of(response.source());
            } else {
                return Optional.empty();
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to fetch movie from Elasticsearch", e);
        }
    }

    public void deleteById(String id) {
        try {
            elasticsearchClient.delete(d -> d.index("movies").id(id));
        } catch (IOException e) {
            throw new RuntimeException("Failed to delete movie from Elasticsearch", e);
        }
    }

    public List<MovieIndex> searchMovie(String query) {
        try {
            SearchResponse<MovieIndex> response = elasticsearchClient.search(s -> s
                            .index("movies")
                            .query(q -> q
                                    .bool(b -> b
                                            .should(sh -> sh
                                                    .wildcard(w -> w
                                                            .field("title")
                                                            .value("*" + query.toLowerCase() + "*")
                                                    )
                                            )
                                            .should(sh -> sh
                                                    .matchPhrasePrefix(m -> m
                                                            .field("title")
                                                            .query(query)
                                                    )
                                            )
                                    )
                            ),
                    MovieIndex.class
            );

            return response.hits().hits().stream()
                    .map(Hit::source)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            throw new RuntimeException("Failed to search movies in Elasticsearch", e);
        }
    }
}
