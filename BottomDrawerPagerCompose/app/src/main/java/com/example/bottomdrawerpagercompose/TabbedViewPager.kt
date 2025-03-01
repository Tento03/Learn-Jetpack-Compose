package com.example.bottomdrawerpagercompose

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.PagerState
import com.google.accompanist.pager.rememberPagerState

@Composable
fun TabbedViewPager(){
    val tabbedList= listOf("Tab 1","Tab 2")
    val pagerState= rememberPagerState()
    var targetPage by remember{
        mutableStateOf(-1)
    }

    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(100.dp))

        ScrollableTabRow(
            tabList = tabbedList,
            pagerState = pagerState,
            onTabSelected = { index->
                targetPage=index
            }
        )

        HorizontalPager(
            count = tabbedList.size,
            modifier = Modifier.weight(1f),
            state = pagerState,
        ) { page->
            when (page){
                0-> Tab1()
                1-> Tab2()
            }
        }
    }

    if (targetPage!=-1){
        LaunchedEffect(targetPage) {
            pagerState.animateScrollToPage(targetPage)
            targetPage=-1
        }
    }
}

@Composable
fun ScrollableTabRow(
    tabList:List<String>,
    pagerState: PagerState,
    onTabSelected : (Int) -> Unit
) {
    val scrollState= rememberScrollState()

    Row(modifier = Modifier.fillMaxSize()
        .padding(8.dp)
        .horizontalScroll(scrollState),
        horizontalArrangement = Arrangement.SpaceAround,
        verticalAlignment = Alignment.CenterVertically
    ) {
        tabList.forEachIndexed { index, s ->
            var isSelected=pagerState.currentPage==index
            Column (modifier = Modifier.fillMaxSize()
                .padding(8.dp)
                .clickable {
                    onTabSelected(index)
                }
            ) {
                Text(s)
                if (isSelected){
                    Box(modifier = Modifier
                        .height(2.dp)
                        .width(40.dp)
                        .background(Color.Blue)
                    )
                }
            }
        }
    }
}


@Composable
fun Tab1() {

}

@Composable
fun Tab2() {

}

