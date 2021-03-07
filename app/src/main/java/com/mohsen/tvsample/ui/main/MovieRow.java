package com.mohsen.tvsample.ui.main;


import androidx.leanback.widget.ArrayObjectAdapter;

/**
 * Created by <a href="mailto:marcus@gabilheri.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 10/11/16.
 */

public class MovieRow {

    private int page;
    private int id;
    private ArrayObjectAdapter adapter;
    private String title;

    public MovieRow() {
    }

    public int getPage() {
        return page;
    }

    public MovieRow setPage(int page) {
        this.page = page;
        return this;
    }

    public int getId() {
        return id;
    }

    public MovieRow setId(int id) {
        this.id = id;
        return this;
    }

    public ArrayObjectAdapter getAdapter() {
        return adapter;
    }

    public MovieRow setAdapter(ArrayObjectAdapter adapter) {
        this.adapter = adapter;
        return this;
    }

    public String getTitle() {
        return title;
    }

    public MovieRow setTitle(String title) {
        this.title = title;
        return this;
    }
}
