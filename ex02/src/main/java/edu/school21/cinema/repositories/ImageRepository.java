package edu.school21.cinema.repositories;

import edu.school21.cinema.models.Image;

import java.util.List;
import java.util.UUID;

public interface ImageRepository extends CrudRepository<Image> {
    List<Image> findAllByUserId(Long id);
    Image findByUUID(UUID uuid);
}
