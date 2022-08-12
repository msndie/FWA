package edu.school21.cinema.services;

import edu.school21.cinema.models.Image;

import java.util.List;
import java.util.UUID;

public interface ImageService {
    void addImage(Image image);
    List<Image> getAllImagesByUserId(Long id);
    Image getByUUID(UUID uuid);
}
