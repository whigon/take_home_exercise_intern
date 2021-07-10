package com.shutl.controller;

import com.shutl.model.Quote;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
public class QuoteController {
    HashMap<String, Double> markupTable = new HashMap<>() {
        {
            put("bicycle", 0.1);
            put("motorbike", 0.15);
            put("parcel_car", 0.2);
            put("small_van", 0.3);
            put("large_van", 0.4);
        }
    };

    @RequestMapping(value = "/quote", method = POST)
    public @ResponseBody
    Quote quote(@RequestBody Quote quote) {
        if (quote.getVehicle() == null) { // Basic service
            Long price = Math.abs((Long.valueOf(quote.getDeliveryPostcode(), 36) - Long.valueOf(quote.getPickupPostcode(), 36)) / 100000000);
            return new Quote(quote.getPickupPostcode(), quote.getDeliveryPostcode(), price);
        } else { // Price by vehicle
            Long price = Math.abs((Long.valueOf(quote.getDeliveryPostcode(), 36) - Long.valueOf(quote.getPickupPostcode(), 36)) / 100000000);

            if (markupTable.get(quote.getVehicle()) != null) {
                price = Math.round((1 + markupTable.get(quote.getVehicle())) * price);
            } else {
                // Return error info or base price
            }

            return new Quote(quote.getPickupPostcode(), quote.getDeliveryPostcode(), quote.getVehicle(), price);
        }
    }

    @RequestMapping(value = "/quote_info", method = POST)
    public String index(HttpServletRequest request) {
        Quote quote = new Quote(request.getParameter("delivery_postcode"), request.getParameter("pickup_postcode"), request.getParameter("vehicle"));
        Long price = Math.abs((Long.valueOf(quote.getDeliveryPostcode(), 36) - Long.valueOf(quote.getPickupPostcode(), 36)) / 100000000);

        if (markupTable.get(quote.getVehicle()) != null)
            price = Math.round((1 + markupTable.get(quote.getVehicle())) * price);

        return price.toString();
    }
}
