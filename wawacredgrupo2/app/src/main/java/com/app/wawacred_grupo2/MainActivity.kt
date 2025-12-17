package com.app.wawacred_grupo2

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.app.wawacred_grupo2.ui.ArticleDetailScreen
import com.app.wawacred_grupo2.ui.ConfirmationDialog
import com.app.wawacred_grupo2.ui.CredHistoryScreen
import com.app.wawacred_grupo2.ui.EventDetailScreen
import com.app.wawacred_grupo2.ui.IronHistoryScreen
import com.app.wawacred_grupo2.ui.LearningArticle
import com.app.wawacred_grupo2.ui.LoginScreen
import com.app.wawacred_grupo2.ui.MainAppScreen
import com.app.wawacred_grupo2.ui.NewsItem
import com.app.wawacred_grupo2.ui.OnboardingScreen
import com.app.wawacred_grupo2.ui.ProfileScreen
import com.app.wawacred_grupo2.ui.RegisterCredScreen
import com.app.wawacred_grupo2.ui.RegisterDoseScreen
import com.app.wawacred_grupo2.ui.RegisterVaccineScreen
import com.app.wawacred_grupo2.ui.VaccineHistoryScreen
import com.app.wawacred_grupo2.ui.theme.Wawacredgrupo2Theme
import com.app.wawacred_grupo2.ui.viewmodel.CredViewModel
import com.app.wawacred_grupo2.ui.viewmodel.CredViewModelFactory
import com.app.wawacred_grupo2.ui.viewmodel.IronDoseViewModel
import com.app.wawacred_grupo2.ui.viewmodel.IronDoseViewModelFactory
import com.app.wawacred_grupo2.ui.viewmodel.VaccineViewModel
import com.app.wawacred_grupo2.ui.viewmodel.VaccineViewModelFactory

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val app = application as WawaCredApp
        val credViewModel: CredViewModel by viewModels {
            CredViewModelFactory(app.database.credControlDao())
        }
        val vaccineViewModel: VaccineViewModel by viewModels {
            VaccineViewModelFactory(app.database.vaccineDao())
        }
        val ironDoseViewModel: IronDoseViewModel by viewModels {
            IronDoseViewModelFactory(app.database.ironDoseDao())
        }

        enableEdgeToEdge()
        setContent {
            Wawacredgrupo2Theme {
                var isLoggedIn by remember { mutableStateOf(false) }
                var isOnboardingCompleted by remember { mutableStateOf(false) }
                var selectedEvent by remember { mutableStateOf<NewsItem?>(null) }
                var showProfile by remember { mutableStateOf(false) }
                var showRegisterVaccine by remember { mutableStateOf(false) }
                var showRegisterDose by remember { mutableStateOf(false) }
                var showRegisterCred by remember { mutableStateOf(false) }
                var showConfirmationDialog by remember { mutableStateOf(false) }
                var activeRegistrationScreen by remember { mutableStateOf<(() -> Unit)?>(null) }
                var showCredHistory by remember { mutableStateOf(false) }
                var showVaccineHistory by remember { mutableStateOf(false) }
                var showIronHistory by remember { mutableStateOf(false) }
                var selectedArticle by remember { mutableStateOf<LearningArticle?>(null) }

                val credHistory by credViewModel.allCredControls.collectAsState()
                val vaccineHistory by vaccineViewModel.allVaccines.collectAsState()
                val ironDoseHistory by ironDoseViewModel.allIronDoses.collectAsState()

                val logout = {
                    isLoggedIn = false
                    isOnboardingCompleted = false
                    selectedEvent = null
                    showProfile = false
                    showRegisterVaccine = false
                    showRegisterDose = false
                    showRegisterCred = false
                    showConfirmationDialog = false
                    showCredHistory = false
                    showVaccineHistory = false
                    showIronHistory = false
                    selectedArticle = null
                }

                val openConfirmationDialog = { screen: () -> Unit ->
                    activeRegistrationScreen = screen
                    showConfirmationDialog = true 
                }
                
                val onConfirm = {
                    activeRegistrationScreen?.invoke()
                    showConfirmationDialog = false
                    activeRegistrationScreen = null
                }

                when {
                    selectedArticle != null -> {
                        ArticleDetailScreen(article = selectedArticle!!, onBack = { selectedArticle = null })
                    }
                    showIronHistory -> {
                        IronHistoryScreen(onBack = { showIronHistory = false }, ironDoseHistory = ironDoseHistory)
                    }
                    showVaccineHistory -> {
                        VaccineHistoryScreen(onBack = { showVaccineHistory = false }, vaccineHistory = vaccineHistory)
                    }
                    showCredHistory -> {
                        CredHistoryScreen(onBack = { showCredHistory = false }, credHistory = credHistory)
                    }
                    showRegisterCred -> {
                        RegisterCredScreen(
                            onBack = { showRegisterCred = false },
                            onSave = { credControl -> 
                                credViewModel.insertCredControl(credControl)
                                openConfirmationDialog { showRegisterCred = false } 
                            }
                        )
                    }
                    showRegisterDose -> {
                        RegisterDoseScreen(
                            onBack = { showRegisterDose = false },
                            onSave = { ironDose ->
                                ironDoseViewModel.insertIronDose(ironDose)
                                openConfirmationDialog { showRegisterDose = false } 
                            }
                        )
                    }
                    showRegisterVaccine -> {
                        RegisterVaccineScreen(
                            onBack = { showRegisterVaccine = false },
                            onSave = { vaccine -> 
                                vaccineViewModel.insertVaccine(vaccine)
                                openConfirmationDialog { showRegisterVaccine = false } 
                            }
                        )
                    }
                    showProfile -> {
                        ProfileScreen(
                            onBack = { showProfile = false },
                            onLogout = logout
                        )
                    }
                    selectedEvent != null -> {
                        EventDetailScreen(
                            event = selectedEvent!!,
                            onBack = { selectedEvent = null }
                        )
                    }
                    !isLoggedIn -> {
                        LoginScreen(onLogin = { isLoggedIn = true })
                    }
                    !isOnboardingCompleted -> {
                        OnboardingScreen(onFinish = { isOnboardingCompleted = true })
                    }
                    else -> {
                        MainAppScreen(
                            onEventClick = { event -> selectedEvent = event },
                            onProfileClick = { showProfile = true },
                            onRegisterVaccineClick = { showRegisterVaccine = true },
                            onRegisterDoseClick = { showRegisterDose = true },
                            onRegisterCredClick = { showRegisterCred = true },
                            onCredHistoryClick = { showCredHistory = true },
                            onVaccineHistoryClick = { showVaccineHistory = true },
                            onIronHistoryClick = { showIronHistory = true },
                            onArticleClick = { article -> selectedArticle = article }
                        )
                    }
                }

                if (showConfirmationDialog) {
                    ConfirmationDialog(
                        onDismissRequest = { showConfirmationDialog = false },
                        onConfirm = onConfirm,
                        onCancel = { showConfirmationDialog = false }
                    )
                }
            }
        }
    }
}
