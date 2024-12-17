package com.example.myapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.google.android.material.tabs.TabLayout;
import com.example.myapplication.adapters.ProductAdapter;
import com.example.myapplication.models.Product;
import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment implements ProductAdapter.OnProductClickListener {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        // Setup TabLayout
        TabLayout categoryTabs = view.findViewById(R.id.categoryTabs);
        categoryTabs.addTab(categoryTabs.newTab().setText("All"));
        categoryTabs.addTab(categoryTabs.newTab().setText("Foods"));
        categoryTabs.addTab(categoryTabs.newTab().setText("Drinks"));
        categoryTabs.addTab(categoryTabs.newTab().setText("Snacks"));
        categoryTabs.addTab(categoryTabs.newTab().setText("Desserts"));

        // Setup RecyclerViews
        RecyclerView featuredRecyclerView = view.findViewById(R.id.featuredRecyclerView);
        featuredRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        RecyclerView popularItemsRecyclerView = view.findViewById(R.id.popularItemsRecyclerView);
        popularItemsRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        // Setup sample data
        List<Product> sampleProducts = new ArrayList<>();
        sampleProducts.add(new Product("1", "Delicious Burger", "Classic beef burger with fresh vegetables", 9.99,
            "https://images.unsplash.com/photo-1571091718767-18b5b1457add"));
        sampleProducts.add(new Product("2", "Margherita Pizza", "Traditional Italian pizza with tomatoes and mozzarella", 12.99,
            "https://images.unsplash.com/photo-1513104890138-7c749659a591"));
        sampleProducts.add(new Product("3", "Fresh Salad", "Mixed greens with vinaigrette", 7.99,
            "https://images.unsplash.com/photo-1512621776951-a57141f2eefd"));
        sampleProducts.add(new Product("4", "Sushi Roll", "Fresh salmon and avocado roll", 14.99,
            "https://images.unsplash.com/photo-1579871494447-9811cf80d66c"));
        sampleProducts.add(new Product("5", "Pasta Carbonara", "Creamy pasta with bacon", 11.99,
            "https://images.unsplash.com/photo-1612874742237-6526221588e3"));
        sampleProducts.add(new Product("6", "Ice Cream", "Vanilla with chocolate sauce", 5.99,
            "https://images.unsplash.com/photo-1563805042-7684c019e1cb"));
        // Add more products...

        ProductAdapter adapter = new ProductAdapter(sampleProducts, this);
        featuredRecyclerView.setAdapter(adapter);
        popularItemsRecyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onProductClick(Product product) {
        // Handle product click
        // You can navigate to product details here
    }

    @Override
    public void onFavoriteClick(Product product) {
        product.setFavorite(!product.isFavorite());
        // Update the UI
    }

    @Override
    public void onAddToCartClick(Product product) {
        // Add to cart logic
    }
} 