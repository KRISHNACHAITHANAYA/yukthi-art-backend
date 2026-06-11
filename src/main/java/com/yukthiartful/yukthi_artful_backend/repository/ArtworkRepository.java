package com.yukthiartful.yukthi_artful_backend.repository;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yukthiartful.yukthi_artful_backend.entity.Artwork;

public interface ArtworkRepository extends JpaRepository<Artwork, Long> {

    List<Artwork> findByTitleContainingIgnoreCase(String keyword);

    List<Artwork> findByCategoryId(Long categoryId);
}