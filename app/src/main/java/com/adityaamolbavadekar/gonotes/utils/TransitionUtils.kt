package com.adityaamolbavadekar.gonotes.utils

import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.adityaamolbavadekar.gonotes.R
import com.google.android.material.transition.MaterialContainerTransform

/**
 * A class that provides methods for applying
 * material motion and transitions throughout the Go Notes app
 * 
 * **Remember** : add `android:transitionName="shared_element_container"`
 * attribute to every layout you use (to identify the start and end Views.)
 * Make sure to mark the root view of your Fragment as a transition group, either
 * with android:transitionGroup="true" for API level 21+
 * or ViewGroupCompat#setTransitionGroup for all API levels.
 */

//TODO
class TransitionUtils {
/*

    class ContainerTransform{

        */
/**
         * Call this method in [Fragment.onCreate] of your [Fragment] class
         *//*

        fun registerStart(fragment: Fragment){
            fragment.sharedElementEnterTransition = MaterialContainerTransform()
            fragment.exitTransition = Hold()
            fragment.exitTransition = MaterialElevationScale(*/
/* growing= *//*
 false)
            fragment.reenterTransition = MaterialElevationScale(*/
/* growing= *//*
 true)
        }
        fun registerDestination(fragment: Fragment){
            fragment.sharedElementEnterTransition = MaterialContainerTransform()
        }

        */
/**
         * Call this method for travelling to next fragment.
         *
         * **Note** : If you use [androidx.Navigation] use `navigationTransition` instead.
         *//*

        fun animate(childFragmentManager:ChildFragmentManager){
            childFragmentManager
                .beginTransaction()
                // Map the start View in FragmentA and the transitionName of the end View in FragmentB
                .addSharedElement(view, "shared_element_container")
                .replace(R.id.fragment_container, fragmentB, FragmentB.TAG)
                .addToBackStack(FragmentB.TAG)
                .commit()
        }


        */
/**
         * Call this method for travelling to next fragment.
         *
         * **Note** : If you do not use [androidx.Navigation] use `animate` instead.
         *//*

        fun navigationTransition(view: View, fragment:Fragment){
            // Map the start View in FragmentA and the transitionName of the end View in FragmentB
            val extras = FragmentNavi(view to "shared_element_container")
            findNavController(fragment).navigate(R.id.action, null, null, extras)
        }

    }
*/

}