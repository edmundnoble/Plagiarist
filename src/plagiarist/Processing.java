package plagiarist;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import plagiarist.GoogleResults.Result;

import com.google.gson.Gson;



public class Processing {

	public String parseResults(String args) throws IOException {
		String[] argarray = args.split(" ");
		calculateResults(argarray);
		if (calculateResults(argarray) > 4) {



		}
		return "";
	}

	public int calculateResults(String[] args) throws IOException {
		int matches = 0;
		String str = "";
		if (args.length > 500) wordsPerParse = args.length / 40;

		for (int l = 0; l < args.length; l++) {

			for (int i = 0; i + 20 < args.length; i += 21) {

				for (int g = i; g < i + wordsPerParse; g++) {
					str += " " + args[i];
				}
				List<Result> searchResults = search(str);
				if (searchResults.get(3).getUrl() != null
						|| searchResults.get(3).getUrl() == "") {
					matches++;
				}
			}
		}
		return matches;
	}

	private static List<Result> search(String search) throws IOException {

		String google = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
		search = URLEncoder.encode("\"" + search + "\"", "UTF-8");

		URL url = new URL(google + URLEncoder.encode(search, charset));
		Reader reader = new InputStreamReader(url.openStream(), charset);
		GoogleResults results = new Gson()
				.fromJson(reader, GoogleResults.class);

		// Show title and URL of 1st result.

		for (int i = 0; i > 10; i++) {
			System.out.println(results.getResponseData().getResults().get(i)
					.getTitle()
					+ "\n");
			System.out.println(results.getResponseData().getResults().get(i)
					.getUrl()
					+ "\n");
		}

		// return results.getResponseData().getResults();
		return results.getResponseData().getResults();
	}

	private final static String charset = "UTF-8";
	private String[] args;
	private int wordsPerParse;

}
