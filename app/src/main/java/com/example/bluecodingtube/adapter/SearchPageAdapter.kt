import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bluecodingtube.databinding.SearchItemBinding

class SearchPageAdapter(searchContext: Context) :
    RecyclerView.Adapter<SearchPageAdapter.searchItemViewHolder>() {

    private var thumbnailUrls = ArrayList<String>()
    private var titles = ArrayList<String>()

    override fun getItemCount() = thumbnailUrls.size

    fun setThumbnailUrls(urls: List<String>) {
        thumbnailUrls.clear()
        thumbnailUrls.addAll(urls)
        notifyDataSetChanged()
    }

    fun setTitles(titles: List<String>) {
        this.titles.clear()
        this.titles.addAll(titles)
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: searchItemViewHolder, position: Int) {
        holder.title.text = titles[position]

        // 썸네일 이미지 로드 및 표시
        Glide.with(holder.searchImage)
            .load(thumbnailUrls[position])
            .into(holder.searchImage)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): searchItemViewHolder {
        val binding = SearchItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return searchItemViewHolder(binding)
    }

    inner class searchItemViewHolder(binding: SearchItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val searchImage: ImageView = binding.searchImage
        val title: TextView = binding.searchTitle
    }
}
