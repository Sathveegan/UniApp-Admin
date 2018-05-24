package com.example.janani.uniapp.core;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.janani.uniapp.data.Donation;
import com.example.janani.uniapp.data.DonationAdapter;
import com.example.janani.uniapp.data.JoinList;
import com.example.janani.uniapp.data.JoinListAdapter;
import com.example.janani.uniapp.data.ListAdapter;
import com.example.janani.uniapp.data.Post;
import com.example.janani.uniapp.data.PostAdapter;
import com.example.janani.uniapp.data.RepeatRegAdapter;
import com.example.janani.uniapp.data.RepeatRegistration;
import com.example.janani.uniapp.data.SemesterRegAdapter;
import com.example.janani.uniapp.data.SemesterRegistration;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;

/**
 * Created by Sathveegan on 29-Apr-18.
 */

public class FirebaseClient {

    private Context context;
    private String DB_URL;
    private Firebase firebase ;
    private ListView listView;
    private ArrayList<Post> postsList = new ArrayList<>();
    final ArrayList<String> keyList = new ArrayList<>();

    private ArrayList<Donation> donationList = new ArrayList<>();
    final ArrayList<String> donationKeyList = new ArrayList<>();

    private ArrayList<SemesterRegistration> semesterRegList = new ArrayList<>();
    final ArrayList<String> semesterRegKeyList = new ArrayList<>();

    private ArrayList<RepeatRegistration> repeatRegList = new ArrayList<>();
    final ArrayList<String> repeatRegKeyList = new ArrayList<>();

    final ArrayList<Post> searchList = new ArrayList<>();
    final ArrayList<JoinList> searchJoinList = new ArrayList<>();
    private ArrayList<JoinList> joinPostsList = new ArrayList<>();
    private PostAdapter postAdapter;
    private ListAdapter listAdapter;
    private JoinListAdapter joinListAdapter;
    private DonationAdapter donationAdapter;
    private SemesterRegAdapter semesterRegAdapter;
    private RepeatRegAdapter repeatRegAdapter;

    public FirebaseClient(String DB_URL){
        firebase = new Firebase(DB_URL);
    }

    public FirebaseClient(Context context, String DB_URL, ListView listView){
        this.context = context;
        this.DB_URL = DB_URL;
        this.listView = listView;

        Firebase.setAndroidContext(context);
        firebase = new Firebase(DB_URL);
    }

    public void loadPostData(){
        firebase.child("posts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getPostUpdates(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.v("Firebase", firebaseError.toString());
            }
        });
    }

    public void getPostUpdates(DataSnapshot dataSnapshot){
        postsList.clear();
        keyList.clear();

        for(DataSnapshot ds : dataSnapshot.getChildren()){
            Post post = ds.getValue(Post.class);
            keyList.add(ds.getKey());
            postsList.add(post);
        }

        if(postsList.size() > 0){
            postAdapter = new PostAdapter(context, postsList);
            listView.setAdapter(postAdapter);
        }else{
            Toast.makeText(context, "No posts...", Toast.LENGTH_SHORT);
        }
    }

    public void createPost(Post post){
        firebase.child("posts").push().setValue(post);
    }

    public void deletePost(int position){
        postsList.remove(position);
        postAdapter.notifyDataSetChanged();
        firebase.child("posts").child(keyList.get(position)).removeValue();
        keyList.remove(position);
    }

    public void searchPosts(String query){
        searchList.clear();
        for (Post p: postsList) {
            if(p.getTitle().toLowerCase().contains(query.trim().toLowerCase())){
                searchList.add(p);
            }
        }
        postAdapter = new PostAdapter(context, searchList);
        listView.setAdapter(postAdapter);

    }

    public void loadListPostData(){
        firebase.child("posts").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getListPostUpdates(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.v("Firebase", firebaseError.toString());
            }
        });
    }

    public void getListPostUpdates(DataSnapshot dataSnapshot){
        postsList.clear();
        keyList.clear();

        for(DataSnapshot ds : dataSnapshot.getChildren()){
            Post post = ds.getValue(Post.class);
            keyList.add(ds.getKey());
            postsList.add(post);
        }

        if(postsList.size() > 0){
            listAdapter = new ListAdapter(context, postsList);
            listView.setAdapter(listAdapter);
        }else{
            Toast.makeText(context, "No posts...", Toast.LENGTH_SHORT);
        }
    }

    public void searchListPosts(String query){
        searchList.clear();
        for (Post p: postsList) {
            if(p.getTitle().toLowerCase().contains(query.trim().toLowerCase())){
                searchList.add(p);
            }
        }
        listAdapter = new ListAdapter(context, searchList);
        listView.setAdapter(listAdapter);

    }

    public void loadJoinPostData(final Post post){
        firebase.child("joinLists").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getJoinListUpdates(dataSnapshot, post);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.v("Firebase", firebaseError.toString());
            }
        });
    }

    public void getJoinListUpdates(DataSnapshot dataSnapshot, Post post){
        joinPostsList.clear();

        for(DataSnapshot ds : dataSnapshot.getChildren()){
            JoinList joinList = ds.getValue(JoinList.class);

            if(joinList.getTitle().equalsIgnoreCase(post.getTitle()) && joinList.getDate().equals(post.getDate())) {
                joinPostsList.add(joinList);
            }
        }

        if(joinPostsList.size() > 0){
            joinListAdapter = new JoinListAdapter(context, joinPostsList);
            listView.setAdapter(joinListAdapter);
        }else{
            Toast.makeText(context, "No posts...", Toast.LENGTH_SHORT);
        }
    }

    public void searchJoinPosts(String query){
        searchJoinList.clear();
        for (JoinList j: joinPostsList) {
            if(j.getTitle().toLowerCase().contains(query.trim().toLowerCase())){
                searchJoinList.add(j);
            }
        }
        joinListAdapter = new JoinListAdapter(context, searchJoinList);
        listView.setAdapter(joinListAdapter);

    }

    public void loadDonationData(){
        firebase.child("donation").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getDonationUpdates(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.v("Firebase", firebaseError.toString());
            }
        });
    }

    public void getDonationUpdates(DataSnapshot dataSnapshot){
        donationList.clear();
        donationKeyList.clear();

        for(DataSnapshot ds : dataSnapshot.getChildren()){
            Donation donation = ds.getValue(Donation.class);
            donationKeyList.add(ds.getKey());
            donationList.add(donation);
        }

        if(donationList.size() > 0){
            donationAdapter = new DonationAdapter(context, donationList);
            listView.setAdapter(donationAdapter);
        }else{
            Toast.makeText(context, "No donations...", Toast.LENGTH_SHORT);
        }
    }

    public void deleteDonation(int position){
        donationList.remove(position);
        donationAdapter.notifyDataSetChanged();
        firebase.child("donation").child(donationKeyList.get(position)).removeValue();
        donationKeyList.remove(position);
    }

    public void loadSemesterRegistrationData(){
        firebase.child("registration").child("semester").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getSemesterRegistrationUpdates(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.v("Firebase", firebaseError.toString());
            }
        });
    }

    public void getSemesterRegistrationUpdates(DataSnapshot dataSnapshot){
        semesterRegList.clear();
        semesterRegKeyList.clear();

        for(DataSnapshot ds : dataSnapshot.getChildren()){
            SemesterRegistration semesterRegistration = ds.getValue(SemesterRegistration.class);
            semesterRegKeyList.add(ds.getKey());
            semesterRegList.add(semesterRegistration);
        }

        if(semesterRegList.size() > 0){
            semesterRegAdapter = new SemesterRegAdapter(context, semesterRegList);
            listView.setAdapter(semesterRegAdapter);
        }else{
            Toast.makeText(context, "No semester registrations...", Toast.LENGTH_SHORT);
        }
    }

    public void deleteSemesterRegistration(int position){
        semesterRegList.remove(position);
        semesterRegAdapter.notifyDataSetChanged();
        firebase.child("registration").child("semester").child(semesterRegKeyList.get(position)).removeValue();
        semesterRegKeyList.remove(position);
    }

    public void loadRepeatRegistrationData(){
        firebase.child("registration").child("repeat").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                getRepeatRegistrationUpdates(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                Log.v("Firebase", firebaseError.toString());
            }
        });
    }

    public void getRepeatRegistrationUpdates(DataSnapshot dataSnapshot){
        repeatRegList.clear();
        repeatRegKeyList.clear();

        for(DataSnapshot ds : dataSnapshot.getChildren()){
            RepeatRegistration repeatRegistration = ds.getValue(RepeatRegistration.class);
            repeatRegKeyList.add(ds.getKey());
            repeatRegList.add(repeatRegistration);
        }

        if(repeatRegList.size() > 0){
            repeatRegAdapter = new RepeatRegAdapter(context, repeatRegList);
            listView.setAdapter(repeatRegAdapter);
        }else{
            Toast.makeText(context, "No repeat registrations...", Toast.LENGTH_SHORT);
        }
    }

    public void deleteRepeatRegistration(int position){
        repeatRegList.remove(position);
        repeatRegAdapter.notifyDataSetChanged();
        firebase.child("registration").child("repeat").child(repeatRegKeyList.get(position)).removeValue();
        repeatRegKeyList.remove(position);
    }

}
