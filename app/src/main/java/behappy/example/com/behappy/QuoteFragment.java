package behappy.example.com.behappy;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.util.concurrent.ThreadLocalRandom;


/**
 * A simple {@link Fragment} subclass.
 */
public class QuoteFragment extends Fragment {


    public QuoteFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View quoteView = inflater.inflate(R.layout.fragment_quote, container, false);
        TextView quoteText = quoteView.findViewById(R.id.quoteText);
        TextView byAuthor = quoteView.findViewById(R.id.byAuthor);
        CardView cardView = quoteView.findViewById(R.id.cardView);
        Button share = quoteView.findViewById(R.id.share);

  /*      share.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent mailIntent = new Intent(Intent.ACTION_VIEW);
                Uri data = Uri.parse("mailto:?subject=" + "subject text"+ "&body=" + "body text " + "&to=" + "destination@mail.com");
                mailIntent.setData(data);
                startActivity(Intent.createChooser(mailIntent, "Send mail..."));
            }
        });*/
        String quote = getArguments().getString("quote");
        String author = getArguments().getString("author");

        int colors[] = new int[] {R.color.blue_500, R.color.pink_900, R.color.green_400, R.color.lime_200,
                R.color.orange_600, R.color.amber_400, R.color.grey_700};

        quoteText.setText(quote);
        byAuthor.setText("-" + author);

        cardView.setBackgroundResource(getRandomQuote(colors));

        return quoteView;
    }

    public static final  QuoteFragment newInstance(String quote, String author)
    {
        QuoteFragment fragment = new QuoteFragment();
        Bundle bundle = new Bundle();
        bundle.putString("quote", quote);
        bundle.putString("author", author);
        fragment.setArguments(bundle);

        return fragment;
    }

    int getRandomQuote(int[] colorArray)
    {
        int color;
        int quoteArrayLen = colorArray.length;

        int randomNum  = ThreadLocalRandom.current().nextInt(quoteArrayLen);

        color = colorArray[randomNum];
        return color;
    }

}
