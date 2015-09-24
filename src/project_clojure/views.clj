(ns project-clojure.views
  (:use clostache.parser
        [hiccup core page])
  (:require [clojure.java.io]
            [noir.session :as session]))

(defn default-partials 
  []
  {:navbar-menu (render-resource "templates/navbar-menu.html") 
   :header (render-resource "templates/header.html")
   :footer (render-resource "templates/footer.html")})

(defn index-page []
  (if (empty? (session/get :name))    
    (render-resource "templates/login.html")
    (render-resource "templates/index.html" {:naslov "Welcome to findnearpoi" :opis "Select your poi and let us find the closest one!"})))

(defn login-page []
  (render-resource "templates/login.html" {:error (session/get :session-message)}))

(defn register-page []
  (render-resource "templates/register.html" {:error (session/get :session-message)}))

(defn results-page [res]
  (render-resource "templates/res.html" res))

(defn favourites-page [results]
  (render-resource "templates/favourites.html" results))
