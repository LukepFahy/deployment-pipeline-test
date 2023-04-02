import java.io.*;
public class ShoppingCart {
	
	ShoppingCartModel shoppingCart= new ShoppingCartModel();
	ProductModel productModel= new ProductModel();
	
	public ShoppingCartModel addToShoppingCart(ProductModel product) {
		List<ProductModel> listOfProductsCurrently= shoppingCart.productList;
		listOfProductsCurrently.add(product);
		shoppingCart.productList = listOfProductsCurrently;
		shoppingCart.cartTotal=shoppingCart.cartTotal+product.price;
		return shoppingCart;
	}
	
	public ShoppingCartModel removeFromShoppingCart(ProductModel product) {
		List<ProductModel> listOfProductsCurrently= shoppingCart.productList;
		listOfProductsCurrently.remove(product);
		shoppingCart.productList = listOfProductsCurrently;
		shoppingCart.cartTotal=shoppingCart.cartTotal-product.price;
		return shoppingCart;
	}

}
