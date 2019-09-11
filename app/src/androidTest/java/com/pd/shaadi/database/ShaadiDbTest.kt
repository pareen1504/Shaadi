package com.pd.wifilogging.model.database

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.pd.shaadi.Util.observeOnce
import com.pd.shaadi.model.database.DbData
import com.pd.shaadicom.model.database.AppDatabase
import com.pd.shaadicom.model.database.AppDbDao
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class ShaadiDbTest {
    private lateinit var databasedao: AppDbDao
    private lateinit var database: AppDatabase

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun SetUp() {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        database = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java)
            .allowMainThreadQueries()
            .build()

        databasedao = database.appDbdao()
    }

    @After
    @Throws(IOException::class)
    fun TearDown() {
        database.close()
    }

    @Test
    @Throws(Exception::class)
    fun insertAndGetWifilist() {
        val listData =
            DbData(
                "982123456", "Pareen", "Doshi",
                "Ghatkopar", "http://www.google.com", "null"
            )
        databasedao.insert(listData)
        val checkdata = databasedao.getAllDbData()
        checkdata.observeOnce { list ->
            assertEquals(list.get(0).firstname, listData.firstname)
            assertEquals(list.get(0).location, listData.location)
        }

    }


}