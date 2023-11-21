package com.bk.hiltdemo.navigationtest

import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.navigation.compose.ComposeNavigator
import androidx.navigation.testing.TestNavHostController
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.bk.hiltdemo.hiltwithretrofit.ApiService
import com.bk.hiltdemo.hiltwithretrofit.AppModule
import com.bk.hiltdemo.hiltwithretrofit.Constants
import com.bk.hiltdemo.ui.navigation.AppNavigation
import com.bk.hiltdemo.ui.navigation.Routes
import com.bk.hiltdemo.ui.screens.MainScreen
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import dagger.hilt.android.testing.UninstallModules
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@UninstallModules(AppModule::class)
@HiltAndroidTest
@Config(application = HiltTestApplication::class)
@RunWith(AndroidJUnit4::class)
class NavigationTest {

    @get:Rule
    val composeTestRule = createComposeRule()

    @get:Rule
    val hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var someString: String

//    @Inject
    lateinit var navController: TestNavHostController

    @Before
    fun setupNavHost(){
        composeTestRule.setContent {
            navController = TestNavHostController(LocalContext.current)
            navController.navigatorProvider.addNavigator(ComposeNavigator())
            AppNavigation(navController)
        }
        hiltRule.inject()
    }

    @Test
    fun verify_isStartDestinationHomeScreen(){
        composeTestRule
            .onNodeWithText("Go to Main Screen")
            .assertIsDisplayed()
    }

    @Test
    fun verify_stringMatch(){
        Assert.assertEquals(someString, Constants.BASE_URL)
    }

    @Test
    fun performClick_navigatesToMainScreen(){
        composeTestRule
            .onNodeWithText("Go to Main Screen")
            .performClick()

        val route = navController.currentBackStackEntry?.destination?.route
        Assert.assertEquals(route, Routes.MAIN)

        composeTestRule
            .onNodeWithText("Main Screen")
            .assertIsDisplayed()
    }

    @Module
    @InstallIn(SingletonComponent::class)
    object TestAppModule {

        @Provides
        @Singleton
        fun provideTestBaseUrl(): String {
            return Constants.BASE_URL
        }

        @Provides
        @Singleton
        fun provideTestMoshi(): Moshi {
            return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        }

        @Provides
        @Singleton
        fun provideTestRetrofit(baseUrl: String, moshi: Moshi): Retrofit {
            return Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(MoshiConverterFactory.create(moshi))
                .build()
        }

        @Provides
        @Singleton
        fun provideTestApiService(retrofit: Retrofit): ApiService {
            return retrofit.create(ApiService::class.java)
        }
    }


}