package com.example.authapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.authapp.remote.AuthApi
import com.example.authapp.remote.RetrofitClient
import com.example.authapp.repository.AuthRepository
import com.example.authapp.ui.screen.HomeScreen
import com.example.authapp.ui.screen.LoginScreen
import com.example.authapp.ui.screen.RegisterScreen
import com.example.authapp.viewmodel.AuthViewModel
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.authapp.ui.theme.AuthAppTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    @SuppressLint("ViewModelConstructorInComposable")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AuthAppTheme {
                val navController = rememberNavController()
                val authApi = RetrofitClient.instance.create(AuthApi::class.java)
                val authRepository = AuthRepository(authApi, this)
                val authViewModel = AuthViewModel(authRepository)

                Scaffold(
                    topBar = {
                        TopAppBar(
                            title = {
                                Text("AuthApp", fontSize = 20.sp)
                            },
                            colors = TopAppBarDefaults.mediumTopAppBarColors(
                                containerColor = Color(0xFF1976D2),
                                titleContentColor = Color.White
                            )
                        )
                    }
                ) { innerPadding ->
                    Box(
                        modifier = Modifier
                            .padding(innerPadding)
                            .fillMaxSize()
                            .background(Color(0xFFE3F2FD)) // Fundo azul claro
                    ) {
                        NavHost(
                            navController = navController,
                            startDestination = "login",
                            modifier = Modifier.fillMaxSize()
                        ) {
                            composable("login") {
                                LoginScreen(
                                    viewModel = authViewModel,
                                    onAuthenticated = {
                                        navController.navigate("home") {
                                            popUpTo("login") { inclusive = true }
                                        }
                                    },
                                    onNavigateToRegister = {
                                        navController.navigate("register")
                                    }
                                )
                            }

                            composable("register") {
                                RegisterScreen(
                                    viewModel = authViewModel,
                                    onRegisterSuccess = {
                                        navController.navigate("home") {
                                            popUpTo("register") { inclusive = true }
                                        }
                                    },
                                    onNavigateToLogin = {
                                        navController.navigate("login")
                                    }
                                )
                            }

                            composable("home") {
                                HomeScreen(
                                    authViewModel,
                                    onLogout = {
                                        navController.navigate("login")
                                    }
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}