package com.example.myapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.models.Product;
import java.util.List;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHolder> {
    private List<Product> products;
    private OnProductClickListener listener;

    public interface OnProductClickListener {
        void onProductClick(Product product);
        void onFavoriteClick(Product product);
        void onAddToCartClick(Product product);
    }

    public ProductAdapter(List<Product> products, OnProductClickListener listener) {
        this.products = products;
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
            .inflate(R.layout.item_product, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product product = products.get(position);
        holder.bind(product, listener);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView productImage;
        TextView productName;
        TextView productDescription;
        TextView productPrice;
        ImageButton favoriteButton;
        ImageButton addToCartButton;

        ViewHolder(View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
            productName = itemView.findViewById(R.id.productName);
            productDescription = itemView.findViewById(R.id.productDescription);
            productPrice = itemView.findViewById(R.id.productPrice);
            favoriteButton = itemView.findViewById(R.id.favoriteButton);
            addToCartButton = itemView.findViewById(R.id.addToCartButton);
        }

        void bind(Product product, OnProductClickListener listener) {
            productName.setText(product.getName());
            productDescription.setText(product.getDescription());
            productPrice.setText(String.format("$%.2f", product.getPrice()));

            Glide.with(itemView.getContext())
                .load(product.getImageUrl())
                .centerCrop()
                .into(productImage);

            favoriteButton.setImageResource(product.isFavorite() ? 
                R.drawable.ic_favorite : R.drawable.ic_favorite_border);

            itemView.setOnClickListener(v -> listener.onProductClick(product));
            favoriteButton.setOnClickListener(v -> listener.onFavoriteClick(product));
            addToCartButton.setOnClickListener(v -> listener.onAddToCartClick(product));
        }
    }
} 