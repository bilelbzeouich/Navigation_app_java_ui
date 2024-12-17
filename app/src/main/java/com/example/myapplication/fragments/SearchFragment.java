package com.example.myapplication.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.myapplication.R;
import com.example.myapplication.adapters.ProductAdapter;
import com.example.myapplication.models.Product;
import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment implements ProductAdapter.OnProductClickListener {
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        RecyclerView searchResultsRecyclerView = view.findViewById(R.id.searchResultsRecyclerView);
        searchResultsRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        // Sample search results
        List<Product> searchResults = new ArrayList<>();
        searchResults.add(new Product("1", "Delicious Burger", "Classic beef burger", 9.99,
            "https://images.unsplash.com/photo-1571091718767-18b5b1457add"));
        searchResults.add(new Product("2", "Margherita Pizza", "Traditional Italian pizza", 12.99,
            "https://images.unsplash.com/photo-1513104890138-7c749659a591"));
        searchResults.add(new Product("3", "Fresh Salad", "Mixed greens with vinaigrette", 7.99,
            "https://images.unsplash.com/photo-1512621776951-a57141f2eefd"));

        ProductAdapter adapter = new ProductAdapter(searchResults, this);
        searchResultsRecyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onProductClick(Product product) {
        // Handle product click
    }

    @Override
    public void onFavoriteClick(Product product) {
        product.setFavorite(!product.isFavorite());
        // Update UI
    }

    @Override
    public void onAddToCartClick(Product product) {
        // Add to cart logic
    }
} 