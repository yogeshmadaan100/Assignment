package com.example.fragments;

import java.util.ArrayList;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.assignment.MainActivity;
import com.example.assignment.R;

public class LibraryFragment extends Fragment implements OnItemClickListener{
	private ListView itemList;
	private ArrayList<String> items = new ArrayList<String>();
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView=inflater.inflate(R.layout.layout_library_fragment	, container,false);
		itemList=(ListView)rootView.findViewById(R.id.listView1);
		for(int i=0;i<100;i++)
			items.add("Item "+i);
		itemList.setAdapter(new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1,items));
		itemList.setOnItemClickListener(this);
		 getActivity().setTitle("My Library");
		 ((MainActivity) getActivity()).unlock();
		return rootView;
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		((MainActivity) getActivity()).selectItem(5, new BlankFragment());
		
		MainActivity.fragment_no=5;
		 getActivity().setTitle("Item"+arg2);
	}

}
