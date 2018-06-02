package xyz.elcomercio.software.rxJava.Response;

import java.util.List;

import xyz.elcomercio.software.entity.ApiCategory;
import xyz.elcomercio.software.entity.Category;

/**
 * Created by mbruns on 20.05.17.
 * The response wrapper for GSON to be able to parse the JSON response into our models
 */

public class CategoryResponse {
    public List<ApiCategory> category;
}
