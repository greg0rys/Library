package Nodes;

import LibraryObjects.Book;

import java.util.List;

/**
 * Used as a wrapper to return search results as sometimes we have a single result and sometimes an array of results.
 * Helps prevent the need to always return an array list.
 */
public class SearchResult
{
    private Book singleResult;
    private List<Book> multiResult;

    public SearchResult()
    {}

    public SearchResult(Book B)
    {
        singleResult = B;
    }

    public SearchResult(List<Book> multi)
    {
        multiResult = multi;
    }
}
