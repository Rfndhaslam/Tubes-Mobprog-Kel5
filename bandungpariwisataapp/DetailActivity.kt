package com.example.bandungpariwisataapp

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.bandungpariwisataapp.adapters.ImagePagerAdapter
import com.example.bandungpariwisataapp.models.Destination

@Suppress("DEPRECATION")
class DetailActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        // Ambil data dari Intent
        val destination = intent.getParcelableExtra<Destination>("destination")
        val destinationImages = intent.getStringArrayListExtra("images")

        // Inisialisasi View
        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        val nameTextView: TextView = findViewById(R.id.destinationName)
        val descriptionTextView: TextView = findViewById(R.id.destinationDescription)
        val destinationInfoTextView: TextView = findViewById(R.id.destinationInfoTextView)
        val directionButton: Button = findViewById(R.id.directionButton)
        val moreInfoButton: Button = findViewById(R.id.moreInfoButton)

        // Set data ke View
        nameTextView.text = destination?.name
        descriptionTextView.text = destination?.description

        // Mengambil informasi destinasi berdasarkan nama
        destinationInfoTextView.text = getDestinationInfo(destination?.name)

        // Setup ViewPager untuk menampilkan gambar-gambar
        viewPager.adapter = destinationImages?.let { ImagePagerAdapter(it) }

        // Set listener untuk membuka peta
        directionButton.setOnClickListener {
            openMap(destination)
        }

        // Set listener untuk membuka website resmi
        moreInfoButton.setOnClickListener {
            openWebsite(destination?.officialWebsite)
        }

        // Mengatur visibilitas button "Info Selengkapnya"
        moreInfoButton.visibility = if (destination?.officialWebsite.isNullOrEmpty()) {
            Button.GONE
        } else {
            Button.VISIBLE
        }
    }

    // Membuka aplikasi peta dengan koordinat destinasi
    private fun openMap(destination: Destination?) {
        destination?.let {
            val gmmIntentUri = Uri.parse("geo:${it.latitude},${it.longitude}?q=${Uri.encode(it.name)}")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri).apply {
                setPackage("com.google.android.apps.maps")
            }
            startActivity(mapIntent)
        }
    }

    // Membuka browser untuk menampilkan website resmi destinasi
    private fun openWebsite(url: String?) {
        url?.let {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
            startActivity(browserIntent)
        }
    }

    // Mengembalikan informasi tambahan tentang destinasi berdasarkan namanya
    private fun getDestinationInfo(name: String?): String {
        return when (name) {
            "Dusun Bambu" -> """
                Dusun Bambu Lembang merupakan salah satu destinasi wisata yang banyak dikunjungi wisatawan di Kota Bandung. 
                Dengan luas sekitar 15 hektar yang menjadi sarana rekreasi keluarga di tengah pemandangan alam. 
                Bahkan destinasi wisata yang satu ini telah memadukan keselarasan budaya tradisional Sunda dalam satu wilayah. 
                
                Harga tiket masuk Dusun Bambu Lembang adalah Rp 20.000 per orang pada Senin-Jumat (weekday), 
                Sedangkan, pada akhir pekan (weekend) dan libur nasional, harga tiket Dusun Bambu adalah Rp 30.000 per orang. 
                Harga tiket tersebut, sudah termasuk voucher senilai Rp 10.000 yang dapat ditukarkan dengan beragam produk food and beverage, 
                namun tidak termasuk Kopi Nusantara, Walini, dan produk ritel. 
                Sedangkan, harga tiket masuk Dusun Bambu pada peak season seperti libur sekolah, Lebaran, Natal, dan tahun baru 
                adalah Rp 40.000 per orang. Tiket itu, dapat ditukarkan dengan es lilin, wedang jahe, atau suvenir Bambu Hoki. 
                Harga tiket di atas, tentunya belum termasuk tarif sejumlah wahana berbayar di dalam Dusun Bambu. 
                
                Berikut rincian tarif wahana di Dusun Bambu, seperti dikutip dari website resminya. 
                1. Crazy Kart Drift, harga tiket Rp 35.000 per 10 menit 
                2. E-Scooter, harga tiket Rp 42.000 per 20 menit 
                3. Sampan Sangkuriang, harga tiket Rp 25.000 per 15 menit 
                4. E-Bike, harga tiket Rp 50.000 per 30 menit.
            """.trimIndent()
            "Tangkuban Perahu" -> """
                Tangkuban Perahu adalah salah satu gunung berapi aktif yang terkenal di Jawa Barat. 
                Lokasi ini populer di kalangan wisatawan karena kawahnya yang menakjubkan. 
                Dari puncak gunung, pengunjung dapat melihat pemandangan spektakuler serta menikmati udara pegunungan yang sejuk. 
                Beberapa kawah yang terkenal di Tangkuban Perahu antara lain Kawah Ratu, Kawah Domas, dan Kawah Upas.
            """.trimIndent()
            "Kawah Putih" -> """
                Kawah Putih adalah danau kawah vulkanik dengan air yang berwarna putih kehijauan karena kandungan belerangnya. 
                Tempat ini merupakan salah satu destinasi wisata populer di Ciwidey, Bandung. 
                Pengunjung dapat menikmati pemandangan yang eksotis serta suasana yang tenang dan sejuk di sekitar kawah.
            """.trimIndent()
            else -> "Informasi mengenai ${name} belum tersedia."
        }
    }
}
