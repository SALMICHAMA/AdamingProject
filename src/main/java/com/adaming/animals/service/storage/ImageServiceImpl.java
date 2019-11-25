package com.adaming.animals.service.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.stream.Stream;

@Service
public class ImageServiceImpl implements StorageService {

//    @Autowired
    public ImageServiceImpl() {
        this.rootLocation = Paths.get(this.location);
    }

    private final Path rootLocation;
    private String location= "uploads/";

    public void storeAvatar(MultipartFile file) {
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try (InputStream inputStream = file.getInputStream()) {
            Files.copy(inputStream, this.rootLocation.resolve(filename),
                    StandardCopyOption.REPLACE_EXISTING);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }



    @Override
    public void store(MultipartFile file) {
    }

    @Override
    public Stream<Path> loadAll() {
        Stream<Path> strPath=null;
        try {
            strPath= Files.walk(this.rootLocation, 1)
                    .filter(path -> !path.equals(this.rootLocation))
                    .map(this.rootLocation::relativize);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return strPath;
    }

    @Override
    public Path load(String filename) {
        return rootLocation.resolve(filename);
    }

    @Override
    public Resource loadAsResource(String filename) {
        Resource resource=null;
        Path file=null;
        try {
            file = this.rootLocation.resolve(filename);
            resource = new UrlResource(file.toUri());
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return resource;
    }

    @Override
    public void deleteAll() {
        FileSystemUtils.deleteRecursively(rootLocation.toFile());
    }

    @Override
    public void init() {
        try {
            Files.createDirectories(rootLocation);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
