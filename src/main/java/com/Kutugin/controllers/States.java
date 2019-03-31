package com.Kutugin.controllers;

import com.Kutugin.domain.Client;
import com.Kutugin.domain.Order;
import com.Kutugin.domain.Product;

public class States {
    private static boolean signIn = false;
    private static Client adminCurrentClient = null;
    private static Order currentOrder = null;
    private static Client currentClient = null;

    public static boolean isSignIn() {
        return signIn;
    }

    public static void setSignIn(boolean signIn) {
        States.signIn = signIn;
    }

    public static Client getAdminCurrentClient() {
        return adminCurrentClient;
    }

    public static void setAdminCurrentClient(Client adminCurrentClient) {
        States.adminCurrentClient = adminCurrentClient;
    }

    public static Order getCurrentOrder() {
        return currentOrder;
    }

    public static void setCurrentOrder(Order currentOrder) {
        States.currentOrder = currentOrder;
    }

    public static Client getCurrentClient() {
        return currentClient;
    }

    public static void setCurrentClient(Client currentClient) {
        States.currentClient = currentClient;
    }

    public static Product getCurrentProduct() {
        return currentProduct;
    }

    public static void setCurrentProduct(Product currentProduct) {
        States.currentProduct = currentProduct;
    }

    private static Product currentProduct = null;
}
