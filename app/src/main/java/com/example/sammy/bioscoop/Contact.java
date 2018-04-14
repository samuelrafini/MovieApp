package com.example.sammy.bioscoop;

/**
 * Created by Pranawa on 4-4-2018.
 */

public class Contact {

    private String contact;
    private String route;
    private String adres;
    private String openingstijden;

    public Contact(String contact, String route, String adres, String openingstijden) {
        this.contact = contact;
        this.route = route;
        this.adres = adres;
        this.openingstijden = openingstijden;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }

    public String getOpeningstijden() {
        return openingstijden;
    }

    public void setOpeningstijden(String openingstijden) {
        this.openingstijden = openingstijden;
    }
}
