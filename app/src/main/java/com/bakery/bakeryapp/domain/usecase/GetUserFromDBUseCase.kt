package com.bakery.bakeryapp.domain.usecase

import com.bakery.bakeryapp.data.repository.MainRepository
import javax.inject.Inject

class GetUserFromDBUseCase @Inject constructor(
    private val repository: MainRepository
) {
    suspend operator fun invoke() {
    }
}
