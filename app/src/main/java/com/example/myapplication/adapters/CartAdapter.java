package com.example.myapplication.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.example.myapplication.R;
import com.example.myapplication.models.Product;
import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartViewHolder> {
    private List<Product> cartItems;
    private OnCartItemClickListener listener;

    public interface OnCartItemClickListener {
        void onRemoveFromCart(Product product);
        void onQuantityChanged(Product product, int quantity);
    }

    public CartAdapter(List<Product> cartItems, OnCartItemClickListener listener) {
        this.cartItems = cartItems;
        this.listener = listener;
    }

    @NonNull
    @Override
    public CartViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_cart, parent, false);
        return new CartViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartViewHolder holder, int position) {
        Product product = cartItems.get(position);
        holder.bind(product);
    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    class CartViewHolder extends RecyclerView.ViewHolder {
        private ImageView productImage;
        private TextView productName;
        private TextView productPrice;
        private TextView quantityText;
        private View removeButton;
        private View decreaseButton;
        private View increaseButton;

        CartViewHolder(@NonNull View itemView) {
            super(itemView);
            productImage = itemView.findViewById(R.id.productImage);
            productName = itemView.findViewById(R.id.productName);
            productPrice = itemView.findViewById(R.id.productPrice);
            quantityText = itemView.findViewById(R.id.quantityText);
            removeButton = itemView.findViewById(R.id.removeButton);
            decreaseButton = itemView.findViewById(R.id.decreaseButton);
            increaseButton = itemView.findViewById(R.id.increaseButton);
        }

        void bind(Product product) {
            productName.setText(product.getName());
            productPrice.setText(String.format("$%.2f", product.getPrice()));
            quantityText.setText(String.valueOf(product.getQuantity()));

            Glide.with(itemView.getContext())
                    .load(product.getImageUrl())
                    .centerCrop()
                    .into(productImage);

            removeButton.setOnClickListener(v -> listener.onRemoveFromCart(product));
            
            decreaseButton.setOnClickListener(v -> {
                int newQuantity = Math.max(1, product.getQuantity() - 1);
                listener.onQuantityChanged(product, newQuantity);
            });
            
            increaseButton.setOnClickListener(v -> {
                int newQuantity = product.getQuantity() + 1;
                listener.onQuantityChanged(product, newQuantity);
            });
        }
    }
} 