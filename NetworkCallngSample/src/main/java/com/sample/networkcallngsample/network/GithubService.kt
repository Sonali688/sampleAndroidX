package com.sample.networkcallngsample.network

import com.sample.networkcallngsample.data.RepoResult
import retrofit2.Call
import retrofit2.http.GET

interface GithubService {
    @GET("/repositories")
    suspend fun retrieveRepositories(): RepoResult

    //sample search
    @GET("/search/repositories?q=language:kotlin&sort=stars&order=desc&per_page=50")
    suspend fun searchRepositories(): RepoResult
}