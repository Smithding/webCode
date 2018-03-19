package com.itheima.cart;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public class Cart {
	
	// 购物项的集合
		private Map<String, CartItem> Map = new HashMap<String, CartItem>();
		// 总金额
		double total;
		
		public Collection<CartItem> getvlaues() {
			Collection<CartItem> values = Map.values();
			return values;
		}
		
		/**
		 * 加入购物车
		 * @param caritemsMap
		 */
		public void addToCart(CartItem caritemsMap){
		    //1.判断购物车是否有该商品
			String pid_2 = caritemsMap.getProduct().getPid(); 
			
			if(Map.containsKey(pid_2)) {
				//2.取出有的购物对象
				CartItem tem = Map.get(pid_2);
				//修改购买数量(原来的数量+item.getCount)
				tem.setCount(tem.getCount()+caritemsMap.getCount());
				total += caritemsMap.getSubtotal();	
			} else {
				//直接put进去 修改总金额(原来的金额+item.getSubtotal())
				Map.put(pid_2, caritemsMap);
				//修改总金额(原来的金额+item.getSubtotal())
				total += caritemsMap.getSubtotal();	
			}
			
			
		}
		
		/**
		 * 删除购物车
		 * @return
		 */public void removeFromCart(String pid) {
			   CartItem cartItem = Map.remove(pid);
			   total = total-cartItem.getSubtotal();
		 }
		 /**
		  * 清空购物车
		  */
		 public void clearCart() {
			 Map.clear();
			 total = 0.0;
		 }
		
		public Map<String, CartItem> getMap() {
			return Map;
		}
		public void setMap(Map<String, CartItem> itemsMap) {
			this.Map = itemsMap;
		}
		public double getTotal() {
			return total;
		}
		public void setTotal(double total) {
			this.total = total;
		}
		
		

}
