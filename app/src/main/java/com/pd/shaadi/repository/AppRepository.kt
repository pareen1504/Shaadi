package com.pd.shaadi.repository

import androidx.lifecycle.LiveData
import com.pd.shaadi.OpenForTesting
import com.pd.shaadi.model.database.DbData
import com.pd.shaadi.model.network.ShaadiApiService
import com.pd.shaadicom.model.database.AppDbDao
import com.pd.shaadicom.model.database.ResponseData
import io.reactivex.Observable
import javax.inject.Inject

@OpenForTesting
class AppRepository @Inject constructor(
    val shaadiApiService: ShaadiApiService,
    val appDbDao: AppDbDao
) {


    fun getDataFromApi(load: Int): Observable<ResponseData> {
        return shaadiApiService.getreposne(load)
            .doOnNext {
                for (item in it.results) {
                    val dbData = DbData(
                        id = item.login.salt,
                        firstname = item.name.first,
                        lastname = item.name.last,
                        location = item.location.city,
                        img_url = item.picture.medium,
                        selection = "Action_Pending"
                    )
                    appDbDao.insert(dbData)
                }
            }
    }

    fun getDataFromDb(): LiveData<List<DbData>> {
        return appDbDao.getAllDbData()
    }

    fun updateProfileaccept(id: String) {
        appDbDao.acceptProfile(id)
    }

    fun updateProfilereject(id: String) {
        appDbDao.rejectProfile(id)
    }

    fun delete(id: String) {
        appDbDao.delete(id)
    }
}