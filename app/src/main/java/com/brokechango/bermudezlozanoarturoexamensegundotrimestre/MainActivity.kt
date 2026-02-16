package com.brokechango.bermudezlozanoarturoexamensegundotrimestre

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import com.brokechango.bermudezlozanoarturoexamensegundotrimestre.ui.screens.ProductScreen
import com.brokechango.bermudezlozanoarturoexamensegundotrimestre.ui.screens.ProductViewModel
import com.brokechango.bermudezlozanoarturoexamensegundotrimestre.ui.theme.BermudezLozanoArturoExamenSegundoTrimestreTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BermudezLozanoArturoExamenSegundoTrimestreTheme {
                ProductScreen(model = viewModel { ProductViewModel() })
            }
        }
    }
}