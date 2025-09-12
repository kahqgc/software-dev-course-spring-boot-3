package com.example.springBoot2.controllers;

import com.example.springBoot2.Repositories.AlbumRepository;
import com.example.springBoot2.models.Album;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/albums")
public class AlbumController {
    private final AlbumRepository albumRepository;

    public AlbumController(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @GetMapping("")
    public List<Album> getAlbums(){
        return albumRepository.findAll();
    }

    @GetMapping("/{id}")
    public Album getAlbum(@PathVariable int id){
        return albumRepository.findById(id).orElse(null);
    }

    @PostMapping("")
    public Album addAlbum (@RequestBody Album album){
        return albumRepository.save(album);
    }

    @PutMapping("/{id}")
    public Optional<Album> updateAlbum(@PathVariable int id, @RequestBody Album album){
        return albumRepository.findById(id).map(existingAlbum -> {
            existingAlbum.setName(album.getName());
            existingAlbum.setArtist(album.getArtist());
            existingAlbum.setYear(album.getYear());
            existingAlbum.setTracks(album.getTracks());
            return albumRepository.save(existingAlbum);
        });
    }

    @DeleteMapping("/{id}")
    public void deleteAlbum(@PathVariable int id){
        albumRepository.deleteById(id);
    }
}
