package com.example.bandungpariwisataapp

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bandungpariwisataapp.adapters.DestinationAdapterHorizontal
import com.example.bandungpariwisataapp.adapters.DestinationAdapterVertical
import com.example.bandungpariwisataapp.decorators.HorizontalSpaceItemDecoration
import com.example.bandungpariwisataapp.models.Destination
import androidx.appcompat.widget.SearchView

class MainActivity : AppCompatActivity() {
    private lateinit var recommendedRecyclerView: RecyclerView
    private lateinit var popularRecyclerView: RecyclerView
    private lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recommendedDestinations = getRecommendedDestinations()
        val popularDestinations = getPopularDestinations()

        searchView = findViewById(R.id.search_view)
        searchView.queryHint = "Cari Tempat Wisata"
        searchView.setIconifiedByDefault(false)
        searchView.isIconified = false

        searchView.clearFocus()

        recommendedRecyclerView = findViewById(R.id.recommendedRecyclerView)
        popularRecyclerView = findViewById(R.id.popularRecyclerView)

        recommendedRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recommendedRecyclerView.addItemDecoration(HorizontalSpaceItemDecoration(16))
        recommendedRecyclerView.adapter = DestinationAdapterHorizontal(recommendedDestinations) { destination ->
            showDetail(destination)
        }

        popularRecyclerView.layoutManager = LinearLayoutManager(this)
        popularRecyclerView.adapter = DestinationAdapterVertical(popularDestinations) { destination ->
            showDetail(destination)
        }
    }

    private fun showDetail(destination: Destination) {
        val images = when (destination.name) {
            "Tangkuban Perahu" -> arrayListOf(
                "https://example.com/tangkuban1.jpg",
                "https://example.com/tangkuban2.jpg",
                "https://example.com/tangkuban3.jpg"
            )
            "Dusun Bambu" -> arrayListOf(
                "https://asset.kompas.com/crops/Rv6AXTxKPpXzL4ERLPOqNhjJ-wE=/0x3:996x667/750x500/data/photo/2022/03/27/624078b55867e.jpg",
                "https://asset.kompas.com/crops/WGCrL4OmxZjvK8OuZdHCGz9ZWkA=/0x0:1000x667/750x500/data/photo/2023/07/27/64c29992be6bc.jpg",
                "https://asset.kompas.com/crops/4Qx7VvXza3ooROmJOw2bbFvPv54=/180x0:952x514/750x500/data/photo/2023/07/27/64c298360a48b.png"
            )
            "Kawah Putih" -> arrayListOf(
                "https://asset.kompas.com/crops/yejbAG5BVJAudCyNgIM_mZCw_xU=/0x0:1003x669/750x500/data/photo/2021/09/26/615070d94add1.jpg",
                "https://example.com/tangkuban2.jpg",
                "https://example.com/tangkuban3.jpg"
            )
            "Trans Studio Bandung" -> arrayListOf(
                "https://salsawisata.com/wp-content/uploads/2022/01/Trans-Studio-Bandung.jpg",
                "https://salsawisata.com/wp-content/uploads/2022/01/Permainan-Trans-Studio-Bandung.jpg",
                "https://salsawisata.com/wp-content/uploads/2022/01/Trans-Studio-Mall-Bandung.jpg"
            )
            else -> arrayListOf(
                "https://example.com/default1.jpg",
                "https://example.com/default2.jpg",
                "https://example.com/default3.jpg"
            )
        }

        val intent = Intent(this, DetailActivity::class.java).apply {
            putExtra("destination", destination)
            putStringArrayListExtra("images", images)
        }
        startActivity(intent)
    }

    private fun getRecommendedDestinations(): List<Destination> {
        return listOf(
            Destination("Tangkuban Perahu", "Gunung dengan kawah yang bisa dikunjungi", "https://blogger.googleusercontent.com/img/b/R29vZ2xl/AVvXsEiYOYLtvYldzsIL6OpvMJ8OWnMOvjLXh8gRfefFXFqHYsvLM3Z5RLb3Ywb2uVrT_PwepZkmyjQkNlLtTM4Q6WJwZZfEp0pGDt_4TsS64eg5C2TiOo4oPbSlx8g5XeUPyh3HaXZY5DolHw/s1600/image4.jpg", -6.759, 107.609, "https://twatangkubanparahu.com/"),
            Destination("Kawah Putih", "Danau kawah vulkanik dengan air berwarna putih kehijauan", "https://i.pinimg.com/564x/dd/61/82/dd6182a26ff14f6cf0151cd6ad3117fc.jpg", -7.166, 107.402, "https://kawahputihciwidey.com/"),
            Destination("Farmhouse Lembang", "Tempat wisata keluarga dengan tema pedesaan Eropa", "https://i.pinimg.com/736x/60/74/6e/60746ef58410c566eda5e52811962ad8.jpg", -6.818, 107.617, "https://example.com/farmhouse"),
            Destination("Dusun Bambu", "Tempat rekreasi alam dengan berbagai aktivitas keluarga", "https://cdn.idntimes.com/content-images/community/2022/07/20220727-151225-329ae2b286ff7799fbbec7d4597d307a-98260cd14596464f8a1ad78806bb8629.jpg", -6.79042, 107.57785, "http://www.dusunbambu.id/"),
            Destination("Curug Cimahi", "Air terjun indah dengan trek yang mudah diakses", "https://i.pinimg.com/564x/79/51/0d/79510d9147ec7b566cce306ac5580e4a.jpg", -6.788, 107.599, "https://example.com/curugcimahi")
        )
    }

    private fun getPopularDestinations(): List<Destination> {
        return listOf(
            Destination("Observatorium Bosscha", "Observatorium astronomi tertua di Indonesia", "https://i.pinimg.com/564x/89/47/ea/8947ea619e9e5569cd19c4fbba8ab059.jpg", -6.824, 107.617, "http://www.bosscha.itb.ac.id/"),
            Destination("Saung Angklung Udjo", "Pusat seni dan budaya angklung", "https://i.pinimg.com/736x/63/d1/fe/63d1fe80c816c2653bbc0444a7afd905.jpg", -6.900, 107.663, "https://example.com/saungudjo"),
            Destination("Trans Studio Bandung", "Taman hiburan indoor terbesar di Indonesia", "https://ik.imagekit.io/tvlk/blog/2020/01/Wisata-Keluarga-Bandung-5-Traveloka.jpeg", -6.926, 107.634, "https://www.transentertainment.com/transstudio/bandung"),
            Destination("Lembang Floating Market", "Pasar terapung dengan berbagai kuliner dan wahana", "https://i.pinimg.com/564x/5d/0b/73/5d0b736304e72318417f8985ec0b9fd5.jpg", -6.818, 107.617, "https://example.com/floatingmarket"),
            Destination("Taman Hutan Raya Juanda", "Taman hutan dengan jalan trekking dan pemandangan alam", "https://assets-a1.kompasiana.com/items/album/2021/12/22/ayobdg-tahura-djuanda-ditutup-kavin-faza2-61c21edc06310e58953b63b3.jpg", -6.878, 107.594, "https://example.com/tahuradjuanda"),
            Destination("Pantai Pangandaran", "Pantai dengan pasir putih dan ombak yang cocok untuk berselancar", "https://i.pinimg.com/564x/67/0c/37/670c373f639e84f4b0f8a53b4d6c0241.jpg", -7.694, 108.553, "https://example.com/pantaipangandaran"),
            Destination("Kebun Binatang Bandung", "Kebun binatang dengan berbagai koleksi satwa", "https://i.pinimg.com/564x/74/97/5b/74975b2a70bfa565ed10681e0df5759e.jpg", -6.905, 107.636, "https://example.com/kebunbinatang"),
            Destination("Cihampelas Walk", "Pusat perbelanjaan dan hiburan di Bandung", "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/16/ab/41/e0/photo0jpg.jpg?w=1200&h=1200&s=1", -6.900, 107.604, "https://example.com/ciwalk"),
            Destination("Braga Street", "Jalan bersejarah dengan banyak kafe dan galeri seni", "https://i.pinimg.com/564x/94/e7/22/94e72205ba5131da5b05b049bd896d79.jpg", -6.918, 107.609, "https://example.com/bragastreet")
        )
    }
}
