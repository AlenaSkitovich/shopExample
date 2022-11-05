package com.example.spring152.services;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class CurrencyService {

    public CurrencyService() {
    }

    public double rate(String currency) throws IOException {
        double rate = 0;
        String str = "";
        JSONObject joNB = null;
        URL[] urlNB = new URL[2];
        BufferedReader brNB = null;
        switch (currency) {
            case "BYN":
                rate = 1;
                break;
            case "USD":
                urlNB[0] = new URL("https://www.nbrb.by/api/exrates/rates/usd?parammode=2");
                brNB = new BufferedReader(new InputStreamReader(urlNB[0].openStream()));
                str = brNB.readLine();
                joNB = new JSONObject(str);
                rate = Double.parseDouble(joNB.get("Cur_OfficialRate").toString());
                break;
            case "EUR":
                urlNB[1] = new URL("https://www.nbrb.by/api/exrates/rates/eur?parammode=2");
                brNB = new BufferedReader(new InputStreamReader(urlNB[1].openStream()));
                str = brNB.readLine();
                joNB = new JSONObject(str);
                rate = Double.parseDouble(joNB.get("Cur_OfficialRate").toString());
                break;
        }
        return rate;
    }
}
