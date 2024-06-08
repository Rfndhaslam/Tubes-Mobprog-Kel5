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

        val destination = intent.getParcelableExtra<Destination>("destination")
        val destinationImages = intent.getStringArrayListExtra("images")

        val viewPager: ViewPager2 = findViewById(R.id.viewPager)
        val nameTextView: TextView = findViewById(R.id.destinationName)
        val descriptionTextView: TextView = findViewById(R.id.destinationDescription)
        val destinationInfoTextView: TextView = findViewById(R.id.destinationInfoTextView)
        val directionButton: Button = findViewById(R.id.directionButton)
        val moreInfoButton: Button = findViewById(R.id.moreInfoButton)

        nameTextView.text = destination?.name
        descriptionTextView.text = destination?.description

        destinationInfoTextView.text = getDestinationInfo(destination?.name)

        viewPager.adapter = destinationImages?.let { ImagePagerAdapter(it) }

        directionButton.setOnClickListener {
            openMap(destination)
        }

        moreInfoButton.setOnClickListener {
            openWebsite(destination?.officialWebsite)
        }

        moreInfoButton.visibility = if (destination?.officialWebsite.isNullOrEmpty()) {
            Button.GONE
        } else {
            Button.VISIBLE
        }
    }

    private fun openMap(destination: Destination?) {
        destination?.let {
            val gmmIntentUri = Uri.parse("geo:${it.latitude},${it.longitude}?q=${Uri.encode(it.name)}")
            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri).apply {
                setPackage("com.google.android.apps.maps")
            }
            startActivity(mapIntent)
        }
    }

    private fun openWebsite(url: String?) {
        url?.let {
            val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
            startActivity(browserIntent)
        }
    }

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
            "Trans Studio Bandung" -> """
                Salah satu taman hiburan dalam ruangan terbesar di Indonesia, Trans Studio Bandung memiliki banyak wahana dan atraksi menarik untuk segala usia. 
                Destinasi ini, yang terletak di pusat kota Bandung, menjanjikan pengalaman yang tak terlupakan yang menggabungkan petualangan, hiburan, dan kesenangan ramah keluarga.
                
                Setiap zona bertema memiliki atraksi dan pertunjukan unik, sehingga selalu ada sesuatu untuk semua orang. 
                Trans Studio Bandung menyediakan berbagai pilihan untuk penggemar sensasi, keluarga, dan orang-orang yang ingin menikmati hari yang penuh kegembiraan. 
                Ada roller coaster yang memacu adrenalin, teater interaktif, dan komidi putar yang menawan.
                
                Taman hiburan Trans Studio Bandung tidak hanya memiliki wahana, tetapi juga memiliki banyak tempat makan dan toko ritel, menjadikannya tempat yang ideal untuk hiburan sepanjang hari. 
                Baik orang lokal maupun wisatawan, Trans Studio Bandung adalah tempat yang harus dikunjungi untuk mengabadikan keajaiban keseruan taman hiburan di pusat kota Indonesia.
                
                Harga Tiket Masuk Trans Studio Bandung:
                1. Senin-Jumat (weekdays): Rp 200.000.
                2. Sabtu-Minggu (weekends) dan Masa Liburan: Rp 300.000.
                Tiket Trans Studio Bandung bersifat universal mulai dari anak-anak, orang dewasa, maupun lanjut usia. 
                Namun, anak di bawah usia satu tahun dapat memasuki tempat ini secara gratis dengan menunjukkan bukti berupa akta lahir atau surat keterangan lainnya.
            """.trimIndent()
            else -> "Informasi mengenai ${name} belum tersedia."
        }
    }
}
