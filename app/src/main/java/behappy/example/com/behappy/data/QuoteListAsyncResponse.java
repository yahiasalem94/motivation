package behappy.example.com.behappy.data;

import java.util.ArrayList;

import behappy.example.com.behappy.models.Quote;

public interface QuoteListAsyncResponse {
    void processFinished(ArrayList<Quote> quote);

}
