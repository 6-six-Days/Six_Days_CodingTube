import android.app.appsearch.SearchResult
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.bluecodingtube.R
import com.example.bluecodingtube.adapter.SearchPageAdapter
import com.example.bluecodingtube.databinding.FragmentSearchPageBinding
import com.example.bluecodingtube.data.YoutubeVideo
import com.example.bluecodingtube.data.YoutubeVideoInfo
import com.example.bluecodingtube.service.RetrofitClient
import com.example.bluecodingtube.service.api.SixDays
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Query

class SearchPage : Fragment() {

    private lateinit var binding: FragmentSearchPageBinding
    private lateinit var searchContext: Context
    private lateinit var adapter: SearchPageAdapter

    private var searchItem: ArrayList<YoutubeVideo>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = SearchPageAdapter(requireContext()) // 어댑터 초기화
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.searchRecycleView.adapter = adapter
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        searchContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchPageBinding.inflate(inflater, container, false)
        setupView()
        setupListener()
        return binding.root
    }

    private fun setupView() {
        val spanCount = 2
        val grid = GridLayoutManager(searchContext, spanCount)
        binding.searchRecycleView.layoutManager = grid
        binding.searchRecycleView.itemAnimator = null
    }

    private fun setupListener() {
        val searchText = binding.searchtext
        val searchButton = binding.searchbutton

        searchButton.setOnClickListener {
            val query = searchText.text.toString()
            if (query.isNotEmpty()) {
                Log.d("확인", "check")
                CoroutineScope(Dispatchers.Main).launch {
                    //fetchYoutubeVideos(query)
                }
            }
        }
    }

    /*private fun fetchYoutubeVideos(query: String) {
        val service = RetrofitClient.searchService
        service.getYoutubeVideosSearch()
            .enqueue(object : retrofit2.Callback<YoutubeVideo> {
            override fun onResponse(
                call: Call<YoutubeVideo>,
                response: Response<YoutubeVideo>
            ) {
                response.body()?.pageInfo?.let { pageInfo ->
                    if (pageInfo.resultsPerPage.toInt() > 0) {
                        response.body()!!.items { items ->
                            val title = items.snippet.title
                            val url = items.snippet.thumbnails.medium.url

                        }
                    }

                }
                adaptor.datalist = datalist
                adaptor.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<YoutubeVideo>, t: Throwable) {

            }

        })
    }*/

}
