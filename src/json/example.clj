(ns json.example
  (:require [ring.util.response :refer [response]] 
            [clojure.data.json :as json]
            [clj-http.client :as client]
            [noir.response :refer [redirect]]
            [cheshire.core :refer :all]))

(def starting-string "https://maps.googleapis.com/maps/api/place/nearbysearch/json?")

(def ending-string "&key=AIzaSyB5C4zVRYarLunQERakUjAhoMCZArQuPxM")

(defn create-url [latitude longitude ordered-list radius]
  (str starting-string "&location=" latitude "," longitude "&types=" ordered-list "&radius=" radius ending-string))

(defn get-url [latitude longitude ordered-list radius]
  (do
    (let [url (create-url latitude longitude ordered-list radius)]
      (client/get url))))

(defn make-json [content]
  (generate-string content))

(defn decode-json [json-string]
  (parse-string json-string true))


;url)))
;      (redirect "http://www.google.com"))))

;(json/read-str (get ooo :body))


; 1) ono sto vraca client/get, cisto/raw

;(def prva (client/get "https://maps.googleapis.com/maps/api/place/nearbysearch/json?&location=44.802351,20.466344&types=lodging|food&radius=500&key=AIzaSyB5C4zVRYarLunQERakUjAhoMCZArQuPxM"))

; 2) vadjenje bodyja iz ooo

(defn step-five-clostache-results [filtered-results]
  {:results filtered-results})

(defn step-four-filter-results [all-json-data]
  (let [filtered-results (get all-json-data :results)]
    (step-five-clostache-results filtered-results))) 

(defn step-three-json [made-json]
  (let [all-json-data (decode-json made-json)]
    (step-four-filter-results all-json-data)))

(defn step-two-json [string-json]
  (let [made-json (make-json string-json)]
    (step-three-json made-json)))

(defn step-one-json [raw-json]    
  (let [string-json (json/read-str (get raw-json :body))]
    (step-two-json string-json)))



; vadjenje konkretnog fielda
;(get-in cetvrta [:results 0 :name])

; vadjenje resultsa

;(def peta (get cetvrta :results))

; clostache friendly response

;(def clresponse {:results peta})

