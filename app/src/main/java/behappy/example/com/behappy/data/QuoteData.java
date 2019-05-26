package behappy.example.com.behappy.data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import behappy.example.com.behappy.controller.AppController;
import behappy.example.com.behappy.models.Quote;

public class QuoteData {

    ArrayList<Quote> quotes = new ArrayList<>();

    public void getQuotes(final QuoteListAsyncResponse callback) {
        String url = "https://raw.githubusercontent.com/pdichone/UIUX-Android-Course/master/Quotes.json%20";

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for (int i = 0; i < response.length(); i++)
                {
                    try {
                        JSONObject quoteObject = response.getJSONObject(i);
                        Quote quote = new Quote();
                        quote.setQuote(quoteObject.getString("quote"));
                        quote.setAuthor(quoteObject.getString("name"));

                        quotes.add(quote);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                if (callback != null)
                {
                    callback.processFinished(quotes);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        AppController.getInstance().addToRequestQueue(request);
    }
}
