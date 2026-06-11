package com.yukthiartful.yukthi_artful_backend.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yukthiartful.yukthi_artful_backend.entity.Artwork;
import com.yukthiartful.yukthi_artful_backend.service.ArtworkService;

@RestController
@RequestMapping("/api/artworks")
public class ArtworkController {

    private final ArtworkService artworkService;

    public ArtworkController(ArtworkService artworkService) {
        this.artworkService = artworkService;
    }

    @PostMapping
    public Artwork createArtwork(@RequestBody Artwork artwork) {
        return artworkService.saveArtwork(artwork);
    }

    @GetMapping
    public List<Artwork> getAllArtworks() {
        return artworkService.getAllArtworks();
    }

    @GetMapping("/{id}")
    public Artwork getArtworkById(
            @PathVariable Long id) {

        return artworkService.getArtworkById(id);
    }

    @PutMapping("/{id}")
    public Artwork updateArtwork(
            @PathVariable Long id,
            @RequestBody Artwork artwork) {

        return artworkService.updateArtwork(id, artwork);
    }

    @DeleteMapping("/{id}")
    public String deleteArtwork(
            @PathVariable Long id) {

        artworkService.deleteArtwork(id);

        return "Artwork deleted successfully";
    }

    @GetMapping("/search")
    public List<Artwork> searchArtworks(
            @RequestParam String keyword) {

        return artworkService.searchArtworks(keyword);
    }

    @GetMapping("/category/{categoryId}")
    public List<Artwork> getByCategory(
            @PathVariable Long categoryId) {

        return artworkService
                .getArtworksByCategory(categoryId);
    }
}