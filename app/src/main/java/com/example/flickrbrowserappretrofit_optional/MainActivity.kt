package com.example.flickrbrowserappretrofit_optional

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.flickrbrowserappretrofit_optional.Constant
import com.example.flickrbrowserappretrofit_optional.Filcker
import com.example.flickrbrowserappretrofit_optional.Item
import com.example.flickrbrowserappretrofit_optional.R
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class MainActivity : AppCompatActivity() {
    lateinit var ll:LinearLayout
    lateinit var btn:Button
    lateinit var rv:RecyclerView
    lateinit var et:EditText
    lateinit var al:ArrayList<Item>
    var url = Constant.url
    var search = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ll = findViewById(R.id.ll)
        btn = findViewById(R.id.button)
        et = findViewById(R.id.editTextTextPersonName)
        al = arrayListOf()
        rv = findViewById(R.id.rv)
        btn.setOnClickListener {
            al.clear()
            rv.adapter?.notifyDataSetChanged()
            requestAPI()
}

        imageView2.setOnClickListener { hidhiddenimg() }

        rv.adapter = MyAdapter(this,al)
        rv.layoutManager = LinearLayoutManager(this)
    }


        private fun requestAPI() {
            val apiInterface = Constant.apiInterface

            if (apiInterface != null) {
                apiInterface.getFilker(et.text.toString())?.enqueue(object : Callback<Filcker> {
                    override fun onResponse(call: Call<Filcker>, response: Response<Filcker>) {
                        val photos = response.body()!!.photos
                        val photo = photos.photo
                        for (i in 0..50) {
                            var title = photo[i].title
                            var farmID = photo[i].farm
                            var serverID = photo[i].server
                            var ID = photo[i].id
                            var secret = photo[i].secret
                            val img = "https://farm$farmID.staticflickr.com/$serverID/${ID}_$secret.jpg"
                            al.add(Item(img,title))
                            Log.d("title",title)
                        }
                        rv.adapter?.notifyDataSetChanged()

                    }

                    override fun onFailure(call: Call<Filcker>, t: Throwable) {
                       Toast.makeText(this@MainActivity,"retro error",Toast.LENGTH_SHORT).show()
                    }

                })
        }
    }



    fun showimg(imm:String){
        imageView2.visibility = View.VISIBLE
        Glide.with(this).load(imm).into(imageView2)
        ll.visibility = View.GONE
        rv.visibility = View.GONE

    }
    fun hidhiddenimg(){
        imageView2.visibility = View.GONE
        ll.visibility = View.VISIBLE
        rv.visibility = View.VISIBLE

    }
}