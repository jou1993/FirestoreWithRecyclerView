package com.example.recyclerviewfirestore

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "MainActivity"
    }

    private val firebaseRepo:FirebaseRepo=FirebaseRepo()

    private var postList : List<PostModel> =  ArrayList()
    private val postListAdapter : PostListAdapter = PostListAdapter(postList)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //check user
        if(firebaseRepo.getUser() == null) {
            //create new user
            firebaseRepo.createUser().addOnCompleteListener {
                if (it.isSuccessful){
                    //load data
                    loadPostData()
                } else {
                    Log.d(TAG, "Error : ${it.exception!!.message} ")
                }
            }
        }else {
            //user logged in
            loadPostData()
        }

        //init recycler view
        firestore_list.layoutManager = LinearLayoutManager(this)
        firestore_list.adapter = postListAdapter


    }

    private fun loadPostData() {
        firebaseRepo.getPostList().addOnCompleteListener {
            if (it.isSuccessful){
                postList=it.result!!.toObjects((PostModel::class.java))
                postListAdapter.postListItems = postList
                postListAdapter.notifyDataSetChanged()
            } else {
                Log.d(TAG, "Error : ${it.exception!!.message}")
            }
        }
    }
}