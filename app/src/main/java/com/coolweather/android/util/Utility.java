package com.coolweather.android.util;

import android.text.TextUtils;
import android.util.Log;

import com.coolweather.android.db.City;
import com.coolweather.android.db.County;
import com.coolweather.android.db.Province;
import com.google.gson.JsonArray;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Utility
{
    public static boolean handleProvinceResponse(String response)
    {
        if(!TextUtils.isEmpty((response)))
        {
        try
        {
            JSONArray allProvinces=new JSONArray(response);
            for(int i=0;i<allProvinces.length();i++)
            {
                JSONObject provinceObject=allProvinces.getJSONObject(i);
                Province province=new Province();
                province.setProvinceName(provinceObject.getString("name"));
                province.setProvinceCode(provinceObject.getInt("id"));
                province.save();
            }
            return true;
        } catch (JSONException e) {
            e.printStackTrace();
        }
        }
        return false;
    }
    public static boolean handleCityResponse(String response,int provinceId)
    {
        if(!TextUtils.isEmpty(response))
        {
            try
            {
                JSONArray allCities=new JSONArray(response);
                for(int i=0;i<allCities.length();i++)
                {
                    JSONObject citiesJSONObject=allCities.getJSONObject(i);
                    City city=new City();
                    city.setCityName(citiesJSONObject.getString("name"));
                    city.setCityCode(citiesJSONObject.getInt("id"));
                    city.setProvinceid(provinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
    public static boolean handleCountyResponse(String response,int cityId)
    {
        if(!TextUtils.isEmpty(response))
        {
            try {
                JSONArray allCountise=new JSONArray(response);
                for(int i=0;i<allCountise.length();i++)
                {
                    JSONObject countyJSONObject=allCountise.getJSONObject(i);
                    County county=new County();
                    county.setCountyName(countyJSONObject.getString("name"));
                    county.setWeather(countyJSONObject.getString("weather_id"));
                    county.setCityId(cityId);
                    Log.d("zzz", county.getCountyName());
                    county.save();
                }
                return  true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
