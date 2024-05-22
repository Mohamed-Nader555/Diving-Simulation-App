package com.ghada.divingsimulation.Utils;

import com.ghada.divingsimulation.R;

public class ChooseImage {

    public static int getImageDrawable(String imageName) {
        switch (imageName) {
            case "01d":
                return R.drawable.sun;
            case "01n":
                return R.drawable.moon;
            case "02d":
                return R.drawable.cloud_day;
            case "02n":
                return R.drawable.cloud_night;
            case "03d":
            case "03n":
                return R.drawable.cloud;
            case "04d":
            case "04n":
                return R.drawable.clouds;
            case "09d":
            case "09n":
            case "10d":
            case "10n":
                return R.drawable.rain;
            case "11d":
            case "11n":
                return R.drawable.storm;
            case "13d":
            case "13n":
                return R.drawable.snowy;
            case "50d":
            case "50n":
                return R.drawable.tornado;
        }
        return R.drawable.cloud;
    }


}
