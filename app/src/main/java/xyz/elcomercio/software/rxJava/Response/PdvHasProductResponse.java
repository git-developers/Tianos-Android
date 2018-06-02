package xyz.elcomercio.software.rxJava.Response;

import java.util.List;

import xyz.elcomercio.software.entity.PdvHasProduct;

/**
 * Created by mbruns on 20.05.17.
 * The response wrapper for GSON to be able to parse the JSON response into our models
 */

public class PdvHasProductResponse {
    public List<PdvHasProduct> pdvHasProduct;
}
