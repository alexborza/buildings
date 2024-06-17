package com.alexborza.controller;

import static com.alexborza.TestConstants.*;

public class BuildingSaveRequestGenerator {

    public static BuildingSaveRequest generateSaveRequest() {
        return new BuildingSaveRequest(
                NAME,
                STREET,
                NUMBER,
                POSTAL_CODE,
                CITY,
                COUNTRY,
                DESCRIPTION
        );
    }
}
