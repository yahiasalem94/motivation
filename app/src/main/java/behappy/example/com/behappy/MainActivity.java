package behappy.example.com.behappy;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import behappy.example.com.behappy.data.QuoteData;
import behappy.example.com.behappy.data.QuoteListAsyncResponse;
import behappy.example.com.behappy.data.QuoteViewPagerAdapter;
import behappy.example.com.behappy.models.Quote;

public class MainActivity extends AppCompatActivity {

    private QuoteViewPagerAdapter quoteViewPagerAdapter;
    private ViewPager viewPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quoteViewPagerAdapter = new QuoteViewPagerAdapter(getSupportFragmentManager(), getFragments());
        viewPager = findViewById(R.id.viewPager);
        viewPager.setAdapter(quoteViewPagerAdapter);

    }

    private List<Fragment> getFragments()
    {
       final List<Fragment> fragmentList = new ArrayList<>();

        new QuoteData().getQuotes(new QuoteListAsyncResponse() {
            @Override
            public void processFinished(ArrayList<Quote> quote) {
                for (int i = 0; i < quote.size(); i++)
                {
                    QuoteFragment quoteFragment = QuoteFragment.newInstance(
                            quote.get(i).getQuote(),
                            quote.get(i).getAuthor());

                    fragmentList.add(quoteFragment);
                }
                quoteViewPagerAdapter.notifyDataSetChanged();
            }
        });

        return fragmentList;
    }
}
