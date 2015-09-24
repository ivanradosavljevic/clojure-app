(ns project-clojure.controller
  (:use [json.example]
        [db.mongodb :as db])
  (:require [clojure.string :as str]
            [noir.session :as session]
            [noir.response :refer [redirect]])) 

(defn split-list [list]
  (str/split list #" "))

(defn join-for-api-string [list]
  (str/join "|" list))

(defn create-path [list]
  (do
    (let [splitted-list (split-list list)]
    (join-for-api-string splitted-list))))

(defn no-name [lat list lng radius]
  (do
    (let [ordered-list (create-path list)]
      (let [generated-url-data (get-url lat lng ordered-list radius)]
        (step-one-json generated-url-data)))))
;      (let [full-url (get-url latitude longitude ordered-list)]
;        (redirect full-url)))))

(defn get-json-data [latitude longitude list radius]
  (let [no-name-json (no-name latitude longitude list radius)]
  (get-in no-name-json [:results 0 :name])))


(defn init-data[]
 (db/init-users))

(defn register [username password repeated-password]
  (if (= password repeated-password)
    (if (db/registration username password)
      (do
        (session/put! :session-message "Welcome, now you may login!")
        (redirect "/"))
      (do
        (session/put! :session-message "Username already taken! Choose another one please.")
        (redirect "/register")))
    (do
      (session/put! :session-message "Password and repeted password are not the same!")
      (redirect "/register"))))

(defn login [username password]
  (let [user-doesnt-exist (db/authenticate username password)]
    (if user-doesnt-exist
      (do
        (session/put! :session-message "Username and password don't match. Try again!")        
        (redirect "/"))
      (do
        (session/put! :name username)       
        (redirect "index")))))

(defn favourites [name address poiid]
  (db/save-to-favourites name address poiid (session/get :name)))

(defn extract-map-field-from-nested-response [result]
  ((into {} result) :favourites))

(defn create-clostache-friendly-response [results]
  {:results (into [] results) })

(defn get-favourites []
  (create-clostache-friendly-response (extract-map-field-from-nested-response (db/read-favourites (session/get :name)))))

(defn logout []
  (do
    (session/remove! (session/get! :name))
    (redirect "/")))

 (defn remove-from-favourites [poiid]
   (do
    (db/delete-favourites poiid (session/get :name))
    (redirect "/favourites")))
