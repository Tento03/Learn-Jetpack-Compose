package com.example.viewpager
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.accompanist.pager.*

@OptIn(ExperimentalPagerApi::class)
@Composable
fun TabbedViewPager() {
    // Daftar judul tab
    val tabTitles = listOf("Tab 1", "Tab 2", "Tab 3", "Tab 4", "Tab 5")
    // State untuk mengelola halaman aktif
    val pagerState = rememberPagerState()
    // State untuk melacak halaman target yang ingin dituju
    var targetPage by remember { mutableStateOf(-1) }

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        // Spacer untuk memberikan jarak antara bagian atas layar dan baris tab
        Spacer(modifier = Modifier.height(20.dp)) // Jarak 20dp dari atas

        // Bagian Tab (dapat digeser)
        ScrollableTabRow(
            tabTitles = tabTitles,
            pagerState = pagerState,
            onTabSelected = { index ->
                targetPage = index // Set halaman target saat tab diklik
            }
        )

        // HorizontalPager untuk konten halaman
        HorizontalPager(
            count = tabTitles.size,
            state = pagerState,
            modifier = Modifier.weight(1f)
        ) { page ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(if (page % 2 == 0) Color.LightGray else Color.White),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Konten ${tabTitles[page]}",
                    fontSize = 24.sp,
                    color = Color.Black
                )
            }
        }
    }

    // Jalankan animasi scroll ke halaman target
    if (targetPage != -1) {
        LaunchedEffect(targetPage) {
            pagerState.animateScrollToPage(targetPage)
            targetPage = -1 // Reset target page setelah animasi selesai
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun ScrollableTabRow(
    tabTitles: List<String>,
    pagerState: PagerState,
    onTabSelected: (Int) -> Unit
) {
    val scrollState = rememberScrollState()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .horizontalScroll(scrollState)
            .background(Color.White)
            .padding(vertical = 8.dp), // Padding vertikal untuk memberikan ruang di atas dan bawah
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        tabTitles.forEachIndexed { index, title ->
            val isSelected = pagerState.currentPage == index

            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .clickable {
                        onTabSelected(index) // Panggil callback saat tab diklik
                    }
                    .padding(horizontal = 16.dp, vertical = 8.dp)
            ) {
                // Teks tab
                Text(
                    text = title,
                    fontSize = 16.sp,
                    fontWeight = if (isSelected) FontWeight.Bold else FontWeight.Normal,
                    color = if (isSelected) Color.Blue else Color.Black
                )

                // Indikator garis bawah untuk tab yang aktif
                if (isSelected) {
                    Spacer(modifier = Modifier.height(4.dp)) // Jarak antara teks dan indikator
                    Box(
                        modifier = Modifier
                            .height(2.dp)
                            .width(40.dp)
                            .background(Color.Blue)
                    )
                }
            }
        }
    }
}