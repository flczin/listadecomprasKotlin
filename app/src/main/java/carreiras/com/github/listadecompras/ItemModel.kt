package carreiras.com.github.listadecompras

data class ItemModel(val name: String, val onRemove: (ItemModel) -> Unit)
