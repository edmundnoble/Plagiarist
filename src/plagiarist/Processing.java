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

	public String parseResults(String args) {
		this.args = args.split(" ");
		return "";
	}

	public boolean[] calculateResults() throws IOException {
		String str = "";
		if (args.length > 500) wordsPerParse = args.length / 40;

		for (int l = 0; l < args.length; l++) {

			for (int i = 0; i + 20 < args.length; i += 21) {

				for (int g = i; g < i + wordsPerParse; g++) {
					str += " " + args[i];
				}

				if (search(str).get(4).getUrl() != null
						|| search(str).get(4).getUrl() == "") {
					results[l] = true;
				}
			}
		}
		return results;
	}

	private static List<Result> search(String search) throws IOException {

		String google = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
		search = "\"" + search + "\"";
		String charset = "UTF-8";
		URL url = new URL(google + URLEncoder.encode(search, charset));
		Reader reader = new InputStreamReader(url.openStream(), charset);
		GoogleResults results = new Gson()
				.fromJson(reader, GoogleResults.class);

		// Show title and URL of 1st result.
		// System.out.println(results.getResponseData().getResults().get(0).getTitle());
		// System.out.println(results.getResponseData().getResults().get(0).getUrl());

		return results.getResponseData().getResults();

	}

	private String[] args;
	private int wordsPerParse;
	private boolean[] results;
}
