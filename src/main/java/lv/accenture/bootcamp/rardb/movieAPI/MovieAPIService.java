package lv.accenture.bootcamp.rardb.movieAPI;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class MovieAPIService {

	@Value("${api.omdb.base}")
	private String requestUrl;
//	private String requestUrl = "http://www.omdbapi.com/?t=Fight%20Club&apikey=f5a0f60f";

	@Value("${api.omdb.key}")
	private String apiKey;

	public void getMovie(String requestedMovieTitle) {

		try {
			URL url = new URL(requestUrl + "?t=" + requestedMovieTitle + "&apikey=" + apiKey);
			// URL url= new URL(requestUrl);
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

		} catch (Exception e) {

			throw new RuntimeException(e);
		}

	}

	public static void main(String[] args) {

		MovieAPIService service = new MovieAPIService();
		service.getMovie("Fight Club");
	}

}
