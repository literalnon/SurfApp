package com.example.literalnon.surfapp.base.navigation

/**
 * Created by dmitry on 20.11.17.
 */
interface ChildNavigationView//<Navigator: BaseNavigator>
{
    //var childNavigator: Navigator?
    fun callback(item: IBaseItem, data: Any? = null)
}