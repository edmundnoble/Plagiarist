package plagiarist;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.List;

import plagiarist.GoogleResults.Result;

import com.google.gson.Gson;


public class Main {
	public Class main = Main.class;

	
	public static void main(String[] args) throws Exception {
		for (int i; i < args.length; i += 20) {
			for (String str : args.toString()) {
				str 
			}
			if (false) {
				search();
			}
		}
	}

	public static List<Result> search(String search) throws IOException {

		String google = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
		search = "\"" + search + "\"";
		String charset = "UTF-8";

		URL url = new URL(google + URLEncoder.encode(search, charset));
		Reader reader = new InputStreamReader(url.openStream(), charset);
		GoogleResults results = new Gson()
				.fromJson(reader, GoogleResults.class);

		// Show title and URL of 1st result.
	//	System.out.println(results.getResponseData().getResults().get(0).getTitle());
		//System.out.println(results.getResponseData().getResults().get(0).getUrl());
		
		return results.getResponseData().getResults();

	}
	private int[] results;
}
