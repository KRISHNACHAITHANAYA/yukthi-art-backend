package com.yukthiartful.yukthi_artful_backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.yukthiartful.yukthi_artful_backend.entity.Artwork;
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
}