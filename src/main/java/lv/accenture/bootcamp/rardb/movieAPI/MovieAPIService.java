package lv.accenture.bootcamp.rardb.movieAPI;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import lv.accenture.bootcamp.rardb.model.Movie;

@Component
public class MovieAPIService {

	@Value("${api.omdb.base}")
	private String requestUrl;

	@Value("${api.omdb.key}")
	private String apiKey;

	public void getMovie(String requestedMovieTitle) {
		requestedMovieTitle = requestedMovieTitle.replaceAll(" ", "%20");
		try {
			System.out.println(requestUrl + "?t=" + requestedMovieTitle + "&apikey=" + apiKey);

			URL url = new URL(requestUrl + "?t=" + requestedMovieTitle + "&apikey=" + apiKey);
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			urlConnection.setRequestMethod("GET");
			urlConnection.setReadTimeout(3000);
			urlConnection.connect();

			InputStream inputStream = urlConnection.getInputStream();
			InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
			BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

			StringBuilder sb = new StringBuilder();

			String line = null;
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line + "\n");
			}

			String jsonResponse = sb.toString();
			System.out.println(jsonResponse);

			bufferedReader.close();

			ObjectMapper objectMapper = new ObjectMapper();
			
			Movie movie = new Movie();
			movie = objectMapper.readValue(jsonResponse, Movie.class);
			System.out.println("Movie : " + movie.toString());

		} catch (Exception e) {

			throw new RuntimeException(e);
		}

	}

}
