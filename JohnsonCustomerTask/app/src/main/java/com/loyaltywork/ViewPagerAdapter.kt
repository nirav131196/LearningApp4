package com.loyaltywork

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter constructor(fm:FragmentManager) : FragmentPagerAdapter(fm) {

    var fragments = mutableListOf<Fragment>()
    var fragmentTitle = mutableListOf<String>()

    fun add(fragment: Fragment,title:String){
        fragments.add(fragment)
        fragmentTitle.add(title)
    }

    override fun getCount(): Int {

        return fragments.size
    }

    override fun getItem(position: Int): Fragment {
         return fragments.get(position)
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return fragmentTitle.get(position)
    }
}