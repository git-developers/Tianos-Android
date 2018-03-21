package xyz.tianos.software.rxJava.Response;

import java.util.List;

import xyz.tianos.software.entity.Geoname;

/**
 * Created by mbruns on 20.05.17.
 * The response wrapper for GSON to be able to parse the JSON response into our models
 */

public class CityResponse {

    public List<Geoname> geonames;
}