package com.yukthiartful.yukthi_artful_backend.service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.yukthiartful.yukthi_artful_backend.dto.AddToCartRequest;
import com.yukthiartful.yukthi_artful_backend.entity.Artwork;
import com.yukthiartful.yukthi_artful_backend.entity.Cart;
import com.yukthiartful.yukthi_artful_backend.entity.CartItem;
import com.yukthiartful.yukthi_artful_backend.entity.User;
import com.yukthiartful.yukthi_artful_backend.exception.ResourceNotFoundException;
import com.yukthiartful.yukthi_artful_backend.repository.ArtworkRepository;
import com.yukthiartful.yukthi_artful_backend.repository.CartItemRepository;
import com.yukthiartful.yukthi_artful_backend.repository.CartRepository;
import com.yukthiartful.yukthi_artful_backend.repository.UserRepository;

@Service
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final ArtworkRepository artworkRepository;
    private final UserRepository userRepository;

    public CartServiceImpl(
            CartRepository cartRepository,
            CartItemRepository cartItemRepository,
            ArtworkRepository artworkRepository,
            UserRepository userRepository) {

        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.artworkRepository = artworkRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Cart addToCart(AddToCartRequest request) {

        Authentication authentication =
                SecurityContextHolder.getContext()
                        .getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart newCart = new Cart();
                    newCart.setUser(user);
                    return cartRepository.save(newCart);
                });

        Artwork artwork = artworkRepository
                .findById(request.getArtworkId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Artwork not found"));

        CartItem item = new CartItem();
        item.setCart(cart);
        item.setArtwork(artwork);
        item.setQuantity(request.getQuantity());

        cartItemRepository.save(item);

        return cartRepository.findById(cart.getId()).get();
    }

    @Override
    public Cart getCart() {

        Authentication authentication =
                SecurityContextHolder.getContext()
                        .getAuthentication();

        String email = authentication.getName();

        User user = userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"));

        return cartRepository.findByUser(user)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Cart not found"));
    }
}