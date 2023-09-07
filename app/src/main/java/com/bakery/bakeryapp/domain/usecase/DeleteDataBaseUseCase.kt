package com.bakery.bakeryapp.domain.usecase

import com.bakery.bakeryapp.data.repository.MainRepository
import javax.inject.Inject

class DeleteDataBaseUseCase @Inject constructor(
    private val repository: MainRepository
) {
    suspend operator fun invoke() {
        repository.deleteUser()
        repository.deleteCategories()
        repository.deleteProducts()
        repository.deleteCart()
        repository.deletePedido()
    }
}
