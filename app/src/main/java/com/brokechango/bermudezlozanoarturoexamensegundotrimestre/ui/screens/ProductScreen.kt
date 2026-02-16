package com.brokechango.bermudezlozanoarturoexamensegundotrimestre.ui.screens

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewModelScope
import com.brokechango.bermudezlozanoarturoexamensegundotrimestre.data.model.Product
import com.brokechango.bermudezlozanoarturoexamensegundotrimestre.data.repository.ProductRepository
import com.brokechango.bermudezlozanoarturoexamensegundotrimestre.data.repository.ProductRepositoryImpl
import com.brokechango.bermudezlozanoarturoexamensegundotrimestre.ui.composables.ProductInfo
import com.brokechango.bermudezlozanoarturoexamensegundotrimestre.ui.theme.Purple40
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProductScreen(
    model: ProductViewModel,
) {
    val uiState by model.uiState.collectAsStateWithLifecycle()


    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Productos") },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Purple40,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                )
            )
        }
    ) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            if (uiState.isLoading) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = Purple40
                )
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize()
                ) {
                    items(uiState.productList) { product ->
                        ProductInfo(product = product)
                    }
                }
            }
        }
    }
}


data class ProductUiState(
    val isLoading: Boolean = false,
    val productList: List<Product> = emptyList(),
    val isError: Boolean = false
)

class ProductViewModel(
    private val repository: ProductRepository = ProductRepositoryImpl()
) : ViewModel() {
    private val _uiState = MutableStateFlow(ProductUiState())
    val uiState: StateFlow<ProductUiState> = _uiState.asStateFlow()

    fun update(action: ProductUiState.() -> ProductUiState) {
        _uiState.value = action(_uiState.value)
    }

    init{
        getData()
    }

    fun getData(){
        viewModelScope.launch {
            update { copy(isLoading = true, isError = false) }
            try{
                val products = repository.getAllProducts()
                update { copy(productList = products) }
                Log.d("ProductScreen", "${uiState.value.productList}")
            }catch (e: Exception){
                update { copy(isError = true) }
                Log.d("ProductScreen", "No se pueden recuperar productos desde la api. $e")
            }
            update { copy(isLoading = false) }
        }
    }
}