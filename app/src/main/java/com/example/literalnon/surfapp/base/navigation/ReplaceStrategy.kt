package com.example.literalnon.surfapp.base.navigation

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import java.util.*

/**
 * Created by dmitry on 20.11.17.
 */
class ReplaceStrategy(override val fragmentManager: FragmentManager, override val containerId: Int) : INavigationStrategy {
    private val tags: Stack<IBaseItem?> = Stack()

    override fun backStackSize(): Int {
        return tags.size
    }

    override fun updateUi(enumObject: IBaseItem?) {

    }

    override fun updateChildUi(enumObject: IBaseItem?, data: Any?) {

    }

    override fun popFragment() {
        tags.pop()

        getCurrentScreen()?.putAnimation(fragmentManager)
                ?.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                ?.replace(containerId, getCurrentScreen()?.getFragment(), getCurrentScreen()?.getTag())
                ?.commit()
    }

    override fun showFragment(enumObject: IBaseItem, data: Any?) {
        showFragmentWithParcelable(enumObject, enumObject.getFragment(), data)
    }

    override fun getCurrentScreen(): IBaseItem? {
        return if (tags.empty()) {
            null
        } else {
            tags.peek()
        }
    }

    override fun showFirstFragment(enumObject: IBaseItem, data: Any?) {
        tags.push(enumObject)

        enumObject.putAnimation(fragmentManager)
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .replace(containerId, enumObject.getFragment(), enumObject.getTag())
                .commit()
    }

    override fun showFragmentWithParcelable(enumObject: IBaseItem, fragment: Fragment, data: Any?) {

        if (tags.peek() != enumObject) {
            if (tags.contains(enumObject)) {
                while (!tags.empty() && tags.peek() != enumObject) {
                    tags.pop()
                }
                tags.pop()
            }

            tags.push(enumObject)

            enumObject.putAnimation(fragmentManager)
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                    .replace(containerId, fragment, enumObject.getTag())
                    .commit()
        }
    }

    override fun getCurrentFragment(): Fragment {
        return fragmentManager.findFragmentByTag(getCurrentScreen()?.getTag())
    }

    override fun clear() {
        tags.clear()
    }

    override fun backNavigation(): Boolean {

        when {
            getCurrentScreen()?.getPreviousEnumObject() == null -> {
                clear()
                return false
            }
            tags.empty() -> {
                return false
            }
            tags.size == 1 -> {
                popFragment()
                return false
            }
            else -> {

                val currentEnumObject: IBaseItem? = tags.peek()

                if (currentEnumObject?.getPreviousEnumObject() == null) {
                    clear()
                    return false
                }

                while (!tags.empty() && tags.peek()?.getTag() != currentEnumObject?.getPreviousEnumObject()?.getTag()) {
                    popFragment()
                }

                if (!tags.empty())
                    updateUi(tags.peek())

                return true
            }
        }
    }
}