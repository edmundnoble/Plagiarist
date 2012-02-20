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
	private int wordsPerParse;
	private static final String charset = "UTF-8";

	public String parseResults(String args) throws IOException {
		String[] argarray = args.split(" ");
		if (argarray.length > 500) {
			wordsPerParse = 40;
		} else {
			wordsPerParse = 20;
		}
		int results = calculateResults(argarray, wordsPerParse);
		if (results > (argarray.length / wordsPerParse)) {
			return "Almost definitely plagiarism. Consult the student.";
		} else if (results > argarray.length / (wordsPerParse * 2)) {
			return "Likely plagiarism. Consult the student.";
		} else if (results > argarray.length / (wordsPerParse * 5)) {
			return "Possible plagiarism. Continue investigation.";
		} else if (results > argarray.length / (wordsPerParse * 10)) {
			return "Plagiarism unlikely, but possible. Continue investigation.";
		} else {
			return "Most likely not plagiarism. Investigation unneeded.";
		}
	}

	private int calculateResults(String[] args, int wordsPerParse)
			throws IOException {
		int matches = 0;
		String[] strarray;

		strarray = new String[wordsPerParse];
		for (int i = 0; i < args.length - 20; i++) {
			System.arraycopy(args, i * 20, strarray, 0, wordsPerParse);
			StringBuffer str = new StringBuffer();
			for (int l = 0; l < strarray.length; l++) {
				str.append(strarray[l]);
				str.append(" ");
			}
			Result[] searchResults = (Result[]) search(str.toString()).toArray();
			if (searchResults.length >= 2) {
				matches++;
			}
			strarray = null;
			str = null;
		}

		return matches;
	}

	private static List<Result> search(String search) throws IOException {

		String google = "http://ajax.googleapis.com/ajax/services/search/web?v=1.0&q=";
		search = URLEncoder.encode("\"" + search + "\"", "UTF-8");

		URL url = new URL(google + URLEncoder.encode(search, charset));
		Reader reader = new InputStreamReader(url.openStream(), charset);
		GoogleResults results = new Gson().fromJson(reader, GoogleResults.class);

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
}
