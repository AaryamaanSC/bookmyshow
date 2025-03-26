package com.aarya.bms.bms.merging.indexes;
import com.aarya.bms.bms.merging.enums.MovieGenre;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;



import java.util.List;

@Setter
@Getter
@Document(indexName = "movie-index")
@NoArgsConstructor
@AllArgsConstructor
public class MovieIndex {

    @Id
    private String id;
    private String name;
    private int durationInMinutes;
    private String language;
    private List<MovieGenre> genre;


}