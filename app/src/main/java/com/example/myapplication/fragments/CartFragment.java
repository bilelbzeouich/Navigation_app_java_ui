package com.example.myapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.adapters.CartAdapter;
import com.example.myapplication.models.Product;
import java.util.ArrayList;
import java.util.List;

public class CartFragment extends Fragment implements CartAdapter.OnCartItemClickListener {
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_cart, container, false);

        RecyclerView cartItemsRecyclerView = view.findViewById(R.id.cartItemsRecyclerView);
        cartItemsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Sample cart items
        List<Product> cartItems = new ArrayList<>();
        cartItems.add(new Product("1", "Delicious Burger", "Classic beef burger", 9.99,
            "https://images.unsplash.com/photo-1571091718767-18b5b1457add"));
        cartItems.add(new Product("2", "Margherita Pizza", "Traditional Italian pizza", 12.99,
            "https://images.unsplash.com/photo-1513104890138-7c749659a591"));

        CartAdapter adapter = new CartAdapter(cartItems, this);
        cartItemsRecyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onRemoveFromCart(Product product) {
        // Handle remove from cart
    }

    @Override
    public void onQuantityChanged(Product product, int quantity) {
        product.setQuantity(quantity);
        // Update UI, total price, etc.
    }
} 