package com.yukthiartful.yukthi_artful_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yukthiartful.yukthi_artful_backend.entity.Artwork;
import com.yukthiartful.yukthi_artful_backend.exception.ResourceNotFoundException;
import com.yukthiartful.yukthi_artful_backend.repository.ArtworkRepository;

@Service
public class ArtworkServiceImpl implements ArtworkService {

    private final ArtworkRepository artworkRepository;

    public ArtworkServiceImpl(ArtworkRepository artworkRepository) {
        this.artworkRepository = artworkRepository;
    }

    @Override
    public Artwork saveArtwork(Artwork artwork) {
        return artworkRepository.save(artwork);
    }

    @Override
    public List<Artwork> getAllArtworks() {
        return artworkRepository.findAll();
    }

    @Override
    public Artwork getArtworkById(Long id) {

        return artworkRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Artwork not found with id: " + id));
    }

    @Override
    public Artwork updateArtwork(Long id, Artwork artwork) {

        Artwork existingArtwork = artworkRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Artwork not found with id: " + id));

        existingArtwork.setTitle(artwork.getTitle());
        existingArtwork.setDescription(artwork.getDescription());
        existingArtwork.setPrice(artwork.getPrice());
        existingArtwork.setStockQuantity(
                artwork.getStockQuantity());

        existingArtwork.setCategory(
                artwork.getCategory());

        return artworkRepository.save(existingArtwork);
    }

    @Override
    public void deleteArtwork(Long id) {

        Artwork artwork = artworkRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException(
                        "Artwork not found with id: " + id));

        artworkRepository.delete(artwork);
    }
}