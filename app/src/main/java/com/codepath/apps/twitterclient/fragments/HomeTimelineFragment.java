package com.codepath.apps.twitterclient.fragments;

import android.widget.Toast;

import com.codepath.apps.twitterclient.TwitterApplication;
import com.codepath.apps.twitterclient.helpers.TwitterClient;
import com.codepath.apps.twitterclient.models.Tweet;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class HomeTimelineFragment extends TweetsListFragment {

    private TwitterClient client = TwitterApplication.getRestClient();

    public void populateTimeline(String maxId) {
        client.getHomeTimeline(maxId, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray response) {
                ArrayList<Tweet> tweets = Tweet.fromJson(response);
                addAll(tweets);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                System.out.println("Home timeline failed");
                System.out.println(errorResponse);
                Toast.makeText(getActivity().getApplicationContext(),
                        "Couldn't get Tweets :(", Toast.LENGTH_SHORT).show();
                swipeContainer.setRefreshing(false);
            }
        });
    }
}
