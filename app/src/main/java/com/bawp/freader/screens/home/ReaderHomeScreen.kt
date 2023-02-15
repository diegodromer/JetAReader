package com.bawp.freader.screens.home

import android.util.Log
import android.widget.Space
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.focusModifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.bawp.freader.components.*
import com.bawp.freader.model.MBook
import com.bawp.freader.navigation.ReaderScreens
import com.google.firebase.auth.FirebaseAuth

@Preview
@Composable
fun Home(navController: NavController = NavController(LocalContext.current)) {
    Scaffold(
        topBar = {
            ReaderAppBar(
                title = "Search", navController = navController,
                icon = Icons.Default.ArrowBack,
            )
        },
        floatingActionButton = {
            FABContent {
                navController.navigate(ReaderScreens.SearchScreen.name)
            }
        }
    ) {
        Surface(
            modifier = Modifier.fillMaxWidth()
        ) {
            HomeContent(navController)
        }
    }
}

@Composable
fun HomeContent(navController: NavController) {

    val listOfBooks = listOf(
        MBook(
            id = "dfasd",
            title = "Hello Again",
            authors = "All of us",
            notes = null
        ),
        MBook(
            id = "dfasd",
            title = "Hello Again",
            authors = "All of us",
            notes = null
        ),
        MBook(
            id = "dfasd",
            title = "Hello Again",
            authors = "All of us",
            notes = null
        ),
        MBook(
            id = "dfasd",
            title = "Hello Again",
            authors = "All of us",
            notes = null
        ),
        MBook(
            id = "dfasd",
            title = "Hello Again",
            authors = "All of us",
            notes = null
        ),
        MBook(
            id = "dfasd",
            title = "Hello Again",
            authors = "All of us",
            notes = null
        )
    )

    val email = FirebaseAuth.getInstance().currentUser?.email
    val currentUserName =
        if (!email.isNullOrEmpty()) email?.split("@")?.get(0)
        else "N/A"

    Column(
        Modifier.padding(2.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier.align(alignment = Alignment.Start)
        ) {
            TitleSection(label = "Your reading \n " + " activity right now...")
            Spacer(modifier = Modifier.fillMaxWidth(0.7f))
            Column {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Profile",
                    modifier = Modifier
                        .clickable {
                            navController.navigate(ReaderScreens.ReaderStatsScreen.name)
                        }
                        .size(45.dp),
                    tint = MaterialTheme.colors.secondaryVariant
                )
                Text(
                    text = currentUserName!!,
                    modifier = Modifier.padding(2.dp),
                    style = MaterialTheme.typography.overline,
                    color = Color.Red,
                    fontSize = 15.sp,
                    maxLines = 1,
                    overflow = TextOverflow.Clip
                )
                Divider()
            }
        }

        ReaderRightNowArea(books = listOf(), navController = navController)

        TitleSection(label = "Reading List")

        BoolListArea(listOfBooks = listOfBooks, navController = navController)
    }
}

@Composable
fun BoolListArea(listOfBooks: List<MBook>, navController: NavController) {

    HorizontalScrollableComponent(listOfBooks) {
        Log.d("TAG", "BoolListArea: $it")
    }
}

@Composable
fun HorizontalScrollableComponent(listOfBooks: List<MBook>, onCardPressed: (String) -> Unit) {

    val scrollState = rememberScrollState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .heightIn(280.dp)
            .horizontalScroll(scrollState)
    ) {

        for (book in listOfBooks) {
            ListCard(book) {
                onCardPressed(it)
            }
        }
    }
}

@Composable
fun ReaderRightNowArea(books: List<MBook>, navController: NavController) {
    ListCard()
}
