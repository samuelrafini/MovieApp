package com.example.sammy.bioscoop;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by Sammy on 3/31/18.
 */

public class FilmTask extends AsyncTask<String, Void, String>{

    private FilmAvailable listener = null;
    private static final String TAG = "FilmTask";

    public FilmTask(FilmAvailable listener){

        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... params) {
        Log.d(TAG, "doInBackground: is called");

        InputStream inputStream = null;
        int responsCode = -1;

        String itemUrl = params[0];
        String response = "";

        try {
            URL url = new URL(itemUrl);
            URLConnection urlConnection = url.openConnection();

            if(!(urlConnection instanceof HttpURLConnection)){
                return null;
            }

            HttpURLConnection httpConnection = (HttpURLConnection) urlConnection;
            httpConnection.setAllowUserInteraction(false);
            httpConnection.setInstanceFollowRedirects(true);
            httpConnection.setRequestMethod("GET");

            httpConnection.connect();

            responsCode = httpConnection.getResponseCode();
            if (responsCode == HttpURLConnection.HTTP_OK) {
                inputStream = httpConnection.getInputStream();
                response = getStringFromInputStream(inputStream);
            } else {
                Log.e(TAG, "Error, invalid response");
            }

        } catch (MalformedURLException e) {
            Log.e(TAG, "doInBackground MalformedURLEx " + e.getLocalizedMessage());
            return null;
        } catch (IOException e) {
            Log.e("TAG", "doInBackground IOException " + e.getLocalizedMessage());
            return null;
        }

        // Hier eindigt deze methode.
        // Het resultaat gaat naar de onPostExecute methode.
        return response;
    }

    protected void onPostExecute(String response) {
        Log.i(TAG, "onPostExecute " + response);

        // Check of er een response is
        if(response == null || response == "") {
            Log.e(TAG, "onPostExecute kreeg een lege response!");
            return;
        }

        // Het resultaat is in ons geval een stuk tekst in JSON formaat.
        // Daar moeten we de info die we willen tonen uit filteren (parsen).
        // Dat kan met een JSONObject.
        JSONObject jsonObject;
        try {
            // Top level json object
            jsonObject = new JSONObject(response);


                // get title img overview and runtime from api
                String title = jsonObject.getString("title");
                String posterPath = jsonObject.getString("poster_path");
                String overview = jsonObject.getString("overview");
                int runtime = jsonObject.getInt("runtime");
                String backDrop = jsonObject.getString("backdrop_path");
                String certification = "";
                ArrayList<String> genres = new ArrayList<>();
                ArrayList<String> actors = new ArrayList<>();
                String director = "";


                //get object inside countries which have the dutch certification
                JSONObject releases = jsonObject.getJSONObject("releases");
                JSONArray countries = releases.getJSONArray("countries");
                for(int i =0; i < countries.length(); i++) {
                    JSONObject objInCountries = (JSONObject) countries.get(i);

                    String thisCountry = objInCountries.getString("iso_3166_1");
//                    Log.d(TAG, "onPostExecute: show name job------------------" + thisCountry);

                    if(thisCountry.equals("US")) {
                       String certificationCode = objInCountries.getString("certification");
                       String release_date = objInCountries.getString("release_date");
                        Log.d(TAG, "onPostExecute: show code certification ------------------------" + certificationCode);
                        Log.d(TAG, "onPostExecute: show date release_date ------------------------" + release_date);

                        certification = certificationCode;

                    }
                }

                // get genres from genres array
                JSONArray genresArray = jsonObject.getJSONArray("genres");
                for(int i =0; i < genresArray.length(); i++) {
                    JSONObject objInGenres = (JSONObject) genresArray.get(i);
                    genres.add(objInGenres.getString("name"));
                }

                JSONObject credits = jsonObject.getJSONObject("credits");

                JSONArray cast = credits.getJSONArray("cast");
                for(int i =0; i < 6; i++) {
                    JSONObject objInCast = (JSONObject) cast.get(i);
                    actors.add(objInCast.getString("name"));
                }

                JSONArray crew = credits.getJSONArray("crew");
                for(int i =0; i < crew.length(); i++) {
                    JSONObject objInCrew = (JSONObject) crew.get(i);
                    String job = objInCrew.getString("job");
//                    Log.d(TAG, "onPostExecute: show name job------------------------" + job);

                    if(job.equals("Director")) {

                        String namedirector = objInCrew.getString("name");
//                        Log.d(TAG, "onPostExecute: show name job---------" + namedirector);

                        director = namedirector;

//                        Log.d(TAG, "onPostExecute: show name job---------" + director);

                    }

                }



            Film filmObj = new Film();
                filmObj.setTitel(title);
                filmObj.setPosterPath(posterPath);
                filmObj.setOverview(overview);
                filmObj.setRuntime(runtime);
                filmObj.setCertification(certification);
                filmObj.setGenres(genres);
                filmObj.setActor(actors);
                filmObj.setDirector(director);
                filmObj.setBackDropPath(backDrop);

                Log.d(TAG, "Got item " + title);
                Log.d(TAG, "onPostExecute: got film " + filmObj.toString());
//            Log.d(TAG, "onPostExecute: show name Director---------" + director);
//            Log.d(TAG, "onPostExecute: show code certification---------" + certification);

            // call back
                listener.filmAvailable(filmObj);


        } catch( JSONException ex) {
            Log.e(TAG, "onPostExecute JSONException " + ex.getLocalizedMessage());
        }
    }

    private static String getStringFromInputStream(InputStream is) {

        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();

        String line;
        try {

            br = new BufferedReader(new InputStreamReader(is));
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return sb.toString();
    }

}


