package com.ekinox.bookshop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PriceCalculatorService {

    @Autowired
    DVDService dvdService;

    public void price(String filename) {

        List<DVD> dvdList = dvdService.listDVDFromFile(filename);

        calculate(dvdList);
    }
    public void calculate(List<DVD> dvdList){
        int totalPrice = 0;
        int discountedPrice = 0;

        for(DVD dvd : dvdList) {
            if(dvd.isDiscount()) {
                discountedPrice += dvd.getPrice();
            } else {
                totalPrice += dvd.getPrice();
            }
        }

        totalPrice += discountedPrice * discountPrice(dvdList);

        System.out.println(totalPrice);
    }

    public double discountPrice(List<DVD> dvdList){
        Set<DVD> dvdSet = dvdList.stream()
                .filter(x -> x.getName().trim().toLowerCase().startsWith("back to the future"))
                .collect(Collectors.toSet());

        if(dvdSet.size() == 2) {
            return 0.9;
        }

        if(dvdSet.size() >= 3) {
            return 0.8;
        }

        return 1;
    }
}
