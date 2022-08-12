package edu.school21.cinema.services;

import edu.school21.cinema.models.Image;
import edu.school21.cinema.repositories.ImageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ImageServiceImpl implements ImageService {

    private ImageRepository imageRepository;

    @Autowired
    public void setImageRepository(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public void addImage(Image image) {
        imageRepository.save(image);
    }

    public List<Image> getAllImagesByUserId(Long id) {
        return imageRepository.findAllByUserId(id);
    }

    public Image getByUUID(UUID uuid) {
        return imageRepository.findByUUID(uuid);
    }
}
