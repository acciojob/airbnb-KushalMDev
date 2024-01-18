package com.driver.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Repository;

import com.driver.model.Booking;
import com.driver.model.Facility;
import com.driver.model.Hotel;
import com.driver.model.User;

@Repository
public class RepositoryClass {
    HashMap<String,Hotel> hotelMap=new HashMap<>();
    HashMap<String,Booking> bookingMap=new HashMap<>();
    HashMap<Integer,User> userMap=new HashMap<>();

    public String addHotel(Hotel hotel) {
        // TODO Auto-generated method stub
        if(hotel.equals(null) || hotel.getHotelName().equals(null) || hotelMap.containsKey(hotel.getHotelName())){
            return "FAILURE";
        }
        hotelMap.put(hotel.getHotelName(),hotel);
        return "SUCCESS";
        
    }

    public int addUser(User user) {
        // TODO Auto-generated method stub
        userMap.put(user.getaadharCardNo(),user);
        return user.getaadharCardNo();
        
    }

    public String getHotelWithMostFacilities() {
        // TODO Auto-generated method stub
        String hotelMax="";
        int max=-1;
        for(String hotelName:hotelMap.keySet()){
            Hotel hotel=hotelMap.get(hotelName);
            List<Facility> facilites=hotel.getFacilities();
            HashSet<Facility>set=new HashSet<>(facilites);
            if(set.size()>0){
                int size=set.size();
                if(size>max){
                    max=size;
                    hotelMax=hotelName;
                }
                else if(size==max){
                    if(hotelMax.compareTo(hotelName)>0){
                        hotelMax=hotelName;
                    }
                }

            }
        }

        return hotelMax;
    }

    public int getBookings(Integer aadharCard) {
        // TODO Auto-generated method stub
        int cnt=0;
        for(String id:bookingMap.keySet()){
            Booking booking=bookingMap.get(id);
            if(booking.getBookingAadharCard()==aadharCard){
                cnt+=booking.getNoOfRooms();
            }
        }
        return cnt;
        
    }

    public int bookARoom(Booking booking) {
        // TODO Auto-generated method stub
        if(booking.equals(null))return 0;
        String bookingId;
        UUID uuid = UUID.randomUUID();  
        bookingId=uuid+"";
        bookingMap.put(bookingId,booking);
        String hotelName=booking.getHotelName();
        Hotel hotel=hotelMap.get(hotelName);
        if(hotel.equals(null))return 0;
        int roomsAvailable=hotel.getAvailableRooms();
        int roomsTobebooked=booking.getNoOfRooms();
        if(roomsTobebooked>roomsAvailable)return -1;
        hotel.setAvailableRooms(hotel.getAvailableRooms()-roomsTobebooked);
        int amountToBePaid=0;
        amountToBePaid=hotel.getPricePerNight()*roomsTobebooked;

        return amountToBePaid;
    }

    public Hotel updateFacilities(List<Facility> newFacilities, String hotelName) {
        // TODO Auto-generated method stub
        if(!hotelMap.containsKey(hotelName))return null;
        Hotel hotel=hotelMap.get(hotelName);
        List<Facility> facilities=hotel.getFacilities();
        HashSet<Facility> set=new HashSet<>(facilities);
        for(Facility facility: newFacilities){
            set.add(facility);
        }
        List<Facility> updFacilities=new ArrayList<>(set);
        hotel.setFacilities(updFacilities);
        return hotel;
    }
    
}
