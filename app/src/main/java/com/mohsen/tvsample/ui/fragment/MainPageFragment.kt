package com.mohsen.tvsample.ui.fragment

import android.os.Bundle
import android.util.Log
import android.util.SparseArray
import androidx.core.content.ContextCompat
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import com.haroldadmin.cnradapter.NetworkResponse
import com.mohsen.tvsample.R
import com.mohsen.tvsample.data.remote.api.ApiInterface
import com.mohsen.tvsample.ui.adapter.MoviePresenter
import com.mohsen.tvsample.ui.main.MovieRow
import com.videostreamshows.data.remote.model.Movie
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class MainPageFragment : BrowseSupportFragment() , KodeinAware{
    override val kodein: Kodein by closestKodein()
    private val apiInterface: ApiInterface by instance()

    private val NOW_PLAYING = 0
    private val TOP_RATED = 1
    private val POPULAR = 2
    private val UPCOMING = 3

    var mRows: SparseArray<MovieRow>? = null


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e("Log__" , "start !!")

        // The brand color will be used as the background for the Headers fragment
        brandColor = activity?.let { ContextCompat.getColor(it, R.color.primary_transparent) }!!
        headersState = HEADERS_ENABLED
        isHeadersTransitionOnBackEnabled = true

        // The TMDB logo on the right corner. It is necessary to show based on their API usage policy

        // The TMDB logo on the right corner. It is necessary to show based on their API usage policy
        badgeDrawable = ContextCompat.getDrawable(requireActivity(), R.drawable.powered_by)

        createDataRows()
        createRows()
        getData()
    }

    /**
     * Creates the data rows objects
     */
    private fun createDataRows() {
        mRows = SparseArray<MovieRow>()
        val moviePresenter = MoviePresenter()
        mRows!!.put(
                NOW_PLAYING, MovieRow()
                .setId(NOW_PLAYING)
                .setAdapter(ArrayObjectAdapter(moviePresenter))
                .setTitle("Now Playing")
                .setPage(1)
        )
        mRows!!.put(
            TOP_RATED, MovieRow()
                .setId(TOP_RATED)
                .setAdapter(ArrayObjectAdapter(moviePresenter))
                .setTitle("Top Rated")
                .setPage(1)
        )
        mRows!!.put(
                POPULAR, MovieRow()
                .setId(POPULAR)
                .setAdapter(ArrayObjectAdapter(moviePresenter))
                .setTitle("Popular")
                .setPage(1)
        )
        mRows!!.put(
                UPCOMING, MovieRow()
                .setId(UPCOMING)
                .setAdapter(ArrayObjectAdapter(moviePresenter))
                .setTitle("Upcoming")
                .setPage(1)
        )
    }

    /**
     * Creates the rows and sets up the adapter of the fragment
     */
    private fun createRows() {
        // Creates the RowsAdapter for the Fragment
        // The ListRowPresenter tells to render ListRow objects
        val rowsAdapter = ArrayObjectAdapter(ListRowPresenter())
        for (i in 0 until mRows!!.size()) {
            val row = mRows!![i]
            // Adds a new ListRow to the adapter. Each row will contain a collection of Movies
            // That will be rendered using the MoviePresenter
            val headerItem = HeaderItem(row.id.toLong(), row.title)
            val listRow = ListRow(headerItem, row.adapter)
            rowsAdapter.add(listRow)
        }
        // Sets this fragments Adapter.
        // The setAdapter method is defined in the BrowseFragment of the Leanback Library
        adapter = rowsAdapter
    }
    fun getData(){
        GlobalScope.launch(IO) {
            when(val callback = apiInterface.getNowPlaying()){
                is NetworkResponse.Success -> {
                    Log.e("Log__" , "${callback.body}")
                    bindMovieResponse(callback.body.movies ,TOP_RATED)
                    startEntranceTransition()
                }
                is NetworkResponse.ServerError -> {
                    Log.e("Log__" , "issue")

                }
                is NetworkResponse.NetworkError -> {
                    Log.e("Log__" , "unknown : ${callback.error}")
                }
            }
        }
    }
    private fun bindMovieResponse(response: List<Movie>, id: Int) {
        GlobalScope.launch(Main) {
            val row = mRows!![id]
            row.page = row.page + 1
            for (m in response) {
                if (m.posterPath != null) { // Avoid showing movie without posters
                    row.adapter.add(m)
                }
            }
        }
    }


}