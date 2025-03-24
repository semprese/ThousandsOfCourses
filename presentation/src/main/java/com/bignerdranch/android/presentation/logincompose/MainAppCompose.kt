//package com.bignerdranch.android.presentation
//
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.collectAsState
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.saveable.rememberSaveable
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.platform.LocalContext
//import androidx.hilt.navigation.compose.hiltViewModel
//import androidx.navigation.compose.NavHost
//import androidx.navigation.compose.composable
//import androidx.navigation.compose.rememberNavController
//import com.bignerdranch.android.presentation.login.ComposeViewModel
//import com.bignerdranch.android.presentation.login.LoginScreen
//
//@Composable
//fun MainApp() {
//    var isEntry by rememberSaveable() { mutableStateOf(false) }
//    val context = LocalContext.current
//
//    if (isEntry){
//        AppNavigation()
//    }else{
////        AndroidView(
////            modifier = Modifier.fillMaxSize(),
////            factory = { context ->
////                FragmentContainerView(context).apply {
////                    id = R.id.content_frame
////                }
////            },
////            update = { view ->
////                val fragmentManager = (context as FragmentActivity).supportFragmentManager
////                fragmentManager.beginTransaction()
////                    .replace(R.id.content_frame, OnboardingFragment { isEntry = true })
////                    .commit()
////            }
////        )
//    }
//}
//
//@Composable
//fun AppNavigation(
//    viewModel: ComposeViewModel = hiltViewModel(),
//) {
//    val navController = rememberNavController()
//
//    NavHost(navController = navController, startDestination = "login") {
//        composable("login") {
//            LoginScreen(
//                loginState = viewModel.loginState.collectAsState().value,
//                onLogin = { userName: String, password: String ->
//                    viewModel.login(userName,password)
//                },
//                onLoginSuccess = { navController.navigate("home") }
//            )
//        }
//        composable("home") {
//            HomeScreen()
//        }
//    }
//}
//
//@Composable
//fun HomeScreen() {
//
//}
