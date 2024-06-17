package com.alexborza.controller;

public record BuildingSaveRequest(String name,
                                  String street,
                                  Integer number,
                                  Integer postalCode,
                                  String city,
                                  String country,
                                  String description) {
}
