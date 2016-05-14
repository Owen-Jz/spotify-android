package com.example.sergio.spotify_angular.utils;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.res.TypedArray;
import android.telephony.TelephonyManager;

import com.example.sergio.spotify_angular.models.MenuAppItem;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;


/**
 * Created by sergio on 07/05/2016.
 */
public class AppHelpers {

    private AppHelpers(){}

    public static void setFragment(Activity activity, Fragment frag, int target, boolean replace, boolean addToBackStack )
    {
        FragmentManager fm = activity.getFragmentManager();
        if (fm.findFragmentById(target) == null) {
            FragmentTransaction transaction = fm.beginTransaction();
            if(replace){
                transaction.replace(target, frag);
            }else{
                transaction.add(target, frag);
            }
            if(addToBackStack) transaction.addToBackStack(null);
            transaction.commit();
        }

    }

    public static List<MenuAppItem> getMenuFromResources(Context context, int id){

        TypedArray menuResources = context.getResources().obtainTypedArray(id);
        List<MenuAppItem> menu = new ArrayList<>();

        for (int i = 0; i < menuResources.length(); i++) {
            int resId = menuResources.getResourceId(i, -1);
            if (resId > 0) {
                TypedArray itemDef = context.getResources().obtainTypedArray(resId);
                String icon = itemDef.getString(0);
                String name = itemDef.getString(1);
                menu.add(new MenuAppItem(icon, name));
            }
        }

        return menu;
    }


}
