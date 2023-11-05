package com.loyaltywork.johnsoncustomertask

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.fragment.app.FragmentManager
import androidx.navigation.Navigation
import com.loyaltywork.johnsoncustomertask.databinding.FragmentSelectAccountTypeBinding


class SelectAccountTypeFragment : Fragment(),View.OnClickListener {

    private lateinit var binding: FragmentSelectAccountTypeBinding


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSelectAccountTypeBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cardviewPlumner.setOnClickListener(this)
        binding.cardviewCSM.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when(v!!.id){

            R.id.cardviewPlumner ->{

                Navigation.findNavController(v).navigate(R.id.loginRegisterMainFragment)
            }

            R.id.cardviewCSM ->{
                Navigation.findNavController(v).navigate(R.id.loginRegisterMainFragment)
            }
        }
    }

}