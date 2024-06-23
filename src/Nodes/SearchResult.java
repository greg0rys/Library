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
    private boolean isMulti = false;
    private boolean isSingle = false;

    public SearchResult()
    {}

    public SearchResult(Book B)
    {
        singleResult = B;
        isSingle = true;
    }

    public SearchResult(List<Book> multi)
    {
        multiResult = multi;
        isMulti = true;
    }

    public int resultType()
    {
        return isSingle ? 1 : 2;
    }
}
