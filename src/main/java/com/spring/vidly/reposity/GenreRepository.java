package com.spring.vidly.reposity;

import com.spring.vidly.domain.Genre;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GenreRepository extends JpaRepository<Genre, Long> {

    Genre findGenreByUuid(String uuid);

    @Transactional
    void deleteGenreByUuid(String uuid);
}
