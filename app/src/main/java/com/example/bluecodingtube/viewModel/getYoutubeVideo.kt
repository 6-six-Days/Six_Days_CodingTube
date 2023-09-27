package com.example.bluecodingtube.viewModel

import com.google.api.services.youtube.model.Video
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.coroutineScope

//fun getYoutubeVideo(keyword: String) =  viewModelScope.launch {
//
//    val result = searchRepository.getYoutubeVideo(keyword)
//
//    when (result.code()) {
//        200 -> {
//
//            val idList = mutableListOf<String>()
//            val convertedList = mutableListOf<Video>()
//
//            result.body()?.let {
//
//                nextPageToken = it.nextPageToken
//
//                it.items?.let { list ->
//                    for (item in list) {
//
//                        val id = item.id
//                        val snippet = item.snippet
//
//                        convertedList.add(
//                            Video(
//                                videoId = id.videoId,
//                                title = snippet.title,
//                                description = snippet.description,
//                                publishedAt = snippet.publishedAt,
//                                imgUrl = snippet.thumbnails.medium.url,
//                                channelTitle = snippet.channelTitle
//                            )
//                        )
//
//                        idList.add(id.videoId)
//                    }
//                }
//
//            }
//
//            coroutineScope {
//                (0 until idList.size).map { idx ->
//                    async(Dispatchers.IO) {
//                        val resultInfo = searchRepository.requestVideoInfo(idList[idx])
//
//                        when (resultInfo.code()) {
//                            200 -> {
//                                resultInfo.body()?.let {
//                                    convertedList.find { video ->
//                                        video.videoId == idList[idx]
//                                    }?.let { findVideo ->
//                                        it.items?.let { list ->
//                                            for (item in list) {
//                                                findVideo.duration =
//                                                    item.contentDetails.duration
//                                                findVideo.viewCount =
//                                                    item.statistics.viewCount.toString()
//                                            }
//                                        }
//                                    }
//                                }
//                            }
//                            else -> {
//                                Log.e(TAG, "onFailure ${resultInfo.message()}")
//                            }
//                        }
//                    }
//                }.awaitAll()
//
//                firstSearch.postValue(convertedList)
//            }
//
//        }
//        else -> {
//            Log.e(TAG, "onFailure ${result.message()}")
//        }
//    }
//
//}