package com.yukthiartful.yukthi_artful_backend.service;

import java.util.List;

import com.yukthiartful.yukthi_artful_backend.entity.Artwork;

public interface ArtworkService {

    Artwork saveArtwork(Artwork artwork);

    List<Artwork> getAllArtworks();
}