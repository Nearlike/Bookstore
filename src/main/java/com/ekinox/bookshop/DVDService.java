package com.ekinox.bookshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
public class DVDService {

    @Autowired
    private ResourceLoader resourceLoader;

    public List<DVD> listDVDFromFile(String filename) {
        List<DVD> dvdList = new ArrayList<>();

        try {
            Resource resource = resourceLoader.getResource("classpath:" + filename);
            File file = resource.getFile();
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String nextLine = scanner.nextLine();
                if(nextLine.trim().toLowerCase().startsWith("back to the future")) {
                    dvdList.add(new DVD(nextLine, 15));
                } else {
                    dvdList.add(new DVD(nextLine, 20));
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(dvdList);
        return dvdList;
    }
}
