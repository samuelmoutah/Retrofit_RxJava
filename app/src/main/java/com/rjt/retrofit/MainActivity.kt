package com.rjt.retrofit

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.rjt.retrofit.Adapter.AdapterCategory
import com.rjt.retrofit.model.CategoryModel
import com.rjt.retrofit.model.CategoryResult
import com.rjt.retrofit.network.ApiInterface
import com.rjt.retrofit.network.ApiService
import io.reactivex.Observable
import io.reactivex.ObservableConverter
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var adapter: AdapterCategory

    lateinit var disposable: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        init()

//        val call = ApiService.getClient().create(ApiInterface::class.java).getCategoriesAsync()
//        call.enqueue(object : Callback<CategoryResult>{
//
//            override fun onFailure(call: Call<CategoryResult>, t: Throwable) {
//                Log.e("Retrofit", t.message)
//                Toast.makeText(applicationContext, "fail", Toast.LENGTH_SHORT).show()
//            }
//
//            override fun onResponse( call: Call<CategoryResult>, response: Response<CategoryResult>) {
//
//                val category = response.body()!!.data
//                Log.d("Retrofit", "success" + response.body().toString())
//
//                //val cat : CategoryResult = response.body()!!
//
//                adapter?.setData(category)
//
//
//                Toast.makeText(applicationContext, "success", Toast.LENGTH_SHORT).show()
//            }
//
//        })


        val listOfCategorySingleObservable =
            ApiService
                // Get an instance of Retrofit
                .getClient()
                // Create an implementation of the API endpoints defined by the {@code service} interface.
                .create(ApiInterface::class.java)
                // Call getProducts inside API Interface
                .getProducts()

//        Observable.just(listOf(1,2,3,4))
//
//        val disposable = listOfCategorySingleObservable()
//            .flatMap{ secondNetworkCall(it) }

//       val listOfCategorySingleObservable = ApiService.instance.getProducts()

        listOfCategorySingleObservable
            .subscribeOn(Schedulers.io()) //this maintains pool of thread, this tell the observable to run the task on background thread
            .observeOn(AndroidSchedulers.mainThread()) //this tells the observer to receive the data on android ui thread


            .subscribe(object :
                SingleObserver<CategoryResult> { //when observer subscribe to observable

                override fun onSubscribe(d: Disposable) {
                    disposable = d
                    Log.d("sam", "OnSubscribe")
                }

                override fun onSuccess(t: CategoryResult) {
                    Log.d("sam", t.toString())
                    val product = t.data!!
                    adapter?.setData(product)
                }


                override fun onError(e: Throwable) {
                    Log.d("sam", "error")
                }
            })

//        val observable = ApiService.getClient().create(ApiInterface::class.java).getProducts()
//
//        observable
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe()object : Observer<CategoryResult> {
//
//            override fun onComplete() {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//
//            override fun onSubscribe(d: Disposable) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//
//            override fun onNext(t: CategoryResult) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//
//            override fun onError(e: Throwable) {
//                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
//            }
//
//        }
//
    }


    private fun init() {
        var list = ArrayList<CategoryModel>()
        recycler_view.layoutManager = LinearLayoutManager(this)
        adapter = AdapterCategory(this, list)
        recycler_view.adapter = adapter
    }

}

