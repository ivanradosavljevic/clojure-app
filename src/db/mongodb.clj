(ns db.mongodb
(:require [monger.core :as mg]
          [monger.collection :as mc]
          [monger.conversion :as mconv]
          [monger.operators :refer :all])          
(:import [org.bson.types ObjectId]))

(def conn (mg/connect))

(def db (mg/get-db conn "googlepoi-db"))
;returns lazy sequence of maps
(defn get-all [table]
  (mc/find-maps db table))

(defn insert-admin []
  (mc/insert db "users" {:id (inc 1) :username "admin" :password "admin" }))

(defn get-all [table]
  (mc/find-maps db table))
(defn init-users[]
  conn
  (do
    (if (empty? (get-all "users"))
      (insert-admin)
      false)))

(defn test-insert [latitude longitude]
  (mc/insert db "test" {:lat latitude :lng longitude}))

(defn registration [username password]
  (if (empty? (mc/find-maps db "users" {:username username}))
    (do
      (mc/insert db "users" {:id (inc 1) :username username :password password})
      true)
    false))

(defn authenticate [username password]
  (let [existing-username (mc/find-maps db "users" {:username username}) existing-password (mc/find-maps db "users" {:password password})]    
    (or (empty? existing-username) (empty? existing-password))))

(defn save-to-favourites [name address poiid username]
    (mc/update db "users" {:username username} {$addToSet {:favourites {:name name :address address :poiid poiid}}}))

(defn read-favourites [username]
    (mc/find-maps db "users" {:username username} ["favourites"]))

(defn delete-favourites [poiid username]
  (mc/update db "users" {:username username} {$pull {:favourites {:poiid poiid}}}))
  