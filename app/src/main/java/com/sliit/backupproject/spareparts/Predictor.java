package com.sliit.backupproject.spareparts;

import android.widget.TextView;

import android.content.Intent;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Predictor {
    private TextView avgPrice;

    TextView predictPrice(String model, String type, String part, String year) {
        String vehicleModel = model;
        String vehicleType = type;
        String vehiclePart = part;
        String theYear = year;

        // Predict price for the requested part
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://127.0.0.1:8000/api/MyAPI/?model="+ vehicleModel + "&type="
                        + vehicleType + "&part=" + vehiclePart + "&year=" + theYear)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JsonPlaceHolderApi jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);

        Call<List<Post>> call = jsonPlaceHolderApi.getPosts();

        call.enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                if (!response.isSuccessful()) {
                    avgPrice.setText("Rs. " + response.code());
                    return;
                }

                List<Post> posts = response.body();

                for (Post post : posts) {
                    String content = "";
                    content += post.getPrice();

                    avgPrice.append(content);
                }
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {
                avgPrice.setText(t.getMessage());
            }
        });

        return avgPrice;
    }

    String predictPrices(String model, String type, String part, String year) {
        String vehicleModel = model;
        String vehicleType = type;
        String vehiclePart = part;
        String theYear = year;

        // if else methods
        if (vehicleModel.equals("Toyota")) {
            if (vehicleType.equals("Corolla")) {
                if (vehiclePart.equals("Headlights")) {
                    return "35710.84";
                }
                else
                    return " - ";
            }
            if (vehicleType.equals("Corolla Hybrid")) {
                if (vehiclePart.equals("Headlights")) {
                    return "40372.12";
                }
                else
                    return " - ";
            }
            if (vehicleType.equals("Corolla Hatchback")) {
                if (vehiclePart.equals("Headlights")) {
                    return "38652.43";
                }
                else
                    return " - ";
            }
            if (vehicleType.equals("Prius")) {
                if (vehiclePart.equals("Headlights")) {
                    return "32184.10";
                }
                else
                    return " - ";
            }
            if (vehicleType.equals("Prius Prime")) {
                if (vehiclePart.equals("Headlights")) {
                    return "38615.50";
                }
                else
                    return " - ";
            }
            if (vehicleType.equals("Camry")) {
                if (vehiclePart.equals("Headlights")) {
                    return "36913.25";
                }
                else
                    return " - ";
            }
            if (vehicleType.equals("Camry Hybrid")) {
                if (vehiclePart.equals("Headlights")) {
                    return "37452.63";
                }
                else
                    return " - ";
            }
            if (vehicleType.equals("Avalon")) {
                if (vehiclePart.equals("Headlights")) {
                    return "39115.72";
                }
                else
                    return " - ";
            }
            if (vehicleType.equals("Avalon Hybrid")) {
                if (vehiclePart.equals("Headlights")) {
                    return "43618.62";
                }
                else
                    return " - ";
            }
            if (vehicleType.equals("Mirai")) {
                if (vehiclePart.equals("Headlights")) {
                    return "42556.62";
                }
                else
                    return " - ";
            }
            if (vehicleType.equals("86")) {
                if (vehiclePart.equals("Headlights")) {
                    return "42685.62";
                }
                else
                    return " - ";
            }
            if (vehicleType.equals("GR Supra")) {
                if (vehiclePart.equals("Headlights")) {
                    return "39675.15";
                }
                else
                    return " - ";
            }
            if (vehicleType.equals("RAV4 Hybrid")) {
                if (vehiclePart.equals("Headlights")) {
                    return "48635.12";
                }
                else
                    return " - ";
            }
            if (vehicleType.equals("RAV4 Prime")) {
                if (vehiclePart.equals("Headlights")) {
                    return "38412.30";
                }
                else
                    return " - ";
            }
            if (vehicleType.equals("Highlander Hybrid")) {
                if (vehiclePart.equals("Headlights")) {
                    return "42196.35";
                }
                else
                    return " - ";
            }
            if (vehicleType.equals("Venza")) {
                if (vehiclePart.equals("Headlights")) {
                    return "41318.00";
                }
                else
                    return " - ";
            }
            if (vehicleType.equals("Sienna")) {
                if (vehiclePart.equals("Headlights")) {
                    return "37625.32";
                }
                else
                    return " - ";
            }
            if (vehicleType.equals("RAV4")) {
                if (vehiclePart.equals("Headlights")) {
                    return "32873.12";
                }
                else
                    return " - ";
            }
            if (vehicleType.equals("Highlander")) {
                if (vehiclePart.equals("Headlights")) {
                    return "36195.45";
                }
                else
                    return " - ";
            }
            if (vehicleType.equals("R4 Runner")) {
                if (vehiclePart.equals("Headlights")) {
                    return "38750.23";
                }
                else
                    return " - ";
            }
            if (vehicleType.equals("Land Cruiser")) {
                if (vehiclePart.equals("Headlights")) {
                    return "43865.30";
                }
                else
                    return " - ";
            }
            if (vehicleType.equals("C-HR")) {
                if (vehiclePart.equals("Headlights")) {
                    return "45318.52";
                }
                else
                    return " - ";
            }
            else
                return " - ";
        }
        else
            return " - ";

    }

}
