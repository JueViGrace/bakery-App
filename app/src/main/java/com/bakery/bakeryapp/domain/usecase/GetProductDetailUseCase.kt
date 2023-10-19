package com.bakery.bakeryapp.domain.usecase

import com.bakery.bakeryapp.common.Resource
import com.bakery.bakeryapp.data.local.relations.ProductsWithCategories
import com.bakery.bakeryapp.data.repository.MainRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetProductDetailUseCase @Inject constructor(
    private val repository: MainRepository
) {
    operator fun invoke(productId: String): Flow<Resource<List<ProductsWithCategories>>> = flow {
        try {
            emit(Resource.Loading())
            repository.getProductDetail(productId).collect { list ->
                emit(Resource.Success(data = list))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.message ?: "An unexpected error occurred"))
        }
    }
}
