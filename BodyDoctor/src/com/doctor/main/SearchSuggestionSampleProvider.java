package com.doctor.main;

import android.content.SearchRecentSuggestionsProvider;

public class SearchSuggestionSampleProvider extends SearchRecentSuggestionsProvider {

	final static String AUTHORITY="com.doctor.main.SearchSuggestionSampleProvider";
	final static int MODE=DATABASE_MODE_QUERIES;
	
	public SearchSuggestionSampleProvider(){
		super();
		setupSuggestions(AUTHORITY, MODE);
	}
}
