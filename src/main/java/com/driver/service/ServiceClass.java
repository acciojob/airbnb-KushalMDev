package com.driver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;
import com.driver.repository.RepositoryClass;

@Service
public class ServiceClass {
    @Autowired
    RepositoryClass repositoryClass;

    public String addHotel(Hotel hotel) {
        // TODO Auto-generated method stub
      return repositoryClass.addHotel(hotel);
    }

    public int addUser(User user) {
        // TODO Auto-generated method stub
        return repositoryClass.addUser(user);
    }

    public String getHotelWithMostFacilities() {
        // TODO Auto-generated method stub
        return repositoryClass.getHotelWithMostFacilities();
    }

    public int getBookings(Integer aadharCard) {
        // TODO Auto-generated method stub
        return repositoryClass.getBookings(aadharCard);
    }

    public int bookARoom(Booking booking) {
        // TODO Auto-generated method stub
        return repositoryClass.bookARoom(booking);
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {
        // TODO Auto-generated method stub
        return repositoryClass.updateFacilities(newFacilities,hotelName);
    }
    
}
