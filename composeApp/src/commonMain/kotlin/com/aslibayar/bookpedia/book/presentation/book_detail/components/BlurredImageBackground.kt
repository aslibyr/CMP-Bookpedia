package com.aslibayar.bookpedia.book.presentation.book_detail.components

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import cmp_bookpedia.composeapp.generated.resources.Res
import cmp_bookpedia.composeapp.generated.resources.back
import cmp_bookpedia.composeapp.generated.resources.book_cover
import cmp_bookpedia.composeapp.generated.resources.book_error_2
import cmp_bookpedia.composeapp.generated.resources.mark_as_fav
import coil3.compose.rememberAsyncImagePainter
import com.aslibayar.bookpedia.core.presentation.DesertWhite
import com.aslibayar.bookpedia.core.presentation.Lilac
import com.aslibayar.bookpedia.core.presentation.PulseAnimation
import com.aslibayar.bookpedia.core.presentation.Purple
import org.jetbrains.compose.resources.painterResource
import org.jetbrains.compose.resources.stringResource

@Composable
fun BlurredImageBackground(
    imageUrl: String?,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    var imageLoadResult by remember {
        mutableStateOf<Result<Painter>?>(null)
    }
    val painter = rememberAsyncImagePainter(
        model = imageUrl,
        onSuccess = {
            val size = it.painter.intrinsicSize
            imageLoadResult = if (size.width > 1 && size.height > 1) {
                Result.success(it.painter)
            } else {
                Result.failure(Exception("Invalid image dimensions"))
            }
        },
    )
    Box(modifier = modifier) {
        Column(modifier = modifier.fillMaxSize()) {
            Box(
                modifier = Modifier.weight(0.3f)
                    .fillMaxWidth()
                    .background(
                        Brush.linearGradient(
                            colors = listOf(
                                Purple,
                                Lilac
                            ),
                        )
                    )
            ) {
                imageLoadResult?.getOrNull()
                    ?.let { painter ->
                        Image(
                            painter = painter,
                            contentDescription = stringResource(Res.string.book_cover),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.fillMaxSize()
                                .blur(20.dp)

                        )
                    }
            }
            Box(
                modifier = Modifier.weight(0.7f)
                    .fillMaxWidth()
                    .background(DesertWhite)
            )
        }
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            IconButton(
                onClick = onBackClick, modifier = Modifier.padding(top = 16.dp, start = 16.dp)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                    contentDescription = stringResource(Res.string.back),
                    tint = DesertWhite
                )
            }
            IconButton(
                onClick = onFavoriteClick, modifier = Modifier.padding(top = 16.dp, end = 16.dp)
            ) {
                Icon(
                    imageVector = if (isFavorite) {
                        Icons.Default.Favorite
                    } else {
                        Icons.Default.FavoriteBorder
                    },
                    contentDescription = stringResource(Res.string.mark_as_fav),
                    tint = if (isFavorite) Color.Red else DesertWhite
                )
            }
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxWidth()
        ) {
            Spacer(modifier = Modifier.fillMaxHeight(0.15f))
            ElevatedCard(
                modifier = Modifier.height(270.dp)
                    .aspectRatio(2 / 3f),
                shape = RoundedCornerShape(8.dp),
                colors = CardDefaults.elevatedCardColors(containerColor = Color.White),
                elevation = CardDefaults.elevatedCardElevation(defaultElevation = 15.dp)
            ) {
                AnimatedContent(targetState = imageLoadResult) { result ->
                    when (result) {
                        null -> Box(
                            modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center
                        ) {
                            PulseAnimation(
                                modifier = Modifier.size(60.dp)
                            )
                        }

                        else -> {
                            Image(
                                painter = if (result.isSuccess) painter else {
                                    painterResource(Res.drawable.book_error_2)
                                },
                                contentDescription = stringResource(Res.string.book_cover),
                                modifier = Modifier.fillMaxSize()
                                    .background(Color.Transparent),
                                contentScale = if (result.isSuccess) {
                                    ContentScale.Crop
                                } else {
                                    ContentScale.Fit
                                },
                            )
                        }
                    }
                }
            }
            content()
        }
    }
}
