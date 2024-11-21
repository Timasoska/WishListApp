package com.example.wishlistapp.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.wishlistapp.domain.model.WishItem
import com.example.wishlistapp.presentation.WishListCommon.WIshListViewIntent
import com.example.wishlistapp.presentation.WishListCommon.WishListViewModel
import com.example.wishlistapp.presentation.WishListCommon.WishListViewState

@Composable
fun MainScreen(
    viewModel: WishListViewModel
) {
    val viewState by viewModel.viewState.collectAsState()
    val orderByTitleState by viewModel.orderByTitleState.collectAsState()
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp)
    ) {
        Text(text = "Мои события", style = MaterialTheme.typography.headlineMedium)
        // Добавьте сюда свои события

        Spacer(modifier = Modifier.height(16.dp))

        Text(text = "Мои желания", style = MaterialTheme.typography.headlineLarge)
        when (viewState) {
            is WishListViewState.Loading -> {
                CircularProgressIndicator()
            }
            is WishListViewState.Success -> {
                WishList(
                    wishes = (viewState as WishListViewState.Success).data,
                    onDelete = { wish ->
                        viewModel.handleIntent(WIshListViewIntent.DeleteWishList(wish))
                    },
                    onEdit = { wish ->
                        viewModel.handleIntent(WIshListViewIntent.UpsertWishList(wish))
                    }
                )
            }
            is WishListViewState.Error -> {
                Text(text = (viewState as WishListViewState.Error).message)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { viewModel.changeOrder() }) {
            Text(text = if (orderByTitleState) "Сортировка по дате" else "Сортировка по названию")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { showDialog = true }) {
            Text(text = "Добавить желание")
        }
    }

    if (showDialog) {
        showAddWishDialog(viewModel, onDismiss = { showDialog = false })
    }
}



@Composable
fun WishList(
    wishes: List<WishItem>,
    onDelete: (WishItem) -> Unit,
    onEdit: (WishItem) -> Unit
) {
    LazyColumn {
        items(wishes) { wish ->
            WishItemRow(wish = wish, onDelete = onDelete, onEdit = onEdit)
        }
    }
}


@Composable
fun WishItemRow(
    wish: WishItem,
    onDelete: (WishItem) -> Unit,
    onEdit: (WishItem) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        elevation = CardDefaults.elevatedCardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = wish.title, style = MaterialTheme.typography.titleMedium)
                Text(text = wish.description, style = MaterialTheme.typography.bodyMedium)
            }
            Row {
                IconButton(onClick = { onEdit(wish) }) {
                    Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit Wish")
                }
                IconButton(onClick = { onDelete(wish) }) {
                    Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete Wish")
                }
            }
        }
    }
}
@Composable
fun showAddWishDialog(viewModel: WishListViewModel, onDismiss: () -> Unit) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    var imageUrl by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { onDismiss() },
        title = { Text(text = "Добавить новое желание") },
        text = {
            Column {
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    label = { Text("Название") }
                )
                OutlinedTextField(
                    value = description,
                    onValueChange = { description = it },
                    label = { Text("Описание") }
                )
                OutlinedTextField(
                    value = imageUrl,
                    onValueChange = { imageUrl = it },
                    label = { Text("URL изображения") }
                )
            }
        },
        confirmButton = {
            Button(onClick = {
                val newWish = WishItem(
                    title = title,
                    description = description,
                    imageUrl = imageUrl,
                    dateAdded = System.currentTimeMillis()
                )
                viewModel.handleIntent(WIshListViewIntent.UpsertWishList(newWish))
                onDismiss() // Закрываем диалог после добавления
            }) {
                Text("Добавить")
            }
        },
        dismissButton = {
            Button(onClick = { onDismiss() }) {
                Text("Отмена")
            }
        }
    )
}



