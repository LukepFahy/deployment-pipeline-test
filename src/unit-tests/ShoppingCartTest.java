import static org.junit.Assert.*;
import org.junit.Test;

public class ShoppingCartTest {

	@Test
	public void testAddToShoppingCart() {
		ShoppingCart cart = new ShoppingCart();
		ProductModel product = new ProductModel("123", "Test Product", 50.0);
		ShoppingCartModel expectedCart = new ShoppingCartModel();
		expectedCart.productList.add(product);
		expectedCart.cartTotal = 50.0;
		ShoppingCartModel actualCart = cart.addToShoppingCart(product);
		assertEquals(expectedCart.productList, actualCart.productList);
		assertEquals(expectedCart.cartTotal, actualCart.cartTotal, 0.0);
	}
	
	@Test
	public void testRemoveFromShoppingCart() {
		ShoppingCart cart = new ShoppingCart();
		ProductModel product1 = new ProductModel("123", "Test Product 1", 50.0);
		ProductModel product2 = new ProductModel("456", "Test Product 2", 25.0);
		cart.addToShoppingCart(product1);
		cart.addToShoppingCart(product2);
		ShoppingCartModel expectedCart = new ShoppingCartModel();
		expectedCart.productList.add(product2);
		expectedCart.cartTotal = 25.0;
		ShoppingCartModel actualCart = cart.removeFromShoppingCart(product1);
		assertEquals(expectedCart.productList, actualCart.productList);
		assertEquals(expectedCart.cartTotal, actualCart.cartTotal, 0.0);
	}

}
