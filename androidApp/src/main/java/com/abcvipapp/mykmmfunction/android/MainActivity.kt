package com.abcvipapp.mykmmfunction.android

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.scaleOut
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.abcvipapp.mykmmfunction.android.ui.main.DetailScreenView
import com.abcvipapp.mykmmfunction.android.ui.main.MainScreen
import com.abcvipapp.mykmmfunction.android.ui.splash.SplashScreenView
import com.abcvipapp.mykmmfunction.viewmodel.CountryViewModel
import com.abcvipapp.mykmmfunction.viewmodel.DetailViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.TimeZone

class MainActivity : ComponentActivity() {

    val countryViewModel: CountryViewModel by viewModel()

    val detailViewModel: DetailViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hideSystemBars()
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
        setContent {
            MyApplicationTheme {
                val navController: NavHostController = rememberNavController()
                Surface {
                    NavHost(navController = navController, startDestination = "splash_ui") {
                        composable("splash_ui") { SplashScreenView(navController = navController) }
                        composable("main_ui") { MainScreen(navControllerMain = navController, detailViewModel)  }
                        composable("setting_ui") { SplashScreenView(navController = navController) }
                        composable("detail") {
                            val imageUrl by detailViewModel.selectedImageUrl.collectAsState()
                            imageUrl?.let {
                                DetailScreenView(url = it, popBack = { navController.popBackStack() })
                            }
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun AdaptiveContent() {

    }

    override fun onStart() {
        super.onStart()
        observer()
    }

    private fun hideSystemBars() {
        val windowInsetsController = WindowCompat.getInsetsController(window, window.decorView)
        windowInsetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        ViewCompat.setOnApplyWindowInsetsListener(window.decorView) { view, windowInsets ->
            windowInsetsController.hide(WindowInsetsCompat.Type.navigationBars())
            ViewCompat.onApplyWindowInsets(view, windowInsets)
        }
    }

    private fun observer() {
        lifecycleScope.launch {
            launch { countryViewModel.fetch() }
            launch { countryViewModel.fetchGit() }
            launch { countryViewModel.fetchVercel() }
        }

        lifecycleScope.launch {
            launch {
                countryViewModel.objects.collect {
                    if (it != null) {
                        println("CALL FINISH")
                    }
                }
            }

            launch {
                countryViewModel.error.collect {
                    if (it != null) {
                        println("")
                    }
                }
            }
        }
    }
}



@Composable
fun SmallScreenLayout() { /* ... */ }
@Composable
fun MediumScreenLayout() { /* ... */ }
@Composable
fun LargeScreenLayout() { /* ... */ }

private fun getTimeZone(): String {
    val timeZone = TimeZone.getDefault()
    return timeZone.id
}

@Composable
fun LoadingOverlay(loading: Boolean) {
    AnimatedVisibility(
        visible = loading,
        enter = fadeIn(animationSpec = tween(300)) + scaleIn(initialScale = 0.95f),
        exit = fadeOut(animationSpec = tween(300)) + scaleOut(targetScale = 0.95f)
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black.copy(alpha = 0.25f))
                .blur(8.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .wrapContentSize()
                    .clip(RoundedCornerShape(20.dp))
                    .background(
                        Brush.verticalGradient(
                            listOf(Color.White, Color(0xFFE0F2FF))
                        )
                    )
                    .padding(horizontal = 32.dp, vertical = 24.dp)
                    .shadow(16.dp, RoundedCornerShape(20.dp), ambientColor = Color.Gray),
                contentAlignment = Alignment.Center
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    CircularProgressIndicator(
                        color = Color(0xFF00B4FF),
                        strokeWidth = 5.dp,
                        modifier = Modifier
                            .size(50.dp)
                            .padding(4.dp)
                    )
                    Spacer(modifier = Modifier.height(12.dp))
                    Text(
                        "Loading...",
                        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.SemiBold),
                        color = Color(0xFF007ACC)
                    )
                }
            }
        }
    }
}
