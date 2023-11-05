package com.loyaltywork.johnsoncustomertask

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.google.android.material.tabs.TabLayout
import com.loyaltywork.ViewPagerAdapter
import com.loyaltywork.johnsoncustomertask.databinding.FragmentLoginRegisterMainBinding


class LoginRegisterMainFragment : Fragment() , View.OnClickListener{

       private lateinit var binding : FragmentLoginRegisterMainBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding =  FragmentLoginRegisterMainBinding.inflate(layoutInflater)
        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val viewPagerAdapter =ViewPagerAdapter(parentFragmentManager)
        viewPagerAdapter.add(LoginFragment(),"Login")
        viewPagerAdapter.add(RegisterFragment(),"Register")
        binding.viewPager.adapter = viewPagerAdapter
        binding.tabLayout.setupWithViewPager(binding.viewPager)

    }

    override fun onClick(v: View?) {

    }
}