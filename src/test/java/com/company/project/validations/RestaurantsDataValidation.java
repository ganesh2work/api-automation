package com.company.project.validations;


import io.restassured.response.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import utils.ApiUtils;

import java.util.List;
import java.util.Map;

/**
 * this class is used to validate the result related to restaurant details
 */

public class RestaurantsDataValidation {

    private static final Logger logger= LoggerFactory.getLogger("Restaurant data validation ");


    public boolean validateRestaurantSearchQueryData(Response response) {
        Assert.assertTrue(ApiUtils.isJSONValid(response.asString()),"The given String is not a valid JSON");
        List<Map> restaurants = response.jsonPath().get("restaurants.restaurant");
        boolean flag = false;

        for (Map restaurant : restaurants) {
            logger.info("\n"+restaurant.get("name") + "\n" + restaurant.get("cuisines"));
            flag = restaurant.get("name").toString().toLowerCase().contains("kerala") || restaurant.get("cuisines").toString().toLowerCase().contains("kerala");
        }
        return flag;
    }
}
