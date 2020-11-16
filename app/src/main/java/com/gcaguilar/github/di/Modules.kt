package com.gcaguilar.github.di

import com.gcaguilar.github.common.JobExecutor
import com.gcaguilar.github.common.PostExecutionThread
import com.gcaguilar.github.common.ThreadExecutor
import com.gcaguilar.github.common.UiThread
import com.gcaguilar.github.data.GithubService
import com.gcaguilar.github.data.RemoteDataSourceImpl
import com.gcaguilar.github.data.mapper.OwnerMapper
import com.gcaguilar.github.data.mapper.RepoMapper
import com.gcaguilar.github.domain.GetPublicReposByOrg
import com.gcaguilar.github.domain.RemoteDataSource
import com.gcaguilar.github.presentation.MainViewModel
import com.gcaguilar.github.presentation.OwnerMvpMapper
import com.gcaguilar.github.presentation.RepoMvpMapper
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

private const val BASE_URL = "https://api.github.com/"

val networkModule = module {
    single<GithubService> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(GithubService::class.java)
    }

    single { NetworkConnection(context = androidContext()) }
}

val databaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), GithubDataBase::class.java, "github-db").build()
    }
    single { get<GithubDataBase>().repsotioryDao() }
}

val dataModule = module {
    factory { OwnerMapper() }
    factory { RepoMapper(ownerMapper = get()) }
}

val domainModule = module {
    single<PostExecutionThread> { UiThread() }
    single<ThreadExecutor> { JobExecutor() }
    single<RemoteDataSource> { RemoteDataSourceImpl(get(), get()) }

    factory {
        GetPublicReposByOrg(
            remoteDataSource = get(),
            threadExecutor = get(),
            postExecutionThread = get()
        )
    }
}

val mainModule = module {
    factory { OwnerMvpMapper() }
    factory { RepoMvpMapper(ownerMvpMapper = get()) }

    viewModel {
        MainViewModel(
            repoMapper = get(),
            getPublicReposByOrg = get()
        )
    }
}